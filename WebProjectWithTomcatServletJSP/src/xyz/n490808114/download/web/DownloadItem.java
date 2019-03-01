package xyz.n490808114.download.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.ExportException;

public class DownloadItem extends HttpServlet {
    public void doGet(HttpServletRequest  request, HttpServletResponse response){
        String item = request.getParameter("item");
        response.setContentType("application/pdf");
        ServletContext context = getServletContext();
        InputStream inputStream = context.getResourceAsStream("/"+item+".pdf");
        try {
            OutputStream outputStream = response.getOutputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,read);
            }
            outputStream.flush();
            outputStream.close();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
