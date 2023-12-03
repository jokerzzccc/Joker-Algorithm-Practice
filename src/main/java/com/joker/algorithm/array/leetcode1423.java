package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * 可获得的最大点数
 *
 * @author jokerzzccc
 * @date 2023/12/3
 */
public class leetcode1423 {

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxScore(cardPoints, k));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maxScore(cardPoints, k));

    }

    /**
     * 解法一：滑动窗口：
     * 可以用逆向思维求，剩下的 n - k 个连续子数组的最小值是多少
     */
    private static class Solution01 {

        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;
            // 固定窗口大小
            int size = n - k;
            int totalSum = Arrays.stream(cardPoints).sum();

            int windowSum = 0;
            for (int i = 0; i < size; i++) {
                windowSum += cardPoints[i];
            }

            int minSum = windowSum;
            for (int i = size; i < n; i++) {
                windowSum += cardPoints[i] - cardPoints[i - size];
                minSum = Math.min(minSum, windowSum);
            }

            return totalSum - minSum;
        }

    }

    /**
     * 解法二：前缀和
     * 正向思考
     * 答案等于如下结果的最大值：
     * <p>
     * 前 kkk 个数的和。
     * 前 k−1k-1k−1 个数以及后 111 个数的和。
     * 前 k−2k-2k−2 个数以及后 222 个数的和。
     * ……
     * 前 222 个数以及后 k−2k-2k−2 个数的和。
     * 前 111 个数以及后 k−1k-1k−1 个数的和。
     * 后 kkk 个数的和。
     * <p>
     */
    private static class Solution02 {

        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;
            int tmpSum = 0;
            for (int i = 0; i < k; i++) {
                tmpSum += cardPoints[i];
            }

            int ans = tmpSum;
            for (int i = 1; i <= k; i++) {
                tmpSum += cardPoints[n - i] - cardPoints[k - i];
                ans = Math.max(ans, tmpSum);
            }

            return ans;
        }

    }

}
