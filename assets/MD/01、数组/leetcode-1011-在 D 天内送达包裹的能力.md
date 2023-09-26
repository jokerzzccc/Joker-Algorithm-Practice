# 目录

[toc]

# leetcode-1011-在 D 天内送达包裹的能力

- 时间：2023-06-15

- 参考链接：
  - [二分搜索运用技巧](https://mp.weixin.qq.com/s/EjL65QmfX20xhhd-wKlgSg)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
- 难度：中等



传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。



**提示：**

+ `1 <= days <= weights.length <= 5 * 104`
+ `1 <= weights[i] <= 500`





# 2、题解

## 题目分析



## 解法一:  二分搜索

### 算法分析

**1、确定x,f(x),七arget分别是什么，并写出函数f的代码。**
题目问什么，什么就是自变量，也就是说船的运载能力就是自变量x。
运输天数和运载能力成反比，所以可以让 f(x) 计算 x 的运载能力下需要的运输天数，那么f(x)是单调递减的。

对于这道题，target显然就是运输天数D,我们要在f(x)==D的约束下，算出船的最小载重。



**2、船的最小载重是多少？最大载重是多少？**
显然，船的最小载重应该是weights数组中元素的最大值，因为每次至少得装一件货物走，不能说装不下嘛。
最大载重显然就是weights数组所有元素之和，也就是一次把所有货物都装走。
这样就确定了搜索区间[1eft,right):



**3、需要根据题目的要求，确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码。**
现在我们确定了自变量是船的载重能力，f(x)是单调递减的函数，target就是运输总天数限制D,题目要我们计算船的最小载重，也就是要尽可能小：这就是搜索左侧边界的二分搜索嘛





### 代码

```java

/**
 * <p>
 * 在 D 天内送达包裹的能力
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode1011 {

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        Solution01 solution01 = new Solution01();
        int days1 = solution01.shipWithinDays(weights, days);
        System.out.println(days1);

    }

    /**
     * 解法一：二分搜索，左侧边界
     */
    private static class Solution01 {

        public int shipWithinDays(int[] weights, int days) {
            // 船的最小载重
            int left= 0;
            // 最大载重
            // right 是开区间，所以 +1
            int right =  1;
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right){
                int mid = left + (right - left) /2;
                if (f(weights, mid) <= days) {
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
            for (int i = 0; i < weights.length;) {
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
15
```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：





# THE END