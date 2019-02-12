package Server;

import Support.Message;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private ArrayList<Account> accounts;
    private ArrayList<Message> messages;

    private Server(){
        accounts = new ArrayList<>();
        messages = new ArrayList<>();
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





                Thread t = new Thread(new SonServer(socket));
                t.start();
            }
        }catch (Exception ex){ex.printStackTrace();}
    }
    class SonServer implements Runnable{
        private Socket socket;
        SonServer(Socket socket){
            this.socket = socket;
        }
        public void run(){
            try {
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) input.readObject();
                System.out.println(message.toString());
                messages.add(message);

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                while (messages.size() > 0) {
                    output.writeObject(messages.get(0));
                    messages.remove(0);
                }
            }catch (Exception ex){ex.printStackTrace();}
        }
    }
}


