class Solution {
    public List<Pair> mergeSort(List<Pair> pairs) {
        // 1. Sanity Check (Always good for "production" code)
        if (pairs == null || pairs.size() <= 1) return pairs;

        int mid = pairs.size() / 2;

        // Note: We still create copies here because 'subList' is just a view.
        // To merge properly without side effects, this copy is the safest "Easy" way.
        List<Pair> left = mergeSort(new ArrayList<>(pairs.subList(0, mid)));
        List<Pair> right = mergeSort(new ArrayList<>(pairs.subList(mid, pairs.size())));

        return merge(left, right);
    }

    private List<Pair> merge(List<Pair> left, List<Pair> right) {
        // Pre-allocate size to avoid resizing the array (Performance Win!)
        List<Pair> result = new ArrayList<>(left.size() + right.size());
        
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            // "Clean Code" tip: Assign to variables for readability if accessing twice
            Pair l = left.get(i);
            Pair r = right.get(j);

            // Stability check: <= ensures we pick the left one first if they are equal
            if (l.key <= r.key) {
                result.add(l);
                i++;
            } else {
                result.add(r);
                j++;
            }
        }

        // Add remaining elements
        // The "addAll" method is slightly "sexier" (more readable) than a while loop
        // provided you use subList to grab the "rest" of the list.
        if (i < left.size()) {
            result.addAll(left.subList(i, left.size()));
        }
        if (j < right.size()) {
            result.addAll(right.subList(j, right.size()));
        }

        return result;
    }
}