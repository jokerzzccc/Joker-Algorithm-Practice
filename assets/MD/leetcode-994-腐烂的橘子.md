# 目录

[toc]

# leetcode-994-腐烂的橘子

- 时间：2023-08-21
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/rotting-oranges/description/
- 难度：中等





# 2、题解

## 题目分析

在给定的 `m x n` 网格 `grid` 中，每个单元格可以有以下三个值之一：

+ 值 `0` 代表空单元格；
+ 值 `1` 代表新鲜橘子；
+ 值 `2` 代表腐烂的橘子。

每分钟，腐烂的橘子 **周围 4 个方向上相邻** 的新鲜橘子都会腐烂。

返回 *直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 `-1`* 。

 

**示例 1：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/16/oranges.png)**

```
输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
输出：4
```

**示例 2：**

```
输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
输出：-1
解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
```

**示例 3：**

```
输入：grid = [[0,2]]
输出：0
解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
```

 

**提示：**

+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 10`
+ `grid[i][j]` 仅为 `0`、`1` 或 `2`

## 解法一：BFS

### 算法分析

- 参考链接：
  - https://leetcode.cn/problems/rotting-oranges/solutions/129831/li-qing-si-lu-wei-shi-yao-yong-bfsyi-ji-ru-he-xie-/



这道题的题目要求：返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。翻译一下，**实际上就是求腐烂橘子到所有新鲜橘子的最短路径**。那么这道题使用 BFS，应该是毫无疑问的了。



这道题的主要思路是：

- 一开始，我们找出所有腐烂的橘子，将它们放入队列，作为第 0 层的结点。
  然后进行 BFS 遍历，每个结点的相邻结点可能是上、下、左、右四个方向的结点，注意判断结点位于网格边界的特殊情况。
- 由于可能存在无法被污染的橘子，我们需要记录新鲜橘子的数量。在 BFS 中，每遍历到一个橘子（污染了一个橘子），就将新鲜橘子的数量减一。如果 BFS 结束后这个数量仍未减为零，说明存在无法被污染的橘子。





### 代码

```java


/**
 * <p>
 * 腐烂的橘子
 * </p>
 *
 * @author admin
 * @date 2023/8/21
 */
public class leetcode994 {

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.orangesRotting(grid));

    }

    /**
     * 解法一：BFS
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。
     * 翻译一下，
     * 实际上就是求腐烂橘子到所有新鲜橘子的最短路径。那么这道题使用 BFS，应该是毫无疑问的了。
     */
    private static class Solution01 {

        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // 表示所有腐烂的橘子的坐标
            Queue<int[]> rotQueue = new LinkedList<>();
            // count 表示新鲜橘子的数量
            int count = 0;

            // 遍历二维数组 找出所有的新鲜橘子和腐烂的橘子
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) { // 新鲜橘子计数
                        count++;
                    } else if (grid[i][j] == 2) {
                        rotQueue.add(new int[]{i, j});
                    }
                }
            }

            // round 表示腐烂的轮数，或者分钟数(即 BFS 的深度)
            int round = 0;

            // 如果有新鲜橘子 并且 队列不为空
            // 直到上下左右都触及边界 或者 被感染的橘子已经遍历完
            while (count > 0 && !rotQueue.isEmpty()) {
                round++;
                // 拿到当前层级的腐烂橘子数量， 因为每个层级会更新队列
                int k = rotQueue.size();
                for (int i = 0; i < k; i++) {
                    int[] orange = rotQueue.poll();

                    int curRow = orange[0];
                    int curCol = orange[1];

                    // ↑ 上邻点 判断是否边界 并且 上方是否是健康的橘子
                    if (curRow - 1 >= 0 && grid[curRow - 1][curCol] == 1) {
                        // 感染它
                        grid[curRow - 1][curCol] = 2;
                        // 好橘子 -1
                        count--;
                        // 把被感染的橘子放进队列 并缓存
                        rotQueue.add(new int[]{curRow - 1, curCol});
                    }
                    // ↓ 下邻点 同上
                    if (curRow + 1 < m && grid[curRow + 1][curCol] == 1) {
                        grid[curRow + 1][curCol] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow + 1, curCol});
                    }
                    // ← 左邻点 同上
                    if (curCol - 1 >= 0 && grid[curRow][curCol - 1] == 1) {
                        grid[curRow][curCol - 1] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow, curCol - 1});
                    }
                    // → 右邻点 同上
                    if (curCol + 1 < n && grid[curRow][curCol + 1] == 1) {
                        grid[curRow][curCol + 1] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow, curCol + 1});
                    }
                }
            }

            // 如果此时还有健康的橘子, 返回 -1
            // 否则 返回层级()
            if (count > 0) {
                return -1;
            } else {
                return round;
            }
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(nm) 即进行一次广度优先搜索的时间，其中 n=grid.length, m=grid[0].length 。

- 空间复杂度：O(nm) 需要额外的 dis 数组记录每个新鲜橘子被腐烂的最短时间，大小为 O(nm)，且广度优先搜索中队列里存放的状态最多不会超过 nm 个，最多需要 O(nm) 的空间，所以最后的空间复杂度为 O(nm)。











# THE END