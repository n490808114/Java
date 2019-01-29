import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomServer {
    private Map<Account,Boolean> accountStates;

    ChatRoom chatRoom;

    ChatRoomServer(){
        accountStates = new HashMap<>();
        chatRoom = new ChatRoom();
    }

    void go(){
        try {
            ServerSocket server = new ServerSocket(5250);

            while(true){

            }
        }catch (Exception ex){ex.printStackTrace();}
    }
    private Boolean loginIn(String n,String p){
        return false;
    }
    private Boolean loginUp(String n,String p){
        return false;
    }
}
class ChatRoom implements Serializable {
    private ArrayList<String> chatMessages;
    private final int SAVE_CHAT_EVERY = 10;
    private int saveChatNums = 0;

    ChatRoom(){
        chatMessages = new ArrayList<>();
    }

    Boolean addMessage(String message){
        chatMessages.add(message);

        saveChatNums += 1;
        if (saveChatNums == SAVE_CHAT_EVERY){
            saveChatMessages();
        }
        return true;
    }
    ArrayList<String> getChatMessages(){
        return chatMessages;
    }
    private void saveChatMessages(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Messages.txt"));
            for(String str :chatMessages){
                writer.write(str);
            }
            writer.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
class Account{
    private String name;
    private String password;
    Account(String n,String p){
        name = n;
        password = p;
    }
    String getName() {
        return name;
    }
    Boolean check(String n,String p){
        return name.equals(n) && password.equals(p);
    }
}