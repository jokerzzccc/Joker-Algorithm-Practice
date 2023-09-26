# 目录

[toc]

# leetcode-2594-修车的最少时间

- 时间：2023-09-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-time-to-repair-cars/description/?envType=daily-question&envId=2023-09-07
- 难度：中等



给你一个整数数组 `ranks` ，表示一些机械工的 **能力值** 。`ranksi` 是第 `i` 位机械工的能力值。能力值为 `r` 的机械工可以在 `r * n2` 分钟内修好 `n` 辆车。

同时给你一个整数 `cars` ，表示总共需要修理的汽车数目。

请你返回修理所有汽车 **最少** 需要多少时间。

**注意：**所有机械工可以同时修理汽车。

 

**示例 1：**

```
输入：ranks = [4,2,3,1], cars = 10
输出：16
解释：
- 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
- 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
- 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
- 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
16 分钟是修理完所有车需要的最少时间。
```

**示例 2：**

```
输入：ranks = [5,1,8], cars = 6
输出：16
解释：
- 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
- 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
- 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
16 分钟时修理完所有车需要的最少时间。
```

 

**提示：**

+ `1 <= ranks.length <= 10^5`
+ `1 <= ranks[i] <= 100`
+ `1 <= cars <= 10^6`







# 2、题解

## 题目分析



## 解法一：二分

### 算法分析

参考链接：

- https://leetcode.cn/problems/minimum-time-to-repair-cars/solutions/2177199/er-fen-da-an-pythonjavacgo-by-endlessche-keqf/?envType=daily-question&envId=2023-09-07



![image-20230908222838614](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230908222838614.png)

![image-20230908222858295](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230908222858295.png)





### 代码

```java


/**
 * <p>
 * 修车的最少时间
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/8
 */
public class leetcode2594 {

    public static void main(String[] args) {
        int[] ranks = {4, 2, 3, 1};
        int cars = 10;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.repairCars(ranks, cars));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.repairCars(ranks, cars));

    }

    /**
     * 解法一：二分查找
     */
    private static class Solution01 {

        public long repairCars(int[] ranks, int cars) {
            // 能力值最小的那个（修车最快的）
            int minR = ranks[0];
            for (int rank : ranks) {
                minR = Math.min(minR, rank);
            }

            // 修好车的时间范围：bottomTime-下限，topTime-上限
            long bottomTime = 0;
            long topTime = (long) minR * cars * cars;

            // 二分查找：最小的最大值
            // 开区间 (bottomTime, topTime)
            while (bottomTime + 1 < topTime) {
                long mid = (bottomTime + topTime) >> 1;
                // 当前时间，可以修好的车数
                long tmpCars = 0;
                for (int rank : ranks) {
                    tmpCars += Math.sqrt(mid / rank);
                }
                if (tmpCars >= cars) {
                    // 可以修好所有的车，那时间范围就往小的那边走
                    topTime = mid;
                } else {
                    // 修不好，时间范围就往大的那边走
                    bottomTime = mid;
                }
            }

            return topTime;
        }

    }

    /**
     * 解法二：二分查找 + 时间优化
     * 能力值相同的人，在 t 分钟内修好的车的个数是一样的。
     */
    private static class Solution02 {

        public long repairCars(int[] ranks, int cars) {
            // 因为最多有100个值不同;
            // cnt 统计 各个能力值的数量
            int[] cnt = new int[101]; // 数组比哈希表更快
            // 能力值最小的那个（修车最快的）
            int minR = ranks[0];
            for (int rank : ranks) {
                cnt[rank]++;
                minR = Math.min(minR, rank);
            }

            // 修好车的时间范围：bottomTime-下限，topTime-上限
            long bottomTime = 0;
            long topTime = (long) minR * cars * cars;

            // 二分查找：最小的最大值
            // 开区间 (bottomTime, topTime)
            while (bottomTime + 1 < topTime) {
                long mid = (bottomTime + topTime) >> 1;
                // 当前时间，可以修好的车数
                long tmpCars = 0;

                // 依据题目限制，1 <= ranks[i] <= 100
                for (int rank = minR; rank <= 100 && tmpCars < cars; rank++) {
                    // 能力值相同的人，在 t 分钟内修好的车的个数是一样的。
                    tmpCars += (long) Math.sqrt(mid / rank) * cnt[rank];
                }
                if (tmpCars >= cars) {
                    // 可以修好所有的车，那时间范围就往小的那边走
                    topTime = mid;
                } else {
                    // 修不好，时间范围就往大的那边走
                    bottomTime = mid;
                }
            }

            return topTime;
        }

    }

}

```





### 复杂度分析

优化后的：

![image-20230908222923380](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230908222923380.png)









# THE END