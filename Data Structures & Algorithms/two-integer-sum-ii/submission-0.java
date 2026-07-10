class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        int j = 0; 
        int i = numbers.length - 1; 
        int[] result = new int[2]; 

        while (j < i) {
            int currSum = numbers[j] + numbers[i]; 

            if (currSum < target) {
                j++;
            } else if (currSum > target) {
                i--;
            } else {
                result[0] = j + 1; 
                result[1] = i + 1; 
                break;
            }
        }
        return result; 
    }
}
