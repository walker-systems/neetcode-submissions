

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Initialize Graph and Indegree Array
        // "Indegree" = How many prerequisites does this course currently have?
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. Build Graph & Count Prerequisites
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];
            
            // Edge: Prereq -> Course
            adj.get(prereq).add(course);
            
            // Increment the dependency count for the course
            indegree[course]++;
        }

        // 3. Initialize Queue with "Ready" courses
        // A course is ready if it has 0 prerequisites (indegree == 0)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // 4. Process the Queue (Take Courses)
        int coursesTaken = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            coursesTaken++; // We successfully "finished" this course

            // Unlock the next courses
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--; // Remove one requirement
                
                // If all requirements are met, add to queue
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // 5. Did we finish everyone?
        // If coursesTaken < numCourses, it means there was a cycle (deadlock),
        // and some courses never reached indegree 0.
        return coursesTaken == numCourses;
    }
}