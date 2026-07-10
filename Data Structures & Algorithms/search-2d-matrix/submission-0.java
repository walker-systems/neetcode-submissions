class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int maxRows = matrix.length; 
        int maxCols = matrix[0].length; 

        int minRow = 0; 
        int minCol = 0; 

        int[][] remainingRows = new int[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            if (matrix[i][maxCols - 1] < target) {
                minRow++; 
            }
        }
        
        for (int j = minRow; j < matrix.length; j++) {
            if (matrix[j][0] > target) {
                maxRows--;
            }
        }
        if (minRow >= maxRows) return false; 

        boolean found = false;
        for (int k = minRow; k < maxRows; k++) {
            found = binarySearch(matrix[k], target);
        }

        return found;
        
    }

    private boolean binarySearch(int[] arr, int target) {

        int left = 0; 
        int right = arr.length - 1; 

        while (left <= right) {
            int mid = left + (right - left) / 2; 
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return false; 
    }
}
