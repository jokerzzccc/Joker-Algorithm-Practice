# 目录

[toc]

# leetcode-206&92-反转链表&反转链表II

- 时间：2023-06-09
- 参考链接：
  - [递归魔法：反转单链表](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/di-gui-mo--10b77/)
  - 




# 1、题目

- 题目：
  - 206 反转链表 https://leetcode.cn/problems/reverse-linked-list/
    - 简单

  - 92 反转链表II https://leetcode.cn/problems/reverse-linked-list-ii/
    - 中等




## 206 反转链表

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

**提示：**

+ 链表中节点的数目范围是 `[0, 5000]`
+ `-5000 <= Node.val <= 5000`



**进阶：**链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？



Related Topics

递归

链表



## 92 反转链表II

给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

提示：

链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


进阶： 你可以使用一趟扫描完成反转吗？



# 2、题解 206 反转链表

## 题目分析

- 快慢指针



## 解法一: 递归

### 算法分析

![image-20230609234004172](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230609234004172.png)



### 代码

```java
/**
     * 反转链表
     */
    private static class leetcode206 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.reverseList(node10);
            while (node01 != null) {
                System.out.println(node01.val);
                node01 = node01.next;
            }

//            Solution02 solution02 = new Solution02();
//            ListNode node02 = solution02.reverseList(node10);
//            while (node02 != null) {
//                System.out.println(node02.val);
//                node02 = node02.next;
//            }

        }

        /**
         * 解法一：递归
         */
        private static class Solution01 {

            public ListNode reverseList(ListNode head) {
                if (head == null || head.next == null) {
                    return head;
                }
                ListNode last = reverseList(head.next);
                head.next.next = head;
                // head 成了反转之后的尾节点，所以要指向 null
                head.next = null;
                return last;

            }

        }

        /**
         * 解法二：迭代，双指针
         */
        private static class Solution02 {

            public ListNode reverseList(ListNode head) {
                // 反转之后的头节点
                ListNode prev = null;
                ListNode curr = head;
                while (curr != null) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                return prev;
            }

        }

    }
```

输出如下：

```sh
5
4
3
2
1
```





### 复杂度分析

- 时间复杂度：O(n),其中 n 是链表的长度。需要对链表的每个节点进行反转操作。
- 空间复杂度：O(n),其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 n 层。



## 解法二: 迭代 

### 算法分析



### 代码

```java
/**
     * 反转链表
     */
    private static class leetcode206 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            Solution02 solution02 = new Solution02();
            ListNode node02 = solution02.reverseList(node10);
            while (node02 != null) {
                System.out.println(node02.val);
                node02 = node02.next;
            }

        }


        /**
         * 解法二：迭代，双指针
         */
        private static class Solution02 {

            public ListNode reverseList(ListNode head) {
                // 反转之后的头节点
                ListNode prev = null;
                ListNode curr = head;
                while (curr != null) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                return prev;
            }

        }

    }
```

输出如下：

```sh
5
4
3
2
1
```





### 复杂度分析

- 时间复杂度：O(n)，n 是链表的长度。
- 空间复杂度：O(1)



# 3、题解 92 反转链表II

## 题目分析



## 解法一: 递归

### 算法分析

- 参考：
  - https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/di-gui-mo--10b77/
  - 



### 代码

```java
    /**
     * 反转链表II
     */
    private static class leetcode92 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            int left = 2, right = 4;

            Solution01 solution01 = new Solution01();
            ListNode node01 = solution01.reverseBetween(node10, left, right);
            // [1,4,3,2,5]
            while (node01 != null) {
                System.out.println(node01.val);
                node01 = node01.next;
            }


        }

        /**
         * 解法一：递归
         */
        private static class Solution01 {

            public ListNode reverseBetween(ListNode head, int left, int right) {
                // base case
                if (left == 1) {
                    return reverseN(head, right);
                }
                // 前进到反转的起点触发 base case
                head.next = reverseBetween(head.next, left - 1, right - 1);

                return head;
            }

            /**
             * 后驱节点
             */
            ListNode successor = null;

            /**
             * 反转以 head 为起点的 n 个节点，返回新的头结点
             */
            private ListNode reverseN(ListNode head, int n) {
                if (n == 1) {
                    // 记录第 n + 1 个节点
                    successor = head.next;
                    return head;
                }
                // 以 head.next 为起点，需要反转前 n - 1 个节点
                ListNode last = reverseN(head.next, n - 1);

                head.next.next = head;
                // 让反转之后的 head 节点和后面的节点连起来
                head.next = successor;
                return last;
            }

        }

    }

```

输出如下：

```sh
1
4
3
2
5
```





### 复杂度分析

- 时间复杂度：O(N),其中N是链表总节点数。最多只遍历历了链表一次，就完成了反转。
- 空间复杂度：O(N)。



## 解法二: 迭代（穿针引线，头插法）(更优)

### 算法分析

**整体思想**是：在需要反转的区间里，每遍历到一个节点，让这个新节点来到反转部分的起始位置。



使用**三个指针变量** pre、curr、next 来记录反转的过程中需要的变量，它们的意义如下：

curr：指向待反转区域的第一个节点 left；
next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。



**操作步骤：**

先将 curr 的下一个节点记录为 next；
执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
执行操作 ③：把 pre 的下一个节点指向 next。



### 代码

```java
 /**
     * 反转链表II
     */
    private static class leetcode92 {

        public static void main(String[] args) {
            ListNode node05 = new ListNode(5);
            ListNode node06 = new ListNode(4, node05);
            ListNode node07 = new ListNode(3, node06);
            ListNode node08 = new ListNode(2, node07);
            ListNode node10 = new ListNode(1, node08);

            int left = 2, right = 4;

            Solution02 solution02 = new Solution02();
            ListNode node02 = solution02.reverseBetween(node10, left, right);
            while (node02 != null) {
                System.out.println(node02.val);
                node02 = node02.next;
            }

        }

 

        /**
         * 解法二：迭代，穿针引线
         * 整体思想是：在需要反转的区间里，每遍历到一个节点，让这个新节点来到反转部分的起始位置。
         * 只需要遍历一次
         */
        private static class Solution02 {

            public ListNode reverseBetween(ListNode head, int left, int right) {
                // 虚拟头结点
                ListNode dummy = new ListNode(-1);
                dummy.next = head;
                // pre：永远指向待反转区域的第一个节点 left 的前一个节点，在反转区间循环过程中不变。
                ListNode pre = dummy;
                for (int i = 0; i < left - 1; i++) {
                    pre = pre.next;
                }
                // cur：指向待反转区域的第一个节点 left；
                ListNode cur = pre.next;
                // next：永远指向 curr 的下一个节点，循环过程中，cur 变化以后 next 会变化；
                ListNode next;
                // 开始操作
                for (int i = 0; i < right - left; i++) {
                    next = cur.next;
                    cur.next = next.next;
                    next.next = pre.next;
                    pre.next = next;
                }

                return dummy.next;
            }

        }

    }
```

输出如下：

```sh
1
4
3
2
5
```





### 复杂度分析

- 时间复杂度：O(N),其中N是链表总节点数。最多只遍历历了链表一次，就完成了反转。
- 空间复杂度：O(1)。只使用到常数个变量。







# THE END