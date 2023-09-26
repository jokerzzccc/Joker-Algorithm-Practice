# 目录

[toc]

# LCP-74-最强祝福力场

- 时间：2023-08-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/xepqZ5/description/
- 难度：中等

![image-20230808210227326](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230808210227326.png)

![image-20230808210243501](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230808210243501.png)

![image-20230808210258654](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230808210258654.png)

![image-20230808210309433](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230808210309433.png)



# 2、题解

## 题目分析



## 解法一

### 算法分析

思路：

- 统计所有左下和右上坐标，由于会出现 0.50.50.5，可以将坐标乘 222。
- 离散化横纵坐标。
- 二维差分，具体见视频讲解。
- 用二维前缀和复原，计算最大值。



### 代码

```java


/**
 * <p>
 * 最强祝福力场
 * </p>
 *
 * @author admin
 * @date 2023/8/7
 */
public class LCP74 {

    public static void main(String[] args) {
        int[][] forceField = {
                {4, 4, 6},
                {7, 5, 3},
                {1, 6, 2},
                {5, 6, 3}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.fieldOfGreatestBlessing(forceField));

    }

    /**
     * 解法一：离散化 + 二维差分
     */
    private static class Solution01 {

        public int fieldOfGreatestBlessing(int[][] forceField) {
            // 1. 统计所有左下和右上坐标
            int nf = forceField.length, k = 0;
            // 横坐标
            long[] xs = new long[nf * 2];
            // 纵坐标
            long[] ys = new long[nf * 2];

            for (int[] f : forceField) {
                long i = f[0], j = f[1], side = f[2];
                // 由于会出现0.5, 所以将坐标乘 2
                xs[k] = 2 * i - side;
                xs[k + 1] = 2 * i + side;
                ys[k++] = 2 * j - side;
                ys[k++] = 2 * j + side;
            }

            // 2. 排序去重
            xs = unique(xs);
            ys = unique(ys);

            // 3. 二维差分
            // 快速的把一个矩形范围内的数都 +1
            int n = xs.length, m = ys.length;
            int[][] diff = new int[n + 2][m + 2];
            for (int[] f : forceField) {
                long i = f[0], j = f[1], side = f[2];
                int r1 = Arrays.binarySearch(xs, 2 * i - side);
                int r2 = Arrays.binarySearch(xs, 2 * i + side);
                int c1 = Arrays.binarySearch(ys, 2 * j - side);
                int c2 = Arrays.binarySearch(ys, 2 * j + side);

                // 将区域 r1<=r<=r2 && c1<=c<=c2 上的数都加上 x
                // 多 +1 是为了方便求后面复原
                ++diff[r1 + 1][c1 + 1];
                --diff[r1 + 1][c2 + 2];
                --diff[r2 + 2][c1 + 1];
                ++diff[r2 + 2][c2 + 2];
            }

            // 4. 直接在 diff 二维前缀和上复原，计算最大值
            int ans = 0;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= m; ++j) {
                    // 前缀和公式
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                    ans = Math.max(ans, diff[i][j]);
                }
            }

            return ans;
        }

        private long[] unique(long[] a) {
            Arrays.sort(a);
            int k = 0;
            for (int i = 1; i < a.length; i++)
                if (a[k] != a[i])
                    a[++k] = a[i];
            return Arrays.copyOf(a, k + 1);
        }


    }

}

```





### 复杂度分析



- 时间复杂度：O(n^2)，其中 n 为 forceField 的长度。
- 空间复杂度：O(n^2)











# THE END