package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 数组中重复的数字
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class offer03 {

    /**
     * 解法一：哈希表
     */
    private static class Solution01 {

        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return num;
                }
                set.add(num);
            }
            return -1;

        }

    }

    /**
     * 解法一：原地交换
     */
    private static class Solution02 {

        public int findRepeatNumber(int[] nums) {
            int i = 0;
            while (i < nums.length) {
                if (nums[i] == i) {
                    i++;
                    continue;
                }
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                // 将此数字交换至对应索引位置
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }

            return -1;
        }

    }

}
