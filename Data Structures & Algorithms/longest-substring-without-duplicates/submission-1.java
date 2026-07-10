//import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 1. Use an integer array to track the LAST seen index of each char
        // ASCII has 128 standard characters.
        int[] lastSeen = new int[128];
        
        // Initialize with -1 to indicate "never seen before"
        Arrays.fill(lastSeen, -1);
        
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            
            // 2. If we have seen this character before...
            if (lastSeen[current] != -1) {
                // ...we move 'left' to skip the old duplicate.
                // KEY: We use Math.max to prevent 'left' from moving BACKWARDS.
                // (e.g., in "abba", when we see the second 'a', the old 'a' is at index 0.
                // But 'left' is already at 2. We shouldn't move left back to 1).
                left = Math.max(left, lastSeen[current] + 1);
            }
            
            // 3. Update the latest index of this character
            lastSeen[current] = right;
            
            // 4. Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}