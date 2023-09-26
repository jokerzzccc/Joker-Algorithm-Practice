# 目录

[toc]

# leetcode-593-有效的正方形

- 时间：2023-07-03
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/valid-square/?company_slug=alibaba
- 难度：中等



给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。

点的坐标 pi 表示为 [xi, yi] 。 输入没有任何顺序 。

一个 **有效的正方形** 有四条等边和四个等角(90度角)。

**示例 1:**

```
输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
输出: True
```



提示:

- p1.length == p2.length == p3.length == p4.length == 2
- -10^4 <= xi, yi <= 10^4



# 2、题解

## 题目分析



## 解法一：数学

### 算法分析

[正方形判定定理](https://baike.baidu.com/item/正方形判定定理/5599805)是几何学里用于判定一个四边形是否为正方形的判定定理。判别正方形的一般顺序为先说明它是平行四边形；再说明它是菱形（或矩形）；最后说明它是矩形（或菱形）。那么我们可以从枚举四边形的两条斜边入手来进行判断：

1. 如果两条斜边的中点相同：则说明以该两条斜边组成的四边形为「平行四边形」。
2. 在满足「条件一」的基础上，如果两条斜边的长度相同：则说明以该两条斜边组成的四边形为「矩形」。
3. 在满足「条件二」的基础上，如果两条斜边的相互垂直：则说明以该两条斜边组成的四边形为「正方形」。





### 代码

```java


/**
 * <p>
 * 有效的正方形
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode593 {

    public static void main(String[] args) {
        int[] p1 = {0, 0}, p2 = {1, 1}, p3 = {1, 0}, p4 = {0, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.validSquare(p1, p2, p3, p4));

    }

    /**
     * 解法一：数学，几何
     */
    private static class Solution01 {

        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            // 两条边为 (p1, p2) (p3, p4)
            if (Arrays.equals(p1, p2)) {
                return false;
            }
            if (help(p1, p2, p3, p4)) {
                return true;
            }

            // 两条边为 (p1, p3) (p2, p4)
            if (Arrays.equals(p1, p3)) {
                return false;
            }
            if (help(p1, p3, p2, p4)) {
                return true;
            }

            // 两条边为 (p1, p4) (p2, p3)
            if (Arrays.equals(p1, p4)) {
                return false;
            }
            if (help(p1, p4, p2, p3)) {
                return true;
            }

            return false;

        }

        public boolean help(int[] p1, int[] p2, int[] p3, int[] p4) {
            int[] v1 = {p1[0] - p2[0], p1[1] - p2[1]};
            int[] v2 = {p3[0] - p4[0], p3[1] - p4[1]};
            if (checkMidPoint(p1, p2, p3, p4) && checkLength(v1, v2) && calCos(v1, v2)) {
                return true;
            }

            return false;
        }

        /**
         * 检查两条斜边的长度是否相同 -> 矩形
         */
        public boolean checkLength(int[] v1, int[] v2) {
            return (v1[0] * v1[0] + v1[1] * v1[1]) == (v2[0] * v2[0] + v2[1] * v2[1]);
        }

        /**
         * 检查两条斜边的中点是否相同 -> 平行四边形
         */
        public boolean checkMidPoint(int[] p1, int[] p2, int[] p3, int[] p4) {
            return (p1[0] + p2[0]) == (p3[0] + p4[0]) && (p1[1] + p2[1]) == (p3[1] + p4[1]);
        }

        /**
         * 检查两条斜边是否垂直 -> 两条斜边是否相互垂直
         */
        public boolean calCos(int[] v1, int[] v2) {
            return (v1[0] * v2[0] + v1[1] * v2[1]) == 0;
        }

    }

}

```





### 复杂度分析











# THE END