class Solution {
    public int removeDuplicates(int[] nums) {
        // Edge case: Empty array
        if (nums.length == 0) return 0;
        
        // Start 'p' at 1. Index 0 is always unique (it's the first one).
        int p = 1;
        
        // Start 'i' at 1, comparing backwards
        for (int i = 1; i < nums.length; i++) {
            // If current number is different from the previous one...
            if (nums[i] != nums[i - 1]) {
                nums[p] = nums[i]; // Write it to the 'clean' spot
                p++;               // Move the writer forward
            }
        }
        
        return p;
    }
}

