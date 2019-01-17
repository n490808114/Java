class Solution {
    public int romanToInt(String s) {
        int result = 0;
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        for (int i=0;i<s.length();i++){
            if((i >= 1) && (map.get(s.substring(i,i+1)) > map.get(s.substring(i-1,i)))){
                result = result + map.get(s.substring(i,i+1)) - 2 * map.get(s.substring(i-1,i));
            }else{
                result = result + map.get(s.substring(i,i+1));
            }
        }
        return result;

    }
    public static void main(String[] args){
        Solution solution = new Solution();
        int a = 1345431;
        System.out.println(solution.reverse(a));
    }
}