import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class QuizCardBuilder{
    private JFrame frame = new JFrame("QuizCardBuilder");
    private Box box = new Box(BoxLayout.Y_AXIS);
    private JLabel quizLabel = new JLabel("Quiz:");
    private JTextArea quizArea = new JTextArea(10,50);

    private JLabel answerLabel = new JLabel("Answer:");
    private JTextArea answerArea = new JTextArea(10,50);
    private JButton submit = new JButton("提交");

    private ArrayList<QuizCard> quizCardArrayList = new ArrayList<>();

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    void go(){
        frame.getContentPane().add(BorderLayout.CENTER,box);
        frame.getContentPane().add(BorderLayout.SOUTH,submit);

        quizArea.setLineWrap(true);
        answerArea.setLineWrap(true);

        JPanel quizPanel = new JPanel();
        JScrollPane quizScrollPane = new JScrollPane(quizArea);
        quizScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        quizScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        quizPanel.add(BorderLayout.NORTH,quizLabel);
        quizPanel.add(BorderLayout.SOUTH,quizScrollPane);

        JPanel answerPanel = new JPanel();
        JScrollPane answerScrollPane = new JScrollPane(answerArea);
        answerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        answerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        answerPanel.add(BorderLayout.NORTH,answerLabel);
        answerPanel.add(BorderLayout.SOUTH,answerScrollPane);

        box.add(Box.createVerticalStrut(10));
        box.add(quizPanel);
        box.add(Box.createVerticalStrut(20));
        box.add(answerPanel);
        box.add(Box.createVerticalStrut(10));

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
        frame.setLocation((screenSize.width-frame.getWidth())/2,
                                (screenSize.height-frame.getHeight())/2);
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
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(frame);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                for(QuizCard quizCard : quizCardArrayList) {
                    objectOutputStream.writeObject(quizCard);
                }
                objectOutputStream.close();
            }catch (Exception ex){ex.printStackTrace();}

            int result = JOptionPane.showConfirmDialog(frame,"是否打开EFlashCardPlayer?","提示",JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                new QuizCardPlayer().go();
                frame.dispose();
            }
        }
    }
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            quizCardArrayList.add(new QuizCard(quizArea.getText(),answerArea.getText()));
            quizArea.setText("");
            answerArea.setText("");
        }
    }
}