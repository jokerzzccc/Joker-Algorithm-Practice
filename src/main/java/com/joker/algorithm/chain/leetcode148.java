package com.joker.algorithm.chain;

/**
 * <p>
 * 排序链表
 * </p>
 *
 * @author admin
 * @date 2023/7/12
 */
public class leetcode148 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        Solution01 solution01 = new Solution01();
        ListNode sorted01 = solution01.sortList(head);
        while (sorted01 != null) {
            System.out.println(sorted01.val);
            sorted01 = sorted01.next;
        }

    }

    /**
     * 解法一：归并排序（自底向上）
     * 最适合链表的排序就是归并排序
     */
    private static class Solution01 {

        public ListNode sortList(ListNode head) {
            if (head == null) {
                return head;
            }

            // 计算链表长度
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }

            // 虚拟头结点
            ListNode dummyHead = new ListNode(-1, head);

            // 3.每次将链表拆分成若干个长度为 subLength 的子链表 , 并按照每两个子链表一组进行合并
            for (int subLength = 1; subLength < length; subLength <<= 1) {
                ListNode prev = dummyHead;
                // curr 用于记录拆分链表的位置
                ListNode curr = dummyHead.next;

                // 两个两个拆，直到拆完
                while (curr != null) {
                    // 3.1 拆分 subLength 长度的链表1
                    // 第一个链表的头 即 curr 初始的位置
                    ListNode head1 = curr;
                    // 拆分出长度为 subLength 的链表1
                    for (int i = 1; i < subLength && curr.next != null; i++) {
                        curr = curr.next;
                    }

                    // 3.2 拆分 subLength 长度的链表2
                    // 第二个链表的头  即 链表1尾部的下一个位置
                    ListNode head2 = curr.next;
                    // 断开第一个链表和第二个链表的链接
                    curr.next = null;
                    // 第二个链表头 重新赋值给curr
                    curr = head2;
                    // 再拆分出长度为 subLength 的链表2
                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }

                    // 3.3 再次断开 第二个链表最后的 next 的链接
                    ListNode next = null;
                    if (curr != null) {
                        // next 用于记录 拆分完两个链表的结束位置
                        next = curr.next;
                        // 断开连接
                        curr.next = null;
                    }

                    // 3.4 合并两个 subLength 长度的有序链表
                    ListNode merged = merge(head1, head2);
                    // prev.next 指向排好序链表的头
                    prev.next = merged;
                    // while循环 将prev移动到 subLength*2 的位置后去
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    // next 用于记录 拆分完两个链表的结束位置
                    curr = next;
                }

            }

            // 返回新排好序的链表
            return dummyHead.next;
        }

        /**
         * 升序合并两个有序链表（双指针法）（leetcode21）
         */
        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(-1);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;

            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }

            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }

    }

}
