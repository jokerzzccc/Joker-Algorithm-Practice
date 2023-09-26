# 目录

[toc]

# leetcode-139-单词拆分

- 时间：2023-07-02
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/word-break/?company_slug=alibaba
- 难度：中等



给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词**可以重复使用**。

 

示例 1：

```sh
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
```



提示：

- 1 <= s.length <= 300
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- s 和 wordDict[i] 仅有小写英文字母组成
- wordDict 中的所有字符串 **互不相同**





# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析



**dp[i] 定义**：

- 表示字符串 *s* 前 *i* 个字符组成的字符串 *s*[0..*i*−1] 是否能被空格拆分成若干个字典中出现的单词。
- 最终的结果就是 `dp[s.length()]`
- base case 就是 `dp[0] = true`

**从前往后**计算考虑转移方程，每次转移的时候我们需要枚举包含位置 `i−1` 的最后一个单词，看它是否出现在字典中以及除去这部分的字符串是否合法即可。

公式化来说，我们需要枚举 *s*[0..*i*−1] 中的分割点 *j* ，看 *s*[0..*j*−1] 组成的字符串 *s*1（**默认 \*j\*=0 时 \*s\*1 为空串**）和 *s*[*j*..*i*−1] 组成的字符串 *s*2 是否都合法，如果两个字符串均合法，那么按照定义 *s*1 和 *s*2 拼接成的字符串也同样合法。由于计算到 *dp*[*i*] 时我们已经计算出了$ dp[0..j−1]$ 的值，因此字符串 *s*1 是否合法可以直接由 $dp[j]$ 得知，剩下的我们只需要看 *s*2 是否合法即可，因此我们可以得出如下**转移方程**：

**dp[i]=dp[j] && check(s[j..i−1])**

其中 *check*(*s*[*j*..*i*−1]) 表示子串 *s*[*j*..*i*−1] 是否出现在字典中。

对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，同时也可以做一些简单的剪枝，枚举分割点的时候倒着枚举，如果分割点 *j* 到 *i* 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举。



对于边界条件，我们定义 *dp*[0]= true 表示空串且合法。



### 代码

```java


/**
 * <p>
 * 单词拆分
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode139 {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        Solution01 solution01 = new Solution01();
        boolean result01 = solution01.wordBreak(s, wordDict);
        System.out.println(result01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            // dp[i] 表示  s 的前 i 位字符串使用可以用 wordDict 的单词表示
            boolean[] dp = new boolean[s.length() + 1];
            // base case
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    // 遍历 s[0,i-1] 的分割点
                    // 状态转移方程 dp[i]= dp[j] && check(s[j..i−1])
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }

    }

}

```





### 复杂度分析

- 时间复杂度：*O*(n^2) ，其中 *n* 为字符串 *s* 的长度。我们一共有 *O*(*n*) 个状态需要计算，每次计算需要枚举 *O*(*n*) 个分割点，哈希表判断一个字符串是否出现在给定的字符串列表需要 *O*(1) 的时间，因此总时间复杂度为 *O*(n^2)。

- 空间复杂度：*O*(*n*) ，其中 *n* 为字符串 *s* 的长度。我们需要 *O*(*n*) 的空间存放 *dp* 值以及哈希表亦需要 *O*(*n*) 的空间复杂度，因此总空间复杂度为 *O*(*n*)。









# THE END