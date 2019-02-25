package com.how2java.jdbc;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 用户线程通过excute方法访问connectionPool
 *class
 */
public class ConnectionPool implements StateOfJDBC {
    private static final ConnectionPool connectionPool = new ConnectionPool();
    private SonThread[] pool;
    private static final int maxThreadNums = 20;

    private ConnectionPool(){}

    public static ConnectionPool getInstance(){
        return connectionPool;
    }
    void init(){
        pool = new SonThread[maxThreadNums];
    }
    public void doIt(int orderCode,String sqlString,JDBCgetResult object){
        int isSended = 0;
        while (isSended == 0){
            for(SonThread eachThread:pool){
                if(eachThread == null){
                    synchronized (ConnectionPool.class){
                        if(eachThread == null){
                            eachThread = new SonThread();
                            System.out.print(eachThread.getThreadNo()+"已创建");
                            new Thread(eachThread).start();
                            System.out.println(eachThread.getThreadNo()+"已运行"+pool.length);
                        }
                    }
                }
                if(eachThread.isWaiting){
                    eachThread.setNewDuty(object,orderCode,sqlString);
                    synchronized (eachThread){eachThread.notify();}
                    isSended = 1;
                    break;
                }
            }
        }
    }
    public void close(){
        for(SonThread thread:pool){
            while (!thread.isWaiting){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            thread.isEnd = true;
        }
        System.out.println("Threads has been closed");
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

    Boolean isWaiting;
    Boolean isEnd;
    private final ConnectJDBC connectJDBC;
    SonThread(){
        connectJDBC = new ConnectJDBC();
        isEnd = false;
        isWaiting = true;
        threadMax += 1;
        orderCode = -1;
        threadNo = threadMax;

    }
    public synchronized void run(){
        connectJDBC.link();

        while (!isEnd){
            try {
                if(object == null){
                    isWaiting = true;
                    System.out.println(threadNo+"等待状态>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    this.wait();
                    System.out.println(threadNo+"回归运行<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    continue;
                }
                isWaiting = false;
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
    void setNewDuty(JDBCgetResult object,int orderCode,String sqlString){
        this.object = object;
        this.orderCode = orderCode;
        this.sqlString = sqlString;
    }
    int getThreadNo(){
        return threadNo;
    }
}
