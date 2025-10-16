package com.joker.algorithm.chain;

/**
 * <p>
 * 合并两个有序链表
 * </p>
 *
 * @author admin
 * @date 2023/6/7
 */
public class leetcode21 {

    public static void main(String[] args) {

        ListNode node01 = new ListNode(4);
        ListNode node02 = new ListNode(2, node01);
        ListNode node03 = new ListNode(1, node02);

        ListNode node04 = new ListNode(5);
        ListNode node05 = new ListNode(4, node04);
        ListNode node06 = new ListNode(2, node05);
        ListNode node07 = new ListNode(1, node06);

        Solution01 solution01 = new Solution01();
        ListNode nodeMerge01 = solution01.mergeTwoLists(node07, node03);
        while (nodeMerge01 != null) {
            System.out.println(nodeMerge01.val);
            nodeMerge01 = nodeMerge01.next;
        }

//        Solution02 solution02 = new Solution02();
//        ListNode nodeMerge02 = solution02.mergeTwoLists(node07, node03);
//        while (nodeMerge02 != null) {
//            System.out.println(nodeMerge02.val);
//            nodeMerge02 = nodeMerge02.next;
//        }

    }

    /**
     * 解法一：循环迭代
     * 代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，
     * 也就是 dummy 节点。你可以试试，如果不使用 dummy 虚拟节点，代码会复杂一些，
     * 需要额外处理指针 p 为空的情况。而有了 dummy 节点这个占位符，可以避免处理空指针的情况，降低代码的复杂性。
     */
    private static class Solution01 {

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // dummy: 最后返回的新链表虚拟头结点
            ListNode dummy = new ListNode(-1);
            // 新链表上的移动指针
            ListNode prev = dummy;
            ListNode p1 = list1, p2 = list2;

            while (p1 != null && p2 != null) {
                if (p1.val > p2.val) {
                    prev.next = p2;
                    p2 = p2.next;
                } else {
                    prev.next = p1;
                    p1 = p1.next;
                }
                // prev 指针前移
                prev = prev.next;
            }
            // p1, p2 两个中未遍历完的放到 P 后
            if (p1 != null) {
                prev.next = p1;
            }
            if (p2 != null) {
                prev.next = p2;
            }
            return dummy.next;

        }

    }

    /**
     * 解法二: 递归
     */
    private static class Solution02 {

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            } else if (list2 == null) {
                return list1;
            } else if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }

    }

}
