class Solution {
    public int lastStoneWeight(int[] stones) {
        List<Integer> stoneList = new ArrayList<>(); 

        for (int stone : stones) {
            stoneList.add(stone);
        }

        while (stoneList.size() > 1) {
            Collections.sort(stoneList);
            int n = stoneList.size();
            int heaviest1 = stoneList.get(n - 1);
            int heaviest2 = stoneList.get(n - 2);

            stoneList.remove(n - 1);
            stoneList.remove(n - 2);

            if (heaviest1 != heaviest2) {
                stoneList.add(Math.abs(heaviest1 - heaviest2));
            }
        }

        return stoneList.isEmpty() ? 0 : stoneList.get(0);
    }
}
