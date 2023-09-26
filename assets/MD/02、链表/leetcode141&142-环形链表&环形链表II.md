# 目录

[toc]

# leetcode141&142-环形链表&环形链表II

- 时间：2023-06-08
- 参考链接：



# 1、题目

- 题目：
  - 141 环形链表 https://leetcode.cn/problems/linked-list-cycle/
    - 简单

  - 142 环形链表II https://leetcode.cn/problems/linked-list-cycle-ii/
    - 中等




## 141 环形链表

给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。

如果链表中存在环 ，则返回 true 。 否则，返回 false 。





## 142 环形链表II

给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

不允许修改 链表。





# 2、题解 141 环形链表

## 题目分析

- 快慢指针



## 解法一: 

### 算法分析

判断链表是否包含环属于经典问题了，解决方案也是用快慢指针：

每当慢指针 `slow` 前进一步，快指针 `fast` 就前进两步。

如果 `fast` 最终遇到空指针，说明链表中没有环；如果 `fast` 最终和 `slow` 相遇，那肯定是 `fast` 超过了 `slow` 一圈，说明链表中含有环。

### 代码

```java

/**
 * <p>
 * 环形链表&环形链表II
 * </p>
 *
 * @author admin
 * @date 2023/6/8
 */
public class leetcode141And142 {

    private static class leetcode141 {

        public static void main(String[] args) {
            ListNode node06 = new ListNode(-4);
            ListNode node07 = new ListNode(0, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(3, node08);
            node06.next = node08;
            int pos = 1;

            Solution01 solution01 = new Solution01();
            boolean hasCycle01 = solution01.hasCycle(node10);
            System.out.println(hasCycle01);

        }

        private static class Solution01 {

            public boolean hasCycle(ListNode head) {
                // 快慢指针初始化指向 head
                ListNode slow = head, fast = head;
                // 快指针走到末尾时停止
                while (fast != null && fast.next != null) {
                    // 慢指针走一步，快指针走两步
                    slow = slow.next;
                    fast = fast.next.next;
                    // 快慢指针相遇，说明含有环
                    if (slow == fast) {
                        return true;
                    }
                }
                // 不包含环
                return false;
            }

        }

    }

}

```

输出如下：

```sh
true
```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(1)



# 3、题解 142 环形链表II

## 题目分析



## 解法一: 

### 算法分析

可以看到，当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。

为什么要这样呢？这里简单说一下其中的原理。

我们假设快慢指针相遇时，慢指针 `slow` 走了 `k` 步，那么快指针 `fast` 一定走了 `2k` 步：



`fast` 一定比 `slow` 多走了 `k` 步，这多走的 `k` 步其实就是 `fast` 指针在环里转圈圈，所以 `k` 的值就是环长度的「整数倍」。

假设相遇点距环的起点的距离为 `m`，那么结合上图的 `slow` 指针，环的起点距头结点 `head` 的距离为 `k - m`，也就是说如果从 `head` 前进 `k - m` 步就能到达环起点。

巧的是，如果从相遇点继续前进 `k - m` 步，也恰好到达环起点。因为结合上图的 `fast` 指针，从相遇点开始走k步可以转回到相遇点，那走 `k - m` 步肯定就走到环起点了：



所以，只要我们把快慢指针中的任一个重新指向 `head`，然后两个指针同速前进，`k - m` 步后一定会相遇，相遇之处就是环的起点了。

### 代码

```java

/**
 * <p>
 * 环形链表&环形链表II
 * </p>
 *
 * @author admin
 * @date 2023/6/8
 */
public class leetcode141And142 {


    private static class leetcode142 {

        public static void main(String[] args) {
            ListNode node06 = new ListNode(-4);
            ListNode node07 = new ListNode(0, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(3, node08);
            node06.next = node08;
            int pos = 1;

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.detectCycle(node10);
            System.out.println(node01.val);
        }

        /**
         * 当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
         */
        private static class Solution01 {

            public ListNode detectCycle(ListNode head) {
                ListNode fast, slow;
                fast = slow = head;
                while (fast != null && fast.next != null) {
                    fast = fast.next.next;
                    slow = slow.next;
                    if (fast == slow) break;
                }
                // 上面的代码类似 hasCycle 函数
                if (fast == null || fast.next == null) {
                    // fast 遇到空指针说明没有环
                    return null;
                }

                // 重新指向头结点
                slow = head;
                // 快慢指针同步前进，相交点就是环起点
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;

            }

        }

    }

}

```

输出如下：

```sh
2
```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(1)



# THE END