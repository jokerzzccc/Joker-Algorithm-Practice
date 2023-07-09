package com.joker.algorithm.chain;

/**
 * <p>
 * 设计链表
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode707 {

    /**
     * 解法一：单向链表
     */
    private class MyLinkedList01 {

        private int size;
        ListNode head;

        public MyLinkedList01() {
            size = 0;
            // 虚拟头结点
            head = new ListNode(-1);
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }

            return cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            index = Math.max(0, index);
            size++;
            ListNode pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }

            ListNode toAdd = new ListNode(val);
            toAdd.next = pre.next;
            pre.next = toAdd;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            ListNode pre = head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
        }

        private class ListNode {

            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }

        }

    }

    /**
     * 解法二：双向链表
     */
    class MyLinkedList02 {

        int size;
        ListNode head;
        ListNode tail;

        public MyLinkedList02() {
            size = 0;
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode curr;
            if (index + 1 < size - index) {
                curr = head;
                for (int i = 0; i <= index; i++) {
                    curr = curr.next;
                }
            } else {
                curr = tail;
                for (int i = 0; i < size - index; i++) {
                    curr = curr.prev;
                }
            }
            return curr.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            index = Math.max(0, index);
            ListNode pred, succ;
            if (index < size - index) {
                pred = head;
                for (int i = 0; i < index; i++) {
                    pred = pred.next;
                }
                succ = pred.next;
            } else {
                succ = tail;
                for (int i = 0; i < size - index; i++) {
                    succ = succ.prev;
                }
                pred = succ.prev;
            }
            size++;
            ListNode toAdd = new ListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            ListNode pred, succ;
            if (index < size - index) {
                pred = head;
                for (int i = 0; i < index; i++) {
                    pred = pred.next;
                }
                succ = pred.next.next;
            } else {
                succ = tail;
                for (int i = 0; i < size - index - 1; i++) {
                    succ = succ.prev;
                }
                pred = succ.prev.prev;
            }
            size--;
            pred.next = succ;
            succ.prev = pred;
        }

        private class ListNode {

            int val;
            ListNode next;
            ListNode prev;

            public ListNode(int val) {
                this.val = val;
            }

        }

    }

}
