# 目录

[toc]

# leetcode-36-有效的数独

- 时间：2023-08-25
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/valid-sudoku/description/
- 难度：中等

![image-20230825202445084](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230825202445084.png)

![image-20230825202508342](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230825202508342.png)

**提示：**

+ `board.length == 9`
+ `board[i].length == 9`
+ `board[i][j]` 是一位数字（`1-9`）或者 `'.'`





# 2、题解

## 题目分析



## 解法一：一次遍历

### 算法分析

![image-20230825203935935](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230825203935935.png)



### 代码

```java


/**
 * <p>
 * 有效的数独
 * </p>
 *
 * @author admin
 * @date 2023/8/25
 */
public class leetcode36 {

    public static void main(String[] args) {
        char[][] board =
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isValidSudoku(board));

    }

    /**
     * 解法一：按照从左往右、从上往下的顺序遍历一次board，完成3个条件的
     */
    private static class Solution01 {

        public boolean isValidSudoku(char[][] board) {
            // 哈希表存储每一行的每个数是否出现过，默认初始情况下，每一行每一个数都没有出现过
            // 整个board有9行，第二维的维数10是为了让下标有9，和数独中的数字9对应。
            int[][] row = new int[9][10];
            // 存储每一列的每个数是否出现过，默认初始情况下，每一列的每一个数都没有出现过
            int[][] col = new int[9][10];
            // 存储每一个box(3x3)的每个数是否出现过，默认初始情况下，在每个box中，每个数都没有出现过。整个board有9个box。
            int[][] box = new int[9][10];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // 遍历到第i行第j列的那个数,我们要判断这个数在其所在的行有没有出现过，
                    // 同时判断这个数在其所在的列有没有出现过
                    // 同时判断这个数在其所在的box中有没有出现过
                    if (board[i][j] == '.') continue;
                    int curNum = board[i][j] - '0';
                    if (row[i][curNum] == 1) return false;
                    if (col[j][curNum] == 1) {
                        return false;
                    }
                    if (box[j / 3 + (i / 3) * 3][curNum] == 1) {
                        return false;
                    }

                    // 之前都没出现过，现在出现了，就给它置为1，下次再遇见就能够直接返回false了。
                    row[i][curNum] = 1;
                    col[j][curNum] = 1;
                    box[j / 3 + (i / 3) * 3][curNum] = 1;
                }
            }

            return true;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(1)。数独共有 81 个单元格，只需要对每个单元格遍历一次即可。

- 空间复杂度：O(1)。由于数独的大小固定，因此哈希表的空间也是固定的。









# THE END