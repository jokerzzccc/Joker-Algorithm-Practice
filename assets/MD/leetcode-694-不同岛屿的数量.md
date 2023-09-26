# 目录

[toc]

# leetcode-694-不同岛屿的数量

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 







# 1、题目

- 题目：https://leetcode.cn/problems/number-of-distinct-islands/
- 难度：中等



给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。

请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/05/01/distinctisland1-1-grid.jpg)

```
输入: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
输出：1
```



**提示：**

+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 50`
+ `grid[i][j]` 仅包含 `0` 或 `1`



# 2、题解

## 题目分析

- 类比 leetcode-1254



## 解法一: DFS

### 算法分析

很显然我们得想办法把二维矩阵中的「岛屿」进行转化，变成比如字符串这样的类型，然后利用 HashSet 这样的数据结构去重，最终得到不同的岛屿的个数。

如果想把岛屿转化成字符串，说白了就是序列化，序列化说白了就是遍历嘛，前文 [二叉树的序列化和反序列化](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-d14d3/) 讲了二叉树和字符串互转，这里也是类似的。

**首先，对于形状相同的岛屿，如果从同一起点出发，`dfs` 函数遍历的顺序肯定是一样的**。

因为遍历顺序是写死在你的递归函数里面的，不会动态改变：

java 🟢cpp 🤖python 🤖go 🤖javascript 🤖

```java
void dfs(int[][] grid, int i, int j) {
    // 递归顺序：
    dfs(grid, i - 1, j); // 上
    dfs(grid, i + 1, j); // 下
    dfs(grid, i, j - 1); // 左
    dfs(grid, i, j + 1); // 右
}
```

所以，遍历顺序从某种意义上说就可以用来描述岛屿的形状，比如下图这两个岛屿：

![image-20230626230405821](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230626230405821.png)

假设它们的遍历顺序是：

> 下，右，上，撤销上，撤销右，撤销下

如果我用分别用 `1, 2, 3, 4` 代表上下左右，用 `-1, -2, -3, -4` 代表上下左右的撤销，那么可以这样表示它们的遍历顺序：

> 2, 4, 1, -1, -4, -2

**你看，这就相当于是岛屿序列化的结果，只要每次使用 `dfs` 遍历岛屿的时候生成这串数字进行比较，就可以计算到底有多少个不同的岛屿了**。

> Info
>
> 细心的读者问到，为什么记录「撤销」操作才能唯一表示遍历顺序呢？不记录撤销操作好像也可以？实际上不是的。
>
> 比方说「下，右，撤销右，撤销下」和「下，撤销下，右，撤销右」显然是两个不同的遍历顺序，但如果不记录撤销操作，那么它俩都是「下，右」，成了相同的遍历顺序，显然是不对的。



所以我们需要稍微改造 `dfs` 函数，添加一些函数参数以便记录遍历顺序：

```java
void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
    int m = grid.length, n = grid[0].length;
    if (i < 0 || j < 0 || i >= m || j >= n 
        || grid[i][j] == 0) {
        return;
    }
    // 前序遍历位置：进入 (i, j)
    grid[i][j] = 0;
    sb.append(dir).append(',');
    
    dfs(grid, i - 1, j, sb, 1); // 上
    dfs(grid, i + 1, j, sb, 2); // 下
    dfs(grid, i, j - 1, sb, 3); // 左
    dfs(grid, i, j + 1, sb, 4); // 右
    
    // 后序遍历位置：离开 (i, j)
    sb.append(-dir).append(',');
}
```

> Note
>
> 仔细看这个代码，在递归前做选择，在递归后撤销选择，它像不像 [回溯算法框架](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/hui-su-sua-c26da/)？实际上它就是回溯算法，因为它关注的是「树枝」（岛屿的遍历顺序），而不是「节点」（岛屿的每个格子）。

`dir` 记录方向，`dfs` 函数递归结束后，`sb` 记录着整个遍历顺序。有了这个 `dfs` 函数就好办了，我们可以直接写出最后的解法代码：



这样，这道题就解决了，至于为什么初始调用 `dfs` 函数时的 `dir` 参数可以随意写，因为这个 `dfs` 函数实际上是回溯算法，它关注的是「树枝」而不是「节点」，前文 [图算法基础](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/tu-lun-ji--d55b2/) 有写具体的区别，这里就不赘述了。

### 代码

```java


/**
 * <p>
 * 不同岛屿的数量
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode694 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}

        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numDistinctIslands(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int numDistinctIslands(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // 记录所有岛屿的序列化结果
            Set<String> islands = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        // 淹掉这个岛屿，同时存储岛屿的序列化结果
                        StringBuilder sb = new StringBuilder();
                        // 初始的方向可以随便写，不影响正确性
                        dfs(grid, i, j, sb, 666);
                        islands.add(sb.toString());
                    }
                }
            }

            // 不相同的岛屿数量
            return islands.size();
        }

        /**
         * 每次使用 dfs 遍历岛屿的时候,序列化这个岛屿
         *
         * @param sb 岛屿序列化后的字符串
         * @param dir dir 记录方向（分别用 1, 2, 3, 4 代表上下左右，用 -1, -2, -3, -4 代表上下左右的撤销）
         */
        void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
                return;
            }
            // 前序遍历位置：进入 (i, j)
            grid[i][j] = 0;
            // 做选择
            sb.append(dir).append(',');

            dfs(grid, i - 1, j, sb, 1); // 上
            dfs(grid, i + 1, j, sb, 2); // 下
            dfs(grid, i, j - 1, sb, 3); // 左
            dfs(grid, i, j + 1, sb, 4); // 右

            // 后序遍历位置：离开 (i, j)
            // 撤销选择
            sb.append(-dir).append(',');
        }

    }

}

```





### 复杂度分析











# THE END