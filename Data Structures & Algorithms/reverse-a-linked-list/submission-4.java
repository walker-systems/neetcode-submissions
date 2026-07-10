/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {

        if (Objects.isNull(head)) {
            return null;
        } else if (head.next == null) {
            return head;
        } else if (head.next.next == null){
            ListNode newHead = head.next;
            newHead.next = head;
            head.next = null;
            return newHead;
        }
        ListNode tail = head;
        ListNode curr = head; 
        
        boolean newTail = true; 

        head = head.next;

        ListNode temp = new ListNode(head.val, head.next);
        
        while (temp.next != null) {
            temp = new ListNode(head.val, head.next);
            head.next = curr;
            if (newTail) {
                curr = curr.next;
                tail.next = null;
                newTail = false;
            } else {
                curr = head;
            }
            if (temp.next != null) {
                head = temp.next;
            }
        }
        return head;
    }
}
