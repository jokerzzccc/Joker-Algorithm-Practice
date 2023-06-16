package com.joker.algorithm.array;

import java.util.*;

/**
 * <p>
 * O(1) 时间插入、删除和获取随机元素
 * </p>
 *
 * @author admin
 * @date 2023/6/16
 */
public class leetcode380 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        ArrayList<Object> res = new ArrayList<>();
        res.add(randomizedSet.insert(1));
        res.add(randomizedSet.remove(2));
        res.add(randomizedSet.insert(2));
        res.add(randomizedSet.getRandom());
        res.add(randomizedSet.remove(1));
        res.add(randomizedSet.insert(2));
        res.add(randomizedSet.getRandom());
        res.stream().forEach(System.out::println);

    }

    /**
     * 解法一：变长数组 + 哈希表
     */
    private static class RandomizedSet {

        // 存储元素的值
        List<Integer> nums;
        // 记录每个元素对应在 nums 中的索引 K-value, V-index
        Map<Integer, Integer> valToIndex;

        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();

        }

        /**
         * 如果 val 不存在集合中，则插入并返回 true，否则直接返回 false
         */
        public boolean insert(int val) {
            // 若 val 不存在，插入到 nums 尾部，
            // 并记录 val 对应的索引值
            if (!valToIndex.containsKey(val)) {
                nums.add(val);
                valToIndex.put(val, nums.size() - 1);
                return true;
            }
            // 若 val 已存在，不用再插入
            return false;

        }

        /**
         * 如果 val 在集合中，则删除并返回 true，否则直接返回 false
         */
        public boolean remove(int val) {
            // 若 val 不存在，不用再删除
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            // 先拿到 val 的索引
            Integer index = valToIndex.get(val);
            // 将最后一个元素对应的索引修改为 index
            valToIndex.put(nums.get(nums.size() - 1), index);
            // 交换 val 和最后一个元素
            Collections.swap(nums, index, nums.size() - 1);
            // 在数组中删除元素 val
            nums.remove(nums.size() - 1);
            // 删除元素 val 对应的索引
            valToIndex.remove(val);
            return true;

        }

        /**
         * 从集合中等概率地随机获得一个元素
         */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }

    }

}
