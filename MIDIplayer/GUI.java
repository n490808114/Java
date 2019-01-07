import java.awt.Button;
import java.awt.event.*;

import javax.swing.*;

public class GUI{
    JButton button;
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.go();


    }
    public void changeIt(){
        button.setText("I`ve clicked");
    }
    public void go(){
        JFrame frame = new JFrame();
        button = new Button("click me!");

        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}