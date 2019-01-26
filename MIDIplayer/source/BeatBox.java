import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class BeatBox{
    private JFrame theFrame;
    private JPanel mainPanel;
    private JPanel background;
    private Box buttonBox;
    private Box nameBox;
    private JLabel state;

    private ArrayList<JCheckBox> checkBoxArrayList;

    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;


    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
        "Open Hi-Hat","Acoustic snare","Crash Cymbal","Hand Clap",
        "High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
        "Cowball","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
    private int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args){
        new BeatBox().bulidGUI();
    }

    private void bulidGUI(){
        checkBoxArrayList = new ArrayList<>();
        //----------------------------------------------------------------------------

        buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("开始播放");
        start.addActionListener(new StartListener());
        buttonBox.add(start);

        state = new JLabel("...");
        buttonBox.add(state);

        JButton stop = new JButton("停止播放");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton upTempo = new JButton("节奏加快");
        upTempo.addActionListener(new UpTempoListener());
        buttonBox.add(upTempo);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton downTempo = new JButton("节奏减慢");
        downTempo.addActionListener(new DownTempoListener());
        buttonBox.add(downTempo);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton serializelt = new JButton("保存乐谱");
        serializelt.addActionListener(new SerializeltListener());
        buttonBox.add(serializelt);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton restore = new JButton("打开文件");
        restore.addActionListener(new RestoreListener());
        buttonBox.add(restore);
        buttonBox.add(Box.createVerticalStrut(10));



        //------------------------------------------------------------------------------

        nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i=0;i<16;i++){
            nameBox.add(new Label(instrumentNames[i]));
        }

        //----------------------------------------------------------------------------------

        GridLayout gridLayout = new GridLayout(16,16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);
        mainPanel = new JPanel(gridLayout);
        for(int i=0;i<256;i++){
            JCheckBox a = new JCheckBox();
            a.setSelected(false);
            checkBoxArrayList.add(a);
            mainPanel.add(a);
        }

        //--------------------------------------------------------------------------------------

        background = new JPanel(new BorderLayout());
        background.add(BorderLayout.WEST,nameBox);
        background.add(BorderLayout.EAST,buttonBox);
        background.add(BorderLayout.CENTER,mainPanel);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//设置边缘


        setUpMidi();

        theFrame = new JFrame("Beat Box");//框架
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮关闭框架
        theFrame.setResizable(false);
        theFrame.getContentPane().add(background);
        theFrame.setBounds(100,100,400,300);
        theFrame.pack();
        theFrame.setVisible(true);
    }
    private void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }catch (Exception ex){ex.printStackTrace();}
    }
    private void bulidTrackAndStart(){
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for(int i=0;i<16;i++){
            trackList = new int[16];
            int key = instruments[i];

            for(int j=0;j<16;j++){
                JCheckBox jCheckBox = checkBoxArrayList.get(j + (16 * i));
                if(jCheckBox.isSelected()){
                    trackList[j] = key;
                }else{
                    trackList[j] = 0;
                }
            }
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }
        try {
            sequencer.setSequence(sequence);
            sequencer.start();
        }catch (Exception ex){
            System.out.println("Can`t play sequencer");
            ex.printStackTrace();
        }
    }
    private void makeTracks(int[] list){
        for(int i=0;i<16;i++){
            int key = list[i];
            if(key != 0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }
    private MidiEvent makeEvent(int comd,int chanel,int data1,int data2,int tick){
        MidiEvent result = null;
        try{
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(comd,chanel,data1,data2);
            result = new MidiEvent(shortMessage,tick);
        }catch (Exception ex){ex.printStackTrace();}
        return result;
    }
    class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(sequencer.isRunning()){
                sequencer.stop();
            }
            setUpMidi();
            bulidTrackAndStart();
            state.setText("Playing...");
            state.setForeground(Color.GREEN);
        }
    }
    class StopListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            sequencer.stop();
            state.setText("STOP!");
            state.setForeground(Color.RED);

        }
    }
    class UpTempoListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
    }
    class DownTempoListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 0.97));
        }
    }
    class SerializeltListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            Boolean[] checkboxStateList = new Boolean[256];
            for(int i=0;i<256;i++){
                checkboxStateList[i] = checkBoxArrayList.get(i).isSelected();
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(theFrame);
            try {
                ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                objectOutput.writeObject(checkboxStateList);
                objectOutput.close();
            }catch (Exception ex){
                System.out.println("Can`t save checkboxArrayList");
                ex.printStackTrace();
            }


        }
    }
    class RestoreListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("ser","ser"));
            fileChooser.showOpenDialog(theFrame);

            try {
                ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
                Boolean[] checkboxStateList =(Boolean[]) objectInput.readObject();
                objectInput.close();
                for(int i=0;i<256;i++){
                    if(checkboxStateList[i]){
                        checkBoxArrayList.get(i).setSelected(true);
                    }else{
                        checkBoxArrayList.get(i).setSelected(false);
                    }
                }
            }catch (Exception ex){
                System.out.println("can`t read ser");
                ex.printStackTrace();
            }

            sequencer.stop();
            bulidTrackAndStart();
        }

    }
}