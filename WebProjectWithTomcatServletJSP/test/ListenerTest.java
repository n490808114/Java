import java.util.EventObject;
import java.util.EventListener;
public class ListenerTest {
    public static void main(String[] args){
        Door1 door = new Door1();
        door.setStateListener(new DoorStateListener());
        door.setNameListener (new DoorNameListener());
        // 开门
        door.setState("open");
        System.out.println("我已经进来了");
        // 关门
        door.setState("close");
        // 改名
        door.setName("dengzy");
    }
}
class Door1 {
    private String state = "";
    private String name = "";
    private DoorStateListener stateListener;
    private DoorNameListener  nameListener;

    public void setState(String newValue) {
        if (!state.equals(newValue)) {
            state = newValue;
            if (stateListener != null){
                //注意参数传递
                Door1Event event = new Door1Event(this, "state",newValue);
                stateListener.doorEvent(event);
            }
        }
    }

    public void setName(String newValue) {
        if (!name.equals(newValue)) {
            name = newValue;
            if (nameListener != null){
                Door1Event event = new Door1Event(this,"name", newValue);
                nameListener.doorEvent(event);
            }
        }
    }

    public void setStateListener(DoorStateListener stateListener) {
        this.stateListener = stateListener;
    }

    public void setNameListener(DoorNameListener nameListener) {
        this.nameListener = nameListener;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}


class Door1Event extends EventObject {
    private static final long serialVersionUID = 6496098798146410884L;

    private final String key ;
    private final String value  ;

    public Door1Event(Object source,String key , String value) {
        super(source);
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
class DoorStateListener implements EventListener {
    public void doorEvent(Door1Event event) {
        if (event.getValue() != null && event.getValue().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}
class DoorNameListener implements EventListener {
    public void doorEvent(Door1Event event) {
        Door1 door = (Door1) event.getSource();
        System.out.println("I got a new name,named \"" + door.getName() + "\"");
    }
}