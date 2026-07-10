class SegmentTree {
    private int[] tree;
    private int n; 

    public SegmentTree(int[] arr) {
        n = arr.length; 
        tree = new int[n * 2]; 

        for (int i = 0; i < n; i++) {
            tree[n + i] = arr[i]; 
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2  + 1]; 
        }
    }

    public void update(int idx, int val) {
        idx += n; 
        tree[idx] = val;

        while (idx > 1) {
            int left = idx; 
            int right = idx; 

            if (idx % 2 == 0) {
                right = idx + 1;
            } else {
                left = idx - 1; 
            }

            tree[idx / 2] = tree[left] + tree[right]; 

            idx /= 2; 
        } 
    }

    public int query(int l, int r) {
        l += n; 
        r += n; 
        int sum = 0; 

        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l]; 
                l++; 
            }

            if (r % 2 == 0) {
                sum += tree[r];
                r--; 
            }

            l /= 2; 
            r /= 2; 
        }
        return sum; 
    }
}
