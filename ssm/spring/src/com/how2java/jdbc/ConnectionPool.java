package com.how2java.jdbc;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户线程通过excute方法访问connectionPool
 *
 */
public class ConnectionPool {
    private SonThread[] pool;
    private int maxThreadNums;
    private LinkedList<String> waitList;
    private int MaxserialNum = 0;

    private static final int EXECUTE = 0;
    private static final int EXECUTE_QUERY = 1;
    private static final int EXECUTE_UPDATE = 2;

    private Map<Integer,Boolean> executeResult = new HashMap<>(maxThreadNums);
    private Map<Integer,Integer> executeUpdateResult = new HashMap<>(maxThreadNums);
    private Map<Integer,LinkedList<String[]>> executeQueryResult = new HashMap<>(maxThreadNums);

    ConnectionPool(){
        pool = new SonThread[20];
        maxThreadNums = 20;
        waitList = new LinkedList<>();
    }
    ConnectionPool(int threadNums){
        pool = new SonThread[threadNums];
        maxThreadNums = threadNums;
        waitList = new LinkedList<>();
    }
    void init(ConnectionPool otherPool){
        for(int i=0;i<maxThreadNums;i++){
            pool[i] = new SonThread(otherPool);
        }
    }
    private synchronized void doIt(int serialNum,int orderCode,String sqlString){
        int isSended = 0;
        while (isSended == 0){
            for(SonThread eachThread:pool){
                if(eachThread.isWaiting){
                    eachThread.setNewDuty(serialNum,orderCode,sqlString);
                    eachThread.notifyAll();
                    isSended = 1;
                }
            }
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
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
    private Boolean getFromBooleanResult(int serialNum){
        Boolean result = null;
        while (result == null){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
            result = executeResult.get(serialNum);
        }
        return result;
    }
    private int getFromIntegerResult(int serialNum){
        int result = 0;
        while (result == 0){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
            result = executeUpdateResult.get(serialNum);
        }
        return result;
    }
    private LinkedList<String[]> getFromObjectResult(int serialNum){
        LinkedList<String[]> result = null;
        while (result == null){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
            result = executeQueryResult.get(serialNum);
        }
        return result;
    }
    void putInBooleanResult(int serialNum,Boolean result){
        while (executeResult.size() == maxThreadNums){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
        }
        executeResult.put(serialNum,result);
    }



    private synchronized int getNum(){
        return MaxserialNum++;
    }
    public Execute useExecute(){
        return new Execute();
    }
    public ExecuteQuery useExecuteQuery(){
        return new ExecuteQuery();
    }
    public ExecuteUpdate useExecuteUpdate(){
        return new ExecuteUpdate();
    }
    class Execute{
        int serialNum;
        public Boolean send(String sqlString){
            this.serialNum = getNum();
            doIt(serialNum,EXECUTE,sqlString);
            return getFromBooleanResult(serialNum);
        }
    }
    class ExecuteQuery{
        int serialNum;
        public LinkedList<String[]> send(String sqlString){
            this.serialNum = getNum();
            doIt(serialNum,EXECUTE_QUERY,sqlString);
            return getFromObjectResult(serialNum);
        }
    }
    class ExecuteUpdate{
        int serialNum;
        public int send(String sqlString){
            this.serialNum = getNum();
            doIt(serialNum,EXECUTE_UPDATE,sqlString);
            return getFromIntegerResult(serialNum);
        }
    }

    Map<Integer, Boolean> getExecuteResult() {
        return executeResult;
    }

    Map<Integer, Integer> getExecuteUpdateResult() {
        return executeUpdateResult;
    }

    Map<Integer, LinkedList<String[]>> getExecuteQueryResult() {
        return executeQueryResult;
    }
}
