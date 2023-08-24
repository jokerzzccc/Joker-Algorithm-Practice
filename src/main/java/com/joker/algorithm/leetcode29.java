package com.joker.algorithm;

/**
 * <p>
 * 两数相除
 * </p>
 *
 * @author admin
 * @date 2023/8/23
 */
public class leetcode29 {

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.divide(dividend, divisor));

    }

    /**
     * 解法一：快速幂
     */
    private static class Solution01 {

        public int divide(int dividend, int divisor) {
            // 特殊情况处理
            if (dividend == 0) {
                return 0;
            }
            if (divisor == 1) {
                return dividend;
            }
            // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
            if (divisor == -1) {
                if (dividend > Integer.MIN_VALUE) {
                    return -dividend;
                } else {
                    return Integer.MAX_VALUE;
                }
            }

            long a = dividend;
            long b = divisor;
            int sign = 1;
            if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
                sign = -1;
            }
            // 把被除数与除数调整为正数,为防止被除数 Integer.MIN_VALUE 转换为正数会溢出，使用 long 类型保存参数
            a = a > 0 ? a : -a;
            b = b > 0 ? b : -b;
            long res = div(a, b);

            if (sign > 0) {
                return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
            } else {
                return (int) -res;
            }

        }

        private long div(long a, long b) {
            if (a < b) {
                return 0;
            }
            // 使用了多少个divisor
            long count = 1;
            // 在后面的代码中不更新b
            // 记录用了 count 个 divisor 的和
            long tb = b;
            // 当前测试的值也翻倍，直到 a < tb + tb
            while (tb + tb <= a) {
                // 最小值翻倍
                count += count;
                tb += tb;
            }

            // 递归计算
            return count + div(a - tb, b);
        }

    }

}
