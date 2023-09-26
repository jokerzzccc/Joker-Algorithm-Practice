# 目录

[toc]

# leetcode-22-括号生成

- 时间：2023-05-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/generate-parentheses/
- 难度：中等



**示例 1：**

```
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
```

**示例 2：**

```
输入：n = 1
输出：["()"]
```



**提示：**

+ `1 <= n <= 8`



Related Topics

字符串

动态规划

回溯





# 2、题解

## 题目分析



## 解法一：回溯法+ DFS

### 算法：

- 参考：https://leetcode.cn/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/

一个树如图：

![image-20230514210920668](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230514210920668.png)

通过画图以后，可以分析出的结论：

- 当前左右括号都有大于 0 个可以使用的时候，才产生分支；
- 产生左分支的时候，只看当前是否还有左括号可以使用；
- 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
- 在左边和右边剩余的括号数都等于 0 的时候结算。





### 代码：debug 版

```java
/**
 * <p>
 * 括号生成
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/14
 */
public class leetcode22 {

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        int n = 2;
        List<String> parenthesis = solution01.generateParenthesis(n);
        System.out.println(parenthesis);

    }

    static class Solution01 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            if (n <= 0) {
                return res;
            }
            backTrack("", 0, 0, n);
            return res;
        }

        /**
         * @param curStr 当前递归得到的结果
         * @param left 左括号已经用了几个
         * @param right 右括号已经用了几个
         * @param n 左括号、右括号一共得用几个
         */
        void backTrack(String curStr, int left, int right, int n) {
            if (right == n && left == n) {
                res.add(curStr);
                return;
            }

            // 剪枝
            if (left < right) {
                return;
            }

            if (left < n) {
                backTrack(curStr + "(", left + 1, right, n);
            }

            if (right < n) {
                backTrack(curStr + ")", left, right + 1, n);
            }
        }

    }

}
```



## 解法二：动态规划

### 算法

- https://leetcode.cn/problems/generate-parentheses/solution/zui-jian-dan-yi-dong-de-dong-tai-gui-hua-bu-lun-da/
- 最核心的思想是，考虑 `i=n` 时相比 `n-1` 组括号增加的那一组括号的位置。
- 只是这个相比法一，性能低很多。



### 思路：

当我们清楚所有 `i<n` 时括号的可能生成排列后，对与 `i=n` 的情况，我们考虑整个括号排列中最左边的括号。
它一定是一个左括号，那么它可以和它对应的右括号组成一组完整的括号 `"( )"`，我们认为这一组是相比 `n-1` 增加进来的括号。

那么，剩下 `n-1` 组括号有可能在哪呢？

**【这里是重点，请着重理解】**

剩下的括号要么在这一组新增的括号内部，要么在这一组新增括号的外部（右侧）。

既然知道了 `i<n` 的情况，那我们就可以对所有情况进行遍历：

**"(" + 【i=p时所有括号的排列组合】 + ")" + 【i=q时所有括号的排列组合】**

其中 `p + q = n-1`，且 `p q` 均为非负整数。

事实上，当上述 `p` 从 `0` 取到 `n-1`，`q` 从 `n-1` 取到 `0` 后，所有情况就遍历完了。

注：上述遍历是没有重复情况出现的，即当 `(p1,q1)≠(p2,q2)` 时，按上述方式取的括号组合一定不同。



- 递推公式：F(n+1)="("+F(i)+")"+F(n-i)



### 代码: debug 版

```java
/**
 * <p>
 * 括号生成
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/14
 */
public class leetcode22 {

    public static void main(String[] args) {
        List<String> parenthesis;
        int n = 2;
        // 法二
        Solution02 solution02 = new Solution02();
        parenthesis = solution02.generateParenthesis(n);

        System.out.println(parenthesis);

    }


    static class Solution02 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            List<List<String>> total = new ArrayList<>();
            if (n <= 0) {
                return res;
            }
            List<String> list0 = new ArrayList<>();
            list0.add("");
            total.add(list0);
            List<String> list1 = new ArrayList<>();
            list1.add("()");
            total.add(list1);

            for (int i = 2; i <= n; i++) {
                List<String> temp = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    List<String> str1 = total.get(j);
                    List<String> str2 = total.get(i - j - 1);
                    for (String s1 : str1) {
                        for (String s2 : str2) {
                            String el = "(" + s1 + ")" + s2;
                            temp.add(el);
                        }
                    }
                }
                total.add(temp);
            }
            return total.get(n);
        }

    }

}
```



# THE END