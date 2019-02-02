package Support;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Cloneable,Serializable{
    public String userName;
    public String password;
    public String message;
    public String time;
    public Message(String userName,String password,String message){
        this.userName = userName;
        this.password = password;
        this.message = message;
        this.time = new SimpleDateFormat("YYYY-MM-DD HH:MM:S").format(new Date());
    }
    public Message(String userName,String password,String message,String time){
        this.userName = userName;
        this.password = password;
        this.message = message;
        this.time = time;
    }
    public Message clone(){
        Message cloneMessage = null;
        try{
            cloneMessage = (Message) super.clone();
        }catch (CloneNotSupportedException ex){ex.printStackTrace();}
        return cloneMessage;
    }
    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append(userName);
        str.append(time + "\n");
        str.append(message + "\n");
        return str.toString();
    }
}
