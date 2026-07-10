class Solution {
    private static char LENGTH_SEPARATOR = '#'; 

    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder(); 

        for (String s : strs) {
            encoded.append(s.length()); 
            encoded.append(LENGTH_SEPARATOR);
            encoded.append(s); 
        }
        return encoded.toString(); 
    }

    public List<String> decode(String encoded) {
        List<String> decoded = new ArrayList<>(); 

        int i = 0; 

        while (i < encoded.length()) {
            int separatorIndex = i; 

            while (encoded.charAt(separatorIndex) != LENGTH_SEPARATOR) {
                separatorIndex++; 
            }

            int length = Integer.parseInt(encoded.substring(i, separatorIndex)); 
            int start = separatorIndex + 1; 
            int end = start + length; 

            decoded.add(encoded.substring(start, end)); 

            i = end; 
        }
        return decoded; 
    }
}
