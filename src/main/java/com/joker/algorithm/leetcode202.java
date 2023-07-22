package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 快乐数
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode202 {

    public static void main(String[] args) {
        int n = 19;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isHappy(n));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.isHappy(n));

    }

    /**
     * 解法一：哈希集合检测循环
     */
    private static class Solution01 {

        public boolean isHappy(int n) {
            Set<Integer> seen = new HashSet<>();

            // 两种情况会出现：
            // 1. 最终会得到1
            // 2. 最终会进入循环（不可能会无限大）
            while (n != 1 && !seen.contains(n)) {
                seen.add(n);
                n = getNext(n);
            }

            return n == 1;

        }

        /**
         * 计算当前数运算后的下一个数
         */
        private int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int digit = n % 10;
                n /= 10;
                totalSum += digit * digit;
            }
            return totalSum;
        }

    }

    /**
     * 解法二：快慢指针
     */
    private static class Solution02 {

        public boolean isHappy(int n) {
            int slow = n;
            int fast = getNext(n);

            // 问题可以转换为检测一个链表是否有环
            while (fast != 1 && slow != fast) {
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            return fast == 1;

        }

        /**
         * 计算当前数运算后的下一个数
         */
        private int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int digit = n % 10;
                n /= 10;
                totalSum += digit * digit;
            }
            return totalSum;
        }

    }

}
