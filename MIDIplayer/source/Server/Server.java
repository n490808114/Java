package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map<Account,Boolean> accountStates;
    private ArrayList<Account> accounts;
    private ChatMessage chatMessages;
    private String[] messageGeted;

    Server(){
        accountStates = new HashMap<>();
        accounts = new ArrayList<>();
        chatMessages = new ChatMessage();
    }
    public static void main(String[] args){
        new Server().go();
    }

    void go(){
        try {
            ServerSocket server = new ServerSocket(5250);

            while(true){
                Socket socket = server.accept();

                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //writer.write(getNewestChatMessage());
                //writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuffer str = new StringBuffer();
                while (true){
                    String a = reader.readLine();
                    if("".equals(a)){break;}
                    str.append(a);
                }

                System.out.println("client:"+str.toString());
                chatMessages.add("result:" + str.toString());
                messageGeted = str.toString().split("|");
                reader.close();
                socket.close();
            }
        }catch (Exception ex){ex.printStackTrace();}
    }
    private String getNewestChatMessage(){

        return "";
    }
    private Boolean loginIn(String n,String p){
        return false;
    }
    private Boolean loginUp(String n,String p){
        return false;
    }
}

