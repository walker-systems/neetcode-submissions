class Solution {
    public boolean isPalindrome(String s) {
        int i = 0; 
        int j = s.length() - 1; 
        while (i < j && i < s.length() && j >= 0) {
            while (!Character.isLetterOrDigit(Character.toLowerCase(s.charAt(i)))
                && i < j
                && i < s.length()) {
                i++; 
            }
            while (!Character.isLetterOrDigit(Character.toLowerCase(s.charAt(j)))
                && i < j
                && j >= 0) {
                j--; 
            }
            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } else {
                i++; 
                j--; 
            }

        }
        return true;
    }
}
