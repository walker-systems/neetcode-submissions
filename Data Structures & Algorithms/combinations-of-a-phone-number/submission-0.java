class Solution {

    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>(); 
        
        if (digits == null || digits.isEmpty()) {
            return result; 
        }

        backtrack(result, new StringBuilder(), digits, 0); 
        return result; 
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        
        if (index == digits.length()) {
            result.add(current.toString()); 
            return;
        }

        int digit = digits.charAt(index) - '0'; 
        String letters = KEYPAD[digit]; 

        for (char letter : letters.toCharArray()) {
            current.append(letter); 
            backtrack(result, current, digits, index + 1); 
            current.deleteCharAt(current.length() - 1); 
        }
    }
}
