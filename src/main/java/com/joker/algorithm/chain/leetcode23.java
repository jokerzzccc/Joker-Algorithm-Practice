package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 合并k个有序链表
 * </p>
 *
 * @author admin
 * @date 2023/6/7
 */
public class leetcode23 {

    public static void main(String[] args) {
        ListNode node01 = new ListNode(5);
        ListNode node02 = new ListNode(4, node01);
        ListNode node03 = new ListNode(1, node02);

        ListNode node05 = new ListNode(4);
        ListNode node06 = new ListNode(3, node05);
        ListNode node07 = new ListNode(1, node06);

        ListNode node08 = new ListNode(6);
        ListNode node10 = new ListNode(2, node08);

        ListNode[] lists = {node03, node07, node10};

        Solution01 solution01 = new Solution01();
        ListNode mergeKLists01 = solution01.mergeKLists(lists);
        while (mergeKLists01 != null) {
            System.out.println(mergeKLists01.val);
            mergeKLists01 = mergeKLists01.next;
        }

//        Solution02 solution02 = new Solution02();
//        ListNode mergeKLists02 = solution02.mergeKLists(lists);
//        while (mergeKLists02 != null) {
//            System.out.println(mergeKLists02.val);
//            mergeKLists02 = mergeKLists02.next;
//        }

    }

    /**
     * 解法一：使用优先队列（PriorityQueue）
     */
    private static class Solution01 {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            // prev 指针,其实就是尾指针
            ListNode prev = dummy;
            // 优先级队列，最小堆
            PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
            // 将 k 个链表的头结点加入最小堆
            for (ListNode head : lists) {
                if (head != null) {
                    priorityQueue.add(head);
                }
            }

            while (!priorityQueue.isEmpty()) {
                // 获取最小节点，接到结果链表中
                ListNode node = priorityQueue.poll();
                prev.next = node;
                if (node.next != null) {
                    priorityQueue.add(node.next);
                }
                // prev 指针不断前进
                prev = prev.next;
            }

            return dummy.next;
        }

    }

    /**
     * 解法二：使用分治法（基于合并两个链表）
     */
    private static class Solution02 {

        public ListNode mergeKLists(ListNode[] lists) {
            return merge(lists, 0, lists.length - 1);
        }

        /**
         * 两两合并，得到最终结果
         */
        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            if (left > right) {
                return null;
            }
            int mid = (left + right) >> 1;
            return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
        }

        private ListNode mergeTwoLists(ListNode a, ListNode b) {
            if (a == null || b == null) {
                return a != null ? a : b;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, aPtr = a, bPtr = b;
            while (aPtr != null && bPtr != null) {
                if (aPtr.val < bPtr.val) {
                    tail.next = aPtr;
                    aPtr = aPtr.next;
                } else {
                    tail.next = bPtr;
                    bPtr = bPtr.next;
                }
                tail = tail.next;
            }
            tail.next = (aPtr != null ? aPtr : bPtr);
            return head.next;
        }

    }

}
