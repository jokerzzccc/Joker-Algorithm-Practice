package com.joker.algorithm.chain;

/**
 * <p>
 * 两数相加
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        Solution01 solution01 = new Solution01();
        ListNode result = solution01.addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

    /**
     * 解法一：模拟，虚拟头结点
     */
    private static class Solution01 {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2;
            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            int carry = 0;
            while (p1 != null || p2 != null) {
                int x = (p1 != null) ? p1.val : 0;
                int y = (p2 != null) ? p2.val : 0;
                int curSum = x + y + carry;
                int newVal = curSum % 10;
                carry = curSum / 10;
                p.next = new ListNode(newVal);
                p1 = (p1 != null) ? p1.next : null;
                p2 = (p2 != null) ? p2.next : null;
                p = p.next;
            }

            return dummy.next;
        }

    }

}
