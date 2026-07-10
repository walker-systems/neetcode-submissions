class Solution {
    public String foreignDictionary(String[] words) {
        final boolean[][] adj = new boolean[26][26];
        final int[] inDegree = new int[26];
        final boolean[] present = new boolean[26];

        int uniqueChars = 0;
        for (String w : words) {
            for (char c : w.toCharArray()) {
                if (!present[c - 'a']) {
                    present[c - 'a'] = true;
                    uniqueChars++;
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            final String w1 = words[i];
            final String w2 = words[i + 1];

            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return ""; 
            }

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                final int u = w1.charAt(j) - 'a';
                final int v = w2.charAt(j) - 'a';
                
                if (u != v) {
                    if (!adj[u][v]) {
                        adj[u][v] = true;
                        inDegree[v]++;
                    }
                    break; 
                }
            }
        }

        final Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (present[i] && inDegree[i] == 0) {
                q.offer(i);
            }
        }

        final StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            int curr = q.poll();
            result.append((char) (curr + 'a'));

            for (int next = 0; next < 26; next++) {
                if (adj[curr][next]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        return result.length() == uniqueChars ? result.toString() : "";
    }
}
