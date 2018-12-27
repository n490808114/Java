class Clock{
    private String time;

    void setTime(String t){
        time = t;
    }
    String getTime(){
        return time;
    }
}
class ClockTestDrive{
    public static void main(String[] args) {
        Clock c = new Clock();

        c.setTime("12345");
        String tod = c.getTime();
        System.out.println("time: "+tod);
    }
}

/* 
in-va = instance variable 实例变量
argument
*/