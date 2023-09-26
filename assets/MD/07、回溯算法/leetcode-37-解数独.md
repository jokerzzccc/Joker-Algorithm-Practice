# 目录

[toc]

# leetcode-37-解数独

- 时间：2023-05-10





# 1、题目

- leetcode: https://leetcode.cn/problems/sudoku-solver/
- 难度：hard



# 2、题解

## 思考前言

- 可以考虑按照「行优先」的顺序依次枚举每一个空白格中填的数字，通过递归 + 回溯的方法枚举所有可能的填法。当递归到最后一个空白格后，如果仍然没有冲突，说明我们找到了答案；在递归的过程中，如果当前的空白格不能填下任何一个数字，那么就进行回溯。
- 由于每个数字在同一行、同一列、同一个九官格中只会出现一次，因此我们可以使用 `line[i]`, `column[j]`, `block[x][y]` 分别表示 第 i 行，第 j 列，第 (x,y) 个九宫格中填写数字的情况。在下面给出的三种方法中，我们将会介绍两种不同的表示填写数字情况的方法。
  - 九宫格的范围为 `0≤x≤2,` 以及 `0≤y≤2`。
  - 具体地，第 i 行第 j 列的格子位于第(L/3,L/3)个九宫格中。x^2 
- 由于这些方法均以递归 + 回溯为基础，算法运行的时间（以及时间复杂度）很大程度取决于给定的输入数据，而我们很难找到一个非常精确的渐进紧界。因此这里只给出一个较为宽松的渐进复杂度上界 $ O(9^{9*9})$ ,即最多有 $9 * 9$ 个空格, 每个格子可以填 [1,9] 中的任意整数。



## 法一：回溯法

### 思路

- 最容易想到的方法是用一个数组记录每个数字是否出现。由于我们可以填写的数字范围为 $[1,9]$ ，而数组的下标从 00 开始，因此在存储时，我们使用一个长度为 9 的布尔类型的数组， 其中第 *i* 个元素的值为 True，当且仅当数字 $i+1$ 出现过。
  - 例如，我们用 line[2][3] = True 表示数字 4 在第2行已经出现过，那么当我们在遍历到第 2 行的空白格时，就不能填入数字 4。



### 算法

我们首先对整个数独数组进行遍历，当我们遍历到第 *i* 行第 *j* 列的位置：

- 如果该位置是一个空白格，那么我们将其加入一个用来存储空白格位置的列表中，方便后续的递归操作；
- 如果该位置是一个数字 *x*，那么我们需要将 $line[i][x-1]$, $column[j][x-1]$ 以及 $block[i/3][j/3][x-1]$ 均置为 True。



当我们结束了遍历过程之后，就可以开始递归枚举。当递归到第 *i* 行第 *j* 列的位置时，我们枚举填入的数字 *x*。根据题目的要求，数字 *x* 不能和当前行、列、九宫格中已经填入的数字相同。因此 $line[i][x-1]$, $column[j][x-1]$ 以及 $block[i/3][j/3][x-1]$ 必须均为 False。

当我们填入了数字 x 之后，我们要将上述的三个值都置为 True，并且继续对下一个空白格位置进行递归。在回溯到当前递归层时，我们还要将上述的三个值重新置为 False。



### debug 版代码

```java

/**
 * <p>
 * 解数独
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/10
 */
public class leetcode37 {

    public static void main(String[] args) {
        char[][] board = initBoard();
        Solution02 solution02 = new Solution02();
        solution02.solveSudoku(board);
        Arrays.stream(board).forEach(System.out::println);

    }

    private static char[][] initBoard() {
        return new char[][]{
                {
                        '5', '3', '.', '.', '7', '.', '.', '.', '.'
                },
                {
                        '6', '.', '.', '1', '9', '5', '.', '.', '.'
                },
                {
                        '.', '9', '8', '.', '.', '.', '.', '6', '.'
                },
                {
                        '8', '.', '.', '.', '6', '.', '.', '.', '3'
                },
                {
                        '4', '.', '.', '8', '.', '3', '.', '.', '1'
                },
                {
                        '7', '.', '.', '.', '2', '.', '.', '.', '6'
                },
                {
                        '.', '6', '.', '.', '.', '.', '2', '8', '.'
                },
                {
                        '.', '.', '.', '4', '1', '9', '.', '.', '5'
                },
                {
                        '.', '.', '.', '.', '8', '.', '.', '7', '9'
                }
        };
    }

}

class Solution02 {

    /**
     * 同一行
     */
    private boolean[][] line = new boolean[9][9];
    /**
     * 同一列
     */
    private boolean[][] column = new boolean[9][9];
    /**
     * 同一九宫格
     */
    private boolean[][][] block = new boolean[3][3][9];
    /**
     * 是否有解
     */
    private boolean valid = false;
    /**
     * 空格列表，存储空格的坐标，用于遍历
     */
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    // '0' 是因为 ASCII 码值 对应 52
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        // 遍历完了 spaces, 即所有空格都填入了符合规则的值，找到解了
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            // 同一行或者同一列或者同一九宫格已经有数字 digit 了
            if (line[i][digit] || column[j][digit] || block[i / 3][j / 3][digit]) {
                continue;
            }
            // 做选择：填入值 digit
            line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
            board[i][j] = (char) (digit + '0' + 1);
            // 递归
            dfs(board, pos + 1);
            // 撤销选择：回溯置为 false
            line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
        }
    }

}

```





