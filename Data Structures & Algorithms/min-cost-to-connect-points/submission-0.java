// package leetcode.graphs;

// import java.util.Arrays;

public class Solution {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // inMst[i] = true once point i has been added to the minimum spanning tree
        boolean[] inMst = new boolean[n];

        // minConnectionCost[i] = cheapest known cost to connect point i
        // to any point already inside the MST
        int[] minConnectionCost = new int[n];
        Arrays.fill(minConnectionCost, Integer.MAX_VALUE);

        // Start Prim's from point 0.
        // It costs 0 to "connect" the first point because it is the starting seed of the MST.
        minConnectionCost[0] = 0;

        int totalCost = 0;

        // We must add exactly n points to the MST.
        for (int edgesChosen = 0; edgesChosen < n; edgesChosen++) {
            int nextPoint = -1;

            // Choose the point outside the MST with the smallest connection cost.
            for (int point = 0; point < n; point++) {
                if (!inMst[point] && (nextPoint == -1 || minConnectionCost[point] < minConnectionCost[nextPoint])) {
                    nextPoint = point;
                }
            }

            // Add the chosen point to the MST.
            inMst[nextPoint] = true;
            totalCost += minConnectionCost[nextPoint];

            // Update the cheapest connection cost for all remaining points.
            for (int point = 0; point < n; point++) {
                if (!inMst[point]) {
                    int connectionCost = manhattanDistance(points[nextPoint], points[point]);
                    if (connectionCost < minConnectionCost[point]) {
                        minConnectionCost[point] = connectionCost;
                    }
                }
            }
        }

        return totalCost;
    }

    private int manhattanDistance(int[] pointA, int[] pointB) {
        return Math.abs(pointA[0] - pointB[0]) + Math.abs(pointA[1] - pointB[1]);
    }
}