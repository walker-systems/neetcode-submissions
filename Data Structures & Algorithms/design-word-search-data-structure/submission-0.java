class TrieNode {
    TrieNode[] children = new TrieNode[26]; 
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
            int index = c - 'a'; 
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index]; 
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
            for (TrieNode child : curr.children) {
                if (child != null && searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            int charIndex = c - 'a';
            if (curr.children[charIndex] == null) {
                return false;
            }
            return searchHelper(word, index + 1, curr.children[charIndex]);
        }
    }
}
