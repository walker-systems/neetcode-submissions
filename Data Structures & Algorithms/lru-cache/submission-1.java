class LRUCache {

    class Node {
        int key; 
        int value; 
        Node prev; 
        Node next;
        Node (int key, int value) { this.key = key; this.value = value; };
    }    

    private final int capacity;
    private final Map<Integer, Node> map; 
    private final Node head;
    private final Node tail; 

    public LRUCache(int capacity) {
        this.capacity = capacity; 
        this.map = new HashMap<>();

        this.head = new Node(0, 0); 
        this.tail = new Node(0, 0); 
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        removeNode(node);
        addNode(node);

        return node.value;
        
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; 
            removeNode(node);
            addNode(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                map.remove(lru.key);
                removeNode(lru);
            }
            Node newNode = new Node(key, value); 
            map.put(key, newNode); 
            addNode(newNode);
        }
        
    }

    private void addNode(Node node) {
        node.prev = head; 
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev; 
    }
}
