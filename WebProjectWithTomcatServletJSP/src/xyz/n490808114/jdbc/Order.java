package xyz.n490808114.jdbc;

public class Order {
    private int orderId;
    private String entryDate;
    private String passagersName;
    private int passagersNum;
    private String depCity;
    private String arrCity;
    private String flightDate;
    private int soldPrice;
    private int clearPrice;
    private int profit;
    private int ticketNo;
    Order(String[] list){

        try {
            orderId = Integer.parseInt(list[0]);
            entryDate = list[1];
            passagersName = list[2];
            passagersNum = Integer.parseInt(list[3]);
            depCity = list[4];
            arrCity = list[5];
            flightDate = list[6];
            soldPrice = Integer.parseInt(list[7]);
            clearPrice = Integer.parseInt(list[8]);
            profit = Integer.parseInt(list[9]);
            ticketNo = Integer.parseInt(list[10]);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public String toString(){
        return ""
                +" | "+this.orderId
                +" | "+this.entryDate
                +" | "+this.passagersName
                +" | "+this.passagersNum
                +" | "+this.depCity
                +" | "+this.arrCity
                +" | "+this.flightDate
                +" | "+this.soldPrice
                +" | "+this.clearPrice
                +" | "+this.profit
                +" | "+this.ticketNo;
    }
}
