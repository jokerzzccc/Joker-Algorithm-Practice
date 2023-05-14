package com.joker.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 复原 IP 地址
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/14
 */
public class leetcode93 {

    public static void main(String[] args) {
        String s = "25525511135";
        Solution solution = new Solution();
        List<String> ipAddresses = solution.restoreIpAddresses(s);
        System.out.println(ipAddresses);
    }

    static class Solution {

        /**
         * 结果：ip 地址
         */
        List<String> res = new ArrayList<>();
        /**
         * IP 地址的段数
         */
        static final int SEG_COUNT = 4;
        /**
         * 存放每一段
         */
        int[] segments = new int[SEG_COUNT];

        public List<String> restoreIpAddresses(String s) {
            if (s.length() < 4 || s.length() > 12) {
                return res;
            }
            int[] nums = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                nums[i] = s.charAt(i) - '0';
            }
            backTrack(nums, 0, 0);
            return res;
        }

        void backTrack(int[] nums, int segId, int segStart) {
            // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
            if (segId == SEG_COUNT) {
                if (segStart == nums.length) {
                    StringBuilder ipAddr = new StringBuilder();
                    for (int i = 0; i < SEG_COUNT; i++) {
                        ipAddr.append(segments[i]);
                        if (i != SEG_COUNT - 1) {
                            ipAddr.append(".");
                        }
                    }
                    res.add(ipAddr.toString());
                }
                return;
            }

            // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
            if (segStart == nums.length) {
                return;
            }
            // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
            if (nums[segStart] == 0) {
                segments[segId] = 0;
                backTrack(nums, segId + 1, segStart + 1);
            }

            int addr = 0;
            for (int segEnd = segStart; segEnd < nums.length; ++segEnd) {
                addr = addr * 10 + nums[segEnd];
                if (addr > 0 && addr <= 255) {
                    segments[segId] = addr;
                    // 当剩余的字符串数量比剩余的段 * 3还大时，就可以剪枝了
                    if ((SEG_COUNT - segId - 1) * 3 < (nums.length - segEnd - 1)) {
                        continue;
                    }
                    backTrack(nums, segId + 1, segEnd + 1);
                } else {
                    break;
                }
            }
        }

    }

}
