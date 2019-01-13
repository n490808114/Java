import java.awt.Color;
import java.awt.event.*;

import javax.sound.midi.ControllerEventListener;
import javax.swing.*;
import java.awt.*;
import javax.sound.midi.*;

public class MainGui {
    Gui gui = new Gui();
    Gui2 gui2 = new Gui2();
    public void go() {
        gui.go();
        gui2.go();
        gui.frame.setVisible(true);

        while (true) {//clycle to get ordering code
            switch (gui.orderCode) {
                case "getGui2":
                    gui2.frame.setVisible(true);
                    gui.frame.setVisible(false);
                    gui.orderCode = "";
                    break;
            }
            switch (gui2.orderCode) {
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
            if (gui2.orderCode.equals("startMusic")) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Thread.sleep() got a error!");
            }
        }
    }
    class Gui { //the first main page
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        String orderCode = "";

        JLabel Label = new JLabel("You should click that button");
        JButton button = new JButton("click me!");
        JRadioButton radioButton = new JRadioButton("单选按钮");
        JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("for ");

        public void go() {
            frame.getContentPane().add(panel);

            button.addActionListener(new ButtonListener());

            panel.add(radioButton);
            panel.add(button);
            panel.add(Label);
            panel.add(radioButtonMenuItem);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
        }

        class ButtonListener implements ActionListener { //son class for get ActionEvent
            public void actionPerformed(ActionEvent event) {
                button.setText("I`ve been clicked!");
                button.setForeground(new Color(255, 0, 0));

                orderCode = "getGui2";
                frame.setVisible(false);
            }
        }
    }

    class Gui2 {//page two
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        String orderCode = "";
        //add four button and a text label ;label don`t have textarea`s white backgroud,so more beauty
        JButton button = new JButton("EXIT");
        ;
        JButton backButton = new JButton("go back the main GUI");
        JButton showPaintButton = new JButton("show the paint");
        JLabel label = new JLabel("You`ve Clicked button");

        //add the Panel  drawPanel
        MyDrawPanel myDrawPanel = new MyDrawPanel();

        public void go() {
            //create a panel for put the buttons on
            frame.getContentPane().add(panel);
            //add ActionListener for four button
            button.addActionListener(new ButtonListener());
            backButton.addActionListener(new BackButtonListener());
            showPaintButton.addActionListener(new ShowPaintButtonListener());
            //add four buton on the panel
            panel.add(button);
            panel.add(backButton);
            panel.add(showPaintButton);
            panel.add(label);
            //set frame size,and set the frame default close way is click the red close button
            frame.setSize(500, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                button.setText("Clicked");
                button.setForeground(new Color(255, 0, 0));

                label.setText("You are clicking button");
                orderCode = "systemOut";
            }
        }

        class BackButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                orderCode = "getGui";
            }
        }

        class ShowPaintButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                frame.getContentPane().removeAll();
                frame.setSize(1000, 1000);
                frame.repaint();
                frame.getContentPane().add(BorderLayout.CENTER, myDrawPanel);
                //frame.addMouseListener(new MouseAdapterListener());
                orderCode = "startMusic";

            }
        }

//这里是鼠标点击监控的代码，注册在ShowPaintButtonListener中
/*    class MouseAdapterListener implements MouseListener{
        public void mouseClicked(MouseEvent event){
            int x = event.getX();
            int y = event.getY();
            int centerX = myDrawPanel.x + myDrawPanel.width / 2;
            int centerY = myDrawPanel.y + myDrawPanel.height / 2;
            for(int i=1;i<10;i++){
                int targetX = centerX + (x - centerX) * i / 10;
                int targetY = centerY + (y - centerY) * i / 10;
                myDrawPanel.x = targetX - myDrawPanel.width / 2;
                myDrawPanel.y = targetY - myDrawPanel.height / 2;
                myDrawPanel.repaint();
                try{
                    Thread.sleep(500);
                }catch(InterruptedException ex){
                    System.out.println("Thread.sleep() got a error!");
                }
            }
        }
        public void mouseExited(MouseEvent event){}
        public void mouseEntered(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        public void mousePressed(MouseEvent event){}
    }*/

        class MyDrawPanel extends JPanel implements ControllerEventListener {
            private static final long serialVersionUID = 6201378234876555585L;
            int x;
            int y;
            int width = 200;
            int height = 200;
            boolean messg = false;

            public void controlChange(ShortMessage event) {
                messg = true;
                repaint();
            }

            public void paintComponent(Graphics gs) {
                if (messg) {
                    gs.setColor(Color.WHITE);
                    gs.fillRect(0, 0, this.getWidth(), this.getHeight());

                    Graphics2D g2d = (Graphics2D) gs;
                    Color randomColor = new Color((int) (Math.random() * 255),
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255));
                    Color randomColor2 = new Color((int) (Math.random() * 255),
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255));
                    x = (int) (Math.random()*400);
                    y = (int) (Math.random()*400);

                    GradientPaint gradientPaint2 = new GradientPaint(200, 200, randomColor, 400, 700, randomColor2, true);
                    g2d.setPaint(gradientPaint2);
                    g2d.fillOval(x, y, width, height);
                }
            }

        }
    }
}

