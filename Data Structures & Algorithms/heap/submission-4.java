class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(0); // Dummy node for 1-based indexing
    }

    public void push(int val) {
        heap.add(val);
        bubbleUp(heap.size() - 1);
    }

    public int pop() {
        if (heap.size() <= 1) return -1; // Empty
        
        int root = heap.get(1);
        int lastVal = heap.remove(heap.size() - 1); // Remove from end
        
        // If the heap is not empty after removing the last element...
        if (heap.size() > 1) {
            heap.set(1, lastVal); // Move the last leaf to the root
            bubbleDown(1);        // Fix the order
        }
        
        return root;
    }

    public int top() {
        if (heap.size() <= 1) return -1;
        return heap.get(1);
    }

    // CHANGED: Accepted type is now List<Integer> to match the hidden driver
    public void heapify(List<Integer> nums) {
        heap = new ArrayList<>();
        heap.add(0);
        for (int num : nums) heap.add(num);

        // Start from the LAST PARENT node and bubble down.
        for (int i = (heap.size() - 1) / 2; i >= 1; i--) {
            bubbleDown(i);
        }
    }

    private void bubbleUp(int i) {
        while (i > 1 && heap.get(i) < heap.get(i / 2)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void bubbleDown(int i) {
        int n = heap.size() - 1;
        
        while (2 * i <= n) { 
            int left = 2 * i;
            int right = 2 * i + 1;
            int smallest = i;

            if (left <= n && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right <= n && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == i) break;

            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}