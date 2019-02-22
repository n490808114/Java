package com.how2java.jdbc;

import java.util.concurrent.TimeUnit;

class SonThread extends Thread{
    private static final int EXECUTE = 0;
    private static final int EXECUTE_QUERY = 1;
    private static final int EXECUTE_UPDATE = 2;

    private int orderCode;
    private int serialNum;
    private String sqlString;

    private int oldSerialNum;

    Boolean isWaiting;
    Boolean isEnd;

    private final ConnectJDBC connectJDBC;
    private final ConnectionPool pool;
    SonThread(ConnectionPool pool){
        connectJDBC = new ConnectJDBC();
        this.pool = pool;
        oldSerialNum = -1;
        serialNum = -1;
        isEnd = false;
    }
    public void run(){
        while (serialNum == -1){
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (java.lang.InterruptedException ex){ex.printStackTrace();}
        }
        connectJDBC.link();

        while (!isEnd){
            try {
                if(oldSerialNum == serialNum){
                    isWaiting = true;
                    wait();
                    continue;
                }
                isWaiting = false;
                oldSerialNum = serialNum;
                switch (orderCode){
                    case EXECUTE:
                        pool.getExecuteResult().put(serialNum, connectJDBC.execute(sqlString));
                        break;
                    case EXECUTE_QUERY:
                        pool.getExecuteQueryResult().put(serialNum, connectJDBC.executeQuery(sqlString));
                        break;
                    case EXECUTE_UPDATE:
                        pool.getExecuteUpdateResult().put(serialNum, connectJDBC.executeUpdate(sqlString));
                        break;
                }


                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException ex){ex.printStackTrace();}
        }
        System.out.println(serialNum+"线程已关闭");
    }
    void setNewDuty(int serialNum,int orderCode,String sqlString){
        this.serialNum = serialNum;
        this.orderCode = orderCode;
        this.sqlString = sqlString;
    }
}