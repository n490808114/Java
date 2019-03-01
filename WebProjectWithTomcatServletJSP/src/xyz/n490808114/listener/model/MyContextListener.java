package xyz.n490808114.listener.model;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import xyz.n490808114.account.model.AccountDB;

public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String name = sce.getServletContext().getInitParameter("DogName");
        String dogSize = sce.getServletContext().getInitParameter("DogSize");
        sce.getServletContext().setAttribute("dog",new Dog(name,Integer.parseInt(dogSize)));
        AccountDB accountDB = new AccountDB();
        sce.getServletContext().setAttribute("accountDB",accountDB);
        sce.getServletContext().setInitParameter("1","1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AccountDB accountDB =(AccountDB) sce.getServletContext().getAttribute("accountDB");
        accountDB.close();
    }
}
