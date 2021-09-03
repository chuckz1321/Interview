package com.chuck;

import java.util.Stack;

public class ReverseQueueLocal {
    public void reverse(Stack<Integer> stack) {
        int last = getAndRemoveLastElement(stack);
        if (!stack.isEmpty()) {
            reverse(stack);
        }
        stack.push(last);
    }

    public int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int tmp = getAndRemoveLastElement(stack);
            stack.push(result);
            return tmp;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ReverseQueueLocal reverseQueueLocal = new ReverseQueueLocal();
        reverseQueueLocal.reverse(stack);
        System.out.println(stack);
    }
}
