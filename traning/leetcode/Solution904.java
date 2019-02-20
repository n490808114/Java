import java.util.ArrayList;
import java.util.Iterator;

public class Solution904{
    public static void main(String[] args) {
        int[] a = {5,0,0,7,0,7,2,7};
        System.out.println(new Solution904().totalFruit(a));
    }
    public int totalFruit(int[] tree) {
        int a = -1;
        int b = -1;
        int result = 0;
        int countA = 0;
        int countB = 0;
        int pureCountA = 0;
        int pureCountB = 0;
        int cacheCount = 0;
        int cacheLastTree = -1;
        for(int i=0;i<tree.length;i++){
            int c = tree[i];
        System.out.println("A:"+a+"|"+countA+"|"+ pureCountA+
                            " B:"+b+"|"+countB+"|"+pureCountB+
                            "   result:"+result+
                            "   cacheCount:"+cacheCount+
                            "   tree("+c+
                            "   cacheLastTree:" + cacheLastTree +
                            ") step:"+i);

            if(c == a){
                countA += 1;

                if(cacheLastTree != a){
                    pureCountA = 1;
                }else{
                    pureCountA += 1;
                }
                pureCountB = 0;
            }else if(c == b){
                countB += 1;
                if(cacheLastTree != b){
                    pureCountB = 1;
                }else{
                    pureCountB += 1;
                }
                pureCountA = 0;
            }else{
                if (a == -1){
                    a = c;
                    countA = 1;
                    pureCountA = 1;
                    cacheLastTree = c;
                    continue;
                }
                if (b == -1) {
                    b = c;
                    countB = 1;
                    pureCountB = 1;
                    cacheLastTree = c;
                    continue;
                }
                if(tree[i-1] == a) {
                    b = c;
                    cacheCount = countA + countB;
                    countB = 1;
                    pureCountB = 1;

                    countA = pureCountA;
                    pureCountA = 0;
                }else{
                    a = c;
                    cacheCount = countA + countB;
                    countA = 1;
                    pureCountA = 1;
                    countB = pureCountB;
                    pureCountB = 0;
                }
            }
            if((countA + countB) > result){
                result = countA + countB;
            }
            if(cacheCount > result){
                result = cacheCount;
            }
            cacheLastTree = c;
        }
        if(result == 0){
        result = countA +countB;
        }
        return result;
    }

}