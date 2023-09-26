

# 目录

[toc]

# leetcode-234-回文链表

- 时间：2023-06.-10
- 参考链接：
  - [如何判断回文链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/ru-he-pan--f9d3c/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/palindrome-linked-list/
- 难度：简单



给你一个单链表的头节点 `head` ，请你判断该链表是否为回文链表。如果是，返回 `true` ；否则，返回 `false` 。



提示：

链表中节点数目在范围[1, 105] 内
0 <= Node.val <= 9


进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？





# 2、题解

## 题目分析



## 解法一: 快慢指针	

### 算法分析

**1、先通过 [双指针技巧](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/shuang-zhi-0f7cc/) 中的快慢指针来找到链表的中点**：

**2、如果`fast`指针没有指向`null`，说明链表长度为奇数，`slow`还要再前进一步**：

**3、从`slow`开始反转后面的链表，现在就可以开始比较回文串了**：



### 代码

```java
/**
 * <p>
 * 回文链表
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode234 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(1);
        ListNode node06 = new ListNode(2, node05);
        ListNode node07 = new ListNode(2, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        boolean palindrome = solution01.isPalindrome(node10);
        System.out.println(palindrome);

    }

    /**
     * 解法一：快慢指针
     */
    private static class Solution01 {

        public boolean isPalindrome(ListNode head) {
            // 1、先通过 [双指针技巧]中的快慢指针来找到链表的中点**：
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // 如果`fast`指针没有指向`null`，说明链表长度为奇数，`slow`还要再前进一步**：
            if (fast != null) {
                slow = slow.next;
            }

            // 2、从`slow`开始反转后面的链表，
            ListNode left = head;
            ListNode right = reverse(slow);

            // 3、现在就可以开始比较回文串了
            while (right != null) {
                if (left.val != right.val) {
                    return false;
                }
                left = left.next;
                right = right.next;
            }

            return true;
        }

        /**
         * 反转单链表
         */
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

    }

}
```

输出：

```sh
true
```





### 复杂度分析

- 时间复杂度：O(N). 其中*n* 指的是链表的大小
- 空间复杂度：O(1). 我们只会修改原本链表中节点的指向，而在堆栈上的堆栈帧不超过 O*(1)。









# THE END