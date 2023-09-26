# 目录

[toc]

# leetcode-20-有效的括号

- 时间：2023-06-28
- 参考链接：
  - [如何解决括号相关的问题](https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-jie--306f6/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/valid-parentheses/
- 难度：简单



给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。
3. 每个右括号都有一个对应的相同类型的左括号。



**提示：**

+ `1 <= s.length <= 104`
+ `s` 仅由括号 `'()[]{}'` 组成





# 2、题解

## 题目分析



## 解法一：栈

### 算法分析

可以利用栈来模仿类似的思路。栈是一种先进后出的数据结构，处理括号问题的时候尤其有用。

我们这道题就用一个名为 `left` 的栈代替之前思路中的 `left` 变量，**遇到左括号就入栈，遇到右括号就去栈中寻找最近的左括号，看是否匹配**：



### 代码

```java

/**
 * <p>
 * 有效的括号
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode20 {

    public static void main(String[] args) {
        String s = "()[]{}";

        Solution01 solution01 = new Solution01();
        boolean valid01 = solution01.isValid(s);
        System.out.println(valid01);

    }

    /**
     * 解法一：栈
     */
    private static class Solution01 {

        public boolean isValid(String s) {
            Stack<Character> left = new Stack<>();
            for (char c : s.toCharArray()) {
                // 遇到左括号就入栈
                if (c == '(' || c == '[' || c == '{') {
                    left.push(c);
                } else {
                    // 遇到右括号就去栈中寻找最近的左括号，看是否匹配
                    if (!left.isEmpty() && leftOf(c) == left.peek()) {
                        // 匹配到，就移除栈顶的左括号
                        left.pop();
                    } else {
                        // 匹配不到就就返回 false
                        return false;
                    }
                }
            }

            // 是否所有的左括号都被匹配了
            return left.isEmpty();

        }

        char leftOf(char c) {
            if (c == '}') return '{';
            if (c == ')') return '(';
            return '[';
        }

    }

}

```





### 复杂度分析

![image-20230628204424283](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230628204424283.png)









# THE END