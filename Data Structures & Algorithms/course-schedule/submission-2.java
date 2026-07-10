class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> adj = new ArrayList<>(); 
        int[] indegree = new int[numCourses]; 

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            int course = pair[0]; 
            int prereq = pair[1]; 

            adj.get(prereq).add(course); 

            indegree[course]++; 
        }

        Queue<Integer> queue = new ArrayDeque<>(); 
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int coursesTaken = 0; 

        while (!queue.isEmpty()) {
            int current = queue.poll(); 
            coursesTaken++; 

            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--; 

                if (indegree[neighbor] == 0) {
                    queue.add(neighbor); 
                }
            }
        }

        return coursesTaken == numCourses;
    }
}
