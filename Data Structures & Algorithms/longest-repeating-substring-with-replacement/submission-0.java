class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // Frequency map for A-Z
        int left = 0;
        int maxCount = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // 1. Add right character to window
            // Subtract 'A' to map ASCII to 0-25
            int index = s.charAt(right) - 'A';
            count[index]++;
            
            // 2. Track the count of the most frequent character seen in the CURRENT window
            maxCount = Math.max(maxCount, count[index]);
            
            // 3. Check Validity
            // Formula: (Window Size) - (Dominant Char Count) = Number of Replacements needed
            // If replacements needed > k, the window is invalid.
            if ((right - left + 1) - maxCount > k) {
                // Shrink from the left
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            // 4. Update Result
            // Note: If we just shrank the window, the size (right - left + 1) stayed the same.
            // If we didn't shrink, the size grew by 1.
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}