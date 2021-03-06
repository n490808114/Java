package Client;

import Support.Message;
import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class BeatBox{
    //------for GUI--------------------------------------------------------------------
    private JFrame theFrame;
    private JLabel state;

    private ArrayList<JCheckBox> checkBoxArrayList;

    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    private JTextArea messageText;
    private JTextArea sendText;
    private JTextArea loginInNameArea;
    private JTextArea loginInPasswordArea;
    private JLabel userNameMainPage;

    JFrame loginInFrame;
    //----------------------------------------------------------------------------------
    //------------for MIDIPlayer--------------------------------------------------------
    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
        "Open Hi-Hat","Acoustic snare","Crash Cymbal","Hand Clap",
        "High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
        "Cowball","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
    private int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    //-----------------------------------------------------------------------------------
    //----------for client---------------------------------------------------------------
    private Client client;
    private String useName;
    private String password;
    //-----------------------------------------------------------------------------------

    public static void main(String[] args){
        System.out.println("1");
        Client client = new Client();
        System.out.println("2");
        BeatBox beatBox = new BeatBox(client);
        System.out.println("3");
        Thread clientThread = new Thread(client);
        System.out.println("4");
        clientThread.start();
        System.out.println("5");
        beatBox.bulidGUI();
        System.out.println("6");
        while (true){
            System.out.println("Box : running");
            Message[] showMessages = client.getMessage();
            if(showMessages != null){
                for (int i=0;i<showMessages.length;i++){
                    beatBox.messageText.append(showMessages[i].toString());
                }
            }
            try{
                Thread.sleep(1000);
            }catch (Exception ex){ex.printStackTrace();}
        }
    }

    private BeatBox(Client client){
        this.client = client;
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
        JButton start = new JButton("START");
        start.addActionListener(new StartListener());
        buttonBox.add(start);

        state = new JLabel("...");
        buttonBox.add(state);

        JButton stop = new JButton("STOP");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton upTempo = new JButton("Tempo UP");
        upTempo.addActionListener(new UpTempoListener());
        buttonBox.add(upTempo);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new DownTempoListener());
        buttonBox.add(downTempo);
        buttonBox.add(Box.createVerticalStrut(10));

        JButton serializelt = new JButton("Serializelt");
        serializelt.addActionListener(new SerializeltListener());
        buttonBox.add(serializelt);
        buttonBox.add(Box.createVerticalStrut(3));

        JButton restore = new JButton("Restore");
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

        JPanel titlePanel = new JPanel();
        JLabel mainTitle = new JLabel("User Name:");
        userNameMainPage = new JLabel(useName);
        userNameMainPage.setForeground(Color.RED);
        titlePanel.add(mainTitle);
        titlePanel.add(userNameMainPage);

        //--------------------------------------------------------------------------------------

        background = new JPanel(new BorderLayout());
        background.add(BorderLayout.NORTH,titlePanel);
        background.add(BorderLayout.WEST,nameBox);
        background.add(BorderLayout.EAST,eastBox);
        background.add(BorderLayout.CENTER,mainPanel);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//??????
        titlePanel.setBackground(new Color(22, 122, 89, 126));

        setUpMidi();

        try{
            int[] eventIWant = {127};
            sequencer.addControllerEventListener(new ControllerListener(),eventIWant);
        }catch (Exception ex){ex.printStackTrace();}

        theFrame = new JFrame("Beat Box");//???
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//??????????
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
        loginInFrame = new JFrame("Login In");
        JPanel loginInPanel = new JPanel();
        loginInFrame.getContentPane().add(loginInPanel);
        loginInPanel.setLayout(new BorderLayout());

        JLabel loginInTitle = new JLabel("Please Input UserName and Password!");
        loginInTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel loginInContent = new JPanel();
        loginInContent.setLayout(new GridLayout(2,2));
        loginInContent.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));

        JLabel loginInNameLabel = new JLabel("Name:");
        loginInNameArea = new JTextArea(1,20);
        loginInNameArea.setLineWrap(true);
        JScrollPane loginInNameScroll = new JScrollPane(loginInNameArea);
        loginInNameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        loginInNameScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        JLabel loginInPasswordLabel = new JLabel("Password:");
        loginInPasswordArea = new JTextArea(1,20);
        loginInPasswordArea.setLineWrap(true);
        JScrollPane loginInPasswordScroll = new JScrollPane(loginInPasswordArea);
        loginInPasswordScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        loginInPasswordScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        loginInContent.add(loginInNameLabel);
        loginInContent.add(loginInNameScroll);
        loginInContent.add(loginInPasswordLabel);
        loginInContent.add(loginInPasswordScroll);

        JPanel loginInButtonPanel = new JPanel();
        JButton loginInButton = new JButton("Login In");
        loginInButton.addActionListener(new LoginInListener());
        loginInButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginInButtonPanel.add(loginInButton);


        loginInPanel.add(BorderLayout.NORTH,loginInTitle);
        loginInPanel.add(BorderLayout.CENTER,loginInContent);
        loginInPanel.add(BorderLayout.SOUTH,loginInButtonPanel);

        loginInFrame.setSize(400,200);
        int loginInFrameX = theFrame.getX() + (theFrame.getWidth() - loginInFrame.getWidth()) / 2;
        int loginInFrameY = theFrame.getY() + (theFrame.getHeight() - loginInFrame.getHeight()) / 2;
        loginInFrame.setLocation(loginInFrameX,loginInFrameY);
        loginInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginInFrame.setVisible(true);

    }
    class LoginInListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            useName = loginInNameArea.getText();
            password = loginInPasswordArea.getText();
            client.send(new Message(useName,password,"LOGIN_IN"));
            loginInFrame.dispose();
            userNameMainPage.setText(useName);
        }
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
            if(useName == null){
                showLoginInGui();
            }else{
                client.send(new Message(useName,password,sendText.getText()));
                sendText.setText("");

            }
        }
    }
}
