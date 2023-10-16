package com.joker.algorithm;

import java.util.*;

/**
 * 避免洪水泛滥
 *
 * @author jokerzzccc
 * @date 2023/10/13 15:23
 */
public class leetcode1488 {

    public static void main(String[] args) {
        int[] rains = {1, 2, 0, 0, 2, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.avoidFlood(rains)));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[] avoidFlood(int[] rains) {
            int n = rains.length;
            int[] ans = new int[n];
            // 为了通过 leetcode 某些测试用例
            Arrays.fill(ans, 1);

            // 有序集合 st 来存储了那些没有下雨的日子
            TreeSet<Integer> st = new TreeSet<>();
            // mp: K-湖泊，V-下雨日期
            Map<Integer, Integer> mp = new HashMap<>();

            for (int i = 0; i < n; i++) {
                if (rains[i] == 0) {
                    st.add(i);
                } else {
                    ans[i] = -1;
                    // 若第 rains[i] 不是第一次下雨
                    if (mp.containsKey(rains[i])) {
                        // 在有序集合 st 中找到大于等于该湖泊上一次下雨天数的最小索引 idx
                        Integer idx = st.ceiling(mp.get(rains[i]));
                        // 如果 idx 不存在（即没有晴天可以用于抽干），此时不能避免洪水的发生，返回一个空数组
                        if (idx == null) {
                            return new int[0];
                        }
                        ans[idx] = rains[i];
                        st.remove(idx);
                    }

                    mp.put(rains[i], i);
                }
            }

            return ans;
        }

    }

}
