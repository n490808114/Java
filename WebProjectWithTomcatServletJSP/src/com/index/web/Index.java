package com.index.web;

import javax.servlet.http.*;

public class Index extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        int isLoginIn =(Integer) session.getAttribute("isLoginIn");
        if(isLoginIn == 1){
            request.setAttribute("userName","n490808114");
        }else{
            request.setAttribute("userName",0);
        }

    }
}
