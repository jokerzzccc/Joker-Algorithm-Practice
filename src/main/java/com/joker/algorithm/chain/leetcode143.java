package com.joker.algorithm.chain;

/**
 * <p>
 * 重排链表
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode143 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution01 solution01 = new Solution01();
        solution01.reorderList(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    /**
     * 解法一：寻找链表中点 + 链表逆序 + 合并链表
     * 目标链表即为将原链表的左半端和反转后的右半端合并后的结果
     */
    private static class Solution01 {

        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            // 取得链表中点
            ListNode mid = middleNode(head);

            // 以中点为界，分为左右两个链表
            ListNode left = head;
            ListNode right = mid.next;
            // 因为左右需要分开，中间结点指向 null
            mid.next = null;
            // 反转右链表
            right = reverseList(right);

            // 交替合并左右两个链表
            mergeList(left, right);
        }

        /**
         * 交替合并两个链表
         */
        private void mergeList(ListNode list1, ListNode list2) {
            ListNode l1_tmp;
            ListNode l2_tmp;
            while (list1 != null && list2 != null) {
                l1_tmp = list1.next;
                l2_tmp = list2.next;

                list1.next = list2;
                list1 = l1_tmp;

                list2.next = list1;
                list2 = l2_tmp;
            }
        }

        /**
         * 反转链表
         */
        private ListNode reverseList(ListNode head) {
            // 反转之后的头结点
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }

            return prev;
        }

        /**
         * 用快慢指针的方法，寻找链表的中点。
         */
        private ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

    }

}
