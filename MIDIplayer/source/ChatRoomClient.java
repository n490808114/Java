import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatRoomClient {
    String message = "";
    public void go(){
        try {
            Socket clientSocket = new Socket("127.0.0.1", 5250);
            while (true){
                if("".equals(message)){ continue; }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                writer.write(message);
                writer.close();
            }
        }catch (Exception ex){ex.printStackTrace();}
    }
}
