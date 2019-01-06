import java.time.Year;
import java.util.Calendar;
//import java.util.Date;

public class DateTestDrive{
    public static void main(String[] args) {
        Calendar  d= Calendar.getInstance();
        //String a = String.format("DateTest:No1,%ta,No2%<tb,,No3,%<td.", new Date());
        //System.out.println(a);

        d.set(1960,Calendar.OCTOBER,12,5,20);
        d.set(d.DAY_OF_MONTH,10);

        long day01 = d.getTimeInMillis();
        day01 += 1000*60*60;
        d.setTimeInMillis(day01);

        System.out.printf("%tc||%,d||%d",d,d.get(d.HOUR_OF_DAY),d.getTimeInMillis());

    }
}