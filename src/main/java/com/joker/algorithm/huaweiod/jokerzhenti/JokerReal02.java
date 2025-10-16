package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 华为 OD 面试真题（技术二面真题）
 * <p>
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * @author jokerzzccc
 * @date 2025/4/16
 */
public class JokerReal02 {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4

        System.out.println("over");
    }

    public static class LRUCache {

        /**
         * 容量
         */
        private int capacity;

        /**
         * 缓存
         */
        private HashMap<Integer, Integer> cache;
        /**
         * 队列：辅助实现 LRU 删除
         */
        private LinkedList<Integer> LRUQueue;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            LRUQueue = new LinkedList<>();
            System.out.println("null,");
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                LRUQueue.remove((Integer) key);
                LRUQueue.addFirst(key);
                System.out.println(cache.get(key) + ",");
                return cache.get(key);
            } else {
                System.out.println(-1 + ",");
                return -1;
            }
        }

        public void put(int key, int value) {
            System.out.println("null,");
            // 判断是否存在
            if (cache.containsKey(key)) {
                LRUQueue.remove((Integer) key);
                LRUQueue.addFirst(key);
                cache.put(key, value);
                return;
            }
            // 容量判断
            if (capacity <= cache.size()) {
                //  容量不够，先依据 LRU 删除，再加入
                this.deleteLRUKey();
            }

            // 容量足够，直接加入
            cache.put(key, value);
            LRUQueue.addFirst(key);
        }

        public void deleteLRUKey() {
            if (LRUQueue.isEmpty()) {
                return;
            }

            int lruKey = LRUQueue.removeLast();
            cache.remove(lruKey);
        }

    }

}
