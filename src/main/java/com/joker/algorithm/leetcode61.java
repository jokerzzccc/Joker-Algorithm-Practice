package com.joker.algorithm;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 旋转链表
 * </p>
 *
 * @author admin
 * @date 2023/8/3
 */
public class leetcode61 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode rotateRight01 = solution01.rotateRight(head, k);
        while (rotateRight01 != null) {
            System.out.println(rotateRight01.val);
            rotateRight01 = rotateRight01.next;
        }

    }

    /**
     * 解法一：闭合为环
     * 新链表的最后一个节点为原链表的第 n − (k mod n) 个节点（从 1 开始计数）。
     * 可以先将给定的链表连接成环，然后将指定位置断开
     */
    private static class Solution01 {

        public ListNode rotateRight(ListNode head, int k) {
            if (k == 0 || head == null || head.next == null) {
                return head;
            }

            // 链表长度
            int n = 1;
            // 原链表的尾结点
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
                n++;
            }

            // add: 原头节点需要向右移动的节点个数
            int add = n - k % n;
            if (add == n) {
                return head;
            }
            // 原来的尾结点指向原来的头结点，形成环
            tail.next = head;
            // 找到断开环的位置，即新链表的尾节点
            while (add > 0) {
                tail = tail.next;
                add--;
            }

            // 新的头结点指向断开环的位置
            ListNode newHead = tail.next;
            // 断开尾部，即，断开环，变回单链表
            tail.next = null;

            return newHead;
        }

    }

}
