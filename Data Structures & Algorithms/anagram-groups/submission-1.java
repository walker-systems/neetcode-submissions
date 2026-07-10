class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> sortedKeyToWords = new HashMap<>(); 

        for (String word : strs) {
            char[] chars = word.toCharArray(); 
            Arrays.sort(chars); 

            String key = new String(chars); 

            sortedKeyToWords
                .computeIfAbsent(key, ignored -> new ArrayList<>())
                .add(word);
        }
        return new ArrayList<>(sortedKeyToWords.values()); 
    }
}
