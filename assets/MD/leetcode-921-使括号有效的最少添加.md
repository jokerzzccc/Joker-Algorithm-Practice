# 目录

[toc]

# leetcode-921-使括号有效的最少添加

- 时间：2023-06-28
- 参考链接：
  - [如何解决括号相关的问题](https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-jie--306f6/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
- 难度：中等



只有满足下面几点之一，括号字符串才是有效的：

- 它是一个空字符串，或者
- 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
- 它可以被写作 (A)，其中 A 是有效字符串。

给定一个括号字符串 s ，在每一次操作中，你都可以在字符串的任何位置插入一个括号

- 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。

返回 **为使结果字符串 s 有效而必须添加的最少括号数**。



**提示：**

+ `1 <= s.length <= 1000`
+ `s` 只包含 `'('` 和 `')'` 字符。



# 2、题解

## 题目分析

- 类比 leetcode-20



## 解法一：贪心

### 算法分析

**核心思路是以左括号为基准，通过维护对右括号的需求数 `need`，来计算最小的插入次数**。

都是在遇到括号无法进行匹配的情况下才进行添加，因此上述做法得到的添加次数是最少的。



需要注意两个地方：

**1、当 `need == -1` 的时候意味着什么**？

因为只有遇到右括号 `)` 的时候才会 `need--`，`need == -1` 意味着右括号太多了，所以需要插入左括号。

比如说 `s = "))"` 这种情况，需要插入 2 个左括号，使得 `s` 变成 `"()()"`，才是一个有效括号串。

**2、算法为什么返回 `res + need`**？

因为 `res` 记录的左括号的插入次数，`need` 记录了右括号的需求，当 for 循环结束后，若 `need` 不为 0，那么就意味着右括号还不够，需要插入。

比如说 `s = "))("` 这种情况，插入 2 个左括号之后，还要再插入 1 个右括号，使得 `s` 变成 `"()()()"`，才是一个有效括号串。





### 代码

```java

/**
 * <p>
 * 使括号有效的最少添加
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode921 {

    public static void main(String[] args) {
        String s = "(((";

        Solution01 solution01 = new Solution01();
        int minAddToMakeValid01 = solution01.minAddToMakeValid(s);
        System.out.println(minAddToMakeValid01);

    }

    /**
     * 解法一：贪心
     * 核心思路是以左括号为基准，通过维护对右括号的需求数 need，来计算最小的插入次数。
     */
    private static class Solution01 {

        public int minAddToMakeValid(String s) {
            // 记录插入次数
            int res = 0;
            // 变量记录右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    // 对右括号的需求 + 1
                    need++;
                }
                if (s.charAt(i) == ')') {
                    // 对右括号的需求 - 1
                    need--;
                    if (need == -1){
                        // 需插入一个左括号
                        need = 0;
                        res ++;
                    }
                }
            }

            // 插入剩余所需的右括号
            return res + need;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n),其中 n 是字符串的长度。遍历字符串一次。
- 空间复杂度：O(1)。只需要维护常量的额外空间。









# THE END