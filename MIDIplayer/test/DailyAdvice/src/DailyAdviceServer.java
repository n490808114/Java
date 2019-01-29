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
            String[] advceList = {"生活有度，人生添寿。",
                    "理想是人生的太阳。",
                    "人生的磨难是很多的，所以我们不可对于每一件轻微的伤害都过于敏感。在生活磨难面前，精神上的坚强和无动于衷是我们抵抗罪恶和人生意外的最好武器。",
                    "人生并不像火车要通过每个站似的经过每一个生活阶段。人生总是直向前行走，从不留下什么。",
                    "她们把自己恋爱作为终极目标，有了爱人便什么都不要了，对社会作不了贡献，人生价值最少。",
                    "人生贵知心，定交无暮早。",
                    "将人生投于赌博的赌徒，当他们胆敢妄为的时候，对自己的力量有充分的自信，并且认为大胆的冒险是唯一的形式。",
                    "真实是人生的命脉，是一切价值的根基。"};
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