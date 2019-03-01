package xyz.n490808114.listener.web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import xyz.n490808114.listener.model.Dog;
import xyz.n490808114.listener.model.MyContextListener;

public class ListenerTester extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        Dog dog = (Dog) getServletContext().getAttribute("dog");
        dog.setContextListener(new MyContextListener());
        out.print(dog.getName());
        out.flush();
        out.close();
    }
}
