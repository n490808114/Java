package com.how2java.jdbc;

import com.how2java.test.Order;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool implements JDBCCallBack{
    private static final ConnectionPool connectionPool = new ConnectionPool();
    private final ObjectManager objectManager = new ObjectManager();

    private SonThread[] threads = new SonThread[MAX_THREAD_NUMS];
    private ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();

    private static final int MAX_THREAD_NUMS = 20;

    private ConnectionPool(){
        for(int i=0;i<MAX_THREAD_NUMS;i++){
            threads[i] = new SonThread();
            threads[i].start();
        }
    }
    private static ConnectionPool getInstance(){
        return connectionPool;
    }
    static ObjectManager getObjectManager(){
        return getInstance().objectManager;
    }
    static void executeQuery(String sqlString){
        connectionPool.send(EXECUTE_QUERY,sqlString, getObjectManager());
    }
    static void execute(String sqlString,Object object){
        connectionPool.send(EXECUTE,sqlString, object);
    }

    private void send(int orderCode, String sqlString, Object object){
        messages.offer(new Message(orderCode,sqlString,object));

    }
    public void close(){
        for(SonThread thread:threads){
            thread.isEnd = true;
        }
        System.out.println("Threads has been closed");
    }
    class SonThread extends Thread{
        private int threadNo;

        private Object callBack;
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
                        new Thread(()->objectManager.onResult(aBoolean)).start();
                        break;
                    case EXECUTE_QUERY:
                        LinkedList<String[]> list = connectJDBC.executeQuery(sqlString);
                        new Thread(()-> objectManager.onResult(list)).start();
                        break;
                    case EXECUTE_UPDATE:
                        int i = connectJDBC.executeUpdate(sqlString);
                        new Thread(()-> objectManager.onResult(i)).start();
                        break;
                }
            }
            connectJDBC.unlink();
            System.out.println(threadNo+"线程已关闭");
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
            this.callBack = message.callBack;
        }
    }
    class Message{
        int orderCode;
        String sqlString;
        Object callBack;
        Message(int orderCode, String sqlString, Object callBack){
            this.orderCode = orderCode;
            this.sqlString = sqlString;
            this.callBack = callBack;
        }
    }
    class ObjectManager implements JDBCCallBack {
        private ConcurrentLinkedQueue<Order> orders;
        private ObjectManager(){
            orders = new ConcurrentLinkedQueue<>();
        }
        public void onResult(LinkedList<String[]> list){
            for(String[] strings:list){
                Order order = new Order(strings);
                orders.offer(order);
                System.out.println(order.toString());
            }
        }
    }
}




