import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class ServletContextAttributeListenerTest implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event){
        System.out.println("added: "+event.getName()+"|"+event.getValue());
    }
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event){
        System.out.println("removed: "+event.getName()+"|"+event.getValue());
    }
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event){
        System.out.println("replaced: "+event.getName()+"|"+event.getValue());
    }
}
