package xyz.n490808114.jdbc;


import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool{
    private static final ConnectionPool connectionPool = new ConnectionPool();

    private SonThread[] threads = new SonThread[MAX_THREAD_NUMS];
    private ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();

    private static final int MAX_THREAD_NUMS = 20;
    private static final int EXECUTE = 0;
    private static final int EXECUTE_QUERY = 1;
    private static final int EXECUTE_UPDATE = 2;

    private ConnectionPool(){}
    public static void init(){
        connectionPool.start();
    }
    public static void destory(){
        connectionPool.close();
    }
    private void start(){
        for(int i=0;i<MAX_THREAD_NUMS;i++){
            threads[i] = new SonThread();
            threads[i].start();
        }
    }
    static void executeQuery(String sqlString,int requestNum){
        connectionPool.send(EXECUTE_QUERY,sqlString,requestNum);
    }
    static void execute(String sqlString,int requestNum){
        connectionPool.send(EXECUTE,sqlString, requestNum);
    }

    private void send(int orderCode, String sqlString,int requestNum){
        messages.offer(new Message(orderCode,sqlString,requestNum));

    }
    public void close(){
        for(SonThread thread:threads){
            thread.isEnd = true;
        }
        System.out.println("Threads has been closed");
    }
    class SonThread extends Thread{
        private int requestNum;
        private int orderCode;
        private String sqlString;

        Boolean isEnd;

        private final ConnectJDBC connectJDBC;

        SonThread(){
            connectJDBC = new ConnectJDBC();
            isEnd = false;
            orderCode = -1;
        }
        @Override
        public synchronized void run(){
            connectJDBC.link();
            while (!isEnd){
                getNewDuty();
                switch (orderCode) {
                    case EXECUTE:
                        Boolean aBoolean = connectJDBC.execute(sqlString);
                        new Thread(()->CreateFactory.onResult(requestNum,aBoolean)).start();
                        break;
                    case EXECUTE_QUERY:
                        LinkedList<String[]> list = connectJDBC.executeQuery(sqlString);
                        new Thread(()->CreateFactory.onResult(requestNum,list)).start();
                        break;
                    case EXECUTE_UPDATE:
                        int i = connectJDBC.executeUpdate(sqlString);
                        new Thread(()->CreateFactory.onResult(requestNum,i)).start();
                        break;
                }
            }
            connectJDBC.unlink();
        }
        void getNewDuty(){
            Message message = null;
            int sleepCount = 0;
            while (message == null){
                message = messages.poll();
                try {
                    sleepCount += 1;
                    TimeUnit.MICROSECONDS.sleep(5);
                    if(sleepCount == 10){
                        TimeUnit.SECONDS.sleep(1);
                        sleepCount = 0;
                    }
                }catch (InterruptedException ex){ex.printStackTrace();}

            }
            this.orderCode = message.orderCode;
            this.sqlString = message.sqlString;
            this.requestNum = message.requestNum;
        }
    }
    class Message{
        int orderCode;
        String sqlString;
        int requestNum;
        Message(int orderCode, String sqlString, int requestNum){
            this.orderCode = orderCode;
            this.sqlString = sqlString;
            this.requestNum = requestNum;
        }
    }
}




