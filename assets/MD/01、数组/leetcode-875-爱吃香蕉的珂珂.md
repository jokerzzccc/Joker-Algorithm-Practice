# 目录

[toc]

# leetcode-875-爱吃香蕉的珂珂

- 时间：2023-06-15

- 参考链接：
  - [二分搜索运用技巧](https://mp.weixin.qq.com/s/EjL65QmfX20xhhd-wKlgSg)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/koko-eating-bananas/
- 难度：中等







# 2、题解

## 题目分析



## 解法一:  二分搜索

### 算法分析

**1、确定x,f(x),target分别是什么，并写出函数f的代码。**
自变量x是什么呢？回忆之前的函数图像，二分搜索的本质就是在搜索自变量。
所以，**题目让求什么，就把什么设为自变量**，珂珂**吃香蕉的速度就是自变量x**,
那么，在x上单调的函数关系f(x)是什么？
显然，吃香蕉的速度越快，吃完所有香蕉堆所需的时间就越少，速度和时间就是一
个单调函数关系。
所以，**f(x)函数**就可以这样定义：
**若吃香蕉的速度为 x 根/小时，则需要 f(x) 小时吃完所有香蕉。**

target就很明显了，**吃香蕉的时间限制H自然就是target**,是对f(x)返回值的最大约束。



**2、找到x的取值范围作为二分搜索的搜索区间，初始化1eEt和right变量。**
珂珂吃香蕉的速度最小是多少？多大是多少？
显然，最小速度应该是1，最大速度是pi1es数组中元素的最大值，因为每小时
最多吃一堆香蕉，胃口再大也白搭嘛。
这里可以有两种选择，

- 要么你用一个fo”循环去遍历pi1e3数组，计算最大值，
- 要么你看题目给的约束，pi1es中的元素取值范围是多少，然后给right初始
  化一个取值范围之外的值。
  - 我选择第二种，题目说了1<=pi1es[i]<=10^9,那么我就可以确定二分
    搜索的区间边界：



3、根据题目的要求，确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码。
现在我们确定了自变量x是吃香蕉的速度，f(x)是单调递减的函数，target就是吃香蕉的时间限制H,题目要我们计算最小速度，也就是x要尽可能小：

这就是**搜索左侧边界的二分搜索**嘛，不过注意()是单调递减的，不要闭眼睛套框架，需要结合上图进行思考，写出代码：



### 代码

```java

/**
 * <p>
 * 爱吃香蕉的珂珂
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode875 {

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        Solution01 solution01 = new Solution01();
        int minEatingSpeed01 = solution01.minEatingSpeed(piles, h);
        System.out.println(minEatingSpeed01);

    }

    /**
     * 解法一：二分搜索（左侧边界）
     */
    private static class Solution01 {

        public int minEatingSpeed(int[] piles, int h) {
            // 最小速度，1
            int left = 1;
            // right 是开区间，所以 +1
            int right = (int) (Math.pow(10, 9) + 1);

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right) {
                // mid 就是 x,
                int mid = left + (right - left) / 2;
                if (f(piles, mid) == h) {
                    // 搜索左侧边界，则需要收缩右侧边界
                    right = mid;
                } else if (f(piles, mid) < h) {
                    // 需要让 f(x) 的返回值大一些，即，x 值小一些
                    right = mid;
                } else if (f(piles, mid) > h) {
                    // 需要让 f(x) 的返回值小一些，即，x 值大一些
                    left = mid + 1;
                }
            }
            return left;
        }

        /**
         * f(x) 随着 x 的增加而减少：
         * 若吃香蕉的速度为 x 根/小时，则需要 f(x) 小时吃完所有香蕉
         */
        int f(int[] piles, int x) {
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += piles[i] / x;
                if (piles[i] % x > 0) {
                    hours++;
                }
            }
            return hours;
        }

    }

}

```

输出：

```sh
4
```





### 复杂度分析

- 时间复杂度：O(n log m),其中n是数组ples的长度，m是数组ples中的最大值。需要O(m)的时间遍历数组找到最大值m,二分查找需要执行O(1ogm)轮，每一轮二分查找需要O(n)的时间，因此总时间复杂度是O(n log m)。
- 空间复杂度：O(1)。





# THE END