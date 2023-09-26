# 目录

[toc]

# leetcode-135-分发糖果

- 时间：2023-07-10
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/candy/
- 难度：困难

n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

你需要按照以下要求，给这些孩子分发糖果：

- 每个孩子至少分配到 1 个糖果。
- 相邻两个孩子评分更高的孩子会获得更多的糖果。



请你给每个孩子分发糖果，计算并返回需要准备的 **最少糖果数目** 。



**示例 1：**

```
输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
```





**提示：**

+ `n == ratings.length`
+ `1 <= n <= 2 * 10^4`
+ `0 <= ratings[i] <= 2 * 10^4`







# 2、题解

## 题目分析



## 解法一：贪心 + 两次遍历

### 算法分析

![image-20230710223922054](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230710223922054.png)



这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边，**如果两边一起考虑一定会顾此失彼**。



先确定**右边评分大于左边**的情况（也就是**从前向后遍历**）

此时局部最优：只要右边评分比左边大，右边的孩子就多一个糖果，全局最优：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果

局部最优可以推出全局最优。

如果ratings[i] > ratings[i - 1] 那么[i]的糖 一定要比[i - 1]的糖多一个，所以贪心：candyVec[i] = candyVec[i - 1] + 1

代码如下：

```CPP
// 从前向后
for (int i = 1; i < ratings.size(); i++) {
    if (ratings[i] > ratings[i - 1]) candyVec[i] = candyVec[i - 1] + 1;
}
```

如图：

![image.png](https://pic.leetcode.cn/1683275614-Oedkaw-image.png)



再确定**左孩子大于右孩子**的情况（**从后向前遍历**）

遍历顺序这里有同学可能会有疑问，为什么不能从前向后遍历呢？

因为 rating[5]与rating[4]的比较 要利用上 rating[5]与rating[6]的比较结果，所以 要从后向前遍历。

如果从前向后遍历，rating[5]与rating[4]的比较 就不能用上 rating[5]与rating[6]的比较结果了 。如图：

![image.png](https://pic.leetcode.cn/1683275632-PezogB-image.png)

**所以确定左孩子大于右孩子的情况一定要从后向前遍历！**

如果 ratings[i] > ratings[i + 1]，此时candyVec[i]（第i个小孩的糖果数量）就有两个选择了，一个是candyVec[i + 1] + 1（从右边这个加1得到的糖果数量），一个是candyVec[i]（之前比较右孩子大于左孩子得到的糖果数量）。

那么又要贪心了，局部最优：取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，保证第i个小孩的糖果数量既大于左边的也大于右边的。全局最优：相邻的孩子中，评分高的孩子获得更多的糖果。

局部最优可以推出全局最优。

所以就取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，**candyVec[i]只有取最大的才能既保持对左边candyVec[i - 1]的糖果多，也比右边candyVec[i + 1]的糖果多**。

如图：

![image.png](https://pic.leetcode.cn/1683275649-QCumLZ-image.png)

所以该过程代码如下：

```CPP
// 从后向前
for (int i = ratings.size() - 2; i >= 0; i--) {
    if (ratings[i] > ratings[i + 1] ) {
        candyVec[i] = max(candyVec[i], candyVec[i + 1] + 1);
    }
}
```



### 代码

```java

/**
 * <p>
 * 分发糖果
 * </p>
 *
 * @author admin
 * @date 2023/7/10
 */
public class leetcode135 {

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.candy(ratings));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.candy(ratings));
    }

    /**
     * 解法一：贪心 + 两次遍历
     */
    private static class Solution01 {

        public int candy(int[] ratings) {
            int n = ratings.length;

            // 从左到右遍历数组
            // 左规则：ratings[i-1]< ratings[i]时，i号学生的糖果数量将比 i-1 号孩子的糖果数量多。
            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 1;
                }
            }
            // 从右到左遍历数组
            // 右规则：当 ratings[i] > ratings[i + 1]时，i号学生的糖果数量将比 i+1 号孩子的糖果数量多。
            int right = 0;
            int ret = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    right++;
                } else {
                    right = 1;
                }

                // 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。
                // 每个人最终分得的糖果数量即为这两个数量的最大值。
                ret += Math.max(left[i], right);
            }

            // 返回答案
            return ret;
        }

    }

    /**
     * 解法一：贪心 + 两次遍历
     */
    private static class Solution02 {

        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] candies = new int[n];
            Arrays.fill(candies, 1); // 初始化每个孩子的糖果数为 1

            // 从左到右遍历，保证相邻两个孩子评分更高的孩子获得更多的糖果
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }

            // 从右到左遍历，保证相邻两个孩子评分更高的孩子获得更多的糖果
            for (int i = n - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                }
            }

            // 计算总糖果数
            int total = 0;
            for (int candy : candies) {
                total += candy;
            }

            return total;
        }

    }

}

```

输出：

```sh
5
5
```





### 复杂度分析

- 时间复杂度：O(n),其中 n 是孩子的数量。我们需要遍历两次数组以分别计算满足左规则或右规则的
  最少糖果数量。
- 空间复杂度：O(n),其中 n 是孩子的数量。我们需要保存所有的左规则对应的糖果数量。









# THE END