class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // WHILE the character is already in the set (Duplicate found!)
            // We must shrink the window from the left until the duplicate is removed.
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            // Now the window is clean. Add the new character.
            window.add(c);
            
            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}