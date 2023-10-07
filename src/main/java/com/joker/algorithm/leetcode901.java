package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 股票价格跨度
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/7
 **/
public class leetcode901 {

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // 返回 1
        System.out.println(stockSpanner.next(80));  // 返回 1
        System.out.println(stockSpanner.next(60));  // 返回 1
        System.out.println(stockSpanner.next(70));  // 返回 2
        System.out.println(stockSpanner.next(60));  // 返回 1
        System.out.println(stockSpanner.next(75));  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
        System.out.println(stockSpanner.next(85));  // 返回 6

    }

    /**
     * 解法一：单调栈
     * <p>
     * 及时去掉无用数据，
     * 保证栈中元素有序。
     */
    static class StockSpanner {

        /**
         * 单调栈：从栈顶到栈底，price 降序
         */
        private final Deque<int[]> stack = new ArrayDeque<>();
        /**
         * 第一个 next 调用算作第 0 天
         */
        private int curDay = -1;

        public StockSpanner() {
            // 加入初始值：这样无需判断栈为空的情况
            stack.push(new int[]{-1, Integer.MAX_VALUE});
        }

        public int next(int price) {
            while (price >= stack.peek()[1]) {
                // 栈顶数据后面不会再用到了，因为 price 更大
                stack.pop();
            }
            int ans = ++curDay - stack.peek()[0];
            stack.push(new int[]{curDay, price});
            return ans;
        }

    }

}
