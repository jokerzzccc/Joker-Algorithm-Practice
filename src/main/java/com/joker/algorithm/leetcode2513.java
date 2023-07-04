package com.joker.algorithm;

/**
 * <p>
 * 最小化两个数组中的最大值
 * </p>
 *
 * @author admin
 * @date 2023/7/4
 */
public class leetcode2513 {

    public static void main(String[] args) {
        int divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minimizeSet(divisor1, divisor2, uniqueCnt1, uniqueCnt2));

    }

    /**
     * 解法一：二分查找
     */
    private static class Solution01 {

        //题意即是：两个数组的最大值n满足：
        // 使得在1~n 中找到uniqueCnt1 个不能被 divisor1整除的数，
        // 找到uniqueCnt2 个不能被divisor2整除的数，
        // 且他们找到的数互不相等，求n的最小值

        //集合角度：应该满足1~n中：
        // 不能被 divisor1整除的数的个数应该大于等于 uniqueCnt1,
        // 不能被 divisor2整除的数的个数应该大于等于 uniqueCnt2,
        // 不能被 divisor1 和 divisor2同时整除的数的个数应该大于等于 uniqueCnt1 + uniqueCnt2,
        // 显然这些个数都随着n增大而增大，所以根据n需要满足的条件去二分查找即可
        public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {

            long m = (long) divisor1 * divisor2 / gcd(divisor1, divisor2);
            int target = uniqueCnt1 + uniqueCnt2;
            int l = 0, r = 2 * target + 1, mid;

            while (l <= r) {
                mid = (r - l) / 2 + l;
                if (uniqueCnt1 <= mid - mid / divisor1
                        && uniqueCnt2 <= mid - mid / divisor2
                        && target <= mid - mid / m) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;

        }

        public int gcd(int a, int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

    }

}
