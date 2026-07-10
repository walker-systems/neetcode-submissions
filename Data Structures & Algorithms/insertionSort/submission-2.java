// Definition for a pair
// class Pair {
//     int key;
//     String value;
//
//     Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
public class Solution {
    public List<List<Pair>> insertionSort(List<Pair> pairs) {

        List<List<Pair>> currentState = new ArrayList<>();
        if (pairs.size() == 0) { return currentState; }
        List<Pair> curr = new ArrayList<>(pairs);
        List<Pair> initialState = new ArrayList<>(curr);
        currentState.add(initialState);
        
        for (int i = 1; i < pairs.size(); i++) {
            int j = i - 1;
            while (j >= 0 && curr.get(j + 1).key < curr.get(j).key) {
                Pair temp = curr.get(j + 1); 
                curr.set(j + 1, curr.get(j));
                curr.set(j, temp);
                j--;
            }
            List<Pair> snapshot = new ArrayList<>(curr);
            currentState.add(snapshot);
        }
        
        return currentState;
    }


}
