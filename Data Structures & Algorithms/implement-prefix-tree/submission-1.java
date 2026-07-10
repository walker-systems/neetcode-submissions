class TrieNode {
    // Array to store children (26 lowercase English letters)
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class PrefixTree {
    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Map 'a' -> 0, 'b' -> 1, etc.
            
            // If the path doesn't exist, create it
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            // Move to the child node
            curr = curr.children[index];
        }
        // Mark the end of the inserted word
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = getLastNode(word);
        // It must exist AND be marked as a complete word
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = getLastNode(prefix);
        // It just needs to exist
        return node != null;
    }

    // Helper function to avoid code duplication
    private TrieNode getLastNode(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return null; // Path breaks
            }
            curr = curr.children[index];
        }
        return curr;
    }
}