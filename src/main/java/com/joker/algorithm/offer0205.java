package com.joker.algorithm;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 链表求和
 * </p>
 *
 * @author admin
 * @date 2023/7/31
 */
public class offer0205 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(6);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(2);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.addTwoNumbers(l1, l2);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：模拟
     */
    private static class Solution01 {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 虚拟答案头节点
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                if (l1 != null) {
                    carry += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    carry += l2.val;
                    l2 = l2.next;
                }

                // 构建新节点
                pre.next = new ListNode(carry % 10);
                pre = pre.next;
                carry /= 10;
            }

            return dummy.next;
        }

    }

}
