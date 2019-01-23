import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class QuizCardPlayer{
    private JFrame frame = new JFrame("Quiz Card Player");

    private JPanel quizPanel = new JPanel();
    private JLabel quizLabel = new JLabel("Quiz:");
    private JTextArea quizArea = new JTextArea(10,22);

    private JPanel answerPanel =new JPanel();
    private JLabel answerLabel = new JLabel("Answer:");
    private JTextArea answerArea = new JTextArea(10,22);

    private JPanel southPanel = new JPanel();
    private JButton showAnswer = new JButton("揭晓答案");
    private JButton changeQuiz = new JButton("显示问题");

    private ArrayList<QuizCard> quizCardArrayList = new ArrayList<>();

    void go(){
        try {
            FileInputStream fileInputStream = new FileInputStream("EFlashCards.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    quizCardArrayList.add((QuizCard) objectInputStream.readObject());
                } catch (Exception ex) { break; }
            }
        }catch (Exception ex){ex.printStackTrace();}
        if(quizCardArrayList.size() == 0){ goEnd(); }else { goPlay(); }
    }
    private void goEnd(){
        frame.getContentPane().add(answerPanel);
        answerPanel.add(answerArea);
        answerArea.setText("未找到文件名为\"EFlashCards.ser\"的文件,请手动选择文件或重新打开程序添加问题");

        frame.setSize(300,300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()-frame.getWidth())/2),
                (int)((screenSize.getHeight()-frame.getHeight())/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void goPlay(){
        frame.getContentPane().add(BorderLayout.WEST, quizPanel);
        frame.getContentPane().add(BorderLayout.EAST, answerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        quizPanel.setLayout(new BorderLayout());
        quizPanel.add(BorderLayout.NORTH,quizLabel);
        quizPanel.add(BorderLayout.CENTER,quizArea);
        quizArea.setLineWrap(true);
        quizArea.setWrapStyleWord(true);

        answerPanel.setLayout(new BorderLayout());
        answerPanel.add(BorderLayout.NORTH,answerLabel);
        answerPanel.add(BorderLayout.CENTER,answerArea);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);

        southPanel.add(showAnswer);
        southPanel.add(changeQuiz);

        showAnswer.addActionListener(new ShowAnswerListener());
        changeQuiz.addActionListener(new ChangeQuizListener());

        quizArea.setText("现有问题数为： " + quizCardArrayList.size()+" !\n请点击“显示问题”!");

        frame.setSize(500, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()-frame.getWidth())/2),
                (int)((screenSize.getHeight()-frame.getHeight())/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    class ShowAnswerListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String quiz = quizArea.getText();
            for(QuizCard quizCard : quizCardArrayList){
                if(quizCard.getQuiz().equals(quiz)){
                    answerArea.setText(quizCard.getAnswer());
                    break;
                }
            }
        }
    }
    class ChangeQuizListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(changeQuiz.getText().equals("显示问题")){changeQuiz.setText("下一个问题");}
            int index = (int) (Math.random() * quizCardArrayList.size());
            quizArea.setText(quizCardArrayList.get(index).getQuiz());
        }
    }
}