package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 移动机器人
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/10
 **/

public class leetcode2731 {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 2};
        String s = "RLL";
        int d = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.sumDistance(nums, s, d));
    }

    /**
     * 脑筋急转弯 + 排序
     * 两个机器人相撞后，它们会立即改变方向，实际上相当于两个机器人继续往原来的方向移动。
     */
    private static class Solution01 {

        private final int MOD = (int) (Math.pow(10, 9) + 7);

        public int sumDistance(int[] nums, String s, int d) {
            int len = nums.length;
            long[] arr = new long[len];

            // 计算移动后的坐标，并从小到大排序
            for (int i = 0; i < len; i++) {
                arr[i] = (long) nums[i] + (s.charAt(i) == 'R' ? d : -d);
            }
            Arrays.sort(arr);

            // 计算答案：
            // 返回值：两两距离之和
            long ans = 0;
            for (int i = 1; i < len; i++) {
                // 对于 (arr[i] - arr[i - 1]) 这段距离，共有 i*(len - i) 次使用
                ans += (arr[i] - arr[i - 1]) * i % MOD * (len - i) % MOD;
                ans %= MOD;
            }

            return (int) ans;
        }
    }


}
