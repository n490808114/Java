package com.how2java.jdbc;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 用户线程通过excute方法访问connectionPool
 *class
 */
public class ConnectionPool implements StateOfJDBC {
    private static final ConnectionPool connectionPool = new ConnectionPool();
    private SonThread[] pool;
    private ConcurrentLinkedQueue<Message> messages;
    private static final int maxThreadNums = 20;
    private static final int MAX_MESSAGE_NUM = 20;
    private static final int POLL = 0;
    private static final int OFFER = 1;

    private ConnectionPool(){}

    static ConnectionPool getInstance(){
        return connectionPool;
    }
    void init(){
        new Thread(this::start);
    }
    private void start(){
        pool = new SonThread[maxThreadNums];
        for(SonThread each:pool){
            each = new SonThread();
            each.start();
        }
        while (true){
            while (messages.isEmpty()){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){e.printStackTrace();}
            }
            for(SonThread eachThread:pool){
                if(eachThread.getState().equals(Thread.State.WAITING)){
                    System.out.println("开始处理请求...................");
                    try{
                        while (true){
                            Message message =messages.peek();
                            if(message != null){
                                eachThread.setNewDuty(message);
                                break;
                            }
                        }
                        synchronized (eachThread){eachThread.notify();}
                    }catch (NoSuchElementException ex){
                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (InterruptedException e){e.printStackTrace();}
                    }
                }
            }
        }
    }

    void send(int orderCode,String sqlString,JDBCgetResult object){
        messages.offer(new Message(orderCode,sqlString,object));
        System.out.println("信息已收到");
    }
    public void close(){
        for(SonThread thread:pool){
            while (thread.getState() == Thread.State.WAITING){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            thread.isEnd = true;
        }
        System.out.println("Threads has been closed");
    }
}
class Message{
    int orderCode;
    String sqlString;
    JDBCgetResult jdbCgetResult;
    Message(int orderCode,String sqlString,JDBCgetResult jdbCgetResult){
        this.orderCode = orderCode;
        this.sqlString = sqlString;
        this.jdbCgetResult = jdbCgetResult;
    }
}


interface StateOfJDBC{
    int EXECUTE = 0;
    int EXECUTE_QUERY = 1;
    int EXECUTE_UPDATE = 2;
}

class SonThread extends Thread implements StateOfJDBC{
    private int threadNo;
    private static int threadMax = 0;
    private JDBCgetResult object;
    private int orderCode;
    private String sqlString;

    private JDBCgetResult object1;

    Boolean isEnd;
    private final ConnectJDBC connectJDBC;
    SonThread(){
        connectJDBC = new ConnectJDBC();
        isEnd = false;
        threadMax += 1;
        orderCode = -1;
        threadNo = threadMax;

    }
    public synchronized void run(){
        connectJDBC.link();

        while (!isEnd){
            try {
                if(object == null){
                    System.out.println(threadNo+"等待状态>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    this.join();
                    System.out.println(threadNo+"回归运行<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    continue;
                }
                object1 = object;
                object = null;
                System.out.println(threadNo+"运行状态+++++++++++++++++++++++++++++++++++++++");
                switch (orderCode) {
                    case EXECUTE:
                        object1.onResult(connectJDBC.execute(sqlString));
                        break;
                    case EXECUTE_QUERY:
                        object1.onResult(connectJDBC.executeQuery(sqlString));
                        break;
                    case EXECUTE_UPDATE:
                        object1.onResult(connectJDBC.executeUpdate(sqlString));
                        break;
                }
                }catch (InterruptedException ex){ex.printStackTrace();}
        }
        connectJDBC.unlink();
        System.out.println(threadNo+"线程已关闭");
    }
    void setNewDuty(Message message){
        this.object = message.jdbCgetResult;
        this.orderCode = message.orderCode;
        this.sqlString = message.sqlString;
    }
    int getThreadNo(){
        return threadNo;
    }
}
