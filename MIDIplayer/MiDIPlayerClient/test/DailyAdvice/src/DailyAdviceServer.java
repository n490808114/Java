import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class DailyAdviceServer {
    private ArrayList<Account> accounts;
    private ArrayList<Boolean> accountsStates;

    public static void main(String[] args){
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
    private void go(){
        try {
            ServerSocket serverSocket = new ServerSocket(4500);
            String[] advceList = {"�����жȣ��������١�",
                    "������������̫����",
                    "������ĥ���Ǻܶ�ģ��������ǲ��ɶ���ÿһ����΢���˺����������С�������ĥ����ǰ�������ϵļ�ǿ���޶����������ǵֿ�����������������������",
                    "�����������Ҫͨ��ÿ��վ�Ƶľ���ÿһ������׶Ρ���������ֱ��ǰ���ߣ��Ӳ�����ʲô��",
                    "���ǰ��Լ�������Ϊ�ռ�Ŀ�꣬���˰��˱�ʲô����Ҫ�ˣ�����������˹��ף�������ֵ���١�",
                    "������֪�ģ�������ĺ�硣",
                    "������Ͷ�ڶĲ��Ķ�ͽ�������ǵ�����Ϊ��ʱ�򣬶��Լ��������г�ֵ����ţ�������Ϊ�󵨵�ð����Ψһ����ʽ��",
                    "��ʵ����������������һ�м�ֵ�ĸ�����"};
            while(true) {
                Socket socket = serverSocket.accept();
                OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());

                BufferedWriter writer = new BufferedWriter(streamWriter);
                writer.write(advceList[(int)(Math.random()*advceList.length)]);
                System.out.println("X");
                writer.close();
            }
        }catch (IOException ex){ex.printStackTrace();}
    }
    private Boolean loginIn(String n,String p){
        for(Account account:accounts){
            if (n.equals(account.getName())){
                if(account.check(n,p)){
                    accountsStates.set(accounts.indexOf(account),true);
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }
    private Boolean loginUp(String n,String p){
        for(Account account:accounts){
            if(account.getName().equals(n)){
                return false;
            }
        }
        accounts.add(new Account(n,p));
        return true;
    }
}
class ChatRoom implements Serializable{
    private ArrayList<String> chatMessages;
    private final int SAVE_CHAT_EVERY = 10;
    private int saveChatNums = 0;

    ChatRoom(){
        chatMessages = new ArrayList<>();
    }

    Boolean addMessage(String message){
        chatMessages.add(message);

        saveChatNums += 1;
        if (saveChatNums == SAVE_CHAT_EVERY){
            saveChatMessages();
        }
        return true;
    }
    ArrayList<String> getChatMessages(){
        return chatMessages;
    }
    private void saveChatMessages(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Messages.txt"));
            for(String str :chatMessages){
                writer.write(str);
            }
            writer.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
class Account{
    private String name;
    private String password;
    Account(String n,String p){
        name = n;
        password = p;
    }
    String getName() {
        return name;
    }
    Boolean check(String n,String p){
        return name.equals(n) && password.equals(p);
    }
}