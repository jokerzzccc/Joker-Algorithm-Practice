package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 链表的中间节点
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/8
 */
public class leetcode876 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.middleNode(node10);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：快慢指针法
     */
    private static class Solution01 {

        public ListNode middleNode(ListNode head) {
            // 快慢指针初始化指向 head
            ListNode slow = head;
            ListNode fast = head;
            // 快指针走到末尾时停止
            while (fast!=null && fast.next!=null) {
                // 慢指针走一步，快指针走两步
                slow = slow.next;
                fast = fast.next.next;
            }
            // 慢指针指向中点
            return slow;

        }

    }

}
