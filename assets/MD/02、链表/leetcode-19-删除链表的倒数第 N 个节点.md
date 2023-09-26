# 目录

[toc]

# leetcode-19-删除链表的倒数第 N 个节点

- 时间：2023-06-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
- 难度：中等



给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。



# 2、题解

## 题目分析

- 类比offer22, 用双指针





## 解法一

### 算法分析

这个逻辑就很简单了，要删除倒数第 `n` 个节点，就得获得倒数第 `n + 1` 个节点的引用，可以用我们实现的 `findFromEnd` 来操作。

不过注意我们又使用了虚拟头结点的技巧，也是为了防止出现空指针的情况，比如说链表总共有 5 个节点，题目就让你删除倒数第 5 个节点，也就是第一个节点，那按照算法逻辑，应该首先找到倒数第 6 个节点。但第一个节点前面已经没有节点了，这就会出错。

但有了我们虚拟节点 `dummy` 的存在，就避免了这个问题，能够对这种情况进行正确的删除。



### 代码

```java

/**
 * <p>
 * 删除链表的倒数第 N 个节点
 * </p>
 *
 * @author admin
 * @date 2023/6/8
 */
public class leetcode19 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);
        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.removeNthFromEnd(node10, k);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：遍历一次链表，双指针
     * 要删除倒数第 n 个节点，就得获得倒数第 n + 1 个节点的引用
     */
    private static class Solution01 {

        public ListNode removeNthFromEnd(ListNode head, int n) {

            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode p1 = dummy;
            for (int i = 0; i < n + 1; i++) {
                p1 = p1.next;
            }
            ListNode p2 = dummy;
            while (p1 != null) {
                p1 = p1.next;
                p2 = p2.next;
            }

            p2.next = p2.next.next;
            return dummy.next;
        }

    }

}

```



输出：

```sh
1
2
3
5
```



### 复杂度分析

- 时间复杂度：O(L)，其中 L 是链表的长度。
- 空间复杂度：O(1)。









# THE END