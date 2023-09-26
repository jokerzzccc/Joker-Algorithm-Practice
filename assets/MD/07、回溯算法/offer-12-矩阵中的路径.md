# 目录

[toc]

# offer-12-矩阵中的路径

- 时间：2023-04-29
- 



# 1、题目

- https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/

- 难度：中等



# 2、解法

## 算法思路：

- DFS + 剪枝



**DFS 解析：**

- **递归参数：**当前元素在矩阵 `board` 中的行列索引 `i` 和 `j` ，当前目标字符在 `word` 中的索引 `k` 。
- **终止条件**：
  - **返回 false:** 
    1. 行或列索引越界；
    2. 当前矩阵元素与目标字符不同；
    3. 当前矩阵元素已访问过（3可合并至2）
  - **返回 true:**
    - `k = len(word) - 1`，即字符串 `word` 已全部匹配。
- **递推工作：**
  1. 标记当前矩阵元素，将 `board[i][j]` 修改为 **空字符** `''` ，代表此元素已访问过，防止之后搜索时重复访问。
  2. 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。

  3. 还原当前矩阵元素： 将 `board[i][j]` 元素还原至初始值，即 `word[k]` 。
- **返回值：** 返回布尔量 `res` ，代表是否搜索到目标字符串。





**复杂度分析：**

![image-20230430001429161](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230430001429161.png)



## 代码:

### 注释版：

```java
/**
 * <p>
 * 矩阵中的路径
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/4/29
 */
public class offer12 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(solution.exist(board, word));
    }

    static class Solution {

        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();

            // 遍历图
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    // 如果找到了，就返回true。否则继续找
                    if (dfs(board, words, i, j, 0)) {
                        return true;
                    }
                }
            }

            // 遍历结束没找到false
            return false;
        }

        /**
         * @param board
         * @param word
         * @param i row index
         * @param j col index
         * @param k 当前字符在 word 里的下标
         * @return
         */
        boolean dfs(char[][] board, char[] word, int i, int j, int k) {
            // 判断传入参数的可行性 i 与图行数row比较，j与图列数col比较
            // i，j初始都是0，都在图左上角
            // k是传入字符串当前索引，一开始是0，如果当前字符串索引和图当前索引对应的值不相等，表示第一个数就不相等
            // 所以继续找第一个相等的数。题目说第一个数位置不固定，即路径起点不固定（不一定是左上角为第一个数）

            // 如果board[i][j] == word[k]，则表明当前找到了对应的数，就继续执行（标记找过，继续dfs 下上右左）
            if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
                return false;
            }
            // 表示找完了，每个字符都找到了
            // 一开始k=0，而word.length肯定不是0，所以没找到，就执行dfs继续找。
            if (k == word.length - 1) {
                return true;
            }

            // 访问过的标记空字符串，“ ”是空格 '\0'是空字符串，不一样的！
            // 比如当前为A，没有标记找过，且A是word中对应元素，则此时应该找A下一个元素，假设是B，在dfs（B）的时候还是-
            // ->要搜索B左边的元素（假设A在B左边），所以就是ABA（凭空多出一个A，A用了2次，不可以），如果标记为空字符串->
            // 就不会有这样的问题，因为他们值不相等AB != ABA。 
            board[i][j] = '\0';

            // 顺序是 下 上 右 左；上面找到了对应索引的值所以k+1
            boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                    dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);

            // 还原找过的元素，因为之后可能还会访问到（不同路径）
            board[i][j] = word[k];

            // 返回结果，如果false，则if(dfs(board, words, i, j, 0)) return true;不会执行，就会继续找
            return res;
        }

    }

}
```



### 无注释版：

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) || 
                      dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}

```









# THE END