import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    private void go(){
        try {
            ServerSocket serverSocket = new ServerSocket(4000);

            while(true) {
                Socket socket = serverSocket.accept();
                OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());

                BufferedWriter writer = new BufferedWriter(streamWriter);
                writer.write("ha ha ha");
                writer.close();
            }
        }catch (IOException ex){ex.printStackTrace();}
    }
    public static void main(String[] args){
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
