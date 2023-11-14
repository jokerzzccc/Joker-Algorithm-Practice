package com.joker.algorithm;

/**
 * 区域和检索 - 数组可修改
 *
 * @author jokerzzccc
 * @date 2023/11/13
 */
public class leetcode307 {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});

        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1,2,5]
        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 2 + 5 = 8
    }

    /**
     * 解法一：树状数组
     * 定义：动态维护序列前缀和的数据结构（序列下标从 1 开始）
     */
    private static class NumArray {

        /**
         * 树状数组：
         * 参考学习：https://oi-wiki.org/ds/fenwick/
         */
        private int[] tree;

        /**
         * 返回参数转为二进制后,最低位 1 的位置所代表的数值
         */
        int lowbit(int x) {
            return x & -x;
        }

        /**
         * 区间查询：查询前 index 个元素的前缀和
         */
        int query(int index) {
            int ans = 0;
            for (int i = index; i > 0; i -= lowbit(i)) ans += tree[i];
            return ans;
        }

        /**
         * 单点修改: 在树状数组 index 位置中增加值 val
         */
        void add(int index, int val) {
            for (int i = index; i <= n; i += lowbit(i)) {
                tree[i] += val;
            }
        }

        /**
         * 入参数组
         */
        private int[] nums;
        /**
         * nums.length
         */
        private int n;

        public NumArray(int[] nums) {
            this.nums = nums;
            n = nums.length;
            tree = new int[n + 1];
            // 初始化「树状数组」 tree，要默认数组是从 1 开始
            for (int i = 0; i < n; i++) {
                this.add(i + 1, nums[i]);
            }

        }

        public void update(int index, int val) {
            // 原有的值是 nums[i]，要使得修改为 val，需要增加 val - nums[i]
            this.add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return query(right + 1) - query(left);
        }

    }

}
