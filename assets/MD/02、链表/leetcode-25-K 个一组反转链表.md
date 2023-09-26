# 目录

[toc]

# leetcode-25-K 个一组反转链表

- 时间：2023-06-09
- 参考链接：
  - [如何 K 个一组反转链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/ru-he-k-ge-d591d/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/reverse-nodes-in-k-group/
- 难度：困难



给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。



提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000


进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？








# 2、题解

## 题目分析







## 解法一: 迭代

### 算法分析

- 参考：
  - https://leetcode.cn/problems/reverse-nodes-in-k-group/solution/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/



### 代码

```java

/**
 * <p>
 * K 个一组反转链表
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode25 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        int k = 2;

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.reverseKGroup(node10, k);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }

    }

    /**
     * 解法一：迭代
     */
    private static class Solution01 {

        public ListNode reverseKGroup(ListNode head, int k) {
            // 虚拟头结点，
            // 减少边界情况处理
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            while (head != null) {
                ListNode tail = prev;
                // 查看剩余部分长度是否 >= K
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        return dummy.next;
                    }
                }

                ListNode next = tail.next;
                ListNode[] reverse = reverseRange(head, tail);
                head = reverse[0];
                tail = reverse[1];
                // 把子链表重新接回原链表
                prev.next = head;
                tail.next = next;
                // 为下一个区间，重置指针
                prev = tail;
                head = tail.next;
            }

            return dummy.next;
        }

        /**
         * 反转一个区间 [head, tail] 的链表，
         *
         * @return 区间链表反转的头尾节点
         */
        private ListNode[] reverseRange(ListNode head, ListNode tail) {

            ListNode prev = tail.next;
            ListNode curr = head;
            ListNode next;
            while (prev != tail) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return new ListNode[]{tail, head};
        }

    }

}

```

输出：

```sh
2
1
4
3
5
```





### 复杂度分析

- 时间复杂度：O(n),其中n为链表的长度。

- 空间复杂度：O(1),我们只需要建立常数个变量。

  



## 解法一: 递归

### 算法分析

- 参考：
  - https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/ru-he-k-ge-d591d/

### 代码

```java

/**
 * <p>
 * K 个一组反转链表
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode25 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(3, node06);
        ListNode node08 = new ListNode(2, node07);
        ListNode node10 = new ListNode(1, node08);

        int k = 2;

//        Solution01 solution01 = new Solution01();
//        ListNode node01 = solution01.reverseKGroup(node10, k);
//        while (node01 != null) {
//            System.out.println(node01.val);
//            node01 = node01.next;
//        }

        Solution02 solution02 = new Solution02();
        ListNode node02 = solution02.reverseKGroup(node10, k);
        while (node02 != null) {
            System.out.println(node02.val);
            node02 = node02.next;
        }

    }


    /**
     * 解法二：递归
     */
    private static class Solution02 {

        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode a, b;
            a = b = head;
            // 判断后面的是否还有 K 个节点
            for (int i = 0; i < k; i++) {
                // 不足 k 个，不需要反转，base case
                if (b == null) {
                    return head;
                }
                b = b.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverseRange(a, b);
            // 递归反转后续链表并连接起来
            a.next = reverseKGroup(b, k);
            return newHead;

        }

        /**
         * 反转区间 [a, b) 的元素，注意是左闭右开
         */
        private ListNode reverseRange(ListNode a, ListNode b) {
            ListNode prev = null;
            ListNode curr = a;
            ListNode next = a;
            while (curr != b) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            // 返回反转后的头结点
            return prev;
        }

    }

}

```

输出：

```sh
2
1
4
3
5
```





### 复杂度分析

- 时间复杂度：O(n),其中n为链表的长度。
- 空间复杂度：O(n),递归栈。





# THE END