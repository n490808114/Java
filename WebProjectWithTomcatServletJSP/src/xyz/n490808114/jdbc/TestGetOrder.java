package xyz.n490808114.jdbc;

public class TestGetOrder {
    public static void main(String[] args){
        Order order = OrderManager.getOrder(397459521);
        if(order == null){
            System.out.println("null");
        }else{
            System.out.println(order.toString());
        }

    }
}
