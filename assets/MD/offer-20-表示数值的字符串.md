# 目录

[toc]

# offer-20-表示数值的字符串

- 时间：2023-08-20
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/description/
- 难度：中等

请实现一个函数用来判断字符串是否表示**数值**（包括整数和小数）。

**数值**（按顺序）可以分成以下几个部分：

1. 若干空格
2. 一个 **小数** 或者 **整数**
3. （可选）一个 `'e'` 或 `'E'` ，后面跟着一个 **整数**
4. 若干空格

**小数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 下述格式之一：
   1. 至少一位数字，后面跟着一个点 `'.'`
   2. 至少一位数字，后面跟着一个点 `'.'` ，后面再跟着至少一位数字
   3. 一个点 `'.'` ，后面跟着至少一位数字

**整数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 至少一位数字

部分**数值**列举如下：

+ `["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]`

部分**非数值**列举如下：

+ `["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]`

 

**示例 1：**

```
输入：s = "0"
输出：true
```

**示例 2：**

```
输入：s = "e"
输出：false
```

**示例 3：**

```
输入：s = "."
输出：false
```

**示例 4：**

```
输入：s = "    .1  "
输出：true
```

 

**提示：**

+ `1 <= s.length <= 20`
+ `s` 仅含英文字母（大写和小写），数字（`0-9`），加号 `'+'` ，减号 `'-'` ，空格 `' '` 或者点 `'.'` 。



# 2、题解

## 题目分析



## 解法一：枚举模拟

### 算法分析





### 代码

```java


/**
 * <p>
 * 表示数值的字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/20
 */
public class offer20 {

    public static void main(String[] args) {
        String s = "3.1416";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isNumber(s));

    }

    /**
     * 解法一：枚举模拟
     */
    private static class Solution01 {

        public boolean isNumber(String s) {
            // s为空对象或 s长度为0(空字符串)时, 不能表示数值
            if (s == null || s.length() == 0) {
                return false;
            }

            // 标记是否遇到数位、小数点、‘e’或'E'
            boolean isNum = false, isDot = false, ise_or_E = false;
            // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符
            char[] str = s.trim().toCharArray();

            // 遍历，判断是否为合法字符串
            for (int i = 0; i < str.length; i++) {
                if (str[i] >= '0' && str[i] <= '9') { // 判断当前字符是否为 0~9 的数位
                    isNum = true;
                } else if (str[i] == '.') { // 遇到小数点
                    if (isDot || ise_or_E) return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                    isDot = true; // 标记已经遇到小数点
                } else if (str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                    if (!isNum || ise_or_E) return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                    ise_or_E = true; // 标记已经遇到‘e’或'E'
                    isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
                } else if (str[i] == '-' || str[i] == '+') {
                    if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                        return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
                } else {
                    return false; // 其它情况均为不合法字符
                }
            }

            return isNum;
        }

    }

}

```





### 复杂度分析











# THE END