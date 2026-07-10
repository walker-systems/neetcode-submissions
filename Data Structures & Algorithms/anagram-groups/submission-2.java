class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> countKeyToWords = new HashMap<>(); 

        for (String word : strs) {
            String key = buildCountKey(word); 

            countKeyToWords
                .computeIfAbsent(key, ignored -> new ArrayList<>())
                .add(word); 
        }
        return new ArrayList<>(countKeyToWords.values()); 
    }

    private String buildCountKey(String word) {
        int[] counts = new int[26]; 

        for (char c : word.toCharArray()) {
            counts[c - 'a']++; 
        }

        StringBuilder key = new StringBuilder(); 

        for (int count : counts) {
            key.append('#'); 
            key.append(count);
        }
        return key.toString(); 
    }
}
