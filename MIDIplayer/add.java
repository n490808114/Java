public class add {
    public static void main(String[] args){
        int[] a = {2,5,7,9,11,15};
        int target = 9;
        System.out.println(twoSum(a,target));

    }
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int x=0;x<nums.length;x++){
            for(int y=0;y<nums.length;y++){
                if(y<=x){
                    break;
                }
                if((nums[x]+nums[y])==target){
                    result[0]= x;
                    result[1] = y;
                    break;
                }
            }

        }
        return result;
    }
}