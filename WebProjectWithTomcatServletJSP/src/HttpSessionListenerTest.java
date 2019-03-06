import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionListenerTest implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event){
        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("sessionCount",(int)context.getAttribute("sessionCount")+1);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("sessionCount",(int)context.getAttribute("sessionCount")-1);
    }

}
