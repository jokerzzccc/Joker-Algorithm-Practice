package com.joker.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 数青蛙
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode1419 {

    public static void main(String[] args) {
        String croakOfFrogs = "croakcroak";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minNumberOfFrogs(croakOfFrogs));
    }

    /**
     * 解法一：计数
     */
    private static class Solution01 {

        private final Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};

        public int minNumberOfFrogs(String croakOfFrogs) {
            int strLen = croakOfFrogs.length();
            if (strLen % 5 != 0) {
                return -1;
            }

            // 最小需要的青蛙数量
            int minFrog = 0;
            // 表示现在正在发出蛙鸣声的青蛙数目
            int frogCroakingNum = 0;
            // cnt[i] 表示已经发出一次有效蛙鸣字符 i 的青蛙个数
            int[] cnt = new int[4];

            for (int i = 0; i < strLen; i++) {
                char c = croakOfFrogs.charAt(i);
                int t = map.get(c);

                if (t == 0) {
                    cnt[t]++;
                    frogCroakingNum++;
                    // 更新答案
                    if (frogCroakingNum > minFrog) {
                        minFrog = frogCroakingNum;
                    }
                } else {
                    if (cnt[t - 1] == 0) {
                        // 因为必须有序发出蛙鸣，如果前一个都没有，说明不符合条件
                        return -1;
                    }
                    cnt[t - 1]--;

                    if (t == 4) {
                        // 已经发出完整蛙鸣了
                        frogCroakingNum--;
                    } else {
                        cnt[t]++;
                    }
                }

            }

            // 还有未蛙鸣完整的，表示，字符串不符合条件
            if (frogCroakingNum > 0) {
                return -1;
            }

            return minFrog;
        }

    }

}
