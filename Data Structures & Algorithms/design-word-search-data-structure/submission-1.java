class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class WordDictionary {
    private TrieNode root; 

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root; 
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode()); 
            curr = curr.children.get(c);
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root); 
    }

    private boolean searchHelper(String word, int index, TrieNode curr) {
        if (index == word.length()) {
            return curr.isEndOfWord; 
        }

        char c = word.charAt(index); 

        if (c == '.') {
            for (TrieNode child : curr.children.values()) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            return searchHelper(word, index + 1, curr.children.get(c)); 
        }
    }
}
