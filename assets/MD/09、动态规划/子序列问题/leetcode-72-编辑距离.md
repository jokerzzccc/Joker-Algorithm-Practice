# 目录

[toc]

# leetcode-72-编辑距离

- 时间：2023-05-26
- 参考链接：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/zi-xu-lie--6bc09/jing-dian--e5f5e/
  - https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html#%E6%80%9D%E8%B7%AF
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/edit-distance/
- 难度：困难



给你两个单词 `word1` 和 `word2`， *请返回将 `word1` 转换成 `word2` 所使用的最少操作数* 。

你可以对一个单词进行如下三种操作：

+ 插入一个字符
+ 删除一个字符
+ 替换一个字符



**示例 1：**

```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

**示例 2：**

```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```



**提示：**

+ `0 <= word1.length, word2.length <= 500`
+ `word1` 和 `word2` 由小写英文字母组成

Related Topics

字符串

动态规划



# 2、题解

## 题目分析



## 解法一：自底向上

### 算法分析

**状态定义：**

- `dp[i][j]` 代表 word1 前 i 个字符串转换成 word2 前 j 个字符串需要最少步数

所以，**转移方程**

- 当 word1[i] == word2[j]，`dp[i][j] = dp[i-1][j-1]`；

- 当 word1[i] != word2[j]，`dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1`

其中，`dp[i-1][j-1]` 表示替换操作，`dp[i-1][j]` 表示删除操作，`dp[i][j-1]` 表示插入操作。

**边界条件**

注意，针对第一行，第一列要单独考虑：

第一行，是 `word1` 为空变成 `word2` 最少步数，就是插入操作

第一列，是 `word2` 为空，需要的最少步数，就是删除操作



三个操作的加深理解：

```sh
对“dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。”的补充理解：

以 word1 为 "horse"，word2 为 "ros"，且 dp[5][3] 为例，即要将 word1的前 5 个字符转换为 word2的前 3 个字符，也就是将 horse 转换为 ros，因此有：

(1) dp[i-1][j-1]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 2 个字符 ro，然后将第五个字符 word1[4]（因为下标基数以 0 开始） 由 e 替换为 s（即替换为 word2 的第三个字符，word2[2]）

(2) dp[i][j-1]，即先将 word1 的前 5 个字符 horse 转换为 word2 的前 2 个字符 ro，然后在末尾补充一个 s，即插入操作

(3) dp[i-1][j]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 3 个字符 ros，然后删除 word1 的第 5 个字符
```



### 代码

```java
/**
 * <p>
 * 编辑距离
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode72 {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        Solution01 solution01 = new Solution01();
        int minDistance01 = solution01.minDistance(word1, word2);
        System.out.println(minDistance01);

    }

    /**
     * 解法一：自底向上
     */
    private static class Solution01 {

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            // dp[i][j] 代表 word1 前 i 个字符串转换成 word2 前 j 个字符串需要最少步数
            int[][] dp = new int[m + 1][n + 1];
            // 边界条件，即，当 word2 为空串时，word1 需要全部删除；
            for (int i = 1; i <= m; i++) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
            // 边界条件，即，当 word1 为空串时，word1 需要全部插入；
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j - 1] + 1;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        // 相等的时候，可以不做操作
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // 不等的时候，三种操作选最小
                        // dp[i-1][j-1] 表示替换操作，
                        // dp[i-1][j] 表示删除操作，
                        // dp[i][j-1] 表示插入操作。
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }
            
            // debug
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp[m][n];
        }

    }
```

输出如下：

```sh
[0, 1, 2, 3]
[1, 1, 2, 3]
[2, 2, 1, 2]
[3, 2, 2, 2]
[4, 3, 3, 2]
[5, 4, 4, 3]
3
```



### 复杂度分析

- 时间复杂度 ：*O*(*mn*)，其中 *m* 为 `word1` 的长度，*n* 为 `word2` 的长度。

- 空间复杂度 ：*O*(*mn*)，我们需要大小为 *O*(*mn*) 的 *D* 数组来记录状态值。



## 解法二：自顶向下-耗时更短

### 算法分析

- **解决两个字符串的动态规划问题，一般都是用两个指针 `i, j` 分别指向两个字符串的最后，然后一步步往前移动，缩小问题的规模**。





### 代码

```java
/**
 * <p>
 * 编辑距离
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode72 {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        Solution02 solution02 = new Solution02();
        int minDistance02 = solution02.minDistance(word1, word2);
        System.out.println(minDistance02);

    }

    /**
     * 解法二：自顶向下
     */
    private static class Solution02 {

        /**
         * 备忘录：减少计算重叠子问题；
         */
        int[][] memo;

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            memo = new int[m][n];

            // -1 表示未被计算
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            int dp = dp(word1, m - 1, word2, n - 1);
            // debug
            Arrays.stream(memo).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp;

        }

        int dp(String word1, int i, String word2, int j) {
            // base case:  i 走完 s1 或 j 走完 s2，可以直接返回另一个字符串剩下的长度
            if (i == -1) {
                return j + 1;
            }
            if (j == -1) {
                return i + 1;
            }

            // 避免重复计算重叠子问题
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                // 相等的时候，啥都不做
                memo[i][j] = dp(word1, i - 1, word2, j - 1);
                // debug
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            } else {
                memo[i][j] = Math.min(dp(word1, i - 1, word2, j) + 1, // 删除
                        Math.min(dp(word1, i, word2, j - 1) + 1 // 插入
                                , dp(word1, i - 1, word2, j - 1) + 1 // 替换
                        ));
                // debug
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            }

            return memo[i][j];
        }

    }

}
```

输出如下：

```java
memo[i-0 j-0]: 1
memo[i-1 j-1]: 1
memo[i-2 j-0]: 2
memo[i-1 j-0]: 2
memo[i-2 j-1]: 2
memo[i-3 j-2]: 2
memo[i-3 j-0]: 3
memo[i-3 j-1]: 3
memo[i-4 j-0]: 4
memo[i-4 j-1]: 4
memo[i-4 j-2]: 3
[1, -1, -1]
[2, 1, -1]
[2, 2, -1]
[3, 3, 2]
[4, 4, 3]
3
```





### 复杂度分析







# THE END