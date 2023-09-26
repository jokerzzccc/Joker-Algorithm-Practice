# 目录

[toc]

# leetcode-227-基本计算器 II

- 时间：2023-07-21
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/basic-calculator-ii/description/
- 难度：中等

给你一个字符串表达式 `s` ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 `[-231, 231 - 1]` 的范围内。

**注意：**不允许使用任何将字符串作为数学表达式计算的内置函数，比如 `eval()` 。



**示例 1：**

```
输入：s = "3+2*2"
输出：7
```

**示例 2：**

```
输入：s = " 3/2 "
输出：1
```

**示例 3：**

```
输入：s = " 3+5 / 2 "
输出：5
```



**提示：**

+ `1 <= s.length <= 3 * 10^5`
+ `s` 由整数和算符 `('+', '-', '*', '/')` 组成，中间由一些空格隔开
+ `s` 表示一个 **有效表达式**
+ 表达式中的所有整数都是非负整数，且在范围 `[0, 2^31 - 1]` 内
+ 题目数据保证答案是一个 **32-bit 整数**



# 2、题解

## 题目分析



## 解法一

- 参考链接：
  - https://leetcode.cn/problems/basic-calculator-ii/solutions/648832/shi-yong-shuang-zhan-jie-jue-jiu-ji-biao-c65k/
  - 

### 算法分析

**对于「表达式计算」这一类问题，你都可以使用这套思路进行解决。我十分建议你加强理解这套处理逻辑。**

对于「任何表达式」而言，我们都使用两个栈 nums 和 ops：

- nums ： 存放所有的数字
- ops ：存放所有的数字以外的操作



然后**从前往后**做，对遍历到的字符做分情况讨论：

- 空格 : 跳过
- `(` : 直接加入 ops 中，等待与之匹配的 )
- `)` : 使用现有的 nums 和 ops 进行计算，直到遇到左边最近的一个左括号为止，计算结果放到 nums
- 数字 : 从当前位置开始继续往后取，将整一个连续数字整体取出，加入 nums

+ `+ - * / ^ %` : 需要将操作放入 ops 中。**在放入之前先把栈内可以算的都算掉（只有「栈内运算符」比「当前运算符」优先级高/同等，才进行运算）**，使用现有的 nums 和 ops 进行计算，直到没有操作或者遇到左括号，计算结果放到 `nums`。



我们可以通过 例子 来理解 **只有「栈内运算符」比「当前运算符」优先级高/同等，才进行运算** 是什么意思：

因为我们是从前往后做的，假设我们当前已经扫描到 2 + 1 了（此时栈内的操作为 + ）。

1. 如果后面出现的 `+ 2` 或者 `- 1` 的话，满足「栈内运算符」比「当前运算符」优先级高/同等，可以将 `2 + 1` 算掉，把结果放到 `nums` 中；
2. 如果后面出现的是 `* 2` 或者 `/ 1` 的话，不满足「栈内运算符」比「当前运算符」优先级高/同等，这时候不能计算 2 + 1。



一些细节：

- 由于第一个数可能是**负数**，为了减少边界判断。一个小技巧是先往 `nums` 添加一个 0
- 为防止 () 内出现的首个字符为运算符，将所有的空格去掉，并将 `(-` 替换为 `(0-`，`(+` 替换为 `(0+`（当然也可以不进行这样的预处理，将这个处理逻辑放到循环里去做）
- 从理论上分析，`nums` 最好存放的是 `long`，而不是 `int`。因为可能存在 `大数 + 大数 + 大数 + … - 大数 - 大数` 的表达式导致中间结果溢出，最终答案不溢出的情况





### 代码

```java

/**
 * <p>
 * 基本计算器 II
 * </p>
 *
 * @author admin
 * @date 2023/7/21
 */
public class leetcode227 {

    public static void main(String[] args) {
        String s = "3+2*2";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.calculate(s));

    }

    /**
     * 解法一：双栈
     */
    private static class Solution01 {

        /**
         * 使用 map 维护一个运算符优先级
         * 这里的优先级划分按照「数学」进行划分即可
         */
        Map<Character, Integer> opsPrioritymap = new HashMap<Character, Integer>() {{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }};

        /**
         * 数字栈：存放所有的数字
         */
        LinkedList<Integer> nums = new LinkedList<>();
        /**
         * 操作符栈：存放所有「非数字以外」的操作
         */
        LinkedList<Character> ops = new LinkedList<>();

        public int calculate(String s) {
            // 将所有的空格去掉
            s = s.replaceAll(" ", "");
            int n = s.length();
            char[] strChars = s.toCharArray();

            // 为了防止第一个数为负数，先往 nums 加个 0
            nums.addLast(0);

            for (int i = 0; i < n; i++) {
                char curChar = strChars[i];

                if (curChar == '(') {
                    ops.addLast(curChar);
                } else if (curChar == ')') {
                    // 计算到最近一个左括号为止
                    while (!ops.isEmpty()) {
                        if (ops.peekLast() != '(') {
                            calculate();
                        } else {
                            // 遇到最近一个左括号，出栈，并结束计算
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (Character.isDigit(curChar)) { // 是数字时
                        int curSerialNum = 0;
                        int j = i;
                        // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                        while (j < n && Character.isDigit(strChars[j])) {
                            curSerialNum = curSerialNum * 10 + (strChars[j++] - '0');
                        }

                        // 加入值栈
                        nums.addLast(curSerialNum);
                        // 因为j++ 加到 strChas[j] 不为数字了。
                        i = j - 1;
                    } else { // 是运算符时
                        // 为防止 () 内出现的首个字符为运算符，将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
                        if (i > 0 && (strChars[i - 1] == '(' || strChars[i - 1] == '+' || strChars[i - 1] == '-')) {
                            nums.addLast(0);
                        }

                        // 需要将操作放入 ops 中，在放入之前先把栈内可以算的都算掉（只有「栈内运算符」比「当前运算符」优先级高/同等，才进行运算）
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            char prev = ops.peekLast();
                            if (opsPrioritymap.get(prev) >= opsPrioritymap.get(curChar)) {
                                calculate();
                            } else {
                                break;
                            }
                        }

                        // 运算符入栈
                        ops.addLast(curChar);
                    }

                }

            }

            // 将剩余的计算完
            while (!ops.isEmpty()) {
                calculate();
            }

            return nums.peekLast();
        }

        /**
         * 计算当前可计算的数字
         */
        void calculate() {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;

            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            int ans = 0;
            if (op == '+') ans = a + b;
            else if (op == '-') ans = a - b;
            else if (op == '*') ans = a * b;
            else if (op == '/') ans = a / b;

            nums.addLast(ans);
        }

    }

}

```





### 复杂度分析











# THE END