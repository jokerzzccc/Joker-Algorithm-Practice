package com.joker.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 串联所有单词的子串
 * </p>
 *
 * @author admin
 * @date 2023/7/13
 */
public class leetcode30 {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        Solution01 solution01 = new Solution01();
        solution01.findSubstring(s, words).forEach(System.out::println);

    }

    /**
     * 解法一：滑动窗口
     * 核心原理就是：抱枕窗口的长度不变，s 窗口里对应单词出现的频次，与 words 中 word 出现的频次一样，则找到一个解，
     * 因为是任意排列，不用考虑顺序。
     */
    private static class Solution01 {

        public List<Integer> findSubstring(String s, String[] words) {
            int sLen = s.length();
            int wordNum = words.length;
            int wordLen = words[0].length();

            List<Integer> res = new ArrayList<>();

            // 分组，考虑单词划分可以从 0,1,...,wordLen-1 开始
            for (int i = 0; i < wordLen; i++) {
                // 最少保证s中需要划分的单词长度大于 words 字符总长
                if (i + wordNum * wordLen > sLen) {
                    break;
                }

                // differ 表示窗口中的单词频次和 words 中的单词频次之差（s的频次 - words 的频次），如果为0，说明窗口和words是完全匹配的
                // 窗口大小=单词大小*单词数量（即 words 字符总长）
                Map<String, Integer> differ = new HashMap<>();
                // 初始化窗口，窗口长度为 wordNum * wordLen (即 words 字符总长), 依次计算窗口里对 s 每个切分的单词的频次
                for (int j = 0; j < wordNum; j++) {
                    String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                }
                // 遍历 words 中的 word ，对窗口里每个单词计算频度差值
                for (String word : words) {
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    // 差值为0时，移除掉这个word
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }

                // 开始向右滑动窗口，每次滑动一个 wordLen 的长度
                for (int start = i; start < sLen - wordNum * wordLen + 1; start += wordLen) {
                    // start == i 时，就是可以判断初始状态是否匹配，不用滑动
                    if (start != i) {
                        // 窗口每次向右移动，右边进来的单词频度+1，左边出去的单词频度-1
                        // 右边的单词滑进来
                        String word = s.substring(start + (wordNum - 1) * wordLen, start + wordNum * wordLen);
                        differ.put(word, differ.getOrDefault(word, 0) + 1);
                        if (differ.get(word) == 0) {
                            differ.remove(word);
                        }
                        // 左边的单词滑出去
                        word = s.substring(start - wordLen, start);
                        differ.put(word, differ.getOrDefault(word, 0) - 1);
                        if (differ.get(word) == 0) {
                            differ.remove(word);
                        }
                    }

                    // 频次相同时，差值都为0，即全部被移除掉，就代表被匹配上了
                    // 窗口匹配的单词数等于 words 中对应的单词数，添加进结果集
                    if (differ.isEmpty()) {
                        res.add(start);
                    }
                }
            }

            return res;
        }

    }

}
