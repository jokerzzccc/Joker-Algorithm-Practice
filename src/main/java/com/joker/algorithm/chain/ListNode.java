package com.joker.algorithm.chain;

/**
 * <p>
 * 链表节点
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/7
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
