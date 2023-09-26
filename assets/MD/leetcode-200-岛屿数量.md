# 目录

[toc]

# leetcode-200-岛屿数量

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 


# 前景知识

**岛屿系列题目的核心考点就是用 DFS/BFS 算法遍历二维数组**。

本文主要来讲解如何用 DFS 算法来秒杀岛屿系列题目，不过用 BFS 算法的核心思路是完全一样的，无非就是把 DFS 改写成 BFS 而已。

那么如何在二维矩阵中使用 DFS 搜索呢？如果你把二维矩阵中的每一个位置看做一个节点，这个节点的上下左右四个位置就是相邻节点，那么整个矩阵就可以抽象成一幅网状的「图」结构。

根据 [学习数据结构和算法的框架思维](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/xue-xi-sua-01220/)，完全可以根据二叉树的遍历框架改写出二维矩阵的 DFS 代码框架：

java 🟢cpp 🤖python 🤖go 🤖javascript 🤖

```java
// 二叉树遍历框架
void traverse(TreeNode root) {
    traverse(root.left);
    traverse(root.right);
}

// 二维矩阵遍历框架
void dfs(int[][] grid, int i, int j, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;
    if (i < 0 || j < 0 || i >= m || j >= n) {
        // 超出索引边界
        return;
    }
    if (visited[i][j]) {
        // 已遍历过 (i, j)
        return;
    }
    // 进入节点 (i, j)
    visited[i][j] = true;
    dfs(grid, i - 1, j, visited); // 上
    dfs(grid, i + 1, j, visited); // 下
    dfs(grid, i, j - 1, visited); // 左
    dfs(grid, i, j + 1, visited); // 右
}
```

因为二维矩阵本质上是一幅「图」，所以遍历的过程中需要一个 `visited` 布尔数组防止走回头路，如果你能理解上面这段代码，那么搞定所有岛屿系列题目都很简单。

这里额外说一个处理二维数组的常用小技巧，你有时会看到使用「方向数组」来处理上下左右的遍历，和前文 [union-find 算法详解](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/bing-cha-j-323f3/) 的代码很类似：

java 🟢cpp 🤖python 🤖go 🤖javascript 🤖

```java
// 方向数组，分别代表上、下、左、右
int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

void dfs(int[][] grid, int i, int j, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;
    if (i < 0 || j < 0 || i >= m || j >= n) {
        // 超出索引边界
        return;
    }
    if (visited[i][j]) {
        // 已遍历过 (i, j)
        return;
    }

    // 进入节点 (i, j)
    visited[i][j] = true;
    // 递归遍历上下左右的节点
    for (int[] d : dirs) {
        int next_i = i + d[0];
        int next_j = j + d[1];
        dfs(grid, next_i, next_j, visited);
    }
    // 离开节点 (i, j)
}
```

这种写法无非就是用 for 循环处理上下左右的遍历罢了，你可以按照个人喜好选择写法。



# 1、题目

- 题目：https://leetcode.cn/problems/number-of-islands/
- 难度：中等

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

示例 1：

```sh
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
```



提示：

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` 的值为 '0' 或 '1'





# 2、题解

## 题目分析

思路很简单，关键在于如何寻找并标记「岛屿」，这就要 DFS 算法发挥作用了。

为了求出岛屿的数量，我们可以扫描整个二堆网格。如果一个位置为1，则以其为起始节点开始进行深度优
先搜索。在深度优先搜索的过程中，每个搜索到的1都会被重新标记为0。
最终岛屿的数量就是我们进行深度优先搜索的次数。



**为什么每次遇到岛屿，都要用 DFS 算法把岛屿「淹了」呢？主要是为了省事，避免维护 `visited` 数组**。

因为 `dfs` 函数遍历到值为 `0` 的位置会直接返回，所以只要把经过的位置都设置为 `0`，就可以起到不走回头路的作用。

## 解法一

### 算法分析





### 代码

```java


/**
 * <p>
 * 岛屿数量
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode200 {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        Solution01 solution01 = new Solution01();
        int islands01 = solution01.numIslands(grid);
        System.out.println(islands01);

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int numIslands(char[][] grid) {
            int res = 0;
            int m = grid.length, n = grid[0].length;
            // 遍历 grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
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
        void dfs(char[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            // 超出索引边界
            if (i < 0 || j < 0 || i >= m || j >= n) return;
            if (grid[i][j] == '0') {
                // 已经是海水了
                return;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = '0';
            // 淹没上下左右的陆地
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(MN),其中M和N分别为行数和列数。
- 空间复杂度：O(MN),在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到MN。









# THE END