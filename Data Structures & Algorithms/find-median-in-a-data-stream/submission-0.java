// import java.util.Collections;
// import java.util.PriorityQueue;

class MedianFinder {

    // Declared as final for safe publication and to prevent reassignment
    private final PriorityQueue<Integer> lowerHalf; // Max-Heap
    private final PriorityQueue<Integer> upperHalf; // Min-Heap

    /** initializes the MedianFinder object. */
    public MedianFinder() {
        // Collections.reverseOrder() efficiently creates a Max-Heap
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        
        // Default PriorityQueue in Java is a Min-Heap
        upperHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 1. Add to the lower half first
        lowerHalf.offer(num);
        
        // 2. Guarantee the invariant: the largest element in the lower half 
        // strictly belongs in the upper half.
        upperHalf.offer(lowerHalf.poll());
        
        // 3. Rebalance: Enforce that the lower half always holds the extra element 
        // if the total number of elements is odd.
        if (lowerHalf.size() < upperHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    public double findMedian() {
        // If the sizes are uneven, lowerHalf holds the median
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        } 
        
        // If sizes are strictly equal, average the two middle values
        // Note: Division by 2.0 forces floating-point arithmetic
        return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
}