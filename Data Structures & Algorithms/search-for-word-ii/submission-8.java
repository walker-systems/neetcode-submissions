class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; 
        String word = null; 
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode(); 
        for (String w : words) {
            TrieNode node = root; 
            for (char c : w.toCharArray()) {
                int idx = c - 'a'; 
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode(); 
                }
                node = node.children[idx]; 
            }
            node.word = w; 
        }

        List<String> result = new ArrayList<>(); 

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (root.children[board[r][c] - 'a'] != null) {
                    dfs(board, r, c, root, result); 
                }
            }
        }
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> res) {
        char letter = board[r][c]; 
        TrieNode curr = node.children[letter - 'a'];

        if (curr.word != null) {
            res.add(curr.word); 
            curr.word = null; 
        }

        board[r][c] = '#'; 

        int[] dr = {1, -1, 0 , 0}; 
        int[] dc = {0, 0, 1, -1}; 

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i]; 
            int nc = c + dc[i]; 

            if (nr >= 0 && nr < board.length &&
                nc >= 0 && nc < board[0].length &&
                board[nr][nc] != '#' &&
                curr.children[board[nr][nc] - 'a'] != null) {
                    dfs(board, nr, nc, curr, res); 
                }
        }

        board[r][c] = letter; 
    }
}
