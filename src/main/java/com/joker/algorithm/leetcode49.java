package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * 字母异位词分组
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode49 {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Solution01 solution01 = new Solution01();
        List<List<String>> lists01 = solution01.groupAnagrams(strs);
        lists01.stream().forEach(System.out::println);
    }

    /**
     * 解法一：排序
     */
    private static class Solution01 {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = Arrays.toString(chars);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }

            return new ArrayList<>(map.values());
        }

    }

}
