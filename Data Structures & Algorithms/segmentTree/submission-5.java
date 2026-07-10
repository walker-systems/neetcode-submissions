class SegmentTree {

    private static class Node {
        int start, end; 
        int sum; 
        Node left, right; 

        public Node(int start, int end) {
            this.start = start; 
            this.end = end; 
            this.sum = 0; 
        }
    }

    private Node root; 

    public SegmentTree(int[] arr) {
        if (arr != null && arr.length > 0) {
            root = buildTree(arr, 0, arr.length - 1); 
        }
    }

    private static Node buildTree(int[] arr, int start, int end) {
        Node node = new Node(start, end); 

        if (start == end) {
            node.sum = arr[start]; 
            return node;
        }

        int mid = start + (end - start) / 2; 
        node.left = buildTree(arr, start, mid); 
        node.right = buildTree(arr, mid + 1, end); 

        node.sum = node.left.sum + node.right.sum; 

        return node; 
    }

    public void update(int index, int val) {
        updateHelper(root, index, val); 
    }

    private static void updateHelper(Node node, int idx, int val) {
        if (node.start == node.end) {
            node.sum = val; 
            return; 
        }

        int mid = node.start + (node.end - node.start) / 2; 
        if (idx <= mid) {
            updateHelper(node.left, idx, val); 
        } else {
            updateHelper(node.right, idx, val); 
        }

        node.sum = node.left.sum + node.right.sum; 
    }

    public int query(int L, int R) {
        return queryHelper(root, L, R); 
    }

    private static int queryHelper(Node node, int start, int end) {
        if (node.start == start && node.end == end) {
            return node.sum; 
        }

        int mid = node.start + (node.end - node.start) / 2; 

        if (end <= mid) {
            return queryHelper(node.left, start, end); 
        } else if (start > mid) {
            return queryHelper(node.right, start, end); 
        } else {
            int leftSum = queryHelper(node.left, start, mid); 
            int rightSum = queryHelper(node.right, mid + 1, end); 
            return leftSum + rightSum; 
        }
    }
}
