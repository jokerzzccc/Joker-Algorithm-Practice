package com.joker.algorithm;

import java.util.Stack;

/**
 * <p>
 * 用两个栈实现队列
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class offer09 {

    public static void main(String[] args) {

    }

    private static class CQueue {

        Stack<Integer> inStack;
        Stack<Integer> outStack;

        public CQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.isEmpty()) {
                if (inStack.isEmpty()) {
                    return -1;
                }
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }

            return outStack.pop();
        }

    }

}
