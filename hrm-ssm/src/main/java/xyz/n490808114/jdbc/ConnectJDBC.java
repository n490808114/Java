package xyz.n490808114.jdbc;

import java.sql.*;
import java.util.LinkedList;

public class ConnectJDBC {
    private static final String JDBC_BASE = "com.mysql.jdbc.Driver";

    private  String databaseURL = "jdbc:mysql://127.0.0.1:3306/database_for_test";
    private  String user = "root";
    private  String password = "admin";

    private Connection connection = null;
    private Statement statement = null;

    ConnectJDBC(){
        try{
            Class.forName(JDBC_BASE);
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    void link(){
        try{
            connection = DriverManager.getConnection(databaseURL,user,password);
            statement = connection.createStatement();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    LinkedList<String[]> executeQuery(String sqlString){
        LinkedList<String[]> result = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);

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
        }catch (Exception ex){ex.printStackTrace(); }
        return  result;
    }

    Boolean execute(String sqlString){
        Boolean result = false;
        try{
            statement.execute(sqlString);
        }catch (SQLException ex){ex.printStackTrace();}
        return  result;
    }
    int executeUpdate(String sqlString){
        int result = 0;
        try {
            result = statement.executeUpdate(sqlString);
        }catch (SQLException ex){ex.printStackTrace();}

        return result;
    }
    void unlink(){
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
