import java.util.Arrays;
import java.util.Stack;

public class Solution4 {
    public static void main(String[] args){
        int[] a = {1,2};
        int[] b = {3,4};
        System.out.println(new Solution4().findMedianSortedArrays(a,b));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] test = {};
        if (Arrays.equals(nums1,test)){
            return emptyTest(nums2);
        }
        if(Arrays.equals(nums2,test)){
            return emptyTest(nums1);
        }
        int a = nums1.length+nums2.length;
        int[] list = new int[a];
        int b;
        if(a%2==0){
            b = a/2+1;
        }else{
            b = (a+1)/2;
        }
        int mark1 = 0;
        int mark2 = 0;
        Stack stack = new Stack();
        for(int i=0;i<b;i++){
            if(nums1[mark1]>=nums2[mark2]){

                if(mark2==nums2.length-1){
                    stack.push(nums1[mark1]);
                    mark1 += 1;
                }else{
                    stack.push(nums2[mark2]);
                    mark2 += 1;
                }

            }else{
                if(mark1==nums1.length-1){
                    stack.push(nums2[mark2]);
                    mark2 += 1;
                }else{
                    stack.push(nums1[mark1]);
                    mark1 += 1;
                }
            }
        }
        if(a%2==0){
            return (double) ((Integer)stack.pop()+(Integer)stack.pop())/2;

        }else{
            return (double)(Integer)stack.pop();
        }
    }
    public double emptyTest(int[] nums){
        int[] test = {};
        if(Arrays.equals(nums,test)){
            return 0.0;
        }
        if(nums.length %2 ==0){
            return (double) (nums[nums.length/2]+nums[nums.length/2-1])/2;
        }else{
            return (double)nums[(nums.length+1)/2];
        }
    }
}
