class Solution {
    public boolean hasDuplicate(int[] nums) {
        List<Integer> original = Arrays.stream(nums)
            .boxed()
            .collect(Collectors.toList()); 
        Set<Integer> set = new HashSet<>(original);
        if (set.size() < original.size()) {
            return true;
        }
        return false;
    }
}