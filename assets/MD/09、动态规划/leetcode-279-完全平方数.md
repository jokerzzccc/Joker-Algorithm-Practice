# 目录

[toc]

# leetcode-279-完全平方数

- 时间：2023-07-18
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/perfect-squares/
- 难度：中等

​	![image-20230718222524862](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230718222524862.png)



# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

![image-20230718222605625](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230718222605625.png)

确实不太好理解，我来说说如何理解比较简单吧！

首先，我们很容易知道所有的数都分布在[0,根号n]之间。 现在我们对这个区间进行遍历，去计算每一个数的平方。

显而易见这个平方（记为pow）是小于或等于n的：

（1）如果pow等于n，那么结果就是1，这个数就是我们枚举的数

（2）如果pow小于n，那么是不是还剩下了n-pow，我们需要去计算？

这里我们再将n-pow记为rem，这个rem肯定也是小于n的。 因为我们的循环是从小遍历到大的，所以rem需要几个 完全平方数才能得到我们是已知的，假设值为x。rem这里有x 个完全平方数，而我们枚举到的数也是一个平方数，所以最终的 结果就是x+1。 但是用我们当前枚举到的数算出来的x+1可能并不是最小的！ 所以我们需要完成一整次遍历，用min求得最小的x+1。

最终就能得到结果啦！！

### 代码

```java

/**
 * <p>
 * 完全平方数
 * </p>
 *
 * @author admin
 * @date 2023/7/18
 */
public class leetcode279 {

    public static void main(String[] args) {
        int n = 12;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numSquares(n));

    }

    /**
     * 解法一：动态规划
     * 完全背包问题
     */
    private static class Solution01 {

        public int numSquares(int n) {
            // dp[i] 表示，最少需要多少个平方数来表示整数 i
            // 边界条件：dp[0] = 0
            int[] dp = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                // 每次都先取最大，即最坏情况 +1
                dp[i] = i;
                for (int j = 1; j * j <= i; j++) {
                    // 状态转移方程, j*j 本身就是一种组合，所以需要 + 1
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }

            return dp[n];
        }

    }

}

```





### 复杂度分析

- 时间复杂度：$O(n\sqrt{n})$,其中 n 为给定的正整数。状态转移方程的时间复杂度为$O(\sqrt{n})$,共需要计算 n
  个状态，因此总时间复杂度为$O(n\sqrt{n})$。
- 空间复杂度：$O(n)$。我们需要O(n)的空间保存状态。





# THE END