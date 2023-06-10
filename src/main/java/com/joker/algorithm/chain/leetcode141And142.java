package com.joker.algorithm.chain;

import com.joker.algorithm.chain.ListNode;

/**
 * <p>
 * 环形链表&环形链表II
 * </p>
 *
 * @author admin
 * @date 2023/6/8
 */
public class leetcode141And142 {

    private static class leetcode141 {

        public static void main(String[] args) {
            ListNode node06 = new ListNode(-4);
            ListNode node07 = new ListNode(0, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(3, node08);
            node06.next = node08;
            int pos = 1;

            Solution01 solution01 = new Solution01();
            boolean hasCycle01 = solution01.hasCycle(node10);
            System.out.println(hasCycle01);

        }

        private static class Solution01 {

            public boolean hasCycle(ListNode head) {
                // 快慢指针初始化指向 head
                ListNode slow = head, fast = head;
                // 快指针走到末尾时停止
                while (fast != null && fast.next != null) {
                    // 慢指针走一步，快指针走两步
                    slow = slow.next;
                    fast = fast.next.next;
                    // 快慢指针相遇，说明含有环
                    if (slow == fast) {
                        return true;
                    }
                }
                // 不包含环
                return false;
            }

        }

    }

    private static class leetcode142 {

        public static void main(String[] args) {
            ListNode node06 = new ListNode(-4);
            ListNode node07 = new ListNode(0, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(3, node08);
            node06.next = node08;
            int pos = 1;

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.detectCycle(node10);
            System.out.println(node01.val);
        }

        /**
         * 当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
         */
        private static class Solution01 {

            public ListNode detectCycle(ListNode head) {
                ListNode fast, slow;
                fast = slow = head;
                while (fast != null && fast.next != null) {
                    fast = fast.next.next;
                    slow = slow.next;
                    if (fast == slow) break;
                }
                // 上面的代码类似 hasCycle 函数
                if (fast == null || fast.next == null) {
                    // fast 遇到空指针说明没有环
                    return null;
                }

                // 重新指向头结点
                slow = head;
                // 快慢指针同步前进，相交点就是环起点
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;

            }

        }

    }

}
