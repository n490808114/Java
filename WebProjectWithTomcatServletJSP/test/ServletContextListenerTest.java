import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerTest  {
    public static void main(String[] args){

    }

}
class TestServletContext{

}
class TestContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String name = sce.getServletContext().getInitParameter("DogName");
        String dogSize = sce.getServletContext().getInitParameter("DogSize");
        int size = Integer.parseInt(dogSize);
        Dog dog = new Dog(name,size);
        sce.getServletContext().setAttribute("dog",dog);
        sce.getServletContext().setInitParameter("1","1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
class Dog{
    String name;
    int size;
    Dog(String name,int size){
        this.name = name;
        this.size = size;
    }
}

