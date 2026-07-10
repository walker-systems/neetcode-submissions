

class HashTable {
    // 1. Internal Container
    // Simple key-value pair. Static because it doesn't need access to outer class members.
    private static class Entry {
        int key;
        int value;
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] map;
    private int size;
    private int capacity;

    // 2. The Tombstone
    // Crucial for Open Addressing. When we delete a node, we can't set it to null,
    // otherwise a search for a key located *after* this slot would stop prematurely.
    // We mark it as "Deleted" (TOMBSTONE) instead.
    private final Entry TOMBSTONE = new Entry(-1, -1);

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.map = new Entry[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void insert(int key, int value) {
        int index = hash(key);
        
        // Optimization: If we find a Tombstone while searching, remember its index.
        // If we don't find the key later on, we can overwrite this Tombstone
        // instead of using a fresh 'null' slot.
        int firstTombstoneIdx = -1;

        while (true) {
            Entry current = map[index];

            // Case A: Found an Empty Slot (Key doesn't exist)
            if (current == null) {
                // If we saw a deleted slot earlier, use that one to recycle space.
                if (firstTombstoneIdx != -1) {
                    map[firstTombstoneIdx] = new Entry(key, value);
                } else {
                    map[index] = new Entry(key, value);
                }
                
                size++;

                // CRITICAL FIX: Check resize AFTER adding.
                // "size * 2 >= capacity" is the integer-safe way to write "size / capacity >= 0.5"
                if (size * 2 >= capacity) {
                    resize();
                }
                return;
            }

            // Case B: Found the Key (Update value)
            else if (current != TOMBSTONE && current.key == key) {
                current.value = value;
                return;
            }

            // Case C: Found a Tombstone
            // Do NOT stop. The key we are looking for might be further down the chain.
            // Just remember this spot for potential reuse.
            else if (current == TOMBSTONE) {
                if (firstTombstoneIdx == -1) {
                    firstTombstoneIdx = index;
                }
            }

            // Linear Probing: Move to the next slot, wrap around if needed
            index = (index + 1) % capacity;
        }
    }

    public int get(int key) {
        int index = hash(key);
        int startIdx = index;

        // Stop if we hit 'null'. If we hit 'TOMBSTONE', keep going.
        while (map[index] != null) {
            if (map[index] != TOMBSTONE && map[index].key == key) {
                return map[index].value;
            }
            
            index = (index + 1) % capacity;
            
            // Safety: If we looped all the way back to start, the key isn't here.
            // (Should be impossible with 0.5 load factor, but good for robustness)
            if (index == startIdx) break; 
        }
        return -1;
    }

    public boolean remove(int key) {
        int index = hash(key);
        int startIdx = index;

        while (map[index] != null) {
            // If we find it, mark it as dead (TOMBSTONE)
            if (map[index] != TOMBSTONE && map[index].key == key) {
                map[index] = TOMBSTONE;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
            if (index == startIdx) break;
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
        this.capacity *= 2;
        this.map = new Entry[this.capacity];
        this.size = 0; // Reset size, because insert() increments it

        for (Entry e : oldMap) {
            // Re-hash only valid entries.
            // Leave the Tombstones behind (garbage collection).
            if (e != null && e != TOMBSTONE) {
                insert(e.key, e.value);
            }
        }
    }
}