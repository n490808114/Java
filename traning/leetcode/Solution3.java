import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution3 {
    public static void main(String[] args){
        System.out.println(new Solution3().lengthOfLongestSubstring("pwwkew"));
    }
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        Queue<Character> queue = new LinkedList<>();
        for(int i=0;i<s.length();i++){

            char a = s.charAt(i);
            if(queue.contains(a)){
                result = Math.max(result,queue.size());
                try {
                    while (!queue.peek().equals(a)) {
                        queue.poll();
                    }
                }catch (NullPointerException ex){ex.printStackTrace();}
                queue.poll();
            }
            queue.offer(a);
            System.out.println(queue);

        }
        result = Math.max(result,queue.size());
        return result;
    }
}
