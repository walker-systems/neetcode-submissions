class KthLargest {

    PriorityQueue<Integer> heap; 
    int k;
    int[] nums;

    public KthLargest(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k; 
        this.nums = nums;

        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
       
        return heap.peek();
    }
}
