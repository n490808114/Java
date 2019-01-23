import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class QuizCardBuilder{
    private JFrame frame = new JFrame("QuizCardBuilder");
    private JPanel panel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JLabel quizLabel = new JLabel("Quiz:");
    private JTextArea quizArea = new JTextArea(10,45);
    private JLabel answerLabel = new JLabel("Answer:");
    private JTextArea answerArea = new JTextArea(10,45);
    private JButton submit = new JButton("提交");

    private JFrame saveMenuItemFrame = new JFrame();
    private JLabel saveMenuItemLabel = new JLabel("文件名");
    private JTextField saveMenuItemTextField = new JTextField();
    private JButton saveMenuItemButton = new JButton("保存");

    private ArrayList<QuizCard> quizCardArrayList = new ArrayList<>();

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    void go(){
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.getContentPane().add(BorderLayout.SOUTH,southPanel);

        quizArea.setLineWrap(true);
        answerArea.setLineWrap(true);

        panel.add(quizLabel);
        panel.add(quizArea);
        panel.add(answerLabel);
        panel.add(answerArea);
        southPanel.add(submit);

        submit.addActionListener(new SubmitListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem newMenuItem = new JMenuItem("新建");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.setSize(500,450);
        frame.setLocation((int)((screenSize.width-frame.getWidth())/2),
                                (int)((screenSize.height-frame.getHeight())/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            quizArea.setText("");
            answerArea.setText("");
            quizCardArrayList = new ArrayList<>();
        }
    }
    class SaveMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            saveMenuItemFrame.getContentPane().add(BorderLayout.NORTH,saveMenuItemLabel);
            saveMenuItemFrame.getContentPane().add(BorderLayout.CENTER,saveMenuItemTextField);
            saveMenuItemFrame.getContentPane().add(BorderLayout.SOUTH,saveMenuItemButton);

            saveMenuItemButton.addActionListener(new SaveMenuItemButtonListener());

            saveMenuItemFrame.setSize(100,100);
            saveMenuItemFrame.setLocation((int)((screenSize.getWidth()-saveMenuItemFrame.getWidth())/2),
                    (int)((screenSize.getHeight()-saveMenuItemFrame.getHeight())/2));
            saveMenuItemFrame.setVisible(true);
            saveMenuItemFrame.setResizable(false);
            saveMenuItemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    class SaveMenuItemButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            saveMenuItemFrame.setVisible(false);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveMenuItemLabel.getText()+".ser"));
                for(QuizCard quizCard : quizCardArrayList) {
                    objectOutputStream.writeObject(quizCard);
                }
                objectOutputStream.close();
            }catch (Exception ex){ex.printStackTrace();}
            frame.remove(southPanel);
            panel.removeAll();
            JButton player = new JButton("To Player?");
            player.addActionListener(new PlayerListener());
            panel.add(player);
            frame.setSize(200,100);
            frame.setLocation((int)((screenSize.width-frame.getWidth())/2),
                    (int)((screenSize.height-frame.getHeight())/2));
            saveMenuItemFrame.dispose();
        }
    }
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            quizCardArrayList.add(new QuizCard(quizArea.getText(),answerArea.getText()));
            quizArea.setText("");
            answerArea.setText("");
        }
    }
    class PlayerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            new QuizCardPlayer().go();
            frame.dispose();
        }
    }
}