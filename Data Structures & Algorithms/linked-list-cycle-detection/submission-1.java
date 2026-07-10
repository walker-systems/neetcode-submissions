/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Initialize both at the start
        ListNode slow = head;
        ListNode fast = head;
        
        // Loop as long as the Fast runner has track ahead of him.
        // We check fast.next because we are about to jump 2 steps.
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move 1 step
            fast = fast.next.next;  // Move 2 steps
            
            // Collision check: Did the Fast runner lap the Slow runner?
            // Important: Compare the OBJECT references (==), not the values (.val)
            if (slow == fast) {
                return true;
            }
        }
        
        // If Fast reaches null, the road ends. No cycle.
        return false;
    }
}

