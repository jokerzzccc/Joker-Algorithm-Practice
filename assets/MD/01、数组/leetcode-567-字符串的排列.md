# 目录

[toc]

# leetcode-567-字符串的排列

- 时间：2023-06-13

- 参考链接：
  - [我写了首诗，把滑动窗口算法算法变成了默写题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--f7a92/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/permutation-in-string/
- 难度：中等



给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。

换句话说，s1 的排列之一是 s2 的 子串 。



**提示：**

+ `1 <= s1.length, s2.length <= 104`
+ `s1` 和 `s2` 仅包含小写字母





# 2、题解

## 题目分析



## 解法一:  滑动窗口

### 算法分析

- 类比 leetcode76

注意哦，输入的 `s1` 是可以包含重复字符的，所以这个题难度不小。

这种题目，是明显的滑动窗口算法，**相当给你一个 `S` 和一个 `T`，请问你 `S` 中是否存在一个子串，包含 `T` 中所有字符且不包含其他字符**？

对于这道题的解法代码，基本上和最小覆盖子串一模一样，只需要改变几个地方：

1、本题移动 `left` 缩小窗口的时机是窗口大小大于 `t.size()` 时，因为排列嘛，显然长度应该是一样的。

2、当发现 `valid == need.size()` 时，就说明窗口中就是一个合法的排列，所以立即返回 `true`。

至于如何处理窗口的扩大和缩小，和最小覆盖子串完全相同。



> 由于这道题中 `[left, right)` 其实维护的是一个**定长**的窗口，窗口大小为 `t.size()`。因为定长窗口每次向前滑动时只会移出一个字符，所以可以把内层的 while 改成 if，效果是一样的。





### 代码

```java

/**
 * <p>
 * 字符串的排列
 * </p>
 *
 * @author admin
 * @date 2023/6/13
 */
public class leetcode567 {

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";

        Solution01 solution01 = new Solution01();
        boolean inclusion01 = solution01.checkInclusion(s1, s2);
        System.out.println(inclusion01);
    }

    /**
     * 解法一：滑动窗口
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

        public boolean checkInclusion(String t, String s) {
            int sLen = s.length();
            int tLen = t.length();

            if (sLen < tLen) {
                return false;
            }
            // 统计 t 中各字符出现次数
            for (int i = 0; i < tLen; i++) {
                need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
            }
            // 索引左闭右开区间 [left, right) 称为一个「窗口」
            int left = 0;
            int right = 0;
            // 窗口中满足需要的字符个数
            int valid = 0;

            while (right < sLen) {
                // c 是将移入窗口的字符
                char c = s.charAt(right);
                // 扩大窗口
                right++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        // 只有当 window[c] 和 need[c] 对应的出现次数一致时，才能满足条件，valid 才能 +1
                        valid++;
                    }
                }

                // 判断左侧窗口是否要收缩
                while (right - left >= tLen) {
                    // 在这里判断是否找到了合法的子串
                    // 当发现 valid == need.size() 时，就说明窗口中就是一个合法的排列，所以立即返回 true。
                    if (valid == need.size()) {
                        return true;
                    }

                    // d 是将移出窗口的字符
                    char d = s.charAt(left);
                    // 缩小窗口
                    left++;
                    // 进行窗口内数据的一系列更新
                    if (need.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            // 只有当 window[d] 内的出现次数和 need[d] 相等时，才能 -1
                            valid--;
                        }
                        window.put(d, window.getOrDefault(d, 0) - 1);
                    }
                }
            }

            // 未找到符合条件的子串
            return false;
        }

    }

}



```

输出：

```sh
true
```





### 复杂度分析

- 时间复杂度：最坏情况下左右指针对s的每个元素各遍历一遍，哈希表中对s中的每个元素各插入、
  删除一次，对t中的元素各插入一次。每次检查是否可行会扁历整个t的哈希表，哈希表的大小与字符
  集的大小有关，设字符集大小为C(s + t 的字符数),则渐进时间复杂度为O(C + |s|+|t|)。
- 空间复杂度：这里用了两张哈希表作为辅助空间，每张哈希表最多不会存放超过字符集大小的键值对，
  我们设字符集大小为C,则新进空间复杂度为O(C)。

.

# THE END