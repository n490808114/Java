import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Solution473 {
    public static void main(String[] args){
        int[] test = {3,3,3,3,4};
        System.out.println(new Solution473().makesquare(test));
    }
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for(int a:nums){
            sum = sum +a;
        }
        if(sum/4*4!=sum){
            return false;
        }
        int countOverHalf =0;
        int countHarf = 0;
        int[] list = {0,0,0,0};
        for(int a:nums){
            if(a> sum/8){
                countOverHalf += 1;
                for(int i=0;i<4;i++){
                    if(list[i]==0){
                        list[i] = a;

                    }
                }
            }else if(a == sum/8){
                countHarf += 1;
            }
        }
        if((countOverHalf + countHarf)>4){
            return false;
        }


        return true;
    }
}
