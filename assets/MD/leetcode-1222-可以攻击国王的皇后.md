# 目录

[toc]

# leetcode-1222-可以攻击国王的皇后

- 时间：2023-09-15
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/queens-that-can-attack-the-king/description/?envType=daily-question&envId=2023-09-14
- 难度：中等

在一个 **8x8** 的棋盘上，放置着若干「黑皇后」和一个「白国王」。

给定一个由整数坐标组成的数组 `queens` ，表示黑皇后的位置；以及一对坐标 `king` ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/13/untitled-diagram.jpg)

```
输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
输出：[[0,1],[1,0],[3,3]]
解释： 
[0,1] 的皇后可以攻击到国王，因为他们在同一行上。 
[1,0] 的皇后可以攻击到国王，因为他们在同一列上。 
[3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。 
[0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。 
[4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。 
[2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
```

**示例 2：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/13/untitled-diagram-1.jpg)**

```
输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
输出：[[2,2],[3,4],[4,4]]
```

**示例 3：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/13/untitled-diagram-2.jpg)**

```
输入：queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
```

 

**提示：**

+ `1 <= queens.length <= 63`
+ `queens[i].length == 2`
+ `0 <= queens[i][j] < 8`
+ `king.length == 2`
+ `0 <= king[0], king[1] < 8`
+ 一个棋盘格上最多只能放置一枚棋子。



# 2、题解

## 题目分析

能攻击到国王的皇后，需要满足：

- 皇后与国王在同一行，或者同一列，或者同一斜线。
- 皇后与国王之间没有棋子。换句话说，皇后不能被其它皇后挡住。

一种思路是枚举每个皇后，去判断是否满足上述条件。

更加巧妙的做法是，**站在国王的视角，计算有哪些皇后能被国王「看到」**。

想象成从国王的位置发射八个方向的射线，记录每条射线首次遇到的皇后。



## 解法一：从国王出发

### 算法分析





### 代码

```java


/**
 * <p>
 * 可以攻击国王的皇后
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/15
 */
public class leetcode1222 {

    public static void main(String[] args) {
        int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
        int[] king = {0, 0};

        Solution01 solution01 = new Solution01();
        List<List<Integer>> ans = solution01.queensAttacktheKing(queens, king);
        for (List<Integer> coordinate : ans) {
            System.out.println(coordinate);
        }

    }

    /**
     * 解法一：从国王出发。
     * 能攻击到国王的皇后，需要满足：
     * 皇后与国王在同一行，或者同一列，或者同一斜线。
     * 皇后与国王之间没有棋子。换句话说，皇后不能被其它皇后挡住。
     */
    private static class Solution01 {

        /**
         * 八个方向移动
         */
        private final static int[][] directions = {
                {1, 0}, {1, 1}, {0, 1}, {-1, 1},
                {-1, 0}, {-1, -1}, {0, -1}, {1, -1}
        };

        public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
            // 数组效率比哈希表高
            boolean[][] isQueen = new boolean[8][8];
            // 初始化皇后的位置
            for (int[] queen : queens) {
                isQueen[queen[0]][queen[1]] = true;
            }

            List<List<Integer>> ans = new ArrayList<>();
            // 在八个方向上，都去找所能遇见的第一个皇后
            for (int[] direction : directions) {
                int x = king[0] + direction[0];
                int y = king[1] + direction[1];
                // 在一个方向上一直往下走，直到找到第一个皇后或达到边界
                while (0 <= x && x < 8 && 0 <= y && y < 8) {
                    if (isQueen[x][y]) {
                        ans.add(Arrays.asList(x, y));
                        break;
                    }
                    x += direction[0];
                    y += direction[1];
                }
            }

            return ans;
        }
    }

}

```





### 复杂度分析

- 时间复杂度：O(m+n)。其中 m 为 queens 的长度，n=8 为棋盘边长。
- 空间复杂度：O(m)。











# THE END