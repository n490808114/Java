package xyz.n490808114.jdbc;

import java.util.concurrent.ConcurrentHashMap;

public class OrderManager {
    private static ConcurrentHashMap<Integer,Order> orders = new ConcurrentHashMap<>();
    private OrderManager(){}
    public static Order getOrder(int orderId)throws NullPointerException{
        Order order =orders.get(orderId);
        if(order!= null){
            return order;
        }else{
            order =  getOrderFromDB(orderId);
            if(order == null){
                throw new NullPointerException();
            }else{
                return order;
            }
        }
    }
    private static Order getOrderFromDB(int orderId){
        Order order = CreateFactory.getOrder(orderId);
        if(order == null){
            return null;
        }else{
            orders.put(orderId,order);
            return order;
        }
    }
}
