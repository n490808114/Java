package com.how2java.jdbc;

import java.sql.*;
import java.util.LinkedList;

public class TestJDBC {
    private static final String JDBC_BASE = "com.mysql.jdbc.Driver";

    private  String databaseURL;
    private  String user;
    private  String password;

    private static final int EXECUTE = 0;
    private static final int EXECUTE_QUERY = 1;
    private static final int EXECUTE_UPDATE = 2;

    public LinkedList<String[]> executeQuery(String sqlString){
        return  run(EXECUTE_QUERY,sqlString);
    }
    public Boolean execute(String sqlString){
        return  ("true").equals(run(EXECUTE,sqlString).get(0)[0]);
    }
    public int executeUpdate(String sqlString){
        return Integer.parseInt(run(EXECUTE_UPDATE,sqlString).get(0)[0]);
    }

    private LinkedList<String[]> run(int order,String sqlString) {
        LinkedList<String[]> result = new LinkedList<>();
        try{
            Class.forName(JDBC_BASE);
            System.out.println("数据库驱动加载成功");
        }catch (ClassNotFoundException ex){
            System.out.println("数据库驱动未找到:" + JDBC_BASE);
        }
        try (
                Connection connection = DriverManager.getConnection(databaseURL,user,password);
                Statement statement = connection.createStatement();
                ){
            System.out.println("连接成功,获取对象为："+connection);
            System.out.println("获取Statement对象为："+statement);
            switch (order){
                case EXECUTE:
                    result.add(new String[]{statement.execute(sqlString)?"true":"false"});
                    return result;
                case EXECUTE_UPDATE:
                    result.add(new String[]{""+statement.executeUpdate(sqlString)});
                    return result;
                case EXECUTE_QUERY:
                    ResultSet resultSet = statement.executeQuery(sqlString);
                    System.out.println("获取数据成功");
                    int length = resultSet.getMetaData().getColumnCount();
                    try {
                        int index = 0;
                        while (resultSet.next()) {
                            index ++;
                            String[] resultEvery = new String[length];
                            for(int i=0;i<length;i++){
                                resultEvery[i]=resultSet.getString(i+1);
                            }
                            result.add(resultEvery);

                        }
                        System.out.println("共有" + index + "条数据");
                        return result;
                    }catch (SQLException ex){ ex.printStackTrace();}
                    break;
            }
        }catch (SQLException ex){
            System.out.println("出现问题");
            result.add(new String[]{"false"});
            return result;
        }
        result.add(new String[]{"true"});
        return result;
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
