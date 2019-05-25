package Design;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class EasyDesignProblems {
    public static void main(String[] args) {
        /*["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]*/
        System.out.println(Integer.MAX_VALUE);
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println("top = " + minStack.top());
        minStack.pop();
        System.out.println("getmin= " + minStack.getMin());
        minStack.pop();
        System.out.println("getmin= " + minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println("top = " + minStack.top());
        System.out.println("getmin= " + minStack.getMin());
        //minStack.push(2147483648);
    }

}
//155. Min Stack
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if(x <= min){
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 *
 * e.g.
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */