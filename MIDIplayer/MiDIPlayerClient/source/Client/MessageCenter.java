package Client;


import Support.Message;

import java.io.*;
import java.util.ArrayList;

class MessageCenter {
    private ArrayList<Message> waitForSaveList;
    private static final int AUTO_SAVE = 10;
    private int autoSave = 0;

    private Message[] waitForShowList;
    private int waitForShowSize;
    private Message[] waitForSendList;
    private int waitForSendSize;

    MessageCenter(){
        waitForSendList = new Message[10];
        waitForShowList = new Message[10];

        waitForSaveList = new ArrayList<>();
    }

    void addSendList(Message message){
        if(waitForSendSize == waitForSendList.length){
            Message[] newWaitForSendList = new Message[waitForSendSize + 10];
            System.arraycopy(waitForSendList,0,newWaitForSendList,0,waitForShowSize);
            waitForSendList = newWaitForSendList;
        }
        waitForSendList[waitForSendSize] = message;
        waitForSendSize += 1;
    }
    Message[] getWaitForShowList(){
        if(waitForShowSize == 0){ return null; }
        Message[] result = new Message[waitForShowSize];
        System.arraycopy(waitForShowList,0,result,0,waitForShowSize);
        waitForShowList = new Message[10];
        waitForShowSize = 0;
        return result;
    }

    void addShowList(Message message){
        if(waitForShowSize == waitForShowList.length){
            Message[] newWaitForShowList = new Message[waitForShowSize + 10];
            System.arraycopy(waitForShowList,0,newWaitForShowList,0,waitForShowSize);
            waitForShowList = newWaitForShowList;
        }
        waitForShowList[waitForShowSize] = message;
        waitForShowSize += 1;

        waitForSaveList.add(message);
        autoSave += 1;
        if(autoSave == AUTO_SAVE){
            saveShowList();
        }
    }
    Message[] getWaitForSendList(){
        if(waitForSendSize == 0){ return null; }
        Message[] result = new Message[waitForSendSize];
        System.arraycopy(waitForSendList,0,result,0,waitForSendSize);
        waitForSendList = new Message[10];
        waitForSendSize = 0;
        return result;
    }

    private void saveShowList(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ChatMessage.txt"));
            StringBuffer str = new StringBuffer("");
            for(Message message:waitForSaveList){
                str.append(message.toString());
            }
            writer.append(str.toString());
            writer.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
