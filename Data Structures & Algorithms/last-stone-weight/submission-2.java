class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int heaviest1 = maxHeap.poll();
            int heaviest2 = maxHeap.poll();

            if (heaviest1 != heaviest2) {
                heaviest1 -= heaviest2;
                maxHeap.offer(heaviest1);
            } else {
                continue;
            }
        }
        return (maxHeap.size() >= 1) ? maxHeap.poll() : 0;
    }
}
