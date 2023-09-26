# 目录

[toc]

# leetcode-79-单词搜索

- 时间：2023-05-18
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/word-search/
- 难度：中等



给定一个 `m x n` 二维字符网格 `board` 和一个字符串单词 `word` 。如果 `word` 存在于网格中，返回 `true` ；否则，返回 `false` 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/04/word2.jpg)

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg)

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true
```

**示例 3：**

![img](https://assets.leetcode.com/uploads/2020/10/15/word3.jpg)

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
```



**提示：**

+ `m == board.length`
+ `n = board[i].length`
+ `1 <= m, n <= 6`
+ `1 <= word.length <= 15`
+ `board` 和 `word` 仅由大小写英文字母组成



**进阶：**你可以使用搜索剪枝的技术来优化解决方案，使其在 `board` 更大的情况下可以更快解决问题？

Related Topics

数组

回溯

矩阵



# 2、题解

## 题目分析

### 整体思路：

使用深度优先搜索（DFS）和回溯的思想实现。关于判断元素是否使用过，我用了一个二维数组 `used` 对使用过的元素做标记。



### 外层：遍历

首先遍历 `board` 的所有元素，先找到和 `word` 第一个字母相同的元素，然后进入递归流程。假设这个元素的坐标为 `(i, j)`，进入递归流程前，先记得把该元素打上 **使用过** 的标记：



### 内层：递归

好了，打完标记了，现在我们进入了递归流程。递归流程主要做了这么几件事：

1. 从 `(i, j)` 出发，朝它的上下左右试探，看看它周边的这四个元素是否能匹配 `word` 的下一个字母

+ 如果匹配到了：带着该元素继续进入下一个递归
+ 如果都匹配不到：返回 `False`

2. 当 `word` 的所有字母都完成匹配后，整个流程返回 `True`



### 几个注意点

+ 递归时元素的坐标是否超过边界
+ 回溯标记 `mark[i][j] = 0`以及 `return` 的时机



## 解法一



### 代码：debug 版

```java
/**
 * <p>
 * 单词搜索
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/18
 */
public class leetcode79 {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word01 = "ABCCED";
        String word02 = "ABCB";
        Solution solution = new Solution();

        boolean exist01 = solution.exist(board, word01);
        System.out.println(exist01);

        boolean exist02 = solution.exist(board, word02);
        System.out.println(exist02);

    }

    private static class Solution {

        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0) {
                return false;
            }
            if (word == null || word.length() == 0) {
                return true;
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] used = new boolean[m][n];
            char[] words = word.toCharArray();

            // 遍历矩阵
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 矩阵元素与字符串第一个字符相等的，才开始递归
                    if (board[i][j] == words[0]) {
                        if (backTrack(i, j, used, board, words, 0)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        /**
         * @param i board row index
         * @param j board col index
         * @param used 当前元素是否使用过
         * @param board board 入参
         * @param words word char 数组
         * @param offset words 下标
         * @return
         */
        boolean backTrack(int i, int j, boolean[][] used, char[][] board, char[] words, int offset) {
            // 剪枝：不超出矩阵边界
            if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
                return false;
            }
            // 剪枝：已走过的路径
            if (used[i][j]) {
                return false;
            }
            // 剪枝：矩阵当前元素与字符串当前元素是否相等
            if (board[i][j] != words[offset]) {
                return false;
            }

            // 终结条件：找到了 word
            if (offset == words.length - 1) {
                return true;
            }

            used[i][j] = true;

            // 四个方向递归
            boolean res = backTrack(i + 1, j, used, board, words, offset + 1)
                    || backTrack(i - 1, j, used, board, words, offset + 1)
                    || backTrack(i, j + 1, used, board, words, offset + 1)
                    || backTrack(i, j - 1, used, board, words, offset + 1);

            used[i][j] = false;

            return res;
        }

    }

}
```







# THE END