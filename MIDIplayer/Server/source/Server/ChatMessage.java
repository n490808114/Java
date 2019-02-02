package Server;

import Support.*;
import java.io.*;

class ChatMessage implements Serializable {
    private Message[] list;
    private int usedSize = 0;
    private final int AUTO_SAVE = 10;
    private int saveChatNums = 0;

    ChatMessage(int size){
        list = new Message[size];
    }
    void add(Message message){
        message.password = "..";
        if (usedSize == list.length){
            Message[] list1 = new Message[usedSize + 10];
            System.arraycopy(list,0,list1,0,usedSize);
            list = list1;
        }
        list[usedSize] = message;
        usedSize += 1;
        saveChatNums += 1;
        if(saveChatNums == AUTO_SAVE){
            save();
            saveChatNums = 0;
        }
    }
    int size(){
        return usedSize;
    }
    Message get(int index){
        return list[index];
    }
    Message getLast(){
        return list[usedSize];
    }
    void save(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ChatMessage.ser"));
            for(Message message :list){
                outputStream.writeObject(message);
            }
            outputStream.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}