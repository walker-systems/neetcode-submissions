class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int targetSum = k * threshold; 

        int currentSum = 0; 
        int count = 0; 

        for (int i = 0; i < k; i++) {
            currentSum += arr[i]; 
        }

        if (currentSum >= targetSum) {
            count++;
        }

        for (int i = k; i < arr.length; i++) {
            currentSum -= arr[i - k]; 
            currentSum += arr[i]; 
            
            if (currentSum >= targetSum) {
                count++;
            }
        }
        return count;
    }
}