class Solution {
    public boolean isPalindrome(String s) {
        
        String str = s.toLowerCase();
        int L = 0; 
        int R = str.length() - 1; 

        while (L < R) {
            while (!Character.isLetterOrDigit(str.charAt(L))) {
                if (L == str.length() - 1) {
                    return true;
                }
                L++;
            }
            while (!Character.isLetterOrDigit(str.charAt(R))) {
                if (R == 0) {
                    return true;
                }
                R--;
            }
            if (str.charAt(L) == str.charAt(R)) {
                L++;
                R--;
            } else {
                return false; 
            }
        }
        return true;
    }
}
