package Server;

import Support.Message;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map<String,Boolean> IPisLoginInState;
    private ArrayList<Account> accounts;
    private ChatMessage chatMessages;


    private int messageForSendCount;
    private static final String WORRY_MESSAGE = "WorryAccount or Acount is exist!";

    private Server(){
        IPisLoginInState = new HashMap<>();
        accounts = new ArrayList<>();
        chatMessages = new ChatMessage(10);
        messageForSendCount = 0;
    }
    public static void main(String[] args){
        new Server().go();
    }

    private void go(){
        try {
            ServerSocket server = new ServerSocket(5250);

            while(true){
                Socket socket = server.accept();
                System.out.println("Server is running...........");

                //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) input.readObject();
                System.out.println(message.toString());
                chatMessages.add(message);

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                while (messageForSendCount < chatMessages.size()){
                    output.writeObject(chatMessages.get(messageForSendCount));
                    messageForSendCount += 1;
                }

                if("LOGIN_UP".equals(message.message)){
                    if(!isAccountExist(message)){
                        loginUp(message);
                        chatMessages.getLast().message = "LOGIN_UP_RIGHT";
                    }else{
                        chatMessages.getLast().message = "ACCOUNT_EXIST";
                    }
                    output.writeObject(chatMessages.getLast());
                }

                if("LOGIN_IN".equals(message.message)){
                    if(checkAccount(message)){
                        chatMessages.getLast().message = "LOGIN_IN_RIGHT";
                        output.writeObject(chatMessages.getLast());
                    }
                }
                socket.close();
            }
        }catch (Exception ex){ex.printStackTrace();}

    }
    private Boolean checkAccount(Message message){
        for(Account account:accounts){
            if(account.check(message.userName, message.password)){
                return true;
            }
        }
        return false;
    }
    private void loginUp(Message message){
        accounts.add(new Account(message.userName, message.password));
    }
    private Boolean isAccountExist(Message message){
        for(Account account:accounts){
            if (account.getName().equals(message.userName))
                return true;
        }
        return false;
    }
}


