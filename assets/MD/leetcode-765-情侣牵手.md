# 目录

[toc]

# leetcode-765-情侣牵手

- 时间：2023-11-11
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/couples-holding-hands/description/?envType=daily-question&envId=2023-11-11
- 难度：困难



`n` 对情侣坐在连续排列的 `2n` 个座位上，想要牵到对方的手。

人和座位由一个整数数组 `row` 表示，其中 `row[i]` 是坐在第 `i `个座位上的人的 **ID**。情侣们按顺序编号，第一对是 `(0, 1)`，第二对是 `(2, 3)`，以此类推，最后一对是 `(2n-2, 2n-1)`。

返回 *最少交换座位的次数，以便每对情侣可以并肩坐在一起*。 *每次*交换可选择任意两人，让他们站起来交换座位。

 

**示例 1:**

```sh
输入: row = [0,2,1,3]
输出: 1
解释: 只需要交换row[1]和row[2]的位置即可。
```

**示例 2:**

```sh
输入: row = [3,2,0,1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。
```

 

**提示:**

- `2n == row.length`
- `2 <= n <= 30`
- `n` 是偶数
- `0 <= row[i] < 2n`
- `row` 中所有元素均**无重复**



方法摘要：

```java
class Solution {
    public int minSwapsCouples(int[] row) {

    }
}
```





# 2、题解

## 题目分析



## 解法一：并查集

### 算法分析

参考链接：

- https://leetcode.cn/problems/couples-holding-hands/solutions/599958/qing-lu-qian-shou-by-leetcode-gl1c/?envType=daily-question&envId=2023-11-11
- https://leetcode.cn/problems/couples-holding-hands/solutions/603505/liang-chong-100-de-jie-fa-bing-cha-ji-ta-26a6/?envType=daily-question&envId=2023-11-11



首先，我们总是以**「情侣对」**为单位进行设想：

- 当有两对情侣相互坐错了位置，ta们两对之间形成了一个环。需要进行一次交换，使得每队情侣独立（相互牵手）

- 如果三对情侣相互坐错了位置，ta们三对之间形成了一个环，需要进行两次交换，使得每队情侣独立（相互牵手）

- 如果四对情侣相互坐错了位置，ta们四对之间形成了一个环，需要进行三次交换，使得每队情侣独立（相互牵手）

也就是说，如果我们**有 k 对情侣形成了错误环，需要交换 k - 1 次才能让情侣牵手**。

于是**问题转化成 n / 2 对情侣中，有多少个这样的环**。

可以直接使用**「并查集」**来做。

由于 0和1配对、2和3配对 ... 因此**互为情侣的两个编号除以 2 （下取整）对应同一个数字**，可直接作为它们的「情侣组」编号：





### 代码

```java
package com.joker.algorithm;

/**
 * 情侣牵手
 *
 * @author jokerzzccc
 * @date 2023/11/11
 */
public class leetcode765 {

    public static void main(String[] args) {
        // int[] row = {0, 2, 1, 3};

        int[] row = {0, 2, 1, 3, 9, 10, 4, 6, 5, 7, 8, 11};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minSwapsCouples(row));

    }

    /**
     * 解法一：并查集
     */
    private static class Solution01 {

        int[] pairs = new int[70];

        public int minSwapsCouples(int[] row) {
            int num = row.length;
            // n: 所有情侣对数量
            int n = num / 2;
            if (n <= 1) {
                return 0;
            }

            // 初始化并查集的父节点，使它们指向自己
            for (int i = 0; i < n; i++) {
                pairs[i] = i;
            }

            // 两个编号的情侣对2整除（向下取整）等于同一个数字
            // 利用这个特性，借助并查集的功能将两个情侣组串成环。
            // 错误的情侣对将会结成环
            for (int i = 0; i < num; i += 2) {
                union(row[i] / 2, row[i + 1] / 2);
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (i == find(i)) {
                    cnt++;
                }
            }

            // 「至少交换的次数 = 所有情侣的对数 - 并查集里连通分量的个数」
            return n - cnt;
        }

        void union(int a, int b) {
            pairs[find(a)] = pairs[find(b)];
        }

        int find(int x) {
            if (pairs[x] != x) {
                pairs[x] = find(pairs[x]);
            }

            return pairs[x];
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(n)









# THE END