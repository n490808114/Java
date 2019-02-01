package Client;

import java.io.FileWriter;
import java.util.ArrayList;

public class MessageList {
    private ArrayList<Message> list;
    private Message lastMessage;
    private int autoSave = 0;
    MessageList(){
        list = new ArrayList<>();
    }
    public void add(Message message){
        list.add(message);
        lastMessage = message;
        autoSave += 1;
        if (autoSave == 10){
            save();
        }
    }
    public Message pop(){
        return lastMessage;
    }
    public void save(){
        try {
            FileWriter writer = new FileWriter("message.txt");
            for(Message mess:list){
                writer.write(mess.userName + "\n");
                writer.write(mess.time + "\n");
                writer.write(mess.message + "\n");
            }
            writer.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
