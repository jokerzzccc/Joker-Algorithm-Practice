# 目录

[toc]

# leetcode-23-合并k个有序链表

- 时间：2023-06-07
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/merge-k-sorted-lists/
- 难度：困难



给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。





# 2、题解

## 题目分析



## 解法一: 优先队列

### 算法分析



合并 `k` 个有序链表的逻辑类似合并两个有序链表，难点在于，如何快速得到 `k` 个节点中的最小节点，接到结果链表上？

这里我们就要用到 [优先级队列（二叉堆）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/er-cha-dui-1a386/) 这种数据结构，把链表节点放入一个最小堆，就可以每次获得 `k` 个节点中的最小节点：



### 代码

```java
/**
 * <p>
 * 合并k个有序链表
 * </p>
 *
 * @author admin
 * @date 2023/6/7
 */
public class leetcode23 {

    public static void main(String[] args) {
        ListNode node01 = new ListNode(5);
        ListNode node02 = new ListNode(4, node01);
        ListNode node03 = new ListNode(1, node02);

        ListNode node05 = new ListNode(4);
        ListNode node06 = new ListNode(3, node05);
        ListNode node07 = new ListNode(1, node06);

        ListNode node08 = new ListNode(6);
        ListNode node10 = new ListNode(2, node08);

        ListNode[] lists = {node03, node07, node10};

        Solution01 solution01 = new Solution01();
        ListNode mergeKLists01 = solution01.mergeKLists(lists);
        while (mergeKLists01 != null) {
            System.out.println(mergeKLists01.val);
            mergeKLists01 = mergeKLists01.next;
        }

    }

    /**
     * 解法一：使用优先队列（PriorityQueue）
     */
    private static class Solution01 {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            // 虚拟头结点
            ListNode dummy = new ListNode(-1);
            // prev 指针
            ListNode prev = dummy;
            // 优先级队列，最小堆
            PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
            // 将 k 个链表的头结点加入最小堆
            for (ListNode head : lists) {
                if (head != null) {
                    priorityQueue.add(head);
                }
            }

            while (!priorityQueue.isEmpty()) {
                // 获取最小节点，接到结果链表中
                ListNode node = priorityQueue.poll();
                prev.next = node;
                if (node.next != null) {
                    priorityQueue.add(node.next);
                }
                // prev 指针不断前进
                prev = prev.next;
            }

            return dummy.next;
        }

    }

}
```

输出如下：

```sh
1
1
2
3
4
4
5
6
```





### 复杂度分析

![image-20230608221316217](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230608221316217.png)

优先队列 `pq` 中的元素个数最多是 `k`，所以一次 `poll` 或者 `add` 方法的时间复杂度是 `O(logk)`；所有的链表节点都会被加入和弹出 `pq`，**所以算法整体的时间复杂度是 `O(Nlogk)`，其中 `k` 是链表的条数，`N` 是这些链表的节点总数**。



## 解法二：分治法

### 算法分析

![image-20230608221333906](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230608221333906.png)

### 代码

```java

/**
 * <p>
 * 合并k个有序链表
 * </p>
 *
 * @author admin
 * @date 2023/6/7
 */
public class leetcode23 {

    public static void main(String[] args) {
        ListNode node01 = new ListNode(5);
        ListNode node02 = new ListNode(4, node01);
        ListNode node03 = new ListNode(1, node02);

        ListNode node05 = new ListNode(4);
        ListNode node06 = new ListNode(3, node05);
        ListNode node07 = new ListNode(1, node06);

        ListNode node08 = new ListNode(6);
        ListNode node10 = new ListNode(2, node08);

        ListNode[] lists = {node03, node07, node10};


        Solution02 solution02 = new Solution02();
        ListNode mergeKLists02 = solution02.mergeKLists(lists);
        while (mergeKLists02 != null) {
            System.out.println(mergeKLists02.val);
            mergeKLists02 = mergeKLists02.next;
        }

    }


    /**
     * 解法二：使用分治法（基于合并两个链表）
     */
    private static class Solution02 {

        public ListNode mergeKLists(ListNode[] lists) {
            return merge(lists, 0, lists.length - 1);
        }

        /**
         * 两两合并，得到最终结果
         */
        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            if (left > right) {
                return null;
            }
            int mid = (left + right) >> 1;
            return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
        }

        private ListNode mergeTwoLists(ListNode a, ListNode b) {
            if (a == null || b == null) {
                return a != null ? a : b;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, aPtr = a, bPtr = b;
            while (aPtr != null && bPtr != null) {
                if (aPtr.val < bPtr.val) {
                    tail.next = aPtr;
                    aPtr = aPtr.next;
                } else {
                    tail.next = bPtr;
                    bPtr = bPtr.next;
                }
                tail = tail.next;
            }
            tail.next = (aPtr != null ? aPtr : bPtr);
            return head.next;
        }

    }

}
```



### 时间复杂度

![image-20230608221300689](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230608221300689.png)



# THE END
