package com.joker.algorithm;

import java.util.Stack;

/**
 * <p>
 * 包含min函数的栈
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class offer30 {

    /**
     * 解法一：辅助栈
     */
    class MinStack {

        // 数据栈A：栈A用于存储所有元素，保证入栈push函数、出栈pop函数、获取栈顶top函数的正常逻辑。
        Stack<Integer> A;
        // 辅助栈B：栈B中存储栈A中所有非严格降序的元素，则栈A中的最小元素始终对应栈B的栈顶元素，即minO函数只需返回栈B的栈顶元素即可。
        Stack<Integer> B;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
            B.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            A.push(x);
            B.push(Math.min(B.peek(), x));
        }

        public void pop() {
            A.pop();
            B.pop();
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();
        }

    }

}


