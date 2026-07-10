class MedianFinder {

    private final PriorityQueue<Integer> lowerHalf; 
    private final PriorityQueue<Integer> upperHalf; 

    public MedianFinder() {
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder()); 
        upperHalf = new PriorityQueue<>(); 
    }
    
    public void addNum(int num) {
        lowerHalf.offer(num); 
        upperHalf.offer(lowerHalf.poll()); 
        if (lowerHalf.size() < upperHalf.size()) {
            lowerHalf.offer(upperHalf.poll()); 
        }
    }
    
    public double findMedian() {
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek(); 
        }

        return (lowerHalf.peek() + upperHalf.peek()) / 2.0; 
    }
}
