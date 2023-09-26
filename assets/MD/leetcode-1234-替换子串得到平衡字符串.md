# 目录

[toc]

# leetcode-1234-替换子串得到平衡字符串

- 时间：2023-07-22
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/replace-the-substring-for-balanced-string/description/
- 难度：中等

有一个只含有 `'Q', 'W', 'E', 'R'` 四种字符，且长度为 `n` 的字符串。

假如在该字符串中，这四个字符都恰好出现 `n/4` 次，那么它就是一个「平衡字符串」。

 

给你一个这样的字符串 `s`，请通过「替换一个子串」的方式，使原字符串 `s` 变成一个「平衡字符串」。

你可以用和「待替换子串」长度相同的 **任何** 其他字符串来完成替换。

请返回待替换子串的最小可能长度。

如果原字符串自身就是一个平衡字符串，则返回 `0`。

 

**示例 1：**

```
输入：s = "QWER"
输出：0
解释：s 已经是平衡的了。
```

**示例 2：**

```
输入：s = "QQWE"
输出：1
解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
```

**示例 3：**

```
输入：s = "QQQW"
输出：2
解释：我们可以把前面的 "QQ" 替换成 "ER"。 
```

**示例 4：**

```
输入：s = "QQQQ"
输出：3
解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
```

 

**提示：**

+ `1 <= s.length <= 10^5`
+ `s.length` 是 `4` 的倍数
+ `s` 中只含有 `'Q'`, `'W'`, `'E'`, `'R'` 四种字符



# 2、题解

## 题目分析



## 解法一：滑动窗口

### 算法分析

- 参考链接：
  - https://leetcode.cn/problems/replace-the-substring-for-balanced-string/solutions/2107520/ti-huan-zi-chuan-de-dao-ping-heng-zi-fu-f8fk8/



![image-20230722233010022](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722233010022.png)



### 代码

```java


/**
 * <p>
 * 替换子串得到平衡字符串
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode1234 {

    public static void main(String[] args) {
        String s = "QQQW";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.balancedString(s));

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        public int balancedString(String s) {
            int n = s.length();
            // 先统计 s 中各个字符的数量
            int[] cnt = new int[26];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                cnt[idx(c)]++;
            }

            int partial = n / 4;
            int res = n;

            if (check(cnt, partial)) {
                return 0;
            }

            // 使用滑动窗口来维护区间 [left, right) 之外的剩余部分中 QWER 的出现次数
            for (int left = 0, right = 0; left < n; ) {
                // 当其中一种字符的出现次数大于 partial 时，另 s[right] 的出现次数减一，并右移 right
                while (right < n && !check(cnt, partial)) {
                    cnt[idx(s.charAt(right++))]--;
                }

                // 如果找不到满足条件的 right，就可以直接跳出循环了。
                if (!check(cnt, partial)) {
                    break;
                }

                // 找到符合条件的 right,
                // 对于所有合法的 [left, right)，取 right - left 的最小值做为答案。
                res = Math.min(res, right - left);
                // 另 s[left] 的出现次数加一，并使得 left 向右移动一个单位。
                cnt[idx(s.charAt(left++))]++;
            }

            return res;

        }

        private int idx(char c) {
            return c - 'A';
        }

        /**
         * 校验是否能组成一个平衡字符串：
         * 只有当 s 剩余的部分中 Q,W,E,R 的出现次数都小于等于 partial 时，我们才有可能使 s 变为「平衡字符串」。
         */
        private boolean check(int[] cnt, int partial) {
            if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
                return false;
            }
            return true;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)，其中 n 为 s 的长度。
- 空间复杂度：O(|Σ|), |Σ| 表示字符集大小，本题解中 |Σ| = 26。









# THE END