package com.how2java.jdbc;

public class TestGetOrder {
    public static void main(String[] args){
        ConnectionPool.ObjectManager objectManager = ConnectionPool.getObjectManager();
        objectManager.send("SELECT * FROM orders");
    }
}
