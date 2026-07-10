

class Solution {
    public int longestConsecutive(int[] nums) {
        // Handle empty array edgecase
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 1. Add all numbers to a HashSet
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int longestStreak = 0;
        
        // 2. Iterate through the set
        for (int num : set) {
            // 3. Only begin counting if this is the start of a sequence!
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // 4. Count upwards until the sequence breaks
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                // 5. Update our maximum
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
}


