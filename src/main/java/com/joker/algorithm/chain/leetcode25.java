package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * K 个一组反转链表
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode25 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        int k = 2;

//        Solution01 solution01 = new Solution01();
//        ListNode node01 = solution01.reverseKGroup(node10, k);
//        while (node01 != null) {
//            System.out.println(node01.val);
//            node01 = node01.next;
//        }

        Solution02 solution02 = new Solution02();
        ListNode node02 = solution02.reverseKGroup(node10, k);
        while (node02 != null) {
            System.out.println(node02.val);
            node02 = node02.next;
        }

    }

    /**
     * 解法一：迭代
     */
    private static class Solution01 {

        public ListNode reverseKGroup(ListNode head, int k) {
            // 虚拟头结点，
            // 减少边界情况处理
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            while (head != null) {
                ListNode tail = prev;
                // 查看剩余部分长度是否 >= K
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        return dummy.next;
                    }
                }

                ListNode next = tail.next;
                ListNode[] reverse = reverseRange(head, tail);
                head = reverse[0];
                tail = reverse[1];
                // 把子链表重新接回原链表
                prev.next = head;
                tail.next = next;
                // 为下一个区间，重置指针
                prev = tail;
                head = tail.next;
            }

            return dummy.next;
        }

        /**
         * 反转一个区间 [head, tail] 的链表，
         *
         * @return 区间链表反转的头尾节点
         */
        private ListNode[] reverseRange(ListNode head, ListNode tail) {

            ListNode prev = tail.next;
            ListNode curr = head;
            ListNode next;
            while (prev != tail) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return new ListNode[]{tail, head};
        }

    }

    /**
     * 解法二：递归
     */
    private static class Solution02 {

        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode a, b;
            a = b = head;
            // 判断后面的是否还有 K 个节点
            for (int i = 0; i < k; i++) {
                // 不足 k 个，不需要反转，base case
                if (b == null) {
                    return head;
                }
                b = b.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverseRange(a, b);
            // 递归反转后续链表并连接起来
            a.next = reverseKGroup(b, k);
            return newHead;

        }

        /**
         * 反转区间 [a, b) 的元素，注意是左闭右开
         */
        private ListNode reverseRange(ListNode a, ListNode b) {
            ListNode prev = null;
            ListNode curr = a;
            ListNode next = a;
            while (curr != b) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            // 返回反转后的头结点
            return prev;
        }

    }

}
