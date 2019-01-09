import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

public class GuiTest{
    public static void main(String[] args) {
        Gui gui = new Gui();
        Gui2 gui2 = new Gui2();
        gui.go();
        gui2.go();

        gui.frame.setVisible(true);

        while (true){
            switch(gui.orderCode){

                case "getGui2":
                gui2.frame.setVisible(true);
                gui.frame.setVisible(false);
                gui.orderCode = "";
                break;
            }
            switch(gui2.orderCode){

                case "getGui":
                gui.frame.setVisible(true);
                gui2.frame.setVisible(false);
                gui2.orderCode = "";
                break;

                case "systemOut":
                gui2.orderCode = "";
                System.exit(1);
                break;
            }
            //System.out.println("Gui.orderCode("+gui.orderCode+")gui2.orderCode("+gui2.orderCode+")");
            try{
                Thread.sleep(500);
            }catch(InterruptedException ex){
                System.out.println("Thread.sleep() got a error!");
            }
        }
    }
}

class Gui implements ActionListener{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    String orderCode = "";

    JTextArea textArea = new JTextArea("You should click that button");
    JButton button = new JButton("click me!");
    JRadioButton radioButton = new JRadioButton("µ¥Ñ¡°´Å¥");

    public void go(){
        frame.getContentPane().add(panel);

        button.addActionListener(this);

        panel.add(radioButton);
        panel.add(button);
        panel.add(textArea);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
    }

    public void actionPerformed(ActionEvent event){
        button.setText("I`ve been clicked!");
        button.setForeground(new Color(255, 0, 0));

        orderCode = "getGui2";
        frame.setVisible(false);
    }
}

class Gui2 implements ActionListener{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    String orderCode = "";

    JButton button = new JButton("click me!");;
    JButton backButton = new JButton("go back the main GUI");;
    JTextArea textArea = new JTextArea("You`ve Clicked button");

    MyDrawPanel myDrawPanel = new MyDrawPanel();

    public void go(){
        frame.getContentPane().add(panel);

        button.addActionListener(this);
        backButton.addActionListener(this);

        panel.add(button);
        panel.add(textArea);
        panel.add(backButton);

        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent event){
        if(event.getSource().equals(button)){
            button.setText("Clicked");
            button.setForeground(new Color(255, 0, 0));

            textArea.setText("You are clicking button");
            orderCode = "systemOut";
        }else if(event.getSource().equals(backButton)){
            //JPanel myDrawPanel = new MyDrawPanel();
            //frame.getContentPane().add(myDrawPanel);
            orderCode = "getGui";
        }

    }
}

class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics gs){
        gs.fillRect(0, 0, this.getWidth(), this.getHeight());
        int red = (int) (Math.random()*255);
        int green = (int) (Math.random()*255);
        int blue = (int) (Math.random()*255);
        Color color = new Color(red,green,blue);
        gs.setColor(color);
        gs.fillOval(70, 70, 100, 100);

        gs.setColor(Color.RED);
        gs.fillRect(20,50,100,200);

        Image image = new ImageIcon("lightblue.jpg").getImage();
        gs.drawImage(image,10,20,this);


    }
}