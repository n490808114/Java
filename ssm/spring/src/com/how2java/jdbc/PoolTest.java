package com.how2java.jdbc;

import com.how2java.test.Order;

import java.util.LinkedList;

public class PoolTest {
    public static void main(String[] args){
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();
        for(int i=0;i<100;i++){
            GetTest test = new GetTest(pool);
            System.out.println(test.testno+"测试线程已创建");
            new Thread(test).start();
        }

    }
}
class GetTest implements Runnable,JDBCgetResult{
    private final ConnectionPool pool;
    static int testMax = 0;
    int testno = 0;
    GetTest(ConnectionPool pool){
        this.pool = pool;
        testMax += 1;
        testno = testMax;
    }
    public void run(){
        synchronized (pool){
            pool.send(ConnectionPool.EXECUTE_QUERY,"Select * From tickets;",this);
        }
    }
    public void onResult(LinkedList<String[]> list){
        new Thread(
                ()-> {
            System.out.println(this.testno+"测试结果已得到");
            for(String[] sonList:list) {
                for (String each : sonList) {
                    System.out.print(each + " | ");
                }
                System.out.print("\n");
            }
        }).start();
    }
}