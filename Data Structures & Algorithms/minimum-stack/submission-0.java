class MinStack {

    List<Integer> minStack;

    public MinStack() {
        this.minStack = new ArrayList<>();
    }
    
    public void push(int val) {
        minStack.add(val);
    }
    
    public void pop() {
        minStack.remove(minStack.size() - 1);
    }
    
    public int top() {
        return minStack.get(minStack.size() - 1);
    }
    
    public int getMin() {
        int min = minStack.get(0); 
        for (int n : minStack) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }
}
