class TNode {

    int key;
    int val;
    TNode left;
    TNode right;
    
    public TNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}

class TreeMap {

    TNode root;

    public TreeMap() {
        TNode root;
        root = null;
    }

    public void insert(int key, int val) {
        
        TNode newNode = new TNode(key, val); 
        if (root == null) {
            root = newNode;
            return;
        }

        TNode curr = root; 
        while (true) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = newNode;
                    return;
                }
                curr = curr.left;
            } else if (key > curr.key) {
                if (curr.right == null) {
                    curr.right = newNode;
                    return;
                }
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }
    }

    public int get(int key) {

        TNode curr = root; 
        while (curr != null) {
            if (key < curr.key) {
                curr = curr.left;
            } else if (key > curr.key) {
                curr = curr.right; 
            } else {
                return curr.val;
            }
        }
        return -1;

    }

    public int getMin() {

        TNode curr = findMin(root);
        return (curr != null) ? curr.val : -1;

    }

    private TNode findMin(TNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int getMax() {

        TNode curr = root;
        while (curr != null && curr.right != null) {
            curr = curr.right; 
        }
        return (curr != null) ? curr.val : -1; 
    }

    public void remove(int key) {
       root = removeHelper(root, key);
    }

    private TNode removeHelper(TNode curr, int key) {
        if (curr == null) {
            return null;
        }

        if (key > curr.key) {
            curr.right = removeHelper(curr.right, key);
        } else if (key < curr.key) {
            curr.left = removeHelper(curr.left, key);
        } else {
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            } else {
                TNode minNode = findMin(curr.right);
                curr.key = minNode.key; 
                curr.val = minNode.val;
                curr.right = removeHelper(curr.right, minNode.key);
            }
        }
        return curr;
    }

    public List<Integer> getInorderKeys() {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result); 
            result.add(root.key); 
            inorderTraversal(root.right, result); 
        }
    }
}