## 法二：回溯法+位运算优化

### 算法

- 



### debug 版代码

```java
/**
 * <p>
 * 解数独
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/10
 */
public class leetcode37 {

    public static void main(String[] args) {
        char[][] board = initBoard();
        Solution solution = new Solution();
        solution.solveSudoku(board);
        Arrays.stream(board).forEach(System.out::println);
    }

    private static char[][] initBoard() {
        return new char[][]{
                {
                        '5', '3', '.', '.', '7', '.', '.', '.', '.'
                },
                {
                        '6', '.', '.', '1', '9', '5', '.', '.', '.'
                },
                {
                        '.', '9', '8', '.', '.', '.', '.', '6', '.'
                },
                {
                        '8', '.', '.', '.', '6', '.', '.', '.', '3'
                },
                {
                        '4', '.', '.', '8', '.', '3', '.', '.', '1'
                },
                {
                        '7', '.', '.', '.', '2', '.', '.', '.', '6'
                },
                {
                        '.', '6', '.', '.', '.', '.', '2', '8', '.'
                },
                {
                        '.', '.', '.', '4', '1', '9', '.', '.', '5'
                },
                {
                        '.', '.', '.', '.', '8', '.', '.', '7', '9'
                }
        };
    }

}

class Solution {

    //储存每一行存在的数字
    private int[] line = new int[9];
    //储存 每一列存在的数字
    private int[] column = new int[9];
    //储存每一个 3*3宫存在的数字
    private int[][] block = new int[3][3];
    //这个布尔数组用来判断是否填完所有空格
    private boolean valid = false;
    //这个list用来记录所有空格的位置，整数数组第一个位置为行的位置 ，第二个位置为列的位置
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        //遍历所有位置
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                //如果位置为空，我们把该位置加入spaces中
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    //不为空则把该数字分别纳入对应的行，列，3*3宫中
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }
        //开始搜索
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        //如果spaces被遍历完了，说明我们填完了空格，将valid改为true，通过return结束这个函数
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        //获取第一个空格的位置
        int[] space = spaces.get(pos);
        //i，j分别为该空格的行数和列数
        int i = space[0], j = space[1];
        //|为or，通过3个或运算我们可以得到一个9位的二进制数字，从右到左分别代表1-9这个9个数是否可以填入该空格，通过~运算（取反），我们用1表示该数字是一个可填入的选项，0x1ff为十六进制 ，等同于111111111）
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        //当mask不为0并且 valid为false表示还有数待填入，继续这个循环，mask &= (mask - 1)把最低位的1变为0
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            //这个操作只保留最低位的1
            int digitMask = mask & (-mask);
            //最低位的1后面的位数，digitMask-1将原本1后面的0全部变为了1
            int digit = Integer.bitCount(digitMask - 1);
            //更新行，列，宫
            flip(i, j, digit);
            //把该数填入板中
            board[i][j] = (char) (digit + '0' + 1);
            //继续搜索
            dfs(board, pos + 1);
            //撤回操作（原理是flip中的~运算，两个 1则为0,0表示这个位置代表的1-9中的那个数 不再是一个可被填入的选项）
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        //^代表异或，只有1个1的时候才为1。比如0011^1001=1010
        //<<代表左移，比如 1<<2=4被加入到下面的三个数组中，在二进制4为100，意味着3这个数字被占用了
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }

}
```













# THE END