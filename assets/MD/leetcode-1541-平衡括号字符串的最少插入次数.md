# 目录

[toc]

# leetcode-1541-平衡括号字符串的最少插入次数

- 时间：2023-06-28
- 参考链接：
  - [如何解决括号相关的问题](https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-jie--306f6/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/
- 难度：中等





给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：

- 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
- 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。



比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。

你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。

请你返回**让 s 平衡的最少插入次数**。

 



# 2、题解

## 题目分析

- 类比 leetcode-921,
  - 区别：这个，左右括号不是 1:1 配对



## 解法一：贪心

### 算法分析

**核心思路还是和刚才一样，通过一个 `need` 变量记录对右括号的需求数，根据 `need` 的变化来判断是否需要插入**。

第一步，我们按照刚才的思路正确维护 `need` 变量：

```java
// 注意：java 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码还未经过力扣测试，仅供参考，如有疑惑，可以参照我写的 cpp 代码对比查看。

int minInsertions(String s) {
    // need 记录需右括号的需求量
    int res = 0, need = 0;
    
    for (int i = 0; i < s.length(); i++) {
        // 一个左括号对应两个右括号
        if (s.charAt(i) == '(') {
            need += 2;
        }
        
        if (s.charAt(i) == ')') {
            need--;
        }
    }
    
    return res + need;
}
```

现在想一想，当 `need` 为什么值的时候，我们可以确定需要进行插入？

**首先，类似leetcode-921，当 `need == -1` 时，意味着我们遇到一个多余的右括号，显然需要插入一个左括号**。

比如说当 `s = ")"`，我们肯定需要插入一个左括号让 `s = "()"`，但是由于一个左括号需要两个右括号，所以对右括号的需求量变为 1：

```cpp
if (s[i] == ')') {
    need--;
    // 说明右括号太多了
    if (need == -1) {
        // 需要插入一个左括号
        res++;
        // 同时，对右括号的需求变为 1
        need = 1;
    }
}
```

**另外，当遇到左括号时，若对右括号的需求量为奇数，需要插入 1 个右括号**。因为一个左括号需要两个右括号嘛，右括号的需求必须是偶数，这一点也是本题的难点。

所以遇到左括号时要做如下判断：

```cpp
if (s[i] == '(') {
    need += 2;
    if (need % 2 == 1) {
        // 插入一个右括号
        res++;
        // 对右括号的需求减一
        need--;
    }
}
```

综上，我们可以写出正确的代码：



### 代码

```java
package com.joker.algorithm;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * <p>
 * 平衡括号字符串的最少插入次数
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode1541 {

    public static void main(String[] args) {
        String s = "(()))";

        Solution01 solution01 = new Solution01();
        int minInsertions01 = solution01.minInsertions(s);
        System.out.println(minInsertions01);

    }

    /**
     * 解法一： 贪心
     * 类比 leetcode921
     */
    private static class Solution01 {

        public int minInsertions(String s) {
            // 记录插入次数
            int res = 0;
            // 变量记录右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    need += 2;
                    // 当遇到左括号时，若对右括号的需求量为奇数，需要插入 1 个右括号
                    // 因为一个左括号需要两个右括号嘛，右括号的需求必须是偶数
                    if (need % 2 == 1) {
                        // 插入一个右括号
                        res++;
                        // 对右括号的需求减一
                        need--;
                    }
                }

                if (s.charAt(i) == ')') {
                    need--;
                    // 说明右括号太多了
                    if (need == -1) {
                        // 需要插入一个左括号
                        res++;
                        // 同时，对右括号的需求变为 1
                        need = 1;
                    }
                }

            }

            return res + need;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n),其中是字符串的长度。遍历字符串一次。
- 空间复杂度：O(1)。只需要维护常量的额外空间。









# THE END