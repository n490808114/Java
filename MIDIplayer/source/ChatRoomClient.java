import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatRoomClient implements Runnable{
    String message = "";
    public void run(){
        try {
            System.out.println("client:run........");
            Socket clientSocket = new Socket("127.0.0.1", 5250);
            while (true){
                try{
                    Thread.sleep(500);
                }catch (Exception ex){ex.printStackTrace();}
                System.out.println("client:doing...................");
                if("".equals(message)){ continue; }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println(message);
                writer.write(message);
                writer.flush();
                message = "";
            }
        }catch (Exception ex){ex.printStackTrace();}
    }
}
