package xyz.n490808114.test;

import xyz.n490808114.jdbc.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ListenerTesterTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Passager z = PassagerManager.getPassager("ZHUXIAOHONG");
        Order order = OrderManager.getOrder(z.getOrderId());
        try{
            OutputStream outputStream = response.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new PrintWriter(outputStream));
            writer.write(order.toString());
            writer.close();
        }catch (IOException ex){ex.printStackTrace();}
        
    }
}
