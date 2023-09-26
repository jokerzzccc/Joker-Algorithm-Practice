# 目录

[toc]

# leetcode-410-分割数组的最大值

- 时间：2023-06-15

- 参考链接：
  - [二分搜索运用技巧](https://mp.weixin.qq.com/s/EjL65QmfX20xhhd-wKlgSg)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/split-array-largest-sum/
- 难度：困难



给定一个非负整数数组 `nums` 和一个整数 `m` ，你需要将这个数组分成 `m` 个非空的连续子数组。

设计一个算法使得这 `m` 个子数组各自和的最大值最小。



提示：

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)





# 2、题解

## 题目分析

- 类比 leetcode-1011
- 



## 解法一:  二分搜索

### 算法分析

其实，这道题和上面讲的运输问题 leetcode-1011 是一模一样的，不相信的话我给你改写一下题目：
你只有一艘货船，现在有若干货物，每个货物的重量是nums[i],现在你需要在天内将这些货物运走，请问你的货船的最小载重是多少？

这不就是刚才我们解决的力扣第1011题「在D天内送达包裹的能力」吗？

- 货船每天运走的货物就是nums的一个子数组；

- 在m天内运完就是将nums划分成个子数组；

- 让货船的载重尽可能小，就是让所有子数组中最大的那个子数组元素之和尽可能小。



所以这道题的解法直接复制粘贴运输问题的解法代码即可：



### 代码

```java
/**
 * <p>
 * 分割数组的最大值
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode410 {

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;

        Solution01 solution01 = new Solution01();
        int split01 = solution01.splitArray(nums, m);
        System.out.println(split01);
    }

    /**
     * 解法一：二分搜索，左侧边界
     * 和 leetcode1011 代码一模一样
     */
    private static class Solution01 {

        public int splitArray(int[] weights, int k) {
            // 船的最小载重
            int left = 0;
            // 最大载重
            // right 是开区间，所以 +1
            int right = 1;
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (f(weights, mid) <= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        /**
         * 当运载能力为 x 时，需要 f(x) 天运送完货物。
         * f(x) 随着 x 的增加而递减
         */
        int f(int[] weights, int x) {
            int days = 0;
            for (int i = 0; i < weights.length; ) {
                int cap = x;
                while (i < weights.length) {
                    if (cap < weights[i]) {
                        break;
                    } else {
                        cap -= weights[i];
                    }
                    i++;
                }
                days++;
            }
            return days;
        }

    }

}
```

输出：

```sh
18
```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：





# THE END