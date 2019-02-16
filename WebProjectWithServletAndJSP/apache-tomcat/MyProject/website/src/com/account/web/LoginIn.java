//LoginIn
package com.account.web;

import com.account.model.AccountDB;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginIn extends HttpServlet{
    AccountDB accountDB = new AccountDB();
    public void doPost(HttpServletRequest request,HttpServletResponse response)
                throws IOException,ServletException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String goal = request.getParameter("login");
        if ("Login In".equals(goal)){
            if(accountDB.check(userName,password)) {
                request.setAttribute("result","<b>Got the account message!" + userName + "|" + password);
            }else{
                request.setAttribute("result","Worry userName or password!");
            }
        }else if ("Login Up".equals(goal)){
            try {
                accountDB.add(userName, password);
                request.setAttribute("result","Loginned Up");
            }catch (ArrayIndexOutOfBoundsException ex){
                request.setAttribute("result","Account have been USED!");
            }
        }
        RequestDispatcher out = request.getRequestDispatcher("result.jsp");
        out.forward(request,response);
    }
}