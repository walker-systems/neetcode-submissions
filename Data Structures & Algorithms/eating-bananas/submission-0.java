class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 1. Define Range: Speed is between 1 and Max Pile
        int left = 1;
        int right = 1; // Start small, find the real max
        for (int p : piles) {
            right = Math.max(right, p);
        }

        int res = right; // Store the best answer found so far

        while (left <= right) {
            int k = left + (right - left) / 2; // k is the speed

            // 2. The Verification Step (The For Loop)
            // We MUST simulate eating every single pile to get the true hours
            long hoursSpent = 0; // Use long to prevent overflow
            for (int p : piles) {
                // Math trick: (a + b - 1) / b is effectively Math.ceil(a/b)
                // This calculates hours for THIS specific pile
                hoursSpent += (p + k - 1) / k; 
            }

            // 3. Binary Search Decisions
            if (hoursSpent <= h) {
                // We did it in time! But can we go SLOWER?
                res = k;      // Save this valid speed
                right = k - 1; // Try smaller speed (Left half)
            } else {
                // Too slow! We ran out of time. Need FASTER speed.
                left = k + 1; // Try larger speed (Right half)
            }
        }
        
        return res;
    }
}
