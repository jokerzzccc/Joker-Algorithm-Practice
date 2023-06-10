package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 链表中倒数第k个节点
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/8
 */
public class offer22 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);
        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.getKthFromEnd(node10, k);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：只遍历一次链表，使用两个指针
     */
    private static class Solution01 {

        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode p1 = head;
            // p1 先走 k 步
            for (int i = 0; i < k; i++) {
                p1 = p1.next;
            }
            ListNode p2 = head;
            // p1 和 p2 同时走 n - k 步
            while (p1 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
            // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
            return p2;
        }

    }

}
