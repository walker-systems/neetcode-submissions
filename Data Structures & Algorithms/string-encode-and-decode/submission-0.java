class Solution {

    private List<String> originals= new ArrayList<>(); 

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder(); 

        for (String s : strs) {
            sb.append(s);
            originals.add(s); 
        }
        return sb.toString(); 
    }

    public List<String> decode(String str) {
        return originals; 
    }
}
