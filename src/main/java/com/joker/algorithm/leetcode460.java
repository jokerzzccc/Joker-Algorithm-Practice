package com.joker.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * LFU 缓存
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode460 {

    public static void main(String[] args) {
        LFUCache01 lfu = new LFUCache01(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3

    }

    /**
     * 解法一：双哈希表，
     * 时间复杂度最优
     */
    private static class LFUCache01 {

        /**
         * 当前缓存最少使用的频率（服务于删除操作）
         */
        private int minFreq;

        /**
         * 容量
         */
        private int capacity;
        /**
         * 键值 key -> freqTable 的结点
         */
        Map<Integer, Node> keyTable;
        /**
         * 频率 -> 同一频率构成的双向链表
         */
        Map<Integer, DoublyLinkedList> freqTable;

        public LFUCache01(int capacity) {
            this.minFreq = 0;
            this.capacity = capacity;
            this.keyTable = new HashMap<>();
            this.freqTable = new HashMap<>();

        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!keyTable.containsKey(key)) {
                return -1;
            }
            Node node = keyTable.get(key);
            int val = node.val, freq = node.freq;

            // 从原 freq 频率中删除
            freqTable.get(freq).remove(node);
            // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minFreq == freq) {
                    minFreq++;
                }
            }

            // 插入到 freq + 1 中
            DoublyLinkedList freqPlusList = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            freqPlusList.addFirst(new Node(key, val, freq + 1));
            freqTable.put(freq + 1, freqPlusList);
            keyTable.put(key, freqTable.get(freq + 1).getHead());

            return val;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            // 缓存中未包含 key
            if ((!keyTable.containsKey(key))) {
                // 缓存已满，需要进行删除操作
                if (keyTable.size() == capacity) {
                    // 删除最小频率的结点
                    // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                    Node minNode = freqTable.get(minFreq).getTail();
                    keyTable.remove(minNode.key);
                    freqTable.get(minFreq).remove(minNode);
                    if (freqTable.get(minFreq).size == 0) {
                        freqTable.remove(minFreq);
                    }
                }

                DoublyLinkedList newList = freqTable.getOrDefault(1, new DoublyLinkedList());
                newList.addFirst(new Node(key, value, 1));
                freqTable.put(1, newList);
                keyTable.put(key, freqTable.get(1).getHead());
                minFreq = 1;
            } else { // 缓存中已包含 key
                // 与 get 操作基本一致，除了需要更新缓存的值
                Node node = keyTable.get(key);
                int freq = node.freq;
                freqTable.get(freq).remove(node);
                if (freqTable.get(freq).size == 0) {
                    freqTable.remove(freq);
                    if (minFreq == freq) {
                        minFreq += 1;
                    }
                }
                DoublyLinkedList freqPlusList = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
                freqPlusList.addFirst(new Node(key, value, freq + 1));
                freqTable.put(freq + 1, freqPlusList);
                keyTable.put(key, freqTable.get(freq + 1).getHead());

            }

        }

        /**
         * 双向链表节点
         */
        class Node {

            int key, val, freq;
            Node prev, next;

            Node() {
                this(-1, -1, 0);
            }

            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }

        }

        /**
         * 双向链表
         */
        class DoublyLinkedList {

            // 虚拟头尾结点
            Node dummyHead, dummyTail;
            // 链表大小
            int size;

            DoublyLinkedList() {
                dummyHead = new Node();
                dummyTail = new Node();
                dummyHead.next = dummyTail;
                dummyTail.prev = dummyHead;
                size = 0;
            }

            public void addFirst(Node node) {
                Node prevHead = dummyHead.next;
                node.prev = dummyHead;
                dummyHead.next = node;
                node.next = prevHead;
                prevHead.prev = node;
                size++;
            }

            public void remove(Node node) {
                Node prev = node.prev, next = node.next;
                prev.next = next;
                next.prev = prev;
                size--;
            }

            public Node getHead() {
                return dummyHead.next;
            }

            public Node getTail() {
                return dummyTail.prev;
            }

        }

    }

}
