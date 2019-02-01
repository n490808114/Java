package Server;

import java.io.*;
import java.util.ArrayList;

class ChatMessage implements Serializable {
    private ArrayList<String> chatMessages;
    private final int SAVE_CHAT_EVERY = 10;
    private int saveChatNums = 0;

    ChatMessage(){
        chatMessages = new ArrayList<>();
    }

    Boolean add(String message){
        chatMessages.add(message);

        saveChatNums += 1;
        if (saveChatNums == SAVE_CHAT_EVERY){
            save();
        }
        return true;
    }
    ArrayList<String> get(){
        return chatMessages;
    }
    private void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Messages.txt"));
            for(String str :chatMessages){
                writer.write(str);
            }
            writer.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}