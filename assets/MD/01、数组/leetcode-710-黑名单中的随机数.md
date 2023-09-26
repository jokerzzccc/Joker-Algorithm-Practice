# 目录

[toc]

# leetcode-710-黑名单中的随机数

- 时间：2023-06-16

- 参考链接：
  - [常数时间删除-查找数组中的任意元素](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/chang-shu--6b296/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/random-pick-with-blacklist/
- 难度：困难



给定一个整数 n 和一个 **无重复** 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 **未加入** 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 **同等的可能性** 被返回。

优化你的算法，使它最小化调用语言 **内置** 随机函数的次数。

实现 Solution 类:

- Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
- int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数



提示:

1 <= n <= 10^9
0 <= blacklist.length <= min(10^5, n - 1)
**0 <= blacklist[i] < n**
blacklist 中所有值都 不同
pick 最多被调用 2 * 10^4 次





# 2、题解

## 题目分析



## 解法一:  

### 算法分析

**我们可以将区间 `[0,N)` 看做一个数组，然后将 `blacklist` 中的元素移到数组的最末尾，同时用一个哈希表进行映射**



### 代码

```java

/**
 * <p>
 * 黑名单中的随机数
 * </p>
 *
 * @author admin
 * @date 2023/6/16
 */
public class leetcode710 {

    public static void main(String[] args) {
        int n = 7;
        int[] blacklist = {2, 3, 5};

        Solution solution = new Solution(n, blacklist);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(solution.pick());
        }
        res.stream().forEach(System.out::println);

    }

    /**
     * 解法一：黑白名单映射
     */
    private static class Solution {

        // 黑白名单的边界
        int bound;
        // 用于记录哪些黑名单索引需要被替换成白名单索引
        HashMap<Integer, Integer> blackToWhiteMapping;

        public Solution(int n, int[] blacklist) {
            bound = n - blacklist.length;
            blackToWhiteMapping = new HashMap<>();

            // 先将所有黑名单数字加入 map
            for (int black : blacklist) {
                // 这里赋值多少都可以
                // 目的仅仅是把键存进哈希表
                // 方便快速判断数字是否在黑名单内
                blackToWhiteMapping.put(black, 666);
            }

            int last = n - 1;
            for (int black : blacklist) {
                // 如果 black 已经在区间 [bound, N) 可以直接忽略
                if (black >= bound) {
                    continue;
                }
                // 跳过所有黑名单中的数字
                while (blackToWhiteMapping.containsKey(last)) {
                    last--;
                }
                // 将黑名单中的索引映射到合法数字
                blackToWhiteMapping.put(black, last);
                last--;
            }

        }

        public int pick() {
            // 随机选取一个白名单索引
            int index = (int) (Math.random() * bound);
            // 这个索引命中了黑名单，
            // 需要被映射到其他位置
            if (blackToWhiteMapping.containsKey(index)) {
                return blackToWhiteMapping.get(index);
            }
            // 若没命中黑名单，则直接返回
            return index;
        }

    }

}

```

输出：

```sh
6
4
6
4
4
0
4

```





### 复杂度分析

- 时间复杂度：初始化为O(m),pick() 为O(1),其中 m 是数组 blacklist 的长度。在初始化结束时，[n-m,n)内的每个数字要么是黑名单数，要么被一个黑名单数所映射，因此white会恰好增加m次，因此初始化的时间复杂度为O(m)。
- 空间复杂度：O(m)。哈希表需要O(m)的空间。



# THE END