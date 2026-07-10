class HashTable {
    private static class Node {
        int key, value; 
        Node next;
        Node (int key, int value) {this.key = key; this.value = value; }
    }

    private Node[] buckets; 
    private int size; 
    private int capacity; 

    public HashTable(int capacity) {
        this.capacity = capacity; 
        this.buckets = new Node[capacity]; 
        this.size = 0; 
    }

    private int hash(int key) { return key % capacity; }


    public void insert(int key, int value) {
        int index = hash(key); 
        Node node = buckets[index];
        while (node != null) {
            if (node.key == key) {node.value = value; return; }
            node = node.next;
        }

        Node newNode = new Node(key, value); 
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        if ((double) size / capacity >= 0.5) resize();
    }

    public int get(int key) {
        int index = hash(key); 
        Node node = buckets[index];
        while (node != null) {
            if (node.key == key) return node.value;
            node = node.next;
        }
        return -1;
    }

    public boolean remove(int key) {
        int index = hash(key); 
        Node node = buckets[index], prev = null;
        while (node != null) {
            if (node.key == key) {
                if (prev == null) buckets[index] = node.next;
                else prev.next = node.next;
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    public int getSize() { return size; }

    public int getCapacity() { return capacity; }

    public void resize() {
        Node[] oldBuckets = this.buckets;
        this.capacity *= 2; 
        this.buckets = new Node[this.capacity];
        this.size = 0; 
        for (Node head : oldBuckets) {
            while (head != null) {
                insert(head.key, head.value);
                head = head.next;
            }
        }
        
    }
}
