package com.joker.algorithm;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 奇偶链表
 * </p>
 *
 * @author admin
 * @date 2023/7/31
 */
public class leetcode328 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.oddEvenList(head);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一；分离奇偶后合并
     */
    private static class Solution01 {

        public ListNode oddEvenList(ListNode head) {
            if (head == null) {
                return null;
            }

            // 偶数链表头结点
            ListNode evenHead = head.next;
            // 奇，偶，移动指针
            ListNode odd = head, even = evenHead;

            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }

            // 偶链表加到奇链表尾部
            odd.next = evenHead;

            return head;
        }

    }

}
