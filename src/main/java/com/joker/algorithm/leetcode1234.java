package com.joker.algorithm;

/**
 * <p>
 * 替换子串得到平衡字符串
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode1234 {

    public static void main(String[] args) {
        String s = "QQQW";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.balancedString(s));

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        public int balancedString(String s) {
            int n = s.length();
            // 先统计 s 中各个字符的数量
            int[] cnt = new int[26];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                cnt[idx(c)]++;
            }

            int partial = n / 4;
            int res = n;

            if (check(cnt, partial)) {
                return 0;
            }

            // 使用滑动窗口来维护区间 [left, right) 之外的剩余部分中 QWER 的出现次数
            for (int left = 0, right = 0; left < n; ) {
                // 当其中一种字符的出现次数大于 partial 时，另 s[right] 的出现次数减一，并右移 right
                while (right < n && !check(cnt, partial)) {
                    cnt[idx(s.charAt(right++))]--;
                }

                // 如果找不到满足条件的 right，就可以直接跳出循环了。
                if (!check(cnt, partial)) {
                    break;
                }

                // 找到符合条件的 right,
                // 对于所有合法的 [left, right)，取 right - left 的最小值做为答案。
                res = Math.min(res, right - left);
                // 另 s[left] 的出现次数加一，并使得 left 向右移动一个单位。
                cnt[idx(s.charAt(left++))]++;
            }

            return res;

        }

        private int idx(char c) {
            return c - 'A';
        }

        /**
         * 校验是否能组成一个平衡字符串：
         * 只有当 s 剩余的部分中 Q,W,E,R 的出现次数都小于等于 partial 时，我们才有可能使 s 变为「平衡字符串」。
         */
        private boolean check(int[] cnt, int partial) {
            if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
                return false;
            }
            return true;
        }

    }

}
