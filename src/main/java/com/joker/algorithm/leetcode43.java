package com.joker.algorithm;

/**
 * <p>
 * 字符串相乘
 * </p>
 *
 * @author admin
 * @date 2023/7/12
 */
public class leetcode43 {

    public static void main(String[] args) {
        String num1 = "123", num2 = "456";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.multiply(num1, num2));

    }

    /**
     * 解法一：做乘法
     */
    private static class Solution01 {

        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }

            int m = num1.length();
            int n = num2.length();
            // 用于存储乘积
            int[] ansStr = new int[m + n];

            // 对于任意 0≤ i <m 和 0 ≤ j <n, num1[i]×num2[j] 的结果位于ansArr[i+j+1]
            for (int i = m - 1; i >= 0; i--) {
                int x = num1.charAt(i) - '0';
                for (int j = n - 1; j >= 0; j--) {
                    int y = num2.charAt(j) - '0';
                    ansStr[i + j + 1] += x * y;
                }
            }

            // 如果 ansStr[i + j + 1] >= 10, 则将进位部分加到 ansStr[i + j]
            for (int i = m + n - 1; i > 0; i--) {
                ansStr[i - 1] += ansStr[i] / 10;
                ansStr[i] %= 10;
            }

            // 处理前导0(如果最高位是0，则放弃)
            int index = ansStr[0] == 0 ? 1 : 0;
            // 返回结果字符串
            StringBuilder ans = new StringBuilder();
            while (index < m + n) {
                ans.append(ansStr[index]);
                index++;
            }

            return ans.toString();
        }

    }

}
