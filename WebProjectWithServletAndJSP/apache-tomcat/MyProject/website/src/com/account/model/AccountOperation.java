package com.account.model;

import java.sql.*;

public class AccountOperation {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/website";

    private static final String USER = "root";
    private static final String PASS = "root";

    public static Boolean checkAccount(String userName,String password){
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            statement = connection.createStatement();

            String sql = "SELECT * FROM account";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String user = rs.getString("username");
                String pass = rs.getString("password");
                if(user.equals(userName) && pass.equals(password)){
                    return true;
                }
            }
            rs.close();
            statement.close();
            connection.close();
        }catch (Exception ex){ex.printStackTrace();}
        finally {
            try{
                if (statement != null){
                    statement.close();
                }
            }catch (SQLException ex){ex.printStackTrace();}
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException ex){ex.printStackTrace();}
        }
        return false;
    }
}
