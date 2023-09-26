# 目录

[toc]

# leetcode-83-删除排序链表中的重复元素

- 时间：2023-06.-10
- 参考链接：
  - 
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
- 难度：简单

- 

给定一个已排序的链表的头 `head` ， *删除所有重复的元素，使每个元素只出现一次* 。返回 *已排序的链表* 。



# 2、题解

## 题目分析



## 解法一: 快慢指针	

### 算法分析





### 代码

```java

/**
 * <p>
 * 删除排序链表中的重复元素
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode83 {

    public static void main(String[] args) {
        ListNode node05 = new ListNode(3);
        ListNode node06 = new ListNode(3, node05);
        ListNode node07 = new ListNode(2, node06);
        ListNode node08 = new ListNode(1, node07);
        ListNode node10 = new ListNode(1, node08);

        Solution01 solution01 = new Solution01();
        ListNode node01 = solution01.deleteDuplicates(node10);
        while (node01 != null) {
            System.out.println(node01.val);
            node01 = node01.next;
        }
    }

    /**
     * 解法一：快慢指针
     * 可以类比 leetcode26 数组版
     */
    private static class Solution01 {

        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode slow = head, fast = head;

            while (fast != null) {
                if (fast.val != slow.val) {
                    // nums[slow] = nums[fast];
                    slow.next = fast;
                    // slow++;
                    slow = slow.next;
                }
                // fast++
                fast = fast.next;
            }
            // 断开与后面重复元素的连接
            slow.next = null;
            return head;

        }

    }

}

```

输出：

```sh
1
2
3
```





### 复杂度分析

- 时间复杂度：O(N). 其中*n* 指的是链表的大小
- 空间复杂度：O(1). 我们只会修改原本链表中节点的指向，而在堆栈上的堆栈帧不超过 O*(1)。









# THE END