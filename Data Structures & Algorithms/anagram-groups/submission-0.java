class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(); 
        List<List<String>> result = new ArrayList<>(); 
        
        for (String original : strs) {
            char[] arr = original.toCharArray(); 
            Arrays.sort(arr); 
            String sorted = new String(arr); 
            
            map.computeIfAbsent(sorted, ignored -> new ArrayList<>()).add(original);
        }
        for (String key : map.keySet()) {
            List<String> curr = new ArrayList<>(); 
            for (String anagram : map.get(key)) {
                curr.add(anagram); 
            }
            result.add(curr);
        }
        return result; 
    }
}
