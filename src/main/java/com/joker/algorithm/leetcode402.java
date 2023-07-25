package com.joker.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * 移掉 K 位数字
 * </p>
 *
 * @author admin
 * @date 2023/7/25
 */
public class leetcode402 {

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.removeKdigits(num, k));

    }

    /**
     * 解法一：贪心 + 单调栈
     */
    private static class Solution01 {

        public String removeKdigits(String num, int k) {
            int len = num.length();
            if (len <= k) {
                return "0";
            }

            // 栈中的元素代表截止到当前位置，删除不超过 k 次个数字后，所能得到的最小整数
            // 在使用 k 个删除次数之前，栈中的序列从栈底到栈顶单调不降
            Deque<Character> deque = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                char digit = num.charAt(i);
                // 贪心策略：找到降序的，然后删掉
                while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                    deque.pollLast();
                    k--;
                }
                deque.offerLast(digit);
            }

            // 如果我们删除了 m 个数字且 m<k，这种情况下我们需要从序列尾部删除额外的 k−m 个数字。
            for (int i = 0; i < k; ++i) {
                deque.pollLast();
            }

            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            while (!deque.isEmpty()) {
                char digit = deque.pollFirst();
                // 删除前导0
                if (leadingZero && digit == '0') {
                    continue;
                }
                leadingZero = false;
                ret.append(digit);
            }

            return ret.length() == 0 ? "0" : ret.toString();
        }

    }

}
