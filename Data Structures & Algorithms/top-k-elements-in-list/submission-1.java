class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k]; 
        Map<Integer, Integer> map = new HashMap<>(); 
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            (key1, key2) -> Integer.compare(map.get(key1), map.get(key2))
        );

        for (int n : nums) {
            map.merge(n, 1, Integer::sum); 
        }

        for (int key : map.keySet()) {
            queue.offer(key); 
            if (queue.size() > k) {
                queue.poll(); 
            }
        }

        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }

        return result; 
    }
}
