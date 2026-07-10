class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0; 
        int result = 0; 
        
        for (int i = 0; i < k; i++) {
            sum += arr[i]; 
        }
        if (sum >= threshold * k) {
            result++;
        }

        for (int i = k; i < arr.length; i++) {
            sum += arr[i];
            sum -= arr[i - k];
            if (sum >= threshold * k) {
                result++;
            }
        }
        return result;
    }
}