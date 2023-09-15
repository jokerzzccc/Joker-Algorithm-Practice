package com.joker.algorithm;

/**
 * <p>
 * 压缩字符串
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/14
 */
public class leetcode443 {

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.compress(chars));

    }

    /**
     * 解法一：双指针
     * 原地修改
     */
    private static class Solution01 {
        public int compress(char[] chars) {
            int n = chars.length;
            // 当前连续子串的从字符开始算的写入的位置
            int write = 0;
            // 当前连续子串的最左侧
            int left = 0;
            for (int read = 0; read < n; read++) {
                if (read == n - 1 || chars[read] != chars[read + 1]) {
                    // 当前连续子串的字符
                    chars[write++] = chars[read];
                    // 当前连续子串的字符个数
                    int num = read - left + 1;
                    // 原地修改连续子串的数量
                    if (num > 1) {
                        int anchor = write;
                        while (num > 0) {
                            chars[write++] = (char) (num % 10 + '0');
                            num /= 10;
                        }
                        // 得到连续子串的数量
                        reverse(chars, anchor, write - 1);
                    }

                    // 更新到下一个连续子串起始位置
                    left = read + 1;
                }
            }

            return write;
        }

        /**
         * 原地反转 chars [left, right] 的字符
         */
        private void reverse(char[] chars, int left, int right) {
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
    }
}
