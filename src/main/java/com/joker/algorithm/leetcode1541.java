package com.joker.algorithm;

/**
 * <p>
 * 平衡括号字符串的最少插入次数
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode1541 {

    public static void main(String[] args) {
        String s = "(()))";

        Solution01 solution01 = new Solution01();
        int minInsertions01 = solution01.minInsertions(s);
        System.out.println(minInsertions01);

    }

    /**
     * 解法一： 贪心
     * 类比 leetcode921
     */
    private static class Solution01 {

        public int minInsertions(String s) {
            // 记录插入次数
            int res = 0;
            // 变量记录右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    need += 2;
                    // 当遇到左括号时，若对右括号的需求量为奇数，需要插入 1 个右括号
                    // 因为一个左括号需要两个右括号嘛，右括号的需求必须是偶数
                    if (need % 2 == 1) {
                        // 插入一个右括号
                        res++;
                        // 对右括号的需求减一
                        need--;
                    }
                }

                if (s.charAt(i) == ')') {
                    need--;
                    // 说明右括号太多了
                    if (need == -1) {
                        // 需要插入一个左括号
                        res++;
                        // 同时，对右括号的需求变为 1
                        need = 1;
                    }
                }

            }

            return res + need;
        }

    }

}
