import java.util.ArrayList;
import java.util.LinkedList;
class Solution {
    public static void main(String[] args){

    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode x = result;
        while(true){
            if((l2==null)||(l1.val >= l2.val)){
                x.val = l1.val;
                l1 = l1.next;
            }else if((l1==null)||(l2.val > l1.val)){
                x.val = l2.val;
                l2 = l2.next;
            }
            x.next = new ListNode(0);
            x = x.next;
        }
    }


}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}