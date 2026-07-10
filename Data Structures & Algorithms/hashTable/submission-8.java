class HashTable {

    private static class Entry {
        int key; 
        int value; 
        Entry(int key, int value) {
            this.key = key; 
            this.value = value; 
        }    }

    private Entry[] map;
    private int size; 
    private int capacity; 

    private final Entry TOMBSTONE = new Entry(-1, -1);

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.map = new Entry[capacity];
        this.size = 0; 
    }

    private int hash(int key) {
        return Math.abs(key) % capacity; 
    }

    public void insert(int key, int value) {
        int index = hash(key); 

        int firstTombstoneIdx = -1;

        while (true) {
            Entry current = map[index];

            if (current == null) {
                if (firstTombstoneIdx != -1) {
                    map[firstTombstoneIdx] = new Entry(key, value);
                } else {
                    map[index] = new Entry(key, value); 
                }
                size++;
                if (size >= (double) capacity / 2) {
                    resize();
                }
                return;
            }

            else if (current != TOMBSTONE && current.key == key) {
                current.value = value;
                return;
            }

            else if (current == TOMBSTONE) {
                if (firstTombstoneIdx == -1) {
                    firstTombstoneIdx = index; 
                }
            }

            index = (index + 1) % capacity; 
        }
    }

    public int get(int key) {
        int index = hash(key); 
        int startIdx = index; 

        while (map[index] != null) {
            if (map[index] != TOMBSTONE && map[index].key == key) {
                return map[index].value;
            }

            index = (index + 1) % capacity;

            if (index == startIdx) break;
        }
        return -1;
    }

    public boolean remove(int key) {
        int index = hash(key); 

        while (map[index] != null) {
            if (map[index] != TOMBSTONE && map[index].key == key) {
                map[index] = TOMBSTONE;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void resize() {
        Entry[] oldMap = this.map;
        int oldCapacity = this.capacity;
        this.capacity *= 2;
        this.map = new Entry[this.capacity]; 
        this.size = 0; 

        for (Entry e : oldMap) {
            if (e != null && e != TOMBSTONE) {
                insert(e.key, e.value); 
            }
        }
    }
}