

class Solution {
    // 1. Trie Node uses a HashMap instead of an Array
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null; // Store the word at the leaf
    }

    public List<String> findWords(char[][] board, String[] words) {
        // 2. Build the Trie
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                // 'putIfAbsent' creates the node if it doesn't exist
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.word = w; // Mark end of word
        }

        List<String> result = new ArrayList<>();
        
        // 3. Iterate every cell to start Backtracking
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // Optimization: Only start if the root has this character key
                if (root.children.containsKey(board[r][c])) {
                    dfs(board, r, c, root, result);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> res) {
        char letter = board[r][c];
        
        // Move to the child node using the Map
        TrieNode curr = node.children.get(letter);

        // Found a word!
        if (curr.word != null) {
            res.add(curr.word);
            curr.word = null; // De-duplicate immediately
        }

        // Mark as visited
        board[r][c] = '#'; 

        // Explore neighbors
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // Bounds check AND Trie path check (using containsKey)
            if (nr >= 0 && nr < board.length && 
                nc >= 0 && nc < board[0].length && 
                board[nr][nc] != '#' && 
                curr.children.containsKey(board[nr][nc])) { // <--- Map Check
                
                dfs(board, nr, nc, curr, res);
            }
        }

        // Backtrack
        board[r][c] = letter;
    }
}