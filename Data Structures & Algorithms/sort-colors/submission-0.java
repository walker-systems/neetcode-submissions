public static final int RANGE = 3;

class Solution {
    public void sortColors(int[] nums) {

        int[] counts = new int[RANGE];
        
        for (int num : nums) {
            counts[num]++;
        }

        int k = 0; 
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[k] = i; 
                k++;
            }
        }
        
    }
}