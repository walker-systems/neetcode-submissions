class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2]; 
        int left = 0; 
        int right = numbers.length - 1; 

        while (left < right) {
            while (numbers[left] + numbers[right] > target) {
                right--; 
            } 
            while (numbers[left] + numbers[right] < target) {
                left++; 
            }
            if (numbers[left] + numbers[right] == target) {
                result[0] = left + 1; 
                result[1] = right + 1; 
                return result; 
            }
        }
        return result; 
    }
}
