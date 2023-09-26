# 目录

[toc]

# leetcode-1048-最长字符串链

- 时间：2023-08-19
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-string-chain/
- 难度：中等

给出一个单词数组 `words` ，其中每个单词都由小写英文字母组成。

如果我们可以 **不改变其他字符的顺序** ，在 `wordA` 的任何地方添加 **恰好一个** 字母使其变成 `wordB` ，那么我们认为 `wordA` 是 `wordB` 的 **前身** 。

+ 例如，`"abc"` 是 `"abac"` 的 **前身** ，而 `"cba"` 不是 `"bcad"` 的 **前身**

**词链**是单词 `[word_1, word_2, ..., word_k]` 组成的序列，`k >= 1`，其中 `word1` 是 `word2` 的前身，`word2` 是 `word3` 的前身，依此类推。一个单词通常是 `k == 1` 的 **单词链** 。

从给定单词列表 `words` 中选择单词组成词链，返回 词链的 **最长可能长度** 。
 

**示例 1：**

```
输入：words = ["a","b","ba","bca","bda","bdca"]
输出：4
解释：最长单词链之一为 ["a","ba","bda","bdca"]
```

**示例 2:**

```
输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
输出：5
解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
```

**示例 3:**

```
输入：words = ["abcd","dbqca"]
输出：1
解释：字链["abcd"]是最长的字链之一。
["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
```

 

**提示：**

+ `1 <= words.length <= 1000`
+ `1 <= words[i].length <= 16`
+ `words[i]` 仅由小写英文字母组成。



# 2、题解

## 题目分析

- 参考链接：
  - https://leetcode.cn/problems/longest-string-chain/solutions/2247269/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-wdkm/



## 解法一：动态规划 + 递归

### 算法分析

重复子问题：

- 对于字符串 s 来说，假设它是词链的最后一个单词，那么去掉 s 中的一个字母，设新字符串为 t，问题就变成计算以 t 结尾的词链的最长长度。由于这是一个和原问题相似的子问题，因此可以用递归解决。




直接把字符串作为递归的参数，定义 dfs(s)\textit{dfs}(s)dfs(s) 表示以 sss 结尾的词链的最长长度。由于字符串的长度不超过 161616，暴力枚举去掉的字符，设新字符串为 ttt 且在 words\textit{words}words 中，则有

$dfs(s)=max⁡{dfs(t)}+1 $
为了快速判断字符串是否在 words 中，需要将所有 words[i] 存入**哈希表 ws** 中。

由于 "aba" 和 "aca" 去掉中间字母都会变成 "aa"，为**避免重复计算**，代码实现时可以用**记忆化搜索**。进一步地，可以直接把计算结果存到 ws中。



### 代码

```java

/**
 * <p>
 * 最长字符串链
 * </p>
 *
 * @author admin
 * @date 2023/8/19
 */
public class leetcode1048 {

    public static void main(String[] args) {
        String[] words = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestStrChain(words));

    }

    /**
     * 解法一：动态规划 + 递归
     */
    private static class Solution01 {

        /**
         * 备忘录优化
         * key: words 的字符串，value: dfs(key)
         */
        private Map<String, Integer> memo = new HashMap<>();

        public int longestStrChain(String[] words) {
            // 初始化备忘录
            for (String word : words) {
                // 0 表示还没计算
                memo.put(word, 0);
            }

            int res = 0;
            for (String word : words) {
                res = Math.max(res, dfs(word));
            }

            return res;

        }

        /**
         * 定义 dfs(s) 表示以 s 结尾的词链的最长长度
         */
        private int dfs(String str) {
            // 备忘录优化
            int res = memo.get(str);
            if (res > 0) {
                return res;
            }

            // 计算 str 减一长度的子字符串的词链最长长度
            for (int i = 0; i < str.length(); i++) {
                // 去掉str[i]
                String subStr = str.substring(0, i) + str.substring(i + 1);
                // subStr 在 words 中
                if (memo.containsKey(subStr)) {
                    res = Math.max(res, dfs(subStr));
                }
            }

            memo.put(str, res + 1);
            return res + 1;
        }

    }


}

```





### 复杂度分析

![image-20230819214229221](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230819214229221.png)



## 解法二：动态规划 + 迭代

### 算法分析





### 代码

```java

/**
 * <p>
 * 最长字符串链
 * </p>
 *
 * @author admin
 * @date 2023/8/19
 */
public class leetcode1048 {

    public static void main(String[] args) {
        String[] words = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        
        Solution02 solution02 = new Solution02();
        System.out.println(solution02.longestStrChain(words));

    }


    /**
     * 解法二：动态规划 + 迭代 ：
     * 自底向上：总是从短的字符串转移到长的字符串
     */
    private static class Solution02 {

        /**
         * key: words 的字符串，value: 以 key 结尾的词链的最长长度
         */
        private Map<String, Integer> wordToChainLongest = new HashMap<>();

        public int longestStrChain(String[] words) {
            // 按长度排序
            Arrays.sort(words, Comparator.comparingInt(String::length));

            int res = 0;
            for (String word : words) {
                // 计算 word 子字符串的词链最长长度
                int subRes = 0;
                for (int i = 0; i < word.length(); i++) {
                    // 去掉str[i]
                    String subStr = word.substring(0, i) + word.substring(i + 1);
                    subRes = Math.max(subRes, wordToChainLongest.getOrDefault(subStr, 0));
                }

                // +1 是因为加当前 word
                wordToChainLongest.put(word, subRes + 1);
                res = Math.max(res, subRes + 1);
            }

            return res;

        }

    }

}

```





### 复杂度分析

![image-20230819213919446](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230819213919446.png)





# THE END