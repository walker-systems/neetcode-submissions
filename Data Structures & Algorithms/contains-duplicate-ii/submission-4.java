class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Use a HashSet to act as our "Window of size k"
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // 1. Sliding Window Management
            // If we have moved past 'k' elements, discard the one that fell out of the window.
            // The element at 'i - k - 1' is now too far away (distance > k).
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            
            // 2. Check & Add
            // set.add() returns 'false' if the element was ALREADY in the set.
            // If it returns false, it means we found a duplicate within the active window.
            if (!window.add(nums[i])) {
                return true;
            }
        }
        
        return false;
    }
}