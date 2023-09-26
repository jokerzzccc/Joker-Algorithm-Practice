# 目录

[toc]

# leetcode-2208-将数组和减半的最少操作次数

- 时间：2023-07-25
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/description/
- 难度：中等

![image-20230725205205196](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230725205205196.png)

**提示：**

+ `1 <= nums.length <= 10^5`
+ `1 <= nums[i] <= 10^7`





# 2、题解

## 题目分析



## 解法一: 贪心 + 优先队列（大顶堆）

### 算法分析





### 代码

```java

/**
 * <p>
 * 将数组和减半的最少操作次数
 * </p>
 *
 * @author admin
 * @date 2023/7/25
 */
public class leetcode2208 {

    public static void main(String[] args) {
        int[] nums = {5, 19, 8, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.halveArray(nums));

    }

    /**
     * 解法一：贪心 + 优先队列（大顶堆）
     */
    private static class Solution01 {

        public int halveArray(int[] nums) {
            double sum = 0;

            // 大顶堆的优先队列
            PriorityQueue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
            for (int num : nums) {
                queue.offer(num * 1.0);
                sum += num;
            }

            sum /= 2.0;
            int ans = 0;
            while (sum > 0) {
                double tmp = queue.poll();
                sum -= tmp / 2.0;
                queue.offer(tmp / 2.0);
                ans++;
            }

            return ans;
        }

    }

}

```





### 复杂度分析

- 时间复杂度O(n×logn)
- 空间复杂度O(n)。其中 n 是数组的长度。









# THE END