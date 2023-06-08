package com.joker.algorithm;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 删除链表的倒数第 N 个节点
 * </p>
 *
 * @author admin
 * @date 2023/6/8
 */
public class leetcode19 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);
        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.removeNthFromEnd(node10, k);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：遍历一次链表，双指针
     * 要删除倒数第 n 个节点，就得获得倒数第 n + 1 个节点的引用
     */
    private static class Solution01 {

        public ListNode removeNthFromEnd(ListNode head, int n) {

            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode p1 = dummy;
            for (int i = 0; i < n + 1; i++) {
                p1 = p1.next;
            }
            ListNode p2 = dummy;
            while (p1 != null) {
                p1 = p1.next;
                p2 = p2.next;
            }

            p2.next = p2.next.next;
            return dummy.next;
        }

    }

}
