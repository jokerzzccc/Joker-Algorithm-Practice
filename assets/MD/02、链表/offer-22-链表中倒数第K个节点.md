

# 目录

[toc]

# offer-22-链表中倒数第K个节点

- 时间：2023-06-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
- 难度：简单

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。



# 2、题解

## 题目分析

- 使用只遍历一次链表的方法。





## 解法一

### 算法分析





### 代码

```java

/**
 * <p>
 * 链表中倒数第k个节点
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/8
 */
public class offer22 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);
        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.getKthFromEnd(node10, k);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：只遍历一次链表，使用两个指针
     */
    private static class Solution01 {

        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode p1 = head;
            // p1 先走 k 步
            for (int i = 0; i < k; i++) {
                p1 = p1.next;
            }
            ListNode p2 = head;
            // p1 和 p2 同时走 n - k 步
            while (p1 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
            // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
            return p2;
        }

    }

}

```

输出：

```sh
4
5
```





### 复杂度分析

- 时间复杂度：O(n),其中n为链表的长度。我们使用快慢指针，只需要一次遍历即可，复杂度为O(n)
- 空间复杂度：O(1).







# THE END