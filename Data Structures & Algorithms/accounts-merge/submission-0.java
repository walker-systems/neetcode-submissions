//import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind<String> uf = new UnionFind<>();
        Map<String, String> emailToName = new HashMap<>();

        // 1. Process all accounts to build the Union-Find structure
        for (List<String> account : accounts) {
            String name = account.get(0);
            // The first email acts as our anchor for this account
            String firstEmail = account.get(1); 
            
            for (int i = 1; i < account.size(); i++) {
                String currentEmail = account.get(i);
                emailToName.put(currentEmail, name);
                // Union every email in the list with the first one
                uf.union(firstEmail, currentEmail);
            }
        }

        // 2. Group emails by their absolute root parent
        Map<String, List<String>> components = new HashMap<>();
        for (String email : emailToName.keySet()) {
            String root = uf.find(email);
            components.putIfAbsent(root, new ArrayList<>());
            components.get(root).add(email);
        }

        // 3. Sort emails and add names to match required output format
        List<List<String>> result = new ArrayList<>();
        for (String root : components.keySet()) {
            List<String> emails = components.get(root);
            Collections.sort(emails);
            
            List<String> account = new ArrayList<>();
            account.add(emailToName.get(root)); // Add the name first
            account.addAll(emails);
            result.add(account);
        }

        return result;
    }
}

// Reuse our Generic UnionFind class
class UnionFind<T> {
    private Map<T, T> parent = new HashMap<>();
    private Map<T, Integer> rank = new HashMap<>();

    public T find(T x) {
        parent.putIfAbsent(x, x);
        rank.putIfAbsent(x, 1);
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(T x, T y) {
        T rootX = find(x);
        T rootY = find(y);
        if (rootX.equals(rootY)) return;

        if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
        } else if (rank.get(rootX) < rank.get(rootY)) {
            parent.put(rootX, rootY);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rank.get(rootX) + 1);
        }
    }
}

