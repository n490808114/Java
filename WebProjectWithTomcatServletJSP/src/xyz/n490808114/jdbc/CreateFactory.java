package xyz.n490808114.jdbc;

import java.util.LinkedList;
import java.util.concurrent.*;

class CreateFactory {
    private static ConcurrentSkipListSet<Integer> serialNum = new ConcurrentSkipListSet<>();
    private static ConcurrentHashMap<Integer,ConcurrentHashMap<Integer,LinkedList<String[]>>> objectSerialNum = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer,Integer> integerSerialNum = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer,Boolean> booleanSerialNum = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Integer,LinkedList<String[]>> orders = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer,LinkedList<String[]>> passagers = new ConcurrentHashMap<>();
    private CreateFactory(){}

    static Order getOrder(int orderId){
        int i = getSerialNum();
        objectSerialNum.put(i,orders);
        ConnectionPool.executeQuery("SELECT * FROM orders WHERE order_id = " + orderId,i);
        LinkedList<String[]> list = orders.remove(i);
        if (list == null){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
            list = orders.remove(i);
        }
        if(list == null){
            return null;
        }else{
            return new Order(list.getFirst());
        }
    }
    static Passager getPassager(String passagerName){
        int i = getSerialNum();
        objectSerialNum.put(i,passagers);
        ConnectionPool.executeQuery("SELECT * FROM passagers WHERE fullname = '" + passagerName+"'",i);
        LinkedList<String[]> list = passagers.remove(i);
        if (list == null){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){ex.printStackTrace();}
            list = passagers.remove(i);
        }
        if(list == null){
            return null;
        }else{
            return new Passager(list.getFirst());
        }
    }
    private static int getSerialNum(){
        int i = (int) (Math.random()*100000000);
        synchronized (serialNum){
            boolean aBoolean = serialNum.add(i);
            while (!aBoolean){
                i = (int) (Math.random()*100000000);
                aBoolean = serialNum.add(i);
            }
        }
        return i;
    }

    static void onResult(int serialNum,LinkedList<String[]> list){
        ConcurrentHashMap<Integer,LinkedList<String[]>> map = objectSerialNum.remove(serialNum);
        map.put(serialNum,list);
    }
    static void onResult(int serialNum,Boolean aBoolean){
        booleanSerialNum.put(serialNum,aBoolean);

    }
    static void onResult(int serialNum,int i){
        integerSerialNum.put(serialNum,i);
    }
}
