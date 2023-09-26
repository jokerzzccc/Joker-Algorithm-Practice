# 目录

[toc]

# leetcode-438-找到字符串中所有字母异位词

- 时间：2023-06-14

- 参考链接：
  - [我写了首诗，把滑动窗口算法算法变成了默写题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--f7a92/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/find-all-anagrams-in-a-string/
- 难度：中等



给定两个字符串 s 和 p，找到 s 中所有 p 的 **异位词** 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

**异位词** 指由相同字母重排列形成的字符串（包括相同的字符串）。



**提示:**

+ `1 <= s.length, p.length <= 3 * 104`
+ `s` 和 `p` 仅包含小写字母





# 2、题解

## 题目分析



## 解法一:  滑动窗口

### 算法分析

这个所谓的字母异位词，不就是排列吗，搞个高端的说法就能糊弄人了吗？**相当于，输入一个串 `S`，一个串 `T`，找到 `S` 中所有 `T` 的排列，返回它们的起始索引**。



### 代码

```java


/**
 * <p>
 * 找到字符串中所有字母异位词
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode438 {

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";

        Solution01 solution01 = new Solution01();
        List<Integer> anagrams01 = solution01.findAnagrams(s, p);
        anagrams01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        /**
         * 用于记录需要的字符(t 的字符)及其出现的次数
         */
        Map<Character, Integer> need = new HashMap<>();
        /**
         * 用于窗口中的字符及其出现的次数
         */
        Map<Character, Integer> window = new HashMap<>();

        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            for (int i = 0; i < pLen; i++) {
                char c = p.charAt(i);
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            int left = 0;
            int right = 0;
            int valid = 0;
            // 记录结果
            List<Integer> res = new ArrayList<>();

            while (right < sLen) {
                char c = s.charAt(right);
                right++;
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (need.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }
                // 判断左侧窗口是否要收缩
                while (right - left >= pLen) {
                    // 当窗口符合条件时，把起始索引加入 res
                    if (valid == need.size()) {
                        res.add(left);
                    }

                    char d = s.charAt(left);
                    left++;
                    if (need.containsKey(d)) {
                        if (need.get(d).equals(window.get(d))) {
                            valid--;
                        }
                        window.put(d, window.getOrDefault(d, 0) - 1);
                    }

                }
            }

            return res;

        }

    }

}

```

输出：

```sh
0
6
```





### 复杂度分析

- 时间复杂度：O(n十m+∑)，其中n为字符串s的长度，m为字符串p的长度，其中∑为所有可能的
  字符数。我们需要O(m)来统计字符串p中每种字母的数量；需要O(m)来初始化滑动窗口；需要
  O(∑)来初始化dfr;需要O(n-m)来滑动窗口并判断窗口内每种字母的数量是否与字符串p中每
  种字母的数量相同，每次判断需要O(1)。因为s和p仅包含小写字母，所以∑=26。
- 空间复杂度：O(∑)。用于存储滑动窗口和字符串p中每种字母数量的差。





# THE END