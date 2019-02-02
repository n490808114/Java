class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){return "";}
        String result = strs[0];
        for(int x=0;x<strs.length;x++){
            String a = "";
            for(int y=0;y<result.length();y++){
                try{
                    if (result.substring(y,y+1).equals(strs[x].substring(y,y+1))){
                        continue;
                    }else{
                        result = result.substring(0,y);
                    }}catch(Exception ex){}
            }
        }
        return result;
    }
    public static void main(String[] args){
        Solution solution = new Solution();
        String[] arga = {"aaa","aa","a"};
        System.out.println(solution.longestCommonPrefix(arga));
    }
}