import java.util.*;
public class Compare{
    public static void main(String[] args) {
        ArrayList<Integer> a =new ArrayList<Integer>();
        a.add(1);
        a.add(3);
        a.add(5);
        a.add(3);
        a.add(6);
        a.sort(new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return a.compareTo(b);
            }
        });
        for (int i=0;i<a.size();i++){
            System.out.print(a.get(i));
        }
    }
}