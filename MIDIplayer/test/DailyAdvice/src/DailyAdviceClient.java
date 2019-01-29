import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DailyAdviceClient {
    private void go(){
        try {
            Socket clientSocket = new Socket("127.0.0.1", 4500);

            InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today's advice:" + advice);

            reader.close();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public static void main(String[] args){
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
