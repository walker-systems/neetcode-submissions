/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        // Initialize both pointers at the head
        ListNode slow = head;
        ListNode fast = head;

        // Iterate as long as Fast has a valid next step.
        // fast != null checks if we ran off the edge (Even length case)
        // fast.next != null checks if we are at the last node (Odd length case)
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move 1 step
            fast = fast.next.next;  // Move 2 steps
        }

        // When fast reaches the end, slow is at the middle.
        return slow;
    }
}