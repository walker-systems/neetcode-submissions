class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(); 
  
        for (String s : strs) {
            int[] counts = new int[26]; 
            StringBuilder sb = new StringBuilder(); 
            for (char c : s.toCharArray()) {
                counts[c - 'a']++; 
            }
            for (int i = 0; i < 26; i++) {
                sb.append('#').append(counts[i]);
            }
            String key = sb.toString(); 
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s); 
        }

        return new ArrayList<>(map.values()); 
    }
}
