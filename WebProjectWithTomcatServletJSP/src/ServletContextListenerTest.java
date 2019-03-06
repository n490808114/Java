import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import xyz.n490808114.jdbc.ConnectionPool;


public class ServletContextListenerTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event){
        ConnectionPool.init();
    }
    @Override
    public void contextDestroyed(ServletContextEvent event){
        ConnectionPool.destory();
    }
}
