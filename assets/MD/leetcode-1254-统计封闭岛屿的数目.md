# 目录

[toc]

# leetcode-1254-统计封闭岛屿的数目

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 







# 1、题目

- 题目：https://leetcode.cn/problems/number-of-closed-islands/
- 难度：中等



二维矩阵 grid 由 0 （土地）和 1 （水）组成。**岛**是由最大的4个方向连通的 0 组成的群，**封闭岛**是一个 **完全** 由1包围（左、上、右、下）的岛。

请返回 **封闭岛屿** 的数目。



示例 1：

![image-20230626205330764](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230626205330764.png)

```sh
输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
输出：2
解释：
灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
```



**提示：**

+ `1 <= grid.length, grid[0].length <= 100`
+ `0 <= grid[i][j] <=1`



# 2、题解

## 题目分析

力扣第 1254 题「[统计封闭岛屿的数目open in new window](https://leetcode.cn/problems/number-of-closed-islands/)」和 leetcode200 有两点不同：

1、用 `0` 表示陆地，用 `1` 表示海水。

2、让你计算「封闭岛屿」的数目。所谓「封闭岛屿」就是上下左右全部被 `1` 包围的 `0`，也就是说**靠边的陆地不算作「封闭岛屿」**。



**那么如何判断「封闭岛屿」呢？其实很简单，把 leetcode200 中那些靠边的岛屿排除掉，剩下的不就是「封闭岛屿」了吗**？

有了这个思路，就可以直接看代码了，注意这题规定 `0` 表示陆地，用 `1` 表示海水：

只要提前把靠边的陆地都淹掉，然后算出来的就是封闭岛屿了。

## 解法一: DFS

### 算法分析





### 代码

```java


/**
 * <p>
 * 统计封闭岛屿的数目
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode1254 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.closedIsland(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int j = 0; j < n; j++) {
                // 把靠上边的岛屿淹掉
                dfs(grid, 0, j);
                // 把靠下边的岛屿淹掉
                dfs(grid, m - 1, j);
            }
            for (int i = 0; i < m; i++) {
                // 把靠左边的岛屿淹掉
                dfs(grid, i, 0);
                // 把靠右边的岛屿淹掉
                dfs(grid, i, n - 1);
            }
            // 遍历 grid，剩下的岛屿都是封闭岛屿
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        // 每发现一个岛屿，岛屿数量加一
                        res++;
                        // 然后使用 DFS 将岛屿淹了,即，1相邻的全部变为0
                        dfs(grid, i, j);
                    }
                }
            }

            return res;

        }

        // 从 (i, j) 开始，将与之相邻的陆地都变成海水
        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == 1) {
                // 已经是海水了
                return;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = 1;
            // 淹没上下左右的陆地
            dfs(grid, i + 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i - 1, j);
            dfs(grid, i, j - 1);
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(mn),其中m,n分别为矩阵的行数与列数。利用深度优先搜索只需对整个矩阵遍历一
  遍即可，因此时间复杂度为O(mn)。
- 空间复杂度：O(1)。直接在原矩阵中进行标记即可，不需要额外的空间。









# THE END