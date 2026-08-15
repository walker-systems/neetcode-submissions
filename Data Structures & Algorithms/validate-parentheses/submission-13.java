class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>(); 
        map.put(')', '('); 
        map.put(']', '['); 
        map.put('}', '{'); 

        Deque<Character> stackOpens = new ArrayDeque<>(); 

        for (Character c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                stackOpens.push(c); 
            } else if (!stackOpens.isEmpty()) {
                if (stackOpens.pop() != map.get(c)) {
                    return false; 
                }
            } else {
                return false;
            }
        }
        if (!stackOpens.isEmpty()) {
            return false; 
        }
        return true;
    }
}
