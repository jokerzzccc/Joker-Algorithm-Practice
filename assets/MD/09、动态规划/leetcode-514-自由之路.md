# 目录

[toc]

# leetcode-514-自由之路

- 时间：2023-06-05
- 参考链接：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/dong-tai-g-9a75a/




# 1、题目

- 题目：https://leetcode.cn/problems/freedom-trail/
- 难度：困难



电子游戏“辐射4”中，任务 **“通向自由”** 要求玩家到达名为 “**Freedom Trail Ring”** 的金属表盘，并使用表盘拼写特定关键词才能开门。

给定一个字符串 `ring` ，表示刻在外环上的编码；给定另一个字符串 `key` ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的**最少**步数。

最初，**ring** 的第一个字符与 `12:00` 方向对齐。您需要顺时针或逆时针旋转 `ring` 以使 **key** 的一个字符在 `12:00` 方向对齐，然后按下中心按钮，以此逐个拼写完 **`key`** 中的所有字符。

旋转 `ring` 拼出 key 字符 `key[i]` 的阶段中：	

1. 您可以将 **ring** 顺时针或逆时针旋转 **一个位置** ，计为1步。旋转的最终目的是将字符串 **`ring`** 的一个字符与 `12:00` 方向对齐，并且这个字符必须等于字符 **`key[i]` 。**
2. 如果字符 **`key[i]`** 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 **1 步**。按完之后，您可以开始拼写 **key** 的下一个字符（下一阶段）, 直至完成所有拼写。



**示例 1：**

![img](https://assets.leetcode.com/uploads/2018/10/22/ring.jpg)



```
输入: ring = "godding", key = "gd"
输出: 4
解释:
 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 当然, 我们还需要1步进行拼写。
 因此最终的输出是 4。
```

**示例 2:**

```
输入: ring = "godding", key = "godding"
输出: 13
```



**提示：**

+ `1 <= ring.length, key.length <= 100`
+ `ring` 和 `key` 只包含小写英文字母
+ **保证** 字符串 `key` 一定可以由字符串 `ring` 旋转拼出

Related Topics

深度优先搜索

广度优先搜索

字符串

动态规划



# 2、题解

## 题目分析

- 拨动一格圆盘和按下圆盘中间的按钮都算是一次操作
- 

## 解法一

### 算法分析

**原题可以转化为：圆盘固定，我们可以拨动指针；现在需要我们拨动指针并按下按钮，以最少的操作次数输入 `key` 对应的字符串**。



那么，这个问题如何使用动态规划的技巧解决呢？或者说，这道题的「状态」和「选择」是什么呢？

**「状态」就是「当前需要输入的字符」和「当前圆盘指针的位置」**。

再具体点，「状态」就是 `i` 和 `j` 两个变量。我们可以用 `i` 表示当前圆盘上指针指向的字符（也就是 `ring[i]`）；用 `j` 表示需要输入的字符（也就是 `key[j]`）。

这样我们可以写这样一个 `dp` 函数：

```java
int dp(String ring, int i, String key, int j);
```

这个 `dp` 函数的定义如下：

**当圆盘指针指向 `ring[i]` 时，输入字符串 `key[j..]` 至少需要 `dp(ring, i, key, j)` 次操作**。

根据这个定义，题目其实就是想计算 `dp(ring, 0, key, 0)` 的值，而且我们可以把 `dp` 函数的 base case 写出来：

```java
int dp(String ring, int i, String, int j) {
    // base case，完成输入
    if (j == key.length()) return 0;
    // ...
}

```

接下来，思考一下如何根据状态做选择，如何进行状态转移？

**「选择」就是「如何拨动指针得到待输入的字符」**。

再具体点就是，对于现在想输入的字符 `key[j]`，我们可以如何拨动圆盘，得到这个字符？



大致的代码逻辑如下：

```java
int dp(String ring, int i, String, int j) {
    // base case 完成输入
    if (j == key.length()) return 0;
    
    // 做选择
    int res = Integer.MAX_VALUE;
    for (int k : [字符 key[j] 在 ring 中的所有索引]) {
        res = min(
            把 i 顺时针转到 k 的代价,
            把 i 逆时针转到 k 的代价
        );
    }
    
    return res;
}

```







### 代码

```java
 * <p>
 * 自由之路
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/5
 */
public class leetcode514 {

    public static void main(String[] args) {
        String ring = "godding", key = "gd";

        Solution01 solution01 = new Solution01();
        int rotateSteps01 = solution01.findRotateSteps(ring, key);
        System.out.println(rotateSteps01);

    }

    /**
     * 解法一：动态规划 + 递归
     */
    private static class Solution01 {

        /**
         * K-字符，V-索引列表
         */
        Map<Character, List<Integer>> charToIndex = new HashMap<>();
        /**
         * 当圆盘指针指向 `ring[i]` 时，输入字符串 `key[j..]` 至少需要 `dp(ring, i, key, j)` 次操作
         */
        int[][] memo;

        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();

            // 备忘录全部初始化为0
            memo = new int[m][n];
            // 记录圆环上字符到索引的映射
            for (int i = 0; i < m; i++) {
                char c = ring.charAt(i);
                if (!charToIndex.containsKey(c)) {
                    charToIndex.put(c, new LinkedList<>());
                }
                charToIndex.get(c).add(i);
            }

            // 圆盘指针最初指向 12 点钟方向，
            // 从第一个字符开始输入 key
            return dp(ring, 0, key, 0);
        }

        /**
         * @param ring ring
         * @param i ring 字符索引
         * @param key key
         * @param j key 字符索引
         * @return 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
         */
        private int dp(String ring, int i, String key, int j) {
            // base case
            if (j == key.length()) {
                return 0;
            }
            // 查找备忘录，避免重叠子问题
            if (memo[i][j] != 0) {
                return memo[i][j];
            }
            int n = ring.length();
            // 做选择
            int res = Integer.MAX_VALUE;
            // ring 上可能有多个字符 key[j]
            for (Integer k : charToIndex.get(key.charAt(j))) {
                // 拨动指针的次数
                int delta = Math.abs(k - i);
                // 选择顺时针还是逆时针
                delta = Math.min(delta, n - delta);
                // 将指针拨到 ring[k]，继续输入 key[j+1..]
                int subProblem = dp(ring, k, key, j + 1);
                // 选择「整体」操作次数最少的
                // 加一是因为按动按钮也是一次操作
                res = Math.min(res, 1 + delta + subProblem);
            }
            // 将结果存入备忘录
            memo[i][j] = res;
            return res;
        }

    }
}
```





### 复杂度分析











# THE END