class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // Initialize prereqToDependents
        List<List<Integer>> prereqToDependents = new ArrayList<>(); 
        for (int i = 0; i < numCourses; i++) {
            prereqToDependents.add(new ArrayList<>());
        }

        // Initialize indegree array
        int[] dependencies = new int[numCourses];

        // Populate prereqToDependents
        // Go through input array ([a, b] = b is prereq for a), populate adj, increment indegree for that course it is added to prereq's dependents list
        // indegree = dependencies
        for (int i = 0; i < prerequisites.length; i++) {
            prereqToDependents.get(prerequisites[i][1]).add(prerequisites[i][0]);
            dependencies[prerequisites[i][0]]++;
        }

        Queue<Integer> readyQueue = new ArrayDeque<>(); 

        // Initialize/add to 'done' queue courses that don't depend on any prereqs (not present in a prereq's dependents list, go through indegree (dependencies) array)
        for (int i = 0; i < numCourses; i++) {
            if (dependencies[i] == 0) {
                readyQueue.add(i);
            }
        }

        // initialize doneCourses counter (to be compared with numCourses passed in)
        int doneCourses = 0; 

        // While loop: if queue isn't empty (take 'done' course = pop from queue and mark as taken - these are the 'done' courses - which courses depend on it?)
        while (!readyQueue.isEmpty()) {
            int done = readyQueue.poll(); 
            doneCourses++; 

            for (int dependent : prereqToDependents.get(done)) {
                dependencies[dependent]--; 
                if (dependencies[dependent] == 0) {
                    readyQueue.add(dependent);
                }
            }
        }

        return numCourses == doneCourses;

            // for this done course's dependents, decrement their dependencies marker because we just took this 'done' courses which it depended on 

        // if we haven't taken all of the courses, return false, else return true 
    }
}
