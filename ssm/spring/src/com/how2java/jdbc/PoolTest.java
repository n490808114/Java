package com.how2java.jdbc;

import com.how2java.test.Order;

import java.util.LinkedList;

public class PoolTest {
    public static void main(String[] args){
        final ConnectionPool pool = new ConnectionPool();
        pool.init(pool);
        for(int i=0;i<20;i++){
            new Thread(new GetTest(pool)).start();
        }
    }
}
class GetTest implements Runnable{
    private final ConnectionPool pool;
    GetTest(ConnectionPool pool){
        this.pool = pool;
    }
    public void run(){
        LinkedList<String[]> list = pool.useExecuteQuery().send("Select * From tickets;");
        for(String[] sonList:list){
            for(String each:sonList){
                System.out.print(each + " | ");
            }
            System.out.print("\n");
        }
    }
}