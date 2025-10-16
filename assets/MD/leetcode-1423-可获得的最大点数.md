# 目录

[toc]

# leetcode-1423-可获得的最大点数

- 时间：2023-12-03
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/description/?envType=daily-question&envId=2023-12-03
- 难度：中等

几张卡牌 **排成一行**，每张卡牌都有一个对应的点数。点数由整数数组 `cardPoints` 给出。

每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 `k` 张卡牌。

你的点数就是你拿到手中的所有卡牌的点数之和。

给你一个整数数组 `cardPoints` 和整数 `k`，请你返回可以获得的最大点数。

 

**示例 1：**

```
输入：cardPoints = [1,2,3,4,5,6,1], k = 3
输出：12
解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
```

**示例 2：**

```
输入：cardPoints = [2,2,2], k = 2
输出：4
解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
```

**示例 3：**

```
输入：cardPoints = [9,7,7,9,7,7,9], k = 7
输出：55
解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
```

**示例 4：**

```
输入：cardPoints = [1,1000,1], k = 1
输出：1
解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。 
```

**示例 5：**

```
输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
输出：202
```

 

**提示：**

- `1 <= cardPoints.length <= 10^5`
- `1 <= cardPoints[i] <= 10^4`
- `1 <= k <= cardPoints.length`



函数签名：

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {

    }
}
```





# 2、题解

## 题目分析

参考链接：

- https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/solutions/2551432/liang-chong-fang-fa-ni-xiang-si-wei-zhen-e3gb/?envType=daily-question&envId=2023-12-03



## 解法一：滑动窗口

### 算法分析

- 逆向思维
- 设总数量为 n , 因为总和为固定值 total，所求的个数为固定数量 K ，且必须在首位取值，那剩下的肯定是连续子数组。
- 问题转换为：求 n - k 个连续子数组的最小值。



### 代码

```java
public class leetcode1423 {

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxScore(cardPoints, k));

    }

    /**
     * 解法一：滑动窗口：
     * 可以用逆向思维求，剩下的 n - k 个连续子数组的最小值是多少
     */
    private static class Solution01 {

        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;
            // 固定窗口大小
            int size = n - k;
            int totalSum = Arrays.stream(cardPoints).sum();

            int windowSum = 0;
            for (int i = 0; i < size; i++) {
                windowSum += cardPoints[i];
            }

            int minSum = windowSum;
            for (int i = size; i < n; i++) {
                windowSum += cardPoints[i] - cardPoints[i - size];
                minSum = Math.min(minSum, windowSum);
            }

            return totalSum - minSum;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)，其中 n 为 cardPoints 的长度。
- 空间复杂度：O(1)。仅用到若干额外变量。



## 解法二：前缀和

### 算法分析

答案等于如下结果的最大值：

前 kkk 个数的和。
前 k−1k-1k−1 个数以及后 111 个数的和。
前 k−2k-2k−2 个数以及后 222 个数的和。
……
前 222 个数以及后 k−2k-2k−2 个数的和。
前 111 个数以及后 k−1k-1k−1 个数的和。
后 kkk 个数的和。





### 代码

```java
public class leetcode1423 {

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maxScore(cardPoints, k));

    }


    /**
     * 解法二：前缀和
     * 正向思考
     * 答案等于如下结果的最大值：
     * <p>
     * 前 kkk 个数的和。
     * 前 k−1k-1k−1 个数以及后 111 个数的和。
     * 前 k−2k-2k−2 个数以及后 222 个数的和。
     * ……
     * 前 222 个数以及后 k−2k-2k−2 个数的和。
     * 前 111 个数以及后 k−1k-1k−1 个数的和。
     * 后 kkk 个数的和。
     * <p>
     */
    private static class Solution02 {

        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;
            int tmpSum = 0;
            for (int i = 0; i < k; i++) {
                tmpSum += cardPoints[i];
            }

            int ans = tmpSum;
            for (int i = 1; i <= k; i++) {
                tmpSum += cardPoints[n - i] - cardPoints[k - i];
                ans = Math.max(ans, tmpSum);
            }

            return ans;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(k)，其中 k 为入参。
- 空间复杂度：O(1)。仅用到若干额外变量。



# THE END