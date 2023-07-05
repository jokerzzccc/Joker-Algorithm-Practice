package com.joker.algorithm.chain;

/**
 * <p>
 * 删除排序链表中的重复元素 II
 * </p>
 *
 * @author admin
 * @date 2023/7/5
 */
public class leetcode82 {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(3);
        root.next.next.next.next = new ListNode(4);
        root.next.next.next.next.next = new ListNode(4);
        root.next.next.next.next.next.next = new ListNode(5);

        Solution01 solution01 = new Solution01();
        ListNode listNode = solution01.deleteDuplicates(root);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    /**
     * 解法一：遍历
     */
    private static class Solution01 {

        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            pre.next = head;

            while (pre.next != null && pre.next.next != null) {
                if (pre.next.val == pre.next.next.val) {
                    int cur = pre.next.val;
                    while (pre.next != null && pre.next.val == cur) {
                        pre.next = pre.next.next;
                    }
                } else {
                    pre = pre.next;
                }
            }

            return dummy.next;
        }

    }

}
