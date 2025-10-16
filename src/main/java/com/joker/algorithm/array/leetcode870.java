package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>
 * 优势洗牌
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode870 {

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {1, 10, 4, 11};

        Solution01 solution01 = new Solution01();
        int[] advantageCount01 = solution01.advantageCount(nums1, nums2);
        Arrays.stream(advantageCount01).forEach(System.out::println);
    }

    /**
     * 解法一：贪心算法，双指针法，优先队列，（原理，就是田忌赛马）
     */
    private static class Solution01 {

        public int[] advantageCount(int[] nums1, int[] nums2) {
            int n = nums1.length;
            // 给 nums2 降序排序 的优先级队列
            PriorityQueue<int[]> maxpq = new PriorityQueue<>((int[] pair1, int[] pair2) -> pair2[1] - pair1[1]);

            for (int i = 0; i < n; i++) {
                maxpq.offer(new int[]{i, nums2[i]});
            }

            // nums1 升序排序
            Arrays.sort(nums1);

            // nums1[left] 是最小值，nums1[right] 是最大值
            int left = 0, right = n - 1;
            // nums1 排列的结果数组
            int[] res = new int[n];

            while (!maxpq.isEmpty()) {
                int[] pair = maxpq.poll();
                // maxval 是 nums2 中的最大值，i 是对应索引
                int i = pair[0], maxval = pair[1];
                if (maxval < nums1[right]) {
                    // 如果 nums1[right] 能胜过 maxval，那就自己上
                    res[i] = nums1[right];
                    right--;
                } else {
                    // 否则用最小值混一下，养精蓄锐
                    res[i] = nums1[left];
                    left++;
                }
            }

            return res;
        }

    }

}
