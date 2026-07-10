class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false; 
        }

        Map<Character, Integer> mapS = new HashMap<>(); 
        Map<Character, Integer> mapT = new HashMap<>(); 

        for (char c : s.toCharArray()) {
            mapS.merge(c, 1, Integer::sum); 
        }
        for (char c : t.toCharArray()) {
            mapT.merge(c, 1, Integer::sum);
        }
        for (Character key : mapS.keySet()) {
            if (!mapS.getOrDefault(key, 0).equals(mapT.getOrDefault(key, 0))) {
                return false;
            }
        }
        return true;
    }
}
