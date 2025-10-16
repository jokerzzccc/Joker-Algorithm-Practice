package com.joker.algorithm.chain;

/**
 * 两两交换链表中的节点
 *
 * @author jokerzzccc
 * @date 2025/4/15
 */
public class leetcode24 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        Solution01 solution01 = new Solution01();
        solution01.swapPairs(head);
        System.out.println(head);

    }

    static class Solution01 {

        public ListNode swapPairs(ListNode head) {
            // 虚拟头节点
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            // temp : 记录当前需要处理的一对节点的前一个节点
            ListNode temp = dummyHead;
            while (temp.next != null && temp.next.next != null) {
                ListNode node1 = temp.next;
                ListNode node2 = temp.next.next;
                temp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                temp = node1;
            }
            return dummyHead.next;
        }

    }

}
