package com.joker.algorithm;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 相交链表
 * </p>
 *
 * @author admin
 * @date 2023/6/9
 */
public class leetcode160 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(8, node06);

        ListNode node08 = new ListNode(1, node07);
        ListNode node10 = new ListNode(4, node08);

        ListNode node11 = new ListNode(1, node07);
        ListNode node12 = new ListNode(6, node11);
        ListNode node13 = new ListNode(5, node12);

        Solution01 solution01 = new Solution01();
        ListNode intersectionNode01 = solution01.getIntersectionNode(node10, node13);
        // 输出 8
        System.out.println(intersectionNode01.val);

    }

    /**
     * 解法一：双指针
     * 通过某些方式，让 `p1` 和 `p2` 能够同时到达相交节点 `c1`**。
     * <p>
     * 所以，我们可以让 `p1` 遍历完链表 `A` 之后开始遍历链表 `B`，让 `p2` 遍历完链表 `B` 之后开始遍历链表 `A`，这样相当于「逻辑上」两条链表接在了一起。
     * <p>
     * 如果这样进行拼接，就可以让 `p1` 和 `p2` 同时进入公共部分，也就是同时到达相交节点 `c1`
     */
    private static class Solution01 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // p1 指向 A 链表头结点，p2 指向 B 链表头结点
            ListNode p1 = headA, p2 = headB;

            while (p1 != p2) {
                // p1 走一步，如果走到 A 链表末尾，转到 B 链表
                if (p1 == null) {
                    p1 = headB;
                } else {
                    p1 = p1.next;
                }
                // p2 走一步，如果走到 B 链表末尾，转到 A 链表
                if (p2 == null) {
                    p2 = headA;
                } else {
                    p2 = p2.next;
                }

            }
            return p1;
        }

    }

}
