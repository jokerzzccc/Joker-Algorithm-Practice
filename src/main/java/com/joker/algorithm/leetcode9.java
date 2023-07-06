package com.joker.algorithm;

/**
 * <p>
 * 回文数
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode9 {

    /**
     * 解法一： 反转一般数字
     */
    private static class Solution01 {

        public boolean isPalindrome(int x) {
            // 边界情况
            if (x < 0 || (x % 10 == 0 && x != 0)) {
                return false;
            }

            int reverse = 0;
            // 当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了
            while (x > reverse) {
                reverse = reverse * 10 + (x % 10);
                x /= 10;
            }

            // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
            // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
            return reverse == x || x == reverse / 10;
        }

    }

}
