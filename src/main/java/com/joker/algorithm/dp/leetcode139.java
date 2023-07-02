package com.joker.algorithm.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 单词拆分
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode139 {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        Solution01 solution01 = new Solution01();
        boolean result01 = solution01.wordBreak(s, wordDict);
        System.out.println(result01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            // dp[i] 表示  s 的前 i 位字符串使用可以用 wordDict 的单词表示
            boolean[] dp = new boolean[s.length() + 1];
            // base case
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    // 遍历 s[0,i-1] 的分割点
                    // 状态转移方程 dp[i]= dp[j] && check(s[j..i−1])
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }

    }

}
