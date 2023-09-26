# 目录

[toc]

# leetcode-187-重复的DNA序列

- 时间：2023-06-14

- 参考链接：
  - [滑动窗口算法延伸：RABIN KARP 字符匹配算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/hua-dong-c-88113/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/repeated-dna-sequences/
- 难度：中等



DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。

- 例如，"ACGAATTCCG" 是一个 DNA序列 。
  在研究 DNA 时，识别 DNA 中的重复序列非常有用。

给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。



**提示：**

+ `0 <= s.length <= 105`
+ `s[i]``==``'A'`、`'C'`、`'G'` or `'T'`



# 2、题解

## 题目分析



## 解法一:  滑动窗口

### 算法分析





### 代码

```java

/**
 * <p>
 * 重复的DNA序列
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode187 {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        Solution01 solution01 = new Solution01();
        List<String> sequences01 = solution01.findRepeatedDnaSequences(s);
        sequences01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：哈希表+滑动窗口
     */
    private static class Solution01 {

        public List<String> findRepeatedDnaSequences(String s) {

            // 先把字符串转化成四进制的数字数组
            int[] nums = new int[s.length()];
            for (int i = 0; i < nums.length; i++) {
                switch (s.charAt(i)) {
                    case 'A':
                        nums[i] = 0;
                        break;
                    case 'G':
                        nums[i] = 1;
                        break;
                    case 'C':
                        nums[i] = 2;
                        break;
                    case 'T':
                        nums[i] = 3;
                        break;
                }
            }
            // 记录重复出现的哈希值
            HashSet<Integer> seen = new HashSet<>();
            // 记录重复出现的字符串结果
            HashSet<String> res = new HashSet<>();

            // 数字位数
            int L = 10;
            // 进制
            int R = 4;
            // 存储 R^(L - 1) 的结果
            int RL = (int) Math.pow(R, L - 1);
            // 维护滑动窗口中字符串的哈希值
            int windowHash = 0;

            // 滑动窗口代码框架，时间 O(N)
            int left = 0, right = 0;
            while (right < nums.length) {
                // 扩大窗口，移入字符，并维护窗口哈希值（在最低位添加数字）
                windowHash = R * windowHash + nums[right];
                right++;

                // 当子串的长度达到要求
                if (right - left == L) {
                    if (seen.contains(windowHash)) {
                        // 当前窗口中的子串是重复出现的
                        res.add(s.substring(left, right));
                    } else {
                        // 当前窗口中的子串之前没有出现过，记下来
                        seen.add(windowHash);
                    }

                    // 缩小窗口，移出字符，并维护窗口哈希值（删除最高位数字）
                    windowHash = windowHash - nums[left] * RL;
                    left++;
                }
            }

            // 转化成题目要求的 List 类型
            return new LinkedList<>(res);
        }

    }

}

```

输出：

```sh
AAAAACCCCC
CCCCCAAAAA
```





### 复杂度分析

滑动窗口算法本身的时间复杂度是 `O(N)`，再看看窗口滑动的过程中的操作耗时，给 `res` 添加子串的过程用到了 `substring` 方法需要 `O(L)` 的复杂度，但一般情况下 `substring` 方法不会调用很多次，只有极端情况（比如字符串全都是相同的字符）下才会每次滑动窗口时都调用 `substring` 方法。

所以我们可以说这个算法一般情况下的平均时间复杂度是 `O(N)`，极端情况下的时间复杂度会退化成 `O(NL)`。





# THE END