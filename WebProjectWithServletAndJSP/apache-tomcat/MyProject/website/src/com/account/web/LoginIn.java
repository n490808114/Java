package com.account.web;

import com.account.model.AccountDB;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginIn extends HttpServlet{
    AccountDB accountDB = new AccountDB();
    public void doPost(HttpServletRequest request,HttpServletResponse response)
                throws IOException,ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("You have been Login In!");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if(accountDB.check(userName,password)) {
            out.println("<b>Got the account message!" + userName + "|" + password);
        }else{
            out.println("Worry userName or password!");
        }
    }
}