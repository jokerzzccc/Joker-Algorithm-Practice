# 目录

[toc]

# leetcode-45-跳跃游戏 II

- 时间：2023-06-24
- 参考链接：
  - [如何运用贪心思想玩跳跃游戏](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/ru-he-yun--48a7c/)



# 1、题目

- 题目：https://leetcode.cn/problems/jump-game-ii/
- 难度：中等



给定一个长度为 n 的 **0 索引**整数数组 nums。初始位置为 nums[0]。

每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

- 0 <= j <= nums[i] 
- i + j < n



返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

**提示:**

+ `1 <= nums.length <= 10^4`
+ `0 <= nums[i] <= 1000`
+ 题目保证可以到达 `nums[n-1]`







# 2、题解

## 题目分析

**现在的问题是，保证你一定可以跳到最后一格，请问你最少要跳多少次，才能跳过去**。



## 解法一: 动态规划

### 算法分析

我们先来说说动态规划的思路，采用自顶向下的递归动态规划，可以这样定义一个 `dp` 函数：

```java
// 定义：从索引 p 跳到最后一格，至少需要 dp(nums, p) 步
int dp(int[] nums, int p);
```

我们想求的结果就是 `dp(nums, 0)`，base case 就是当 `p` 超过最后一格时，不需要跳跃：

```java
if (p >= nums.length - 1) {
    return 0;
}
```



这个动态规划应该很明显了，按照前文 [动态规划套路详解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/dong-tai-g-1e688/) 所说的套路，状态就是当前所站立的索引 `p`，选择就是可以跳出的步数。

### 代码

```java


/**
 * <p>
 * 跳跃游戏 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode45 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.jump(nums));

    }

    /**
     * 解法一：动态规划
     * 这个时间复杂度为 O(n^2) ，过高
     */
    private static class Solution01 {

        int[] memo;

        public int jump(int[] nums) {
            int n = nums.length;
            // 备忘录都初始化为 n，相当于 INT_MAX
            // 因为从 0 跳到 n - 1 最多 n - 1 步
            memo = new int[n];
            Arrays.fill(memo, n);

            return dp(nums, 0);

        }

        /**
         * 定义：从索引 p 跳到最后一格，至少需要 dp(nums, p) 步
         */
        private int dp(int[] nums, int p) {
            int n = nums.length;
            // base case
            if (p >= n - 1) {
                return 0;
            }
            // 子问题已经计算过
            if (memo[p] != n) {
                return memo[p];
            }

            int curSteps = nums[p];
            // 你可以选择跳 1 步，2 步...
            for (int i = 1; i <= curSteps; i++) {
                // 穷举每一个选择
                // 计算每一个子问题的结果
                int subProblem = dp(nums, i + p);
                // 取其中最小的作为最终结果
                memo[p] = Math.min(memo[p], subProblem + 1);
            }

            return memo[p];
        }

    }


}

```





### 复杂度分析

该算法的时间复杂度是 递归深度 × 每次递归需要的时间复杂度，即 O(N^2)，在 LeetCode 上是无法通过所有用例的，会超时。

## 解法二: 贪心（更优）

### 算法分析

**贪心算法比动态规划多了一个性质：贪心选择性质**。我知道大家都不喜欢看严谨但枯燥的数学形式定义，那么我们就来直观地看一看什么样的问题满足贪心选择性质。

刚才的动态规划思路，不是要穷举所有子问题，然后取其中最小的作为结果吗？核心的代码框架是这样：

java 🟢cpp 🤖python 🤖go 🤖javascript 🤖

```java
int steps = nums[p];
// 你可以选择跳 1 步，2 步...
for (int i = 1; i <= steps; i++) {
    // 计算每一个子问题的结果
    int subProblem = dp(nums, p + i);
    res = min(subProblem + 1, res);
}
```

for 循环中会陷入递归计算子问题，这是动态规划时间复杂度高的根本原因。

但是，真的需要「递归地」计算出每一个子问题的结果，然后求最值吗？**直观地想一想，似乎不需要递归，只需要判断哪一个选择最具有「潜力」即可**：

![image-20230624213728472](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624213728472.png)

比如上图这种情况，我们站在索引 0 的位置，可以向前跳 1，2 或 3 步，你说应该选择跳多少呢？

**显然应该跳 2 步调到索引 2，因为 `nums[2]` 的可跳跃区域涵盖了索引区间 `[3..6]`，比其他的都大**。如果想求最少的跳跃次数，那么往索引 2 跳必然是最优的选择。

你看，**这就是贪心选择性质，我们不需要「递归地」计算出所有选择的具体结果然后比较求最值，而只需要做出那个最有「潜力」，看起来最优的选择即可**。

绕过这个弯儿来，就可以写代码了：

### 代码

```java

/**
 * <p>
 * 跳跃游戏 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode45 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.jump(nums));
    }


    /**
     * 解法二：贪心
     */
    private static class Solution02 {

        public int jump(int[] nums) {
            int n = nums.length;
            // i 和 end 标记了可以选择的跳跃步数
            // farthest 标记了所有选择 [i..end] 中能够跳到的最远距离
            int end = 0, farthest = 0;
            // jumps 记录了跳跃次数
            int jumps = 0;
            for (int i = 0; i < n - 1; i++) {
                farthest = Math.max(nums[i] + i, farthest);
                if (end == i) {
                    jumps++;
                    end = farthest;
                }
            }

            return jumps;
        }

    }

}

```



结合刚才那个图，就知道这段短小精悍的代码在干什么了：

![image-20230624213820578](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624213820578.png)

`i` 和 `end` 标记了可以选择的跳跃步数，`farthest` 标记了所有选择 `[i..end]` 中能够跳到的最远距离，`jumps` 记录了跳跃次数。

### 复杂度分析

本算法的时间复杂度 O(N)，空间复杂度 O(1)，可以说是非常高效，动态规划都被吊起来打了。





# THE END