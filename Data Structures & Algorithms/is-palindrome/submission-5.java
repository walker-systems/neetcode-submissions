class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;

        int i = 0; 
        int j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            char leftChar = Character.toLowerCase(s.charAt(i)); 
            char rightChar = Character.toLowerCase(s.charAt(j));

            if (leftChar != rightChar) {
                return false;
            }

            i++;
            j--;
        }
        return true;

    }
}
