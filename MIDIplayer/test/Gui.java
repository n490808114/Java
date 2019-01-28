import javax.swing.*;
import java.awt.*;

public class Gui {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button01 = new JButton("test1");
        JButton button02 = new JButton("test2");
        JButton button03 = new JButton("test3");


        panel.setBackground(Color.lightGray);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        frame.getContentPane().add(BorderLayout.CENTER,panel);

        panel.add(button01);
        panel.add(button02);
        panel.add(button03);
        //frame.pack();
        //frame.setSize(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
