# 目录

[toc]

# leetcode-76-最小覆盖子串

- 时间：2023-06-13

- 参考链接：
  - [我写了首诗，把滑动窗口算法算法变成了默写题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--f7a92/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-window-substring/
- 难度：困难



给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

 

注意：

- 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
- 如果 s 中存在这样的子串，我们保证它是唯一的答案。



提示：

m == s.length
n == t.length
1 <= m, n <= 105
s 和 t 由英文字母组成

**进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？**





# 2、题解

## 题目分析



## 解法一:  滑动窗口

### 算法分析

1、我们在字符串 `S` 中使用双指针中的左右指针技巧，初始化 `left = right = 0`，把索引**左闭右开**区间 `[left, right)` 称为一个「窗口」。

- 理论上你可以设计两端都开或者两端都闭的区间，但设计为左闭右开区间是最方便处理的。因为这样初始化 `left = right = 0` 时区间 `[0, 0)` 中没有元素，但只要让 `right` 向右移动（扩大）一位，区间 `[0, 1)` 就包含一个元素 `0` 了。如果你设置为两端都开的区间，那么让 `right` 向右移动一位后开区间 `(0, 1)` 仍然没有元素；如果你设置为两端都闭的区间，那么初始区间 `[0, 0]` 就包含了一个元素。这两种情况都会给边界处理带来不必要的麻烦。



2、我们先不断地增加 `right` 指针扩大窗口 `[left, right)`，直到窗口中的字符串符合要求（包含了 `T` 中的所有字符）。

3、此时，我们停止增加 `right`，转而不断增加 `left` 指针缩小窗口 `[left, right)`，直到窗口中的字符串不再符合要求（不包含 `T` 中的所有字符了）。同时，每次增加 `left`，我们都要更新一轮结果。

4、重复第 2 和第 3 步，直到 `right` 到达字符串 `S` 的尽头。



这个思路其实也不难，**第 2 步相当于在寻找一个「可行解」，然后第 3 步在优化这个「可行解」，最终找到最优解**，也就是最短的覆盖子串。左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。



### 代码

```java

/**
 * <p>
 * 最小覆盖子串
 * </p>
 *
 * @author admin
 * @date 2023/6/13
 */
public class leetcode76 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        Solution01 solution01 = new Solution01();
        String minWindow01 = solution01.minWindow(s, t);
        System.out.println(minWindow01);
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

        public String minWindow(String s, String t) {
            int sLen = s.length();
            int tLen = t.length();

            if (sLen < tLen) {
                return "";
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
            // 记录最小覆盖子串的起始索引及长度
            int start = 0, len = Integer.MAX_VALUE;

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
                while (valid == need.size()) {
                    // 更新最小覆盖子串
                    if (right - left < len) {
                        start = left;
                        len = right - left;
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
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }

    }

}

```

输出：

```sh
BANC
```





### 复杂度分析

- 时间复杂度：最坏情况下左右指针对s的每个元素各遍历一遍，哈希表中对s中的每个元素各插入、
  删除一次，对t中的元素各插入一次。每次检查是否可行会扁历整个t的哈希表，哈希表的大小与字符
  集的大小有关，设字符集大小为C,则渐进时间复杂度为O(C·|s|+|t|)。
- 空间复杂度：这里用了两张哈希表作为辅助空间，每张哈希表最多不会存放超过字符集大小的键值对，
  我们设字符集大小为C,则新进空间复杂度为O(C)。

.

# THE END