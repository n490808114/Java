//2-1
import RandomData;
public class Insertion{
    public static void main(String[] args) {
        int[] as = RandomData.getList(1000000,0,1000000);
        for(int a:as){
            System.out.print(a+" ");
        }
    }
}