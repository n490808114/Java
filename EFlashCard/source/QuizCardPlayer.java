import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class QuizCardPlayer{
    private JFrame frame = new JFrame("Quiz Card Player");
    private JLabel label = new JLabel();
    private JTextArea textArea = new JTextArea(10,40);
    private JButton nextButton = new JButton();

    private ArrayList<QuizCard> quizCardArrayList;
    private QuizCard quizCard;

    //for nextButton
    private final String SELECTE_FILE = "请打开EflashCard文件";
    private final String SHOW_ANSWER = "Show Answer";
    private final String NEXT_CARD = "Next Card";

    private String fileChoosePath = "";

    void go(){
        frame.getContentPane().add(BorderLayout.NORTH,label);
        frame.getContentPane().add(BorderLayout.SOUTH, nextButton);
        frame.getContentPane().add(BorderLayout.CENTER,textArea);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        nextButton.setEnabled(false);
        nextButton.setText(SELECTE_FILE);
        nextButton.addActionListener(new NextListener());

        //frame settings
        frame.setSize(500, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()-frame.getWidth())/2),
                (int)((screenSize.getHeight()-frame.getHeight())/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        //MeauBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem openFile = new JMenuItem("打开");

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(openFile);

        openFile.addActionListener(new OpenFileListener());
    }
    private void showQuizArea(){
        label.setText("Question:");
        textArea.setText(quizCard.getQuiz());
        nextButton.setText(SHOW_ANSWER);
    }
    private void showAnswerArea(){
        label.setText("Answer:");
        textArea.setText(quizCard.getAnswer());
        nextButton.setText(NEXT_CARD);
    }
    private ArrayList<QuizCard> getCardList(File file){
        ArrayList<QuizCard> result = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while(true){
                try {
                    result.add((QuizCard) objectInputStream.readObject());
                }catch (Exception ex){break;}
            }
        }catch (Exception ex){ex.printStackTrace();}
        return result;
    }
    class OpenFileListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JFileChooser fileChooser = new JFileChooser(fileChoosePath);
            fileChooser.setFileFilter(new FileNameExtensionFilter("ser","ser"));
            fileChooser.showOpenDialog(frame);

            File file = fileChooser.getSelectedFile();
            quizCardArrayList = getCardList(file);
            fileChoosePath = file.getPath();

            quizCard = quizCardArrayList.get(0);
            nextButton.setEnabled(true);
            showQuizArea();
        }
    }
    class NextListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if(SHOW_ANSWER.equals(nextButton.getText())){
                showAnswerArea();
            }else if(NEXT_CARD.equals(nextButton.getText())){
                try {
                    quizCard = quizCardArrayList.get(quizCardArrayList.indexOf(quizCard) + 1);
                    showQuizArea();
                }catch (Exception ex){
                    label.setText("");
                    textArea.setText("");
                    nextButton.setText(SELECTE_FILE);
                    nextButton.setEnabled(false);
                    JOptionPane.showMessageDialog(frame,"No More Questions","提示",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}