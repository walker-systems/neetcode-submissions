class HashTable {
    
    // Internal Node class (Singly Linked List)
    private static class Node {
        int key, value;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;
    private int size;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return key % capacity;
    }

    // 1. Put - O(1) average, O(N) worst case (if all keys collide)
    public void insert(int key, int value) {
        int index = hash(key);
        Node node = buckets[index];

        // Search the list to see if key exists (Update)
        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        // Key not found? Create new node and insert at HEAD (fastest)
        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;

        // Resize if load factor exceeds 0.5 (as per your requirements)
        if ((double) size / capacity >= 0.5) {
            resize();
        }
    }

    // 2. Get - O(1) average
    public int get(int key) {
        int index = hash(key);
        Node node = buckets[index];

        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1; // Not found
    }

    // 3. Remove - O(1) average
    public boolean remove(int key) {
        int index = hash(key);
        Node node = buckets[index];
        Node prev = null;

        while (node != null) {
            if (node.key == key) {
                // Found it. Unlink it.
                if (prev == null) {
                    // It was the head
                    buckets[index] = node.next;
                } else {
                    // It was in the middle/end
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    // 4. Resize - O(N)
    public void resize() {
        Node[] oldBuckets = this.buckets;
        this.capacity *= 2;
        this.buckets = new Node[this.capacity];
        this.size = 0; // Reset size and re-insert

        for (Node head : oldBuckets) {
            while (head != null) {
                insert(head.key, head.value);
                head = head.next;
            }
        }
    }
}