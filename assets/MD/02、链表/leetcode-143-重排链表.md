# 目录

[toc]

# leetcode-143-重排链表

- 时间：2023-07-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/reorder-list/
- 难度：中等

给定一个单链表 L 的头节点 head ，单链表 L 表示为：

```sh
L0 → L1 → … → Ln - 1 → Ln
```

请将其重新排列后变为：

```sh
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
```

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



**提示：**

+ 链表的长度范围为 `[1, 5 * 10^4]`
+ `1 <= node.val <= 1000`





# 2、题解

## 题目分析



## 解法一: *寻找链表中点* *+* *链表逆序* *+* *合并链表*

### 算法分析

注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。

这样我们的任务即可划分为三步：

1. 找到原链表的中点（参考「876. 链表的中间结点」）。
   - 我们可以使用快慢指针来 O(N) 地找到链表的中间节点。
2. 将原链表的右半端反转（参考「206. 反转链表」）。
   - 我们可以使用迭代法实现链表的反转。
3. 将原链表的两端合并。
   - 因为两链表长度相差不超过 1, ，因此直接合并即可。





### 代码

```java


/**
 * <p>
 * 重排链表
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode143 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution01 solution01 = new Solution01();
        solution01.reorderList(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    /**
     * 解法一：寻找链表中点 + 链表逆序 + 合并链表
     * 目标链表即为将原链表的左半端和反转后的右半端合并后的结果
     */
    private static class Solution01 {

        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            // 取得链表中点
            ListNode mid = middleNode(head);

            // 以中点为界，分为左右两个链表
            ListNode left = head;
            ListNode right = mid.next;
            // 因为左右需要分开，中间结点指向 null
            mid.next = null;
            // 反转右链表
            right = reverseList(right);

            // 交替合并左右两个链表
            mergeList(left, right);
        }

        /**
         * 交替合并两个链表
         */
        private void mergeList(ListNode list1, ListNode list2) {
            ListNode l1_tmp;
            ListNode l2_tmp;
            while (list1 != null && list2 != null) {
                l1_tmp = list1.next;
                l2_tmp = list2.next;

                list1.next = list2;
                list1 = l1_tmp;

                list2.next = list1;
                list2 = l2_tmp;
            }
        }

        /**
         * 反转链表
         */
        private ListNode reverseList(ListNode head) {
            // 反转之后的头结点
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }

            return prev;
        }

        /**
         * 用快慢指针的方法，寻找链表的中点。
         */
        private ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(N),其中 N 是链表中的节点数。
- 空间复杂度：O(1)。







# THE END