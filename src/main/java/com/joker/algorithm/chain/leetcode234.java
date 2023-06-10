package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 回文链表
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode234 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(1);
        ListNode node06 = new ListNode(2, node05);
        ListNode node07 = new ListNode(2, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        boolean palindrome = solution01.isPalindrome(node10);
        System.out.println(palindrome);

    }

    /**
     * 解法一：快慢指针
     */
    private static class Solution01 {

        public boolean isPalindrome(ListNode head) {
            // 1、先通过 [双指针技巧] 中的快慢指针来找到链表的中点**：
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // 如果`fast`指针没有指向`null`，说明链表长度为奇数，`slow`还要再前进一步**：
            if (fast != null) {
                slow = slow.next;
            }

            // 2、从`slow`开始反转后面的链表，
            ListNode left = head;
            ListNode right = reverse(slow);

            // 3、现在就可以开始比较回文串了
            while (right != null) {
                if (left.val != right.val) {
                    return false;
                }
                left = left.next;
                right = right.next;
            }

            return true;
        }

        /**
         * 反转单链表
         */
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

    }

}
