package com.joker.algorithm.chain;

/**
 * <p>
 * 删除排序链表中的重复元素
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode83 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(3);
        ListNode node06 = new ListNode(3, node05);
        ListNode node07 = new ListNode(2, node06);
        ListNode node08 = new ListNode(1, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.deleteDuplicates(node10);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }
    }

    /**
     * 解法一：快慢指针
     * 可以类比 leetcode26 数组版
     */
    private static class Solution01 {

        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode slow = head, fast = head;

            while (fast != null) {
                if (fast.val != slow.val) {
                    // nums[slow] = nums[fast];
                    slow.next = fast;
                    // slow++;
                    slow = slow.next;
                }
                // fast++
                fast = fast.next;
            }
            // 断开与后面重复元素的连接
            slow.next = null;
            return head;

        }

    }

}
