package Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import Support.*;

public class Client implements Runnable{

    Socket clientSocket ;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    ArrayList<Message> messages = new ArrayList<>();

    private String host = "127.0.0.1";
    private int port = 5200;
    private final int AUTO_SAVE = 10;

    Client(){
        try {
            clientSocket = new Socket(host, port);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Client:setUp OK!................");
        }catch (Exception ex){ ex.printStackTrace(); }
    }
    public void run(){
        try {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("client:run...........................");
            while (true) {
                Object o;
                try {
                    o = inputStream.readObject();
                }catch (IOException ex){continue;}
                Thread.sleep(100);
                System.out.println("client:getting...................");
                Message messageGet = (Message) o;
                messages.add(messageGet);

            }
        }catch (Exception ex){ ex.printStackTrace(); }
    }
    synchronized void send(Message message){
        try {
            System.out.println("client:sending....................");
            outputStream.writeObject(message);
            outputStream.flush();
        }catch (IOException ex){ex.printStackTrace();}
    }
    synchronized Message[] getMessage(){
        if(messages.size()>0){
            Message[] result = new Message[messages.size()];
            for(int i=0;i<messages.size();i++){
                result[i] = messages.get(i);
            }
            save(messages.size());
            messages.clear();
            return result;
        }else{ return null; }
    }
    synchronized void autoSave(){
        save(AUTO_SAVE);
    }
    private synchronized void save(int every){
        if(messages.size() >= every){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
                for(int i=0;i<every;i++){
                    writer.write(messages.get(0).toString());
                }
                writer.close();
            }catch (Exception ex){ex.printStackTrace();}
        }
    }
}
