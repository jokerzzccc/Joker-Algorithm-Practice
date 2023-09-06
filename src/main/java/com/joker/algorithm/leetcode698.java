package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 划分为k个相等的子集
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/1
 */
public class leetcode698 {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.canPartitionKSubsets(nums, k));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.canPartitionKSubsets(nums, k));

    }

    /**
     * 解法一：回溯 + 贪心 + 剪枝
     */
    private static class Solution01 {

        /**
         * 每个子集看成一个桶，里面装的 nums 元素，看成一个球
         * buckets[i] 表示：第 i 个子集的当前和
         */
        private int[] buckets;

        /**
         * 目标子集和
         */
        private int target;

        /**
         * k 值，即需要分成的子集数量，
         */
        private int bucketCount;

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int n = nums.length;
            if (n < k) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) {
                return false;
            }

            target = sum / k;
            bucketCount = k;
            buckets = new int[bucketCount];

            // 降序排序
            // 增加剪枝二的命中概率
            nums = Arrays.stream(nums)
                    .boxed()
                    .sorted((a, b) -> b - a)
                    .mapToInt(o -> o)
                    .toArray();

            return backTracking(nums, 0);
        }

        private boolean backTracking(int[] nums, int index) {
            // 结束条件，遍历完数组的时候
            if (index == nums.length) {
                return true;
            }

            // 遍历所有桶（横向）
            for (int i = 0; i < bucketCount; i++) {
                // 剪枝一：值相同的相邻树枝，只遍历第一条
                // 如果当前桶和上一个桶内的元素和相等，则跳过
                // 原因：如果元素和相等，那么 nums[index] 选择上一个桶和选择当前桶可以得到的结果是一致的
                if (i > 0 && buckets[i] == buckets[i - 1]) {
                    continue;
                }
                // 剪枝二：当前桶溢出了
                // 放入球后超过 target 的值，选择一下桶
                if (buckets[i] + nums[index] > target) {
                    continue;
                }

                // 做选择, 放入 i 号桶
                buckets[i] += nums[index];
                // 递归，每层为第 index 个球做选择（竖向）
                if (backTracking(nums, index + 1)) {
                    return true;
                }
                // 撤销选择
                buckets[i] -= nums[index];
            }

            return false;
        }

    }

    /**
     * 解法二：回溯 + 贪心 + 剪枝
     */
    private static class Solution02 {

        /**
         * 目标子集和
         */
        private int target;

        /**
         * k 值，即需要分成的子集数量，
         */
        private int subsetTotalCount;

        private int n;
        private int[] numsIn;

        /**
         * visited[i] 表示 nums[i] 是否已经使用
         */
        private boolean[] visited;

        public boolean canPartitionKSubsets(int[] nums, int k) {
            n = nums.length;
            if (n < k) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) {
                return false;
            }

            target = sum / k;
            subsetTotalCount = k;
            visited = new boolean[n];
            numsIn = nums;
            Arrays.sort(numsIn);

            // 降序传入，
            // 增加剪枝的命中概率
            return backTracking(n - 1, 0, 0);
        }

        /**
         * @param index nums 索引下标
         * @param curSubSum 当前集合的元素和（初始值为 0）
         * @param completedSubCnt 是已凑成多少个总和为 t 的集合（初始值为 0，当 curSubSum=k 时，我们搜索到了合法方案，返回 true，
         * 否则对 completedSubCnt 进行加一操作，并将 cur 置零，搜索下个集合）
         */
        private boolean backTracking(int index, int curSubSum, int completedSubCnt) {
            // 如果已经找到 k 组集合满足条件则返回 true
            if (completedSubCnt == subsetTotalCount) {
                return true;
            }
            // 如果当前累加值等于 target，则完成了一个集合的寻找
            // 从头开始寻找下一个子集
            if (curSubSum == target) {
                return backTracking(n - 1, 0, completedSubCnt + 1);
            }

            // 当前子集对每一个元素进行选择
            // 遍历所有元素（横向），同一层为不同的 nums 元素
            for (int i = index; i >= 0; i--) {
                // 可行性剪枝：
                // 当前位置已经被访问或者当前位置的值加上之前的值超过 target 则跳过
                if (visited[i] || curSubSum + numsIn[i] > target) {
                    continue;
                }

                // 做选择：设置当前位置被访问
                visited[i] = true;
                // 深度搜索左侧的值是否满足条件
                // 竖向一条路径上，为一个集合的值。
                if (backTracking(i - 1, curSubSum + numsIn[i], completedSubCnt)) {
                    return true;
                }
                // 撤销选择：
                visited[i] = false;

                // 可行性剪枝：
                // 因为最后的满足条件是数组中的所有元素都要用上，
                // 如果该次搜索没找到可以选择的值，则可以直接返回 false
                if (curSubSum == 0) {
                    return false;
                }

            }

            return false;
        }

    }

}
