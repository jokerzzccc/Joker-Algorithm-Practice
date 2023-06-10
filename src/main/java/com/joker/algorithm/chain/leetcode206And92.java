package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 反转链表&反转链表II
 * </p>
 *
 * @author admin
 * @date 2023/6/9
 */
public class leetcode206And92 {

    /**
     * 反转链表
     */
    private static class leetcode206 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.reverseList(node10);
            while (node01 != null) {
                System.out.println(node01.val);
                node01 = node01.next;
            }

//            Solution02 solution02 = new Solution02();
//            ListNode node02 = solution02.reverseList(node10);
//            while (node02 != null) {
//                System.out.println(node02.val);
//                node02 = node02.next;
//            }

        }

        /**
         * 解法一：递归
         */
        private static class Solution01 {

            public ListNode reverseList(ListNode head) {
                if (head == null || head.next == null) {
                    return head;
                }
                ListNode last = reverseList(head.next);
                head.next.next = head;
                // head 成了反转之后的尾节点，所以要指向 null
                head.next = null;
                return last;

            }

        }

        /**
         * 解法二：迭代，双指针
         */
        private static class Solution02 {

            public ListNode reverseList(ListNode head) {
                // 反转之后的头节点
                ListNode prev = null;
                ListNode curr = head;
                while (curr != null) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                return prev;
            }

        }

    }

    /**
     * 反转链表II
     */
    private static class leetcode92 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            int left = 2, right = 4;

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.reverseBetween(node10, left, right);
            // [1,4,3,2,5]
            while (node01 != null) {
                System.out.println(node01.val);
                node01 = node01.next;
            }

            Solution02 solution02 = new Solution02();
            ListNode node02 = solution02.reverseBetween(node10, left, right);
            while (node02 != null) {
                System.out.println(node02.val);
                node02 = node02.next;
            }

        }

        /**
         * 解法一：递归
         */
        private static class Solution01 {

            public ListNode reverseBetween(ListNode head, int left, int right) {
                // base case
                if (left == 1) {
                    return reverseN(head, right);
                }
                // 前进到反转的起点触发 base case
                head.next = reverseBetween(head.next, left - 1, right - 1);

                return head;
            }

            /**
             * 后驱节点
             */
            ListNode successor = null;

            /**
             * 反转以 head 为起点的 n 个节点，返回新的头结点
             */
            private ListNode reverseN(ListNode head, int n) {
                if (n == 1) {
                    // 记录第 n + 1 个节点
                    successor = head.next;
                    return head;
                }
                // 以 head.next 为起点，需要反转前 n - 1 个节点
                ListNode last = reverseN(head.next, n - 1);

                head.next.next = head;
                // 让反转之后的 head 节点和后面的节点连起来
                head.next = successor;
                return last;
            }

        }

        /**
         * 解法二：迭代，穿针引线，头插法
         * 整体思想是：在需要反转的区间里，每遍历到一个节点，让这个新节点来到反转部分的起始位置。
         * 只需要遍历一次
         */
        private static class Solution02 {

            public ListNode reverseBetween(ListNode head, int left, int right) {
                // 虚拟头结点
                ListNode dummy = new ListNode(-1);
                dummy.next = head;
                // pre：永远指向待反转区域的第一个节点 left 的前一个节点，在反转区间循环过程中不变。
                ListNode pre = dummy;
                for (int i = 0; i < left - 1; i++) {
                    pre = pre.next;
                }
                // cur：指向待反转区域的第一个节点 left；
                ListNode cur = pre.next;
                // next：永远指向 curr 的下一个节点，循环过程中，cur 变化以后 next 会变化；
                ListNode next;
                // 开始操作
                for (int i = 0; i < right - left; i++) {
                    next = cur.next;
                    cur.next = next.next;
                    next.next = pre.next;
                    pre.next = next;
                }

                return dummy.next;
            }

        }

    }

}
