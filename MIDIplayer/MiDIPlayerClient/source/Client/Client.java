package Client;

import java.io.*;
import java.net.Socket;
import Support.*;

public class Client implements Runnable{

    private MessageCenter messageCenter;

    Socket clientSocket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    private String host = "127.0.0.1";
    private int port = 5250;

    Client(MessageCenter messageCenter){
        this.messageCenter = messageCenter;
        try{
            clientSocket = new Socket(host, port);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        }catch (Exception ex){ex.printStackTrace();}
    }

    public void run(){
        try {
            System.out.println("client:run........");
            while (true) {
                Object o;
                try {
                    o = inputStream.readObject();
                }catch (IOException ex){continue;}
                Thread.sleep(100);
                System.out.println("client:getting...................");
                Message messageGet = (Message) o;
                messageCenter.addShowList(messageGet);

            }
        }catch (Exception ex){ ex.printStackTrace(); }
    }
    void send(){
        try {
            System.out.println("client:sending....................");
            Message[] sendList = messageCenter.getWaitForSendList();
            if (sendList != null) {
                for (Message message : sendList) {
                    System.out.println(message.toString());
                    outputStream.writeObject(message);
                }
            }
            outputStream.flush();
        }catch (IOException ex){ex.printStackTrace();}
    }
}
