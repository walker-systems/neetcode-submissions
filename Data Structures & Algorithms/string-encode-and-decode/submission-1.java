class Solution {

    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder(); 

        for (String str : strs) {
            encoded.append(str.length()); 
            encoded.append('#'); 
            encoded.append(str);
        }
        return encoded.toString(); 
    }

    public List<String> decode(String str) {
        List<String> decoded = new ArrayList<>(); 

        int index = 0; 

        while (index < str.length()) {
            int delimiterIndex = index; 

            while (str.charAt(delimiterIndex) != '#') {
                delimiterIndex++; 
            }

            int length = Integer.parseInt(str.substring(index, delimiterIndex)); 

            int stringStart = delimiterIndex + 1; 
            int stringEnd = stringStart + length; 

            decoded.add(str.substring(stringStart, stringEnd)); 

            index = stringEnd; 
        }
        return decoded; 
    }
}
