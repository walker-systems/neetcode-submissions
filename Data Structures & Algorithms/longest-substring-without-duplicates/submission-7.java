class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];

        Arrays.fill(lastSeen, -1);

        int left = 0; 
        int maxLen = 0; 

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            if (lastSeen[current] != -1) {
                left = Math.max(left, lastSeen[current] + 1);
            }

            lastSeen[current] = right; 
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
