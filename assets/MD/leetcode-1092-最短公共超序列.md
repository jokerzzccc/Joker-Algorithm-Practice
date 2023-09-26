# 目录

[toc]

# leetcode-1092-最短公共超序列

- 时间：2023-08-16
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/shortest-common-supersequence/
- 难度：困难

给你两个字符串 `str1` 和 `str2`，返回同时以 `str1` 和 `str2` 作为 **子序列** 的最短字符串。如果答案不止一个，则可以返回满足条件的 **任意一个** 答案。

如果从字符串 `t` 中删除一些字符（也可能不删除），可以得到字符串 `s` ，那么 `s` 就是 t 的一个子序列。

 

**示例 1：**

```
输入：str1 = "abac", str2 = "cab"
输出："cabac"
解释：
str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。 
str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
最终我们给出的答案是满足上述属性的最短字符串。
```

**示例 2：**

```
输入：str1 = "aaaaaaaa", str2 = "aaaaaaaa"
输出："aaaaaaaa"
```

 

**提示：**

+ `1 <= str1.length, str2.length <= 1000`
+ `str1` 和 `str2` 都由小写英文字母组成。



# 2、题解

## 题目分析



## 解法一：动态规划 + 递归 + 备忘录

### 算法分析

参考链接：

- https://leetcode.cn/problems/shortest-common-supersequence/solutions/2194615/cong-di-gui-dao-di-tui-jiao-ni-yi-bu-bu-auy8z/

![image-20230816205457479](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230816205457479.png)

### 代码

```java


/**
 * <p>
 * 最短公共超序列
 * </p>
 *
 * @author admin
 * @date 2023/8/16
 */
public class leetcode1092 {

    public static void main(String[] args) {
        String str1 = "abac", str2 = "cab";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.shortestCommonSupersequence(str1, str2));

    }

    /**
     * 解法一：动态规划 + 递归 + 备忘录
     */
    private static class Solution01 {

        /**
         * 入参
         */
        private String s1, s2;

        /**
         * 备忘录
         */
        private int[][] memo;

        public String shortestCommonSupersequence(String str1, String str2) {
            if (Objects.equals(str1, str2)) {
                return str1;
            }
            s1 = str1;
            s2 = str2;

            int len1 = s1.length();
            int len2 = s2.length();
            memo = new int[len1][len2];

            return makeAns(len1 - 1, len2 - 1);
        }

        /**
         * makeAns(i,j) 返回 s1 的前 index1(下标) 个字母和 s2 的前 index2(下标) 个字母的最短公共超序列.
         * 看上去和 dfs 没啥区别，但是末尾的递归是 if-else;
         * makeAns(i-1,j) 和 makeAns(i,j-1) 不会都调用;
         * 所以 makeAns 的递归树仅仅是一条链.
         */
        private String makeAns(int index1, int index2) {
            // s1 是空串，返回剩余的 s2
            if (index1 < 0) {
                return s2.substring(0, index2 + 1);
            }
            // s2 是空串，返回剩余的 s1
            if (index2 < 0) {
                return s1.substring(0, index1 + 1);
            }
            // s1,s2 尾字母相同，则最短公共超序列一定包含该尾字母
            if (s1.charAt(index1) == s2.charAt(index2)) {
                return makeAns(index1 - 1, index2 - 1) + s1.charAt(index1);
            }

            // 如果下面 if 成立，说明上面 dfs 中的 min 取的是 dfs(i - 1, j)
            // 说明 dfs(i - 1, j) 对应的公共超序列更短
            // 那么就在 makeAns(i - 1, j) 的结果后面加上 s[i]
            // 否则说明 dfs(i, j - 1) 对应的公共超序列更短
            // 那么就在 makeAns(i, j - 1) 的结果后面加上 t[j]
            if (dfs(index1, index2) == dfs(index1 - 1, index2) + 1) {
                return makeAns(index1 - 1, index2) + s1.charAt(index1);
            } else {
                return makeAns(index1, index2 - 1) + s2.charAt(index2);
            }

        }

        /**
         * dfs(i,j) 返回 s1 的前 i 个字母和 s2 的前 j 个字母的最短公共超序列的长度
         */
        private int dfs(int i, int j) {
            // s1 是空串，返回剩余的 s2 的长度
            if (i < 0) {
                return j + 1;
            }
            // s2 是空串，返回剩余的 s1 的长度
            if (j < 0) {
                return i + 1;
            }
            // 备忘录优化： 避免重复计算 dfs 的结果
            if (memo[i][j] > 0) {
                return memo[i][j];
            }
            // s1,s2 尾字母相同，则最短公共超序列一定包含该尾字母
            if (s1.charAt(i) == s2.charAt(j)) {
                return memo[i][j] = dfs(i - 1, j - 1) + 1;
            }

            return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - 1)) + 1;
        }

    }

}

```





### 复杂度分析

![image-20230816205541352](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230816205541352.png)









# THE END