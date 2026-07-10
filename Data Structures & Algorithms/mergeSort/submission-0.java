// Definition for a pair.
// class Pair {
//     public int key;
//     public String value;
//
//     public Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
class Solution {
    public List<Pair> mergeSort(List<Pair> pairs) {
        // Base case
        if (pairs.size() <= 1) return pairs; 

        int mid = pairs.size() / 2;

        // Split arrays 
        List<Pair> left = new ArrayList<>(pairs.subList(0, mid));
        List<Pair> right = new ArrayList<>(pairs.subList(mid, pairs.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        // Return sorted array
        return merge(left, right); 
    }

    public List<Pair> merge(List<Pair> left, List<Pair> right) {
        List<Pair> result = new ArrayList<>();

        int i = 0; 
        int j = 0; 
        int k = 0; 

        while (i < left.size() && j < right.size()) {
            if (left.get(i).key <= right.get(j).key) {
                result.add(left.get(i)); 
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            result.add(left.get(i)); 
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result; 
    }
}
