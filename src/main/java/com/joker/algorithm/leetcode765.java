package com.joker.algorithm;

/**
 * 情侣牵手
 *
 * @author jokerzzccc
 * @date 2023/11/11
 */
public class leetcode765 {

    public static void main(String[] args) {
        // int[] row = {0, 2, 1, 3};

        int[] row = {0, 2, 1, 3, 9, 10, 4, 6, 5, 7, 8, 11};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minSwapsCouples(row));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.minSwapsCouples(row));
    }

    /**
     * 解法一：并查集
     */
    private static class Solution01 {

        int[] pairs = new int[70];

        public int minSwapsCouples(int[] row) {
            int num = row.length;
            // n: 所有情侣对数量
            int n = num / 2;
            if (n <= 1) {
                return 0;
            }

            // 初始化并查集的父节点，使它们指向自己
            for (int i = 0; i < n; i++) {
                pairs[i] = i;
            }

            // 两个编号的情侣对2整除（向下取整）等于同一个数字
            // 利用这个特性，借助并查集的功能将两个情侣组串成环。
            // 错误的情侣对将会结成环
            for (int i = 0; i < num; i += 2) {
                union(row[i] / 2, row[i + 1] / 2);
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (i == find(i)) {
                    cnt++;
                }
            }

            // 「至少交换的次数 = 所有情侣的对数 - 并查集里连通分量的个数」
            return n - cnt;
        }

        void union(int a, int b) {
            pairs[find(a)] = pairs[find(b)];
        }

        int find(int x) {
            if (pairs[x] != x) {
                pairs[x] = find(pairs[x]);
            }

            return pairs[x];
        }

    }

    /**
     * 解法二：贪心
     */
    private static class Solution02 {

        public int minSwapsCouples(int[] row) {
            int n = row.length;

            int ans = 0;
            // 缓存编号（人的ID）对应下标
            int[] cache = new int[n];
            for (int i = 0; i < n; i++) {
                cache[row[i]] = i;
            }
            for (int i = 0; i < n - 1; i += 2) {
                // 用异或来判断情侣编号相邻
                // 因为情侣对2整除（向下取整）等于同一个数字
                // b 就是 a 对应的情侣
                int a = row[i], b = a ^ 1;
                // 情侣对如果不相邻，则找到其对应情侣，放到 i + 1 的位置上，交换位置
                if (row[i + 1] != b) {
                    // 这对情侣相邻位置的下标
                    int source = i + 1;
                    // 这对情侣的另一半在数组中的下标
                    int target = cache[b];
                    // 更新缓存人对应的下标
                    cache[row[target]] = source;
                    cache[row[source]] = target;
                    // 交换原数组 row 的人
                    swap(row, source, target);

                    ans++;
                }
            }

            return ans;
        }

        void swap(int[] nums, int a, int b) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }

    }

}
