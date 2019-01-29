import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class BeatBox{
    private JFrame theFrame;
    private JLabel state;

    private ArrayList<JCheckBox> checkBoxArrayList;

    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    private JTextArea messageText;
    private JTextArea sendText;

    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
        "Open Hi-Hat","Acoustic snare","Crash Cymbal","Hand Clap",
        "High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
        "Cowball","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
    private int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    private Boolean isLoginIn = false;
    private ChatRoomClient client;

    public static void main(String[] args){
        new BeatBox().bulidGUI();
    }

    private void bulidGUI(){
        checkBoxArrayList = new ArrayList<>();
        //============================================================================
        JPanel mainPanel;
        JPanel background;

        Box nameBox;

        Box eastBox;
        Box buttonBox;
        Box clientBox;
        //============================================================================
        //----------------------------------------------------------------------------
        eastBox = new Box(BoxLayout.X_AXIS);

        buttonBox = new Box(BoxLayout.Y_AXIS);
        buttonBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton start = new JButton("¿ªÊ¼²¥·Å");
        start.addActionListener(new StartListener());
        buttonBox.add(start);

        state = new JLabel("...");
        buttonBox.add(state);

        JButton stop = new JButton("Í£Ö¹²¥·Å");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton upTempo = new JButton("½Ú×à¼Ó¿ì");
        upTempo.addActionListener(new UpTempoListener());
        buttonBox.add(upTempo);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton downTempo = new JButton("½Ú×à¼õÂý");
        downTempo.addActionListener(new DownTempoListener());
        buttonBox.add(downTempo);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton serializelt = new JButton("±£´æÀÖÆ×");
        serializelt.addActionListener(new SerializeltListener());
        buttonBox.add(serializelt);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton restore = new JButton("´ò¿ªÎÄ¼þ");
        restore.addActionListener(new RestoreListener());
        buttonBox.add(restore);
        buttonBox.add(Box.createVerticalStrut(10));
        //==============================================================================
        clientBox = new Box(BoxLayout.Y_AXIS);

        clientBox.add(Box.createHorizontalStrut(100));
        JLabel clientTitle = new JLabel("Chat Room");
        clientBox.add(clientTitle);
        clientBox.add(Box.createVerticalStrut(10));

        messageText = new JTextArea(20,40);
        JScrollPane messageScroll = new JScrollPane(messageText);
        messageText.setLineWrap(true);
        messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        clientBox.add(messageScroll);
        clientBox.add(Box.createVerticalStrut(5));

        sendText = new JTextArea(2,40);
        JScrollPane sendScroll = new JScrollPane(sendText);
        sendText.setLineWrap(true);
        sendScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sendScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        clientBox.add(sendScroll);
        clientBox.add(Box.createVerticalStrut(3));

        clientBox.add(Box.createHorizontalStrut(100));
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendListener());
        clientBox.add(sendButton);
        clientBox.setBackground(Color.GRAY);


        eastBox.add(buttonBox);
        eastBox.add(clientBox);

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
        background.add(BorderLayout.EAST,eastBox);
        background.add(BorderLayout.CENTER,mainPanel);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//ÉèÖÃ±ßÔµ



        setUpMidi();

        try{
            int[] eventIWant = {127};
            sequencer.addControllerEventListener(new ControllerListener(),eventIWant);
        }catch (Exception ex){ex.printStackTrace();}

        theFrame = new JFrame("Beat Box");//¿ò¼Ü
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//¹Ø±Õ°´Å¥¹Ø±Õ¿ò¼Ü
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
    private void buildTrackAndStart(){
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();
        //track.add(makeEvent(176,1,126,0,1));
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

        }
        track.add(makeEvent(176,1,127,0,16));
        try {
            sequencer.setSequence(sequence);
            sequencer.start();
        }catch (Exception ex){
            System.out.println("Can`t play sequencer");
            ex.printStackTrace();
        }
        state.setText("Playing...");
        state.setForeground(Color.GREEN);
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
    private void save(){
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
    private void readFile(){
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
    }
    private void showLoginInGui(){
        JFrame loginInFrame = new JFrame("µÇÂ½");
        JPanel loginInPanel = new JPanel();
        loginInFrame.getContentPane().add(loginInPanel);
        loginInPanel.setLayout(new BorderLayout());

        JLabel loginInTitle = new JLabel("ÇëÊäÈëÕÊºÅºÍÃÜÂë£º");

        Box loginInContentBox = new Box(BoxLayout.Y_AXIS);
        loginInContentBox.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        JPanel loginInNamePanel  = new JPanel();
        JLabel loginInNameLabel = new JLabel("ÕÊºÅ£º");
        JTextArea loginInNameArea = new JTextArea(1,20);
        loginInNameArea.setLineWrap(true);
        JScrollPane loginInNameScroll = new JScrollPane(loginInNameArea);
        loginInNameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        loginInNameScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        loginInNamePanel.add(loginInNameLabel);
        loginInNamePanel.add(loginInNameScroll);

        JPanel loginInPasswordPanel  = new JPanel();
        JLabel loginInPasswordLabel = new JLabel("ÕÊºÅ£º");
        JTextArea loginInPasswordArea = new JTextArea(1,20);
        loginInPasswordArea.setLineWrap(true);
        JScrollPane loginInPasswordScroll = new JScrollPane(loginInPasswordArea);
        loginInPasswordScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        loginInPasswordScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        loginInPasswordPanel.add(loginInPasswordLabel);
        loginInPasswordPanel.add(loginInPasswordScroll);

        loginInContentBox.add(loginInNamePanel);
        loginInContentBox.add(loginInPasswordPanel);

        Box loginInButtonBox = new Box(BoxLayout.X_AXIS);
        JButton loginInButton = new JButton("µÇÂ½");
        loginInButton.addActionListener(new LoginInListener());
        loginInButtonBox.add(Box.createHorizontalStrut(110));
        loginInButtonBox.add(loginInButton);

        loginInPanel.add(BorderLayout.NORTH,loginInTitle);
        loginInPanel.add(BorderLayout.CENTER,loginInContentBox);
        loginInPanel.add(BorderLayout.SOUTH,loginInButtonBox);

        loginInFrame.setSize(300,200);
        int loginInFrameX = theFrame.getX() + (theFrame.getWidth() - loginInFrame.getWidth()) / 2;
        int loginInFrameY = theFrame.getY() + (theFrame.getHeight() - loginInFrame.getHeight()) / 2;
        loginInFrame.setLocation(loginInFrameX,loginInFrameY);
        loginInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginInFrame.setVisible(true);

    }
    class LoginInListener implements ActionListener{
        public void actionPerformed(ActionEvent event){}
    }
    class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(sequencer.isRunning()){
                sequencer.stop();
            }
            setUpMidi();
            buildTrackAndStart();
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
        public void actionPerformed(ActionEvent event){ save(); }
    }
    class RestoreListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            readFile();
            sequencer.stop();
            buildTrackAndStart();
        }
    }
    class ControllerListener implements ControllerEventListener{
        public void controlChange(ShortMessage event){
            System.out.println("lalalalal");
            if (event.getData1() == 126){
                state.setText("Playing...");
                state.setForeground(Color.GREEN);
            }
            if (event.getData1() == 127){
                state.setText("STOP!");
                state.setForeground(Color.RED);
            }
        }
    }
    class SendListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(isLoginIn){
                client.message = sendText.getText();
                sendText.setText("");
            }else{
                showLoginInGui();
            }
        }
    }
}