class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>(); 


        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());

        }

        for (int[] pre : prerequisites) {
            int course = pre[0]; 
            int prereq = pre[1]; 
            map.get(prereq).add(course);
        }

        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, map, state)) {
                return false;
            }
            

        }
        return true;

    }

    private boolean hasCycle (int i, Map<Integer, List<Integer>> adj, int[] state) {
        if (state[i] == 1) return true; 
        if (state[i] == 2) return false;

        state[i] = 1; 
        for (int neighbor : adj.get(i)) {
            if (hasCycle(neighbor, adj, state)) return true;
        }
        state[i] = 2; 
        return false; 



    }
}
