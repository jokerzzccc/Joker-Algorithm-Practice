# 目录

[toc]

# leetcode-904-水果成篮

- 时间：2023-07-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/fruit-into-baskets/
- 难度：中等



- 这个题目中文的有一点错误理解，应该看英文的。

![image-20230708223402898](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230708223402898.png)



# 2、题解

## 题目分析

- 题目可以理解为求**只包含两种元素的最长连续子序列**

## 解法一：滑动窗口

### 算法分析





### 代码

```java


/**
 * <p>
 * 水果成篮
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode940 {

    public static void main(String[] args) {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.totalFruit(fruits));

    }

    /**
     * 解法一：滑动窗口（最大滑窗）
     * 题目可以理解为求只包含两种元素的最长连续子序列
     * (看英文题目）
     */
    private static class Solution01 {

        public int totalFruit(int[] fruits) {
            int n = fruits.length;
            // 滑动窗口
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

            int left = 0, res = 0;
            for (int right = 0; right < n; ++right) {
                cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
                // 超过窗口值，则，缩小窗口，
                // 如果篮子中的水果种类超过两种，则需要移动左边界，对应从子序列中删去水果的value要减一
                while (cnt.size() > 2) {
                    cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                    // 若对应水果key的value变为0，说明篮子里已经没有这种水果了，水果种类要对应变化
                    if (cnt.get(fruits[left]) == 0) {
                        cnt.remove(fruits[left]);
                    }
                    ++left;
                }
                res = Math.max(res, right - left + 1);
            }

            return res;
        }

    }

}

```





### 复杂度分析



- 时间复杂度：O(n),其中n是数组 fruits 的长度。
- 空间复杂度：O(1)。哈希表中最多会有三个键值对，可以看成使用了常数级别的空间。







# THE END