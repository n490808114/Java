import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EFlashCard{
    private JFrame frame = new JFrame("EFlashCard");
    private JPanel panel = new JPanel();
    private JButton builderButton = new JButton("Builder");
    private JButton playerButton = new JButton("Player");
    public static void main(String[] args){
        new EFlashCard().go();
    }
    private void go(){
        frame.getContentPane().add(panel);
        panel.add(builderButton);
        panel.add(playerButton);
        builderButton.addActionListener(new BuilerButtonListener());
        playerButton.addActionListener(new PlayerButtonListener());

        frame.setSize(50,100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)((screenSize.getWidth()-frame.getWidth())/2),
                (int)((screenSize.getHeight()-frame.getHeight())/2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    class BuilerButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            new QuizCardBuilder().go();
            frame.dispose();
        }
    }
    class PlayerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            new QuizCardPlayer().go();
            frame.dispose();
        }
    }
}