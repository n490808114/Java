import java.util.ArrayList;
import java.util.Iterator;

public class Solution904{
    public static void main(String[] args) {

    }
    public int totalFruit(int[] tree) {
        ArrayList<Counter> counters = new ArrayList<>();
        for(int i=0;i<tree.length;i++){
            counters.add(new Counter());
            Iterator it = counters.iterator();
            while(it.hasNext()){
                ((Counter)it.next()).count(tree[i]);
            }
        }
        int result = 0;
        for(int i=0;i<counters.size();i++){
            int count = counters.get(i).count;
            if(count > result){result = count;}
        }
        return 1;
    }
    class Counter implements Runnable{
        int a = -1;
        int b = -1;
        int count;
        Boolean isStop = false;
        public void count(int num){
            if(!isStop){
                if(a == num || b == num){
                    count += 1;
                }
                if(a != num && b != num){
                    if(a == -1){
                        a = num;
                    }else if(b == -1){
                        b = num;
                    }else{
                        isStop = true;
                    }
                }
            }

        }
    }
}