# 目录

[toc]

# leetcode-131-分割回文串

- 时间：2023-07-23
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/palindrome-partitioning/description/
- 难度：中等



给你一个字符串 `s`，请你将 `s` 分割成一些子串，使每个子串都是 **回文串** 。返回 `s` 所有可能的分割方案。

**回文串** 是正着读和反着读都一样的字符串。

 

**示例 1：**

```
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
```

**示例 2：**

```
输入：s = "a"
输出：[["a"]]
```

 

**提示：**

+ `1 <= s.length <= 16`
+ `s` 仅由小写英文字母组成



# 2、题解

## 题目分析

- 参考链接：
  - https://leetcode.cn/problems/palindrome-partitioning/solutions/54233/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
  - 

## 解法一：回溯算法

### 算法分析

![image-20230723161700903](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230723161700903.png)



编码。

- 每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
- 产生前缀字符串的时候，判断前缀字符串是否是回文。
  - 如果前缀字符串是回文，则可以产生分支和结点；
  - 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
- 在叶子结点是空字符串的时候结算，此时 **从根结点到叶子结点的路径，就是结果集里的一个结果，使用深度优先遍历，记录下所有可能的结果**。
- 使用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，要生成一个拷贝），因此在递归执行方法结束以后需要回溯，即将递归之前添加进来的元素拿出去；
- path 的操作只在列表的末端，因此合适的数据结构是栈。





### 代码

```java


/**
 * <p>
 * 分割回文串
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode131 {

    public static void main(String[] args) {
        String s = "aab";

        Solution01 solution01 = new Solution01();
        List<List<String>> partition01 = solution01.partition(s);
        for (List<String> strings : partition01) {
            System.out.println(strings);
        }


    }

    /**
     * 解法一：回溯算法
     */
    private static class Solution01 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }

            char[] chars = s.toCharArray();
            backTracking(chars, 0, len);
            return res;
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                // 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
                // 判断是否为回文
                if (!checkPalindrome(chars, index, i)) {
                    continue;
                }
                // 做选择
                track.addLast(new String(chars, index, i + 1 - index));

                // 递归，纵向遍历
                backTracking(chars, i + 1, len);

                // 撤销选择
                track.removeLast();
            }

        }

        /**
         * 判断子串区间 [left, right] 是否为一个回文字符串
         */
        private boolean checkPalindrome(char[] chars, int left, int right) {
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

    }

}

```





### 复杂度分析

![image-20230723161735098](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230723161735098.png)





## 解法二：回溯算法 + 动态规划优化（预处理所有子串是否为回文串）

### 算法分析

验证回文串的时候，每一次都得使用「双指针」的方式验证子串是否是回文子串。利用「力扣」第 5 题：最长回文子串 的思路，可以**先用动态规划把结果算出来，这样就可以以 O(1) 的时间复杂度直接得到一个子串是否是回文**。



### 代码

```java


/**
 * <p>
 * 分割回文串
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode131 {

    public static void main(String[] args) {
        String s = "aab";

        Solution01 solution01 = new Solution01();
        List<List<String>> partition01 = solution01.partition(s);
        for (List<String> strings : partition01) {
            System.out.println(strings);
        }

    }


    /**
     * 解法二：回溯算法 + 动态规划优化（预处理所有子串是否为回文串）
     */
    private static class Solution02 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }
            char[] chars = s.toCharArray();

            // 预处理，动态规划,类比 leetcode5
            // 状态：dp[i][j] 表示 s[i...j] 是否是回文
            boolean[][] dp = new boolean[len][len];
            // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
            for (int right = 0; right < len; right++) {
                // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
                for (int left = 0; left <= right; left++) {
                    if (chars[left] == chars[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            // 回溯
            backTracking(chars, 0, len, dp);

            return res;
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len, boolean[][] dp) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    track.addLast(new String(chars, index, i + 1 - index));
                    backTracking(chars, i + 1, len, dp);
                    track.removeLast();
                }
            }

        }

    }

}

```





### 复杂度分析

![image-20230723161822726](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230723161822726.png)



事实上，还可以使用**中心扩散**的方法得到所有子串是否是回文。可以看 leetcode5



## 解法三：回溯算法 + 中心扩展法（预处理所有子串是否为回文串）

### 算法分析





### 代码

```java


/**
 * <p>
 * 分割回文串
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode131 {

    public static void main(String[] args) {
        String s = "aab";

        Solution03 solution03 = new Solution03();
        List<List<String>> partition03 = solution03.partition(s);
        partition03.stream().forEach(System.out::println);

    }


    /**
     * 解法三：回溯算法 + 中心扩展法（预处理所有子串是否为回文串）
     */
    private static class Solution03 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        // 状态：dp[i][j] 表示 s[i...j] 是否是回文
        boolean[][] dp;

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }
            char[] chars = s.toCharArray();

            // 预处理，动态规划,类比 leetcode5
            dp = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                // 两种边界条件，len1: 中心子串长度为1；len2: 中心子串长度为2；
                expandAroundCenter(s, i, i);
                expandAroundCenter(s, i, i + 1);
            }

            // 回溯
            backTracking(chars, 0, len, dp);

            return res;
        }

        /**
         * 由中心向两边扩展
         *
         * @param s 输入字符串
         * @param left 左下标
         * @param right 右下标
         */
        public void expandAroundCenter(String s, int left, int right) {
            // 不越界，且头尾相等，才是回文。
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                dp[left][right] = true;
                --left;
                ++right;
            }
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len, boolean[][] dp) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    track.addLast(new String(chars, index, i + 1 - index));
                    backTracking(chars, i + 1, len, dp);
                    track.removeLast();
                }
            }

        }

    }

}



```





### 复杂度分析













# THE END