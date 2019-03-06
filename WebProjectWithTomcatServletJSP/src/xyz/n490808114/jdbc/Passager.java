package xyz.n490808114.jdbc;

public class Passager {
    String name;
    int orderId;
    String getName(){
        return name;
    }
    Passager(String[] list){
        this.name = list[0];
        this.orderId = Integer.parseInt(list[1]);
        System.out.println("获取Passager成功");
    }
    public int getOrderId(){
        return orderId;
    }
}
