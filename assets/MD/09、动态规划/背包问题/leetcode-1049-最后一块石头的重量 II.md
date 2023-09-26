# 目录

[toc]

# leetcode-1049-最后一块石头的重量 II

- 时间：2023-07-16
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/last-stone-weight-ii/
- 难度：中等

有一堆石头，用整数数组 stones 表示。其中 `stones[i]` 表示第 i 块石头的重量。

每一回合，从中选出**任意两块石头**，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

- 如果 x == y，那么两块石头都会被完全粉碎；
- 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。

最后，**最多只会剩下一块** 石头。返回此石头 **最小的可能重量** 。如果没有石头剩下，就返回 0。



示例 1：

```sh
输入：stones = [2,7,4,1,8,1]
输出：1
解释：
组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
```



**提示：**

+ `1 <= stones.length <= 30`
+ `1 <= stones[i] <= 100`



# 2、题解

## 题目分析



## 解法一: 动态规划-0/1背包问题

### 算法分析

- 参考链接；
  - https://leetcode.cn/problems/last-stone-weight-ii/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-i-95p9/

![image-20230716144031906](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230716144031906.png)





### 代码

```java

/**
 * <p>
 * 最后一块石头的重量 II
 * </p>
 *
 * @author admin
 * @date 2023/7/16
 */
public class leetcode1049 {

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.lastStoneWeightII(stones));

    }

    /**
     * 解法一：动态规划-0/1 背包，
     */
    private static class Solution01 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            int target = sum / 2;
            int n = stones.length;
            int res = Integer.MAX_VALUE;

            // dp[i + 1][j] 表示前 i 个石头能否凑出重量 j
            boolean[][] dp = new boolean[n + 1][target + 1];
            // base case
            // dp[0][...] 表示不选任何石头的状态，所以 dp[0][0] 为 true, 其余 dp[0][j] 均为 false
            dp[0][0] = true;

            // 状态一：可选择的物品 stones[i]
            for (int i = 0; i < n; i++) {
                // 状态一：背包的容量 target
                for (int weight = 0; weight <= target; weight++) {
                    // 做选择
                    if (weight < stones[i]) {
                        // 不能选第 i 个石头
                        dp[i + 1][weight] = dp[i][weight];

                    } else {
                        // 不选：  dp[i][weight]
                        // 选：则需要考虑能否凑出重量 weight - stones[i]
                        dp[i + 1][weight] = dp[i][weight] || dp[i][weight - stones[i]];
                    }

                    // 更新答案，
                    // 求出所有的 dp[n][...] 后，最大的 weight 即为，符合要求能取到的小于等于 target 的最大重量
                    if (dp[n][weight]) {
                        res = Math.min(res, sum - 2 * weight);
                    }
                }
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：`O(n*sum)`。其中n是数组 stones 的长度，sum为stones所有元素之和。
- 空间复杂度：O(sum)。





## 解法二：动态规划-0/1 背包，(一维数组优化)

### 算法分析

- 参考链接；
  - https://leetcode.cn/problems/last-stone-weight-ii/solution/yi-pian-wen-zhang-chi-tou-bei-bao-wen-ti-5lfv/



这道题看出是背包问题比较有难度
最后一块石头的重量：从一堆石头中,每次拿两块重量分别为x,y的石头,若x=y,则两块石头均粉碎;若x<y,两块石头变为一块重量为y-x的石头求最后剩下石头的最小重量(若没有剩下返回0)
问题转化为：**把一堆石头分成两堆,求两堆石头重量差最小值**
进一步分析：要让差值小,两堆石头的重量都要接近`sum/2`;我们假设两堆分别为A,B,A<sum/2,B>sum/2,若A更接近sum/2,B也相应更接近sum/2
进一步转化：**将一堆stone放进最大容量为`sum/2`的背包,求放进去的石头的最大重量 MaxWeight,最终答案即为 sum-2*MaxWeight;、**

0/1背包最值问题：外循环 stones,内循环`target=sum/2`倒序,应用转移方程1





### 代码

```java


/**
 * <p>
 * 最后一块石头的重量 II
 * </p>
 *
 * @author admin
 * @date 2023/7/16
 */
public class leetcode1049 {

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.lastStoneWeightII(stones));

    }


    /**
     * 解法二：动态规划-0/1 背包，(一维数组优化)
     */
    private static class Solution02 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            // 背包容量
            int target = sum / 2;

            // dp[i] 表示，当前 stone 之前（包含当前）的几个石头，当重量为 i 时，能放进去的最多石头的重量和 maxWeight
            int[] dp = new int[target + 1];

            for (int stone : stones) {
                for (int i = target; i >= stone; i--) {
                    // 做选择，不放进去，和放进去
                    dp[i] = Math.max(dp[i], dp[i - stone] + stone);
                }
            }

            return sum - 2 * dp[target];
        }

    }

}

```





### 复杂度分析



## 解法三：动态规划-0/1 背包，(二维数组)

### 算法分析





### 代码

```java


/**
 * <p>
 * 最后一块石头的重量 II
 * </p>
 *
 * @author admin
 * @date 2023/7/16
 */
public class leetcode1049 {

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.lastStoneWeightII(stones));

    }


    /**
     * 解法二：动态规划-0/1 背包，
     */
    private static class Solution02 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            // 背包容量
            int target = sum / 2;
            int n = stones.length;

            // dp[i][j] 表示，表示前 i 个石头可以凑出的满足要求的最大重量
            int[][] dp = new int[n + 1][target + 1];
            for (int i = 1; i <= n; i++) {
                int curStone = stones[i - 1];
                for (int j = 0; j <= target; j++) {
                    // 不选 当前石头
                    dp[i][j] = dp[i - 1][j];
                    if (j >= curStone) {
                        // 不选或者选当前石头
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - curStone] + curStone);
                    }
                }
            }

            return Math.abs(sum - 2 * dp[n][target]);
        }

    }


}

```





### 复杂度分析





# THE END