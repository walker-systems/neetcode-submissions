class TrieNode {
    Map<Character, TrieNode> children; 
    boolean isEndOfWord = false;

    public TrieNode() {
        children = new HashMap<>();
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
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = getLastNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = getLastNode(prefix);
        return node != null;
    }

    private TrieNode getLastNode(String s) {
        TrieNode curr = root; 
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return null;
            }
            curr = curr.children.get(c);
        }
        return curr;
    }
}
