package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * 自由之路
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/5
 */
public class leetcode514 {

    public static void main(String[] args) {
        String ring = "godding", key = "gd";

        Solution01 solution01 = new Solution01();
        int rotateSteps01 = solution01.findRotateSteps(ring, key);
        System.out.println(rotateSteps01);

        Solution02 solution02 = new Solution02();
        int rotateSteps02 = solution02.findRotateSteps(ring, key);
        System.out.println(rotateSteps02);

    }

    /**
     * 解法一：动态规划 + 递归
     */
    private static class Solution01 {

        /**
         * K-字符，V-索引列表
         */
        Map<Character, List<Integer>> charToIndex = new HashMap<>();
        /**
         * 当圆盘指针指向 `ring[i]` 时，输入字符串 `key[j..]` 至少需要 `dp(ring, i, key, j)` 次操作
         */
        int[][] memo;

        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();
            // 备忘录全部初始化为0
            memo = new int[m][n];
            // 记录圆环上字符到索引的映射
            for (int i = 0; i < m; i++) {
                char c = ring.charAt(i);
                if (!charToIndex.containsKey(c)) {
                    charToIndex.put(c, new LinkedList<>());
                }
                charToIndex.get(c).add(i);
            }

            // 圆盘指针最初指向 12 点钟方向，
            // 从第一个字符开始输入 key
            int dp = dp(ring, 0, key, 0);
            Arrays.stream(memo).forEach(row -> System.out.println(Arrays.toString(row)));
            return dp;
        }

        /**
         * @param ring ring
         * @param i ring 字符索引
         * @param key key
         * @param j key 字符索引
         * @return 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
         */
        private int dp(String ring, int i, String key, int j) {
            // base case
            if (j == key.length()) {
                return 0;
            }
            // 查找备忘录，避免重叠子问题
            if (memo[i][j] != 0) {
                return memo[i][j];
            }
            int m = ring.length();
            // 做选择
            int res = Integer.MAX_VALUE;
            // ring 上可能有多个字符 key[j]
            for (Integer k : charToIndex.get(key.charAt(j))) {
                // 拨动指针的次数
                int delta = Math.abs(k - i);
                // 选择顺时针还是逆时针
                delta = Math.min(delta, m - delta);
                // 将指针拨到 ring[k]，继续输入 key[j+1..]
                int subProblem = dp(ring, k, key, j + 1);
                // 选择「整体」操作次数最少的
                // 加一是因为按动按钮也是一次操作
                res = Math.min(res, 1 + delta + subProblem);
            }
            // 将结果存入备忘录
            memo[i][j] = res;
            return res;
        }

    }

    /**
     * 解法二：动态规划 + 递推（从后往前）
     */
    private static class Solution02 {

        public int findRotateSteps(String ring, String key) {
            int n = ring.length(), m = key.length();
            // pos: 字符 i 在 ring 中出现的位置下标集合
            List<Integer>[] pos = new List[26];
            for (int i = 0; i < 26; ++i) {
                pos[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n; ++i) {
                pos[ring.charAt(i) - 'a'].add(i);
            }
            // 定义 dp[i][j] 表示从前往后拼写出 key 的第 i 个字符，ring 的第 j 个字符与12：00方向对齐的最少步数（下标均从0开始)。
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dp[i], 0x3f3f3f);
            }
            for (int i : pos[key.charAt(0) - 'a']) {
                dp[0][i] = Math.min(i, n - i) + 1;
            }
            for (int i = 1; i < m; ++i) {
                for (int j : pos[key.charAt(i) - 'a']) {
                    for (int k : pos[key.charAt(i - 1) - 'a']) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                    }
                }
            }
            return Arrays.stream(dp[m - 1]).min().getAsInt();

        }

    }

}
