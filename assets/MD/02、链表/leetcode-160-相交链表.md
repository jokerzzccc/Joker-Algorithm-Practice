# 目录

[toc]

# leetcode-160-相交链表

- 时间：2023-06-09
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/intersection-of-two-linked-lists/
- 难度：简单



给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null` 。

图示两个链表在节点 `c1` 开始相交**：**

![image-20230609222153267](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230609222153267.png)

题目数据 **保证** 整个链式结构中不存在环。

**注意**，函数返回结果后，链表必须 **保持其原始结构** 。

自定义评测：

评测系统 的输入如下（你设计的程序 不适用 此输入）：

intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
listA - 第一个链表
listB - 第二个链表
skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。



提示：

listA 中节点数目为 m
listB 中节点数目为 n
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
如果 listA 和 listB 没有交点，intersectVal 为 0
如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]


进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？



# 2、题解

## 题目分析

这个题直接的想法可能是用 `HashSet` 记录一个链表的所有节点，然后和另一条链表对比，但这就需要额外的空间。

如果不用额外的空间，只使用两个指针，你如何做呢？

难点在于，由于两条链表的长度可能不同，两条链表之间的节点无法对应。

如果用两个指针 `p1` 和 `p2` 分别在两条链表上前进，并不能**同时**走到公共节点，也就无法得到相交节点 `c1`。

**解决这个问题的关键是，通过某些方式，让 `p1` 和 `p2` 能够同时到达相交节点 `c1`**。

所以，我们可以让 `p1` 遍历完链表 `A` 之后开始遍历链表 `B`，让 `p2` 遍历完链表 `B` 之后开始遍历链表 `A`，这样相当于「逻辑上」两条链表接在了一起。

如果这样进行拼接，就可以让 `p1` 和 `p2` 同时进入公共部分，也就是同时到达相交节点 `c1`：

那你可能会问，如果说两个链表没有相交点，是否能够正确的返回 null 呢？

这个逻辑可以覆盖这种情况的，相当于 `c1` 节点是 null 空指针嘛，可以正确返回 null。





## 解法一

### 算法分析





### 代码

```java

/**
 * <p>
 * 相交链表
 * </p>
 *
 * @author admin
 * @date 2023/6/9
 */
public class leetcode160 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(5);
        ListNode node06 = new ListNode(4, node05);
        ListNode node07 = new ListNode(8, node06);

        ListNode node08 = new ListNode(1, node07);
        ListNode node10 = new ListNode(4, node08);

        ListNode node11 = new ListNode(1, node07);
        ListNode node12 = new ListNode(6, node11);
        ListNode node13 = new ListNode(5, node12);

        Solution01 solution01 = new Solution01();
        ListNode intersectionNode01 = solution01.getIntersectionNode(node10, node13);
        System.out.println(intersectionNode01.val);

    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // p1 指向 A 链表头结点，p2 指向 B 链表头结点
            ListNode p1 = headA, p2 = headB;

            while (p1 != p2) {
                // p1 走一步，如果走到 A 链表末尾，转到 B 链表
                if (p1 == null) {
                    p1 = headB;
                } else {
                    p1 = p1.next;
                }
                // p2 走一步，如果走到 B 链表末尾，转到 A 链表
                if (p2 == null) {
                    p2 = headA;
                } else {
                    p2 = p2.next;
                }

            }
            return p1;
        }

    }

}

```

输出：

```sh
8
```





### 复杂度分析

- 时间复杂度：O(m + n). m 为 headA 的链表长度，n 为 HeadB 的链表长度。
- 空间复杂度：O(1).













# THE END