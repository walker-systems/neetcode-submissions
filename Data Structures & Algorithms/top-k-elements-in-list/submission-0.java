class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); 
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); 
        List<Integer> res = new ArrayList<>(); 

        for (int num : nums) {
            map.merge(num, 1, Integer::sum); 
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int n = e.getKey(); 
            int f = e.getValue(); 
            int[] numFreq = new int[]{n, f}; 
            pq.offer(numFreq); 
            if (pq.size() > k) {
                pq.poll(); 
            }
        }

        for (int[] nf : pq) {
            res.add(nf[0]); 
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i); 
        }

        return result; 
    }
}
