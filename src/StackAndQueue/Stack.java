package StackAndQueue;

import java.util.Arrays;
import java.util.Iterator;

/*
Stack is a linear data structure which follows a particular order in which the operations are performed.
The order may be LIFO(Last In First Out) or FILO(First In Last Out).
 */
public class Stack {

    int top;

    int[] arr;

    Stack(int size) {
        top = -1;
        arr = new int[size];
    }

    /*
    Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
     */
    void push(int e) {
        arr[++top] = e;
    }

    /*
    Removes an item from the stack. remove from top.
    The items are popped in the reversed order in which they are pushed. If the stack is empty, then it is said to be an Underflow condition.
     */
    int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        return arr[top--];
    }

    /*
    Returns top element of stack.
     */
    int peek() {
        return arr[top];
    }

    boolean isEmpty() {
        return top < 0;
    }

    void print() {
        String s = "bottom: ";
//        for(int i: arr) {
        for (int i=0;i<top;i++) {
            if (arr[i]!=0)
                s+=arr[i] + " -> ";
        }
        s+=arr[top]+" :top";
        System.out.print(s);
    }

    public static void main(String[] args) {
        Stack stack = new Stack(1000);
        stack.push(2);
        stack.push(1);
        System.out.println("pop: " + stack.pop());
        stack.push(4);
        stack.push(3);
        System.out.println("peek: " + stack.peek());
        stack.push(6);
        System.out.println("pop: " + stack.pop());
        stack.push(5);

        stack.print();
    }
}
