// Definition for a pair.
// class Pair {
//     int key;
//     String value;
//
//     public Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
class Solution {
    public List<Pair> quickSort(List<Pair> pairs) {
        return quickSortHelper(pairs, 0, pairs.size() - 1); 
    }

    private List<Pair> quickSortHelper(List<Pair> arr, int start, int end) {

        if (end - start + 1 <= 1) {
            return arr;
        }

        Pair pivot = arr.get(end);
        int left = start;

        for (int i = start; i < end; i++) {
            if (arr.get(i).key < pivot.key) {
                Pair temp = arr.get(left);
                arr.set(left, arr.get(i));
                arr.set(i, temp);
                left++;
            }
        }

        arr.set(end, arr.get(left));
        arr.set(left, pivot);
        quickSortHelper(arr, start, left - 1);
        quickSortHelper(arr, left + 1, end);

        return arr;
    }
}
