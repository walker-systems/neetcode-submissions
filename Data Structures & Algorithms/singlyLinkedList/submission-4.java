class LinkedList {

    ListNode head;
    ListNode tail; 

    class ListNode {
        int val; 
        ListNode next; 

        public ListNode (int val) {
            this.val = val;
            this.next = null;
        }
    }

    public LinkedList() {
        head = new ListNode(-1);
        tail = head;
    }

    public int get(int index) {
        ListNode curr = head;
        if (curr.next == null) {
            return -1;
        }
        int i = -1;
        while (i < index - 1 && curr.next != null) {
            if (index == 0) {
                return curr.next.val;
            } 
            else {
                curr = curr.next;
                i++;
            }
        }
        if (curr.next == null && index > i) {
            return -1;
        }
        return curr.next.val;
    }

    public void insertHead(int val) {
        if (tail == head) {
            head.next = new ListNode(val);
            tail = tail.next;                                                                                                                                                                                                                                       
        } else {
            ListNode temp = head.next;
            head.next = new ListNode(val);
            head.next.next = temp; 
            if (temp.next == null) {
                tail = temp;
            }
        }
    }

    public void insertTail(int val) {
        tail.next = new ListNode(val); 
        tail = tail.next;
    }

    public boolean remove(int index) {
        ListNode curr = head;
        int i = -1; 
        // array is empty
        if (curr.next == null) {
            return false;
        } 
        // array has one element
        else if (curr.next.next == null) {
            if (index == 0) {
                head.next = head.next.next;
                return true;
            } else {
                return false;
            }
        }
        else {
            while (curr.next != null && i < index - 1) {
                curr = curr.next;
                i++;
            }
            if (curr.next.next != null) {
                curr.next = curr.next.next;
                return true;
            } else {
                curr.next = null;
                tail = curr;
                return true;
            }
        }
        
        
    }

    public ArrayList<Integer> getValues() {
        ListNode curr = head; 
        ArrayList<Integer> ans = new ArrayList<>();
        while (curr.next != null) {
            curr = curr.next;
            ans.add(curr.val);
        } 
        return ans;
    }
}
