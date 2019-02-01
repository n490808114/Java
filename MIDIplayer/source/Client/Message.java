package Client;

import java.text.SimpleDateFormat;
import java.util.Date;

class Message implements Cloneable{
    String userName;
    String password;
    String message;
    String time;
    Message(String userName,String password,String message){
        this.userName = userName;
        this.password = password;
        this.message = message;
        this.time = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").format(new Date());
    }
    public Message clone(){
        Message cloneMessage = null;
        try{
            cloneMessage = (Message) super.clone();
        }catch (CloneNotSupportedException ex){ex.printStackTrace();}
        return cloneMessage;
    }
}
