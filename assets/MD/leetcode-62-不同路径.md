# 目录

[toc]

# leetcode-62-不同路径

- 时间：2023-08-10
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/unique-paths/description/
- 难度：中等



一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？



**示例 2：**

```
输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
```

**示例 3：**

```
输入：m = 7, n = 3
输出：28
```

**示例 4：**

```
输入：m = 3, n = 3
输出：6
```

 

**提示：**

+ `1 <= m, n <= 100`
+ 题目数据保证答案小于等于 `2 * 10^9`



# 2、题解

## 题目分析



## 解法一:动态规划

### 算法分析

![image-20230810221320713](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230810221320713.png)



### 代码

```java


/**
 * <p>
 * 不同路径
 * </p>
 *
 * @author admin
 * @date 2023/8/10
 */
public class leetcode62 {

    public static void main(String[] args) {
        int m = 3, n = 7;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.uniquePaths(m, n));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.uniquePaths(m, n));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int uniquePaths(int m, int n) {
            // dp[i][j] 是到达 (i, j) 最多路径
            int[][] dp = new int[m + 1][n + 1];

            // 对于第一行 dp[0][j] (一直向右)，或者第一列 dp[i][0] （一直向下），
            // 由于都是在边界，所以只能为 1（路径只有一条）
            for (int i = 0; i < n; i++) dp[0][i] = 1;
            for (int i = 0; i < m; i++) dp[i][0] = 1;

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            return dp[m - 1][n - 1];
        }

    }

    /**
     * 解法二：动态规划 + 空间优化
     * 因为我们每次只需要 dp[i-1][j],dp[i][j-1]
     */
    private static class Solution02 {

        public int uniquePaths(int m, int n) {
            int[] cur = new int[n];

            Arrays.fill(cur, 1);

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] += cur[j - 1];
                }
            }

            return cur[n - 1];
        }

    }

}

```





### 复杂度分析











# THE END