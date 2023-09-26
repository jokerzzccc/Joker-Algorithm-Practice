# 目录

[toc]

# leetcode-51-N皇后

- 时间：2023-05-08



# 1、题目

- https://leetcode.cn/problems/n-queens/?company_slug=alibaba
- 难度：hard



# 2、解法

- 「N 皇后问题」研究的是如何将  N 个皇后放置在 N×N 的棋盘上，并且使皇后彼此之间不能相互攻击。

- 皇后的走法是：可以横直斜走，格数不限。因此要求皇后彼此之间不能相互攻击，等价于要求任何两个皇后都不能在同一行、同一列以及同一条斜线上。

  

## 方法一 基于集合的回溯

- 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 columns、diagonals1 和 diagonals2
  分别记录每一列以及两个方向的每条斜线上是否有皇后。
- 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足**行下标与列下标之差相等**
- 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足**行下标与列下标之和相等**



### debug 版代码

```java
/**
 * <p>
 * N 皇后
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/9
 */
public class leetcode51 {

    public static void main(String[] args) {
        List<List<String>> solveNQueens = solveNQueens(4);
        for (List<String> solveNQueen : solveNQueens) {
            System.out.println(solveNQueen.toString());
        }
    }

        public static List<List<String>> solveNQueens(int n) {
            List<List<String>> solutions = new ArrayList<List<String>>();
            int[] queens = new int[n];

            Arrays.fill(queens, -1);
            Set<Integer> columns = new HashSet<>();
            Set<Integer> diagnoals1 = new HashSet<>();
            Set<Integer> diagnoals2 = new HashSet<>();

            backtrak(solutions, queens, n, 0, columns, diagnoals1, diagnoals2);
            return solutions;
        }

        public static void backtrak(List<List<String>> solutions, int[] queens, int n,
                int row, Set<Integer> columns,
                Set<Integer> diagonals1, Set<Integer> diagonals2){
            if (row == n){
                List<String> board = generateBoard(queens, n);
                solutions.add(board);
                return;
            }
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)){
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)){
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)){
                    continue;
                }

                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);

                backtrak(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }

        public static List<String> generateBoard(int[] queens, int n){
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                board.add(new String(row));
            }
            return board;
        }
    }
```
