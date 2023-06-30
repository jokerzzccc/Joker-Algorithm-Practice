package com.joker.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * LRU 缓存
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode146 {

    public static void main(String[] args) {
        LRUCache01 lruCache01 = new LRUCache01(2);
        lruCache01.put(1, 1); // 缓存是 {1=1}
        lruCache01.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache01.get(1);    // 返回 1
        lruCache01.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache01.get(2);    // 返回 -1 (未找到)
        lruCache01.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache01.get(1);    // 返回 -1 (未找到)
        lruCache01.get(3);    // 返回 3
        lruCache01.get(4);    // 返回 4


        System.out.println("================================");

        LRUCache02 lruCache02 = new LRUCache02(2);
        lruCache02.put(1, 1); // 缓存是 {1=1}
        lruCache02.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache02.get(1);    // 返回 1
        lruCache02.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache02.get(2);    // 返回 -1 (未找到)
        lruCache02.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache02.get(1);    // 返回 -1 (未找到)
        lruCache02.get(3);    // 返回 3
        lruCache02.get(4);    // 返回 4
    }

    /**
     * 解法一：自己构造：哈希表 + 双向链表
     */
    private static class LRUCache01 {

        /**
         * 双向链表结点
         */
        class Node {

            private int key;
            private int value;
            private Node prev;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

        }

        /**
         * 双向链表
         */
        class DoubleLinkedList {

            // 链表的头尾结点
            private Node head;
            private Node tail;
            // 链表的大小
            private int size;

            public DoubleLinkedList() {
                // 初始化双向链表的数据
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            // 在链表尾部添加节点 x，时间 O(1)
            public void addLast(Node node) {
                node.prev = tail.prev;
                node.next = tail;
                tail.prev.next = node;
                tail.prev = node;
                size++;
            }

            // 删除链表中的 x 节点（x 一定存在）
            // 由于是双链表且给的是目标 Node 节点，时间 O(1)
            public void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            // 删除链表中第一个节点，并返回该节点，时间 O(1)
            public Node removeFirst() {
                if (head.next == null) {
                    return null;
                }
                Node first = head.next;
                remove(first);
                return first;
            }

            // 返回链表长度，时间 O(1)
            public int getSize() {
                return size;
            }

        }

        // key -> Node(key, val)
        private HashMap<Integer, Node> map;
        // Node(k1, v1) <-> Node(k2, v2)...
        private DoubleLinkedList cache;
        // 最大容量
        private int capacity;

        public LRUCache01(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            cache = new DoubleLinkedList();

        }

        /* 将某个 key 提升为最近使用的 */
        private void makeRecently(int key) {
            Node node = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(node);
            // 重新插到队尾
            cache.addLast(node);
        }

        /* 添加最近使用的元素 */
        public void addRecently(int key, int val) {
            Node node = new Node(key, val);

            // 链表尾部就是最近使用的元素
            cache.addLast(node);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, node);
        }

        /* 删除某一个 key */
        private void deleteKey(int key) {
            Node node = map.get(key);
            // 从链表中删除
            cache.remove(node);
            // 从 map 中删除
            map.remove(key);
        }

        /* 删除最近最久未使用的元素 */
        public void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node node = cache.removeFirst();
            // 同时别忘了从 map 中删除它的 key
            map.remove(node.key);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, value);
                return;
            }

            if (capacity == cache.size) {
                // 删除最久未使用的数据
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, value);
        }

    }

    /**
     * 解法二：使用本身的 LinkedHashMap
     */
    private static class LRUCache02 extends LinkedHashMap<Integer, Integer> {

        int capacity;

        public LRUCache02(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int val) {
            super.put(key, val);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

    }

}
