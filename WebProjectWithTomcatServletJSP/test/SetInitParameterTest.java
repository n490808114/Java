import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/SetInitParameterExample",
        initParams={
                @WebInitParam(name="middleName", value="Kumar")
        }
)
public class SetInitParameterTest extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletConfig config= getServletConfig();

        ServletContext context= getServletContext();
        context.setInitParameter("firstName", "Bipul");

        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

        String contextParam= context.getInitParameter("firstName");
        out.println("Context Parameter Value = "+contextParam);

        String configParam= config.getInitParameter("middleName");
        out.println("<br>Config Parameter Value = "+configParam);

        context.setInitParameter("lastName", "Choudhary");
        String contextParam1= context.getInitParameter("lastName");

        out.println("<br>Context Parameter Value = "+contextParam1);
        boolean bol= context.setInitParameter("lastName", "Kumar");

        if(bol!=true)
        {
            out.println("<br>you can not replace the value with same key");
        }
        else
        {
            out.println("Value is replaced.");
            out.println("And Replaced value is : "+context.getInitParameter("lastName"));
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}