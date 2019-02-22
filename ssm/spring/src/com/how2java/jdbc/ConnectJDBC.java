package com.how2java.jdbc;

import org.hibernate.hql.ast.SqlASTFactory;

import java.sql.*;
import java.util.LinkedList;

public class ConnectJDBC {
    private static final String JDBC_BASE = "com.mysql.jdbc.Driver";

    private  String databaseURL;
    private  String user;
    private  String password;

    private int isInited = 0;
    private Connection connection = null;
    private Statement statement = null;

    private static final int EXECUTE = 0;
    private static final int EXECUTE_QUERY = 1;
    private static final int EXECUTE_UPDATE = 2;

    private int executeCode;
    private String sqlString;

    ConnectJDBC(){
        executeCode = 0;
        try{
            Class.forName(JDBC_BASE);
            System.out.println("数据库驱动加载成功");
        }catch (ClassNotFoundException ex){
            System.out.println("数据库驱动未找到:" + JDBC_BASE);
        }
    }
    ConnectJDBC(int executeCode, String sqlString){
        this.executeCode = executeCode;
        this.sqlString = sqlString;
        try{
            Class.forName(JDBC_BASE);
            System.out.println("数据库驱动加载成功");
        }catch (ClassNotFoundException ex){
            System.out.println("数据库驱动未找到:" + JDBC_BASE);
        }
    }
    public void link(){
        try{
            connection = DriverManager.getConnection(databaseURL,user,password);
            statement = connection.createStatement();
            System.out.println("连接成功,获取对象为："+connection);
            System.out.println("获取Statement对象为："+statement);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public LinkedList<String[]> executeQuery(String sqlString){
        LinkedList<String[]> result =null;
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            System.out.println("获取数据成功");
            int length = resultSet.getMetaData().getColumnCount();

            int index = 0;
            while (resultSet.next()) {
                index++;
                String[] resultEvery = new String[length];
                for (int i = 0; i < length; i++) {
                    resultEvery[i] = resultSet.getString(i + 1);
                }
                result.add(resultEvery);
            }
            System.out.println("共有" + index + "条数据");
        }catch (Exception ex){ex.printStackTrace(); }
        return  result;
    }

    public Boolean execute(String sqlString){
        Boolean result = false;
        try{
            statement.execute(sqlString);
        }catch (SQLException ex){ex.printStackTrace();}
        return  result;
    }
    public int executeUpdate(String sqlString){
        int result = 0;
        try {
            result = statement.executeUpdate(sqlString);
        }catch (SQLException ex){ex.printStackTrace();}

        return result;
    }
    public void unlink(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException ex){ ex.printStackTrace();}
        }
        if(statement != null){
            try{
                statement.close();
            }catch (SQLException ex){ex.printStackTrace();}
        }
    }
    public void setDatabaseURL(String databaseURL){
        this.databaseURL = databaseURL;
    }
    public String getDatabaseURL(){
        return databaseURL;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
