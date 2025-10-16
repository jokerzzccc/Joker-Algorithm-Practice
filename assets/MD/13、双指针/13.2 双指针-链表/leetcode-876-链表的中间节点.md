# 目录

[toc]

# leetcode-876-链表的中间节点

- 时间：2023-06-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/middle-of-the-linked-list/
- 难度：简单

给你单链表的头结点 `head` ，请你找出并返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 



# 2、题解

## 题目分析

- 用快慢指针





## 解法一

### 算法分析

如果想一次遍历就得到中间节点，也需要耍点小聪明，使用「快慢指针」的技巧：

我们让两个指针 `slow` 和 `fast` 分别指向链表头结点 `head`。

**每当慢指针 `slow` 前进一步，快指针 `fast` 就前进两步，这样，当 `fast` 走到链表末尾时，`slow` 就指向了链表中点**。



需要注意的是，如果链表长度为偶数，也就是说中点有两个的时候，我们这个解法返回的节点是靠后的那个节点。

另外，这段代码稍加修改就可以直接用到判断链表成环的算法题上。

### 代码

```java

/**
 * <p>
 * 链表的中间节点
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/8
 */
public class leetcode876 {

    public static void main(String[] args) {

        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.middleNode(node10);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：快慢指针法
     */
    private static class Solution01 {

        public ListNode middleNode(ListNode head) {
            // 快慢指针初始化指向 head
            ListNode slow = head;
            ListNode fast = head;
            // 快指针走到末尾时停止
            while (fast!=null && fast.next!=null) {
                // 慢指针走一步，快指针走两步
                slow = slow.next;
                fast = fast.next.next;
            }
            // 慢指针指向中点
            return slow;

        }

    }

}
```



输出：

```sh
3
4
5
```



### 复杂度分析

- 时间复杂度：O(L)，其中 L 是链表的长度。
- 空间复杂度：O(1)。









# THE END