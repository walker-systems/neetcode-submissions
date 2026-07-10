public class DLLNode {
    DLLNode prev;
    DLLNode next;
    int val; 

    public DLLNode(int val) {
        this.val = val; 
        this.prev = null;
        this.next = null; 
    }
}

class Deque {

    DLLNode head;
    DLLNode tail; 

    public Deque() {
        head = new DLLNode(-1);
        tail = new DLLNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    public boolean isEmpty() {
        return (tail.prev == head && head.next == tail);
    }

    public void append(int value) {
       DLLNode newNode = new DLLNode(value); 
       newNode.next = tail;
       newNode.prev = tail.prev;
       tail.prev.next = newNode;
       tail.prev = newNode;
    }

    public void appendleft(int value) {
        DLLNode newNode = new DLLNode(value);
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
    }

    public int pop() {
        DLLNode temp = tail.prev;
        if (temp.val != -1) {
            tail.prev.prev.next = tail; 
            tail.prev = tail.prev.prev;
        } 
        return temp.val;
    }

    public int popleft() {
        DLLNode temp = head.next;
        if (temp.val != -1) {
            head.next.next.prev = head;
            head.next = head.next.next;

        }
        return temp.val;
    }
}
