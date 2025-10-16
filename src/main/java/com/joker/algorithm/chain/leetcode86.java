package com.joker.algorithm.chain;

/**
 * <p>
 * 分割链表
 * </p>
 *
 * @author admin
 * @date 2023/6/7
 */
public class leetcode86 {

    public static void main(String[] args) {

        ListNode node02 = new ListNode(2);
        ListNode node03 = new ListNode(5, node02);
        ListNode node04 = new ListNode(2, node03);
        ListNode node05 = new ListNode(3, node04);
        ListNode node06 = new ListNode(4, node05);
        ListNode head = new ListNode(1, node06);

        Solution01 solution01 = new Solution01();
        ListNode nodeRes01 = solution01.partition(head, 3);
        while (nodeRes01 != null) {
            System.out.println(nodeRes01.val);
            nodeRes01 = nodeRes01.next;
        }

    }

    /**
     * 解法一：分割为两个小链表
     */
    private static class Solution01 {

        public ListNode partition(ListNode head, int x) {
            // 存放小于 x 的链表的虚拟头结点
            ListNode dummy1 = new ListNode(-1);
            // 存放大于等于 x 的链表的虚拟头结点
            ListNode dummy2 = new ListNode(-1);
            // prev1, prev2 指针负责生成结果链表
            ListNode prev1 = dummy1, prev2 = dummy2;

            // curr 负责遍历原链表，类似合并两个有序链表的逻辑
            // 这里是将一个链表分解成两个链表
            ListNode curr = head;
            while (curr != null) {
                if (curr.val >= x) {
                    prev2.next = curr;
                    prev2 = prev2.next;
                } else {
                    prev1.next = curr;
                    prev1 = prev1.next;
                }
                // 断开原链表中的每个节点的 next 指针
                ListNode temp = curr.next;
                curr.next = null;
                curr = temp;
            }

            // 连接两个链表
            prev1.next = dummy2.next;

            return dummy1.next;
        }

    }

}
