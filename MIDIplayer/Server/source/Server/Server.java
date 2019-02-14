package Server;

import Support.Message;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    private ArrayList<Account> accounts;
    private ArrayList<Message> messages;
    private ArrayList<ObjectOutputStream> outputStreams;

    private Server(){
        accounts = new ArrayList<>();
        messages = new ArrayList<>();
        outputStreams = new ArrayList<>();
    }

    public static void main(String[] args){
        new Server().run();
    }

    public void run(){
        try {
            ServerSocket server = new ServerSocket(5200);

            while(true){
                Socket socket = server.accept();
                System.out.println("Server is running...........");
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                outputStreams.add(output);

                Thread t = new Thread(new SonServer(socket));
                t.start();
            }
        }catch (Exception ex){ex.printStackTrace();}
    }
    private void tellEveryOne(Message message){
        Iterator iterator = outputStreams.iterator();
        while (iterator.hasNext()){
            try {
                ObjectOutputStream outputStream = (ObjectOutputStream) iterator.next();
                outputStream.writeObject(message);
                outputStream.flush();
            }catch (Exception ex){ex.printStackTrace();}
        }
    }
    class SonServer implements Runnable{
        private Socket socket;
        SonServer(Socket socket){
            this.socket = socket;
        }
        public void run(){
                try {
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    while(true) {
                        Message message = (Message) input.readObject();
                        System.out.println(message.toString());
                        messages.add(message);

                        tellEveryOne(message);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

        }
    }
}


