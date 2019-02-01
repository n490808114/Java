package Client;

import java.text.SimpleDateFormat;
import java.util.Date;

class Message{
    String userName;
    String password;
    String message;
    String time;
    Message(String userNameGet,String passwordGet,String messageGet){
        userName = userNameGet;
        password = passwordGet;
        message = messageGet;
        time = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").format(new Date());
    }
}
