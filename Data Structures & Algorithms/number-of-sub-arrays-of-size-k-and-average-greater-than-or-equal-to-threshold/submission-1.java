class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int result = 0; 
        int sum = 0; 
        int currCount = 0; 

        for (int i = 0; i <= arr.length - k; i++) { 
             
            while (currCount < k) {
                sum += arr[i + currCount];
                currCount++;
            }
            if (sum >= threshold * k) {
                result++;
            }
            sum = 0; 
            currCount = 0; 

        }
        return result;
    }
}