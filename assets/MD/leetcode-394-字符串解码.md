# 目录

[toc]

# leetcode-394-字符串解码

- 时间：2023-07-17
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/decode-string/
- 难度：中等

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 ![image-20230717210121785](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230717210121785.png)

提示：

- 1 <= s.length <= 30
- s 由小写英文字母、数字和方括号 '[]' 组成
- s 保证是一个 有效 的输入。
- s 中所有整数的取值范围为 [1, 300] 





# 2、题解

## 题目分析



## 解法一：辅助栈

### 算法分析

![image-20230717214015396](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230717214015396.png)



### 代码

```java


/**
 * <p>
 * 字符串解码
 * </p>
 *
 * @author admin
 * @date 2023/7/17
 */
public class leetcode394 {

    public static void main(String[] args) {
        String s1 = "2[abc]3[cd]ef";
        String s2 = "3[a2[c]]";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.decodeString(s1));

    }

    /**
     * 解法一：辅助栈
     */
    private static class Solution01 {

        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            // 存放数字的倍数栈
            LinkedList<Integer> stackMulti = new LinkedList<>();
            // 存放字符串的栈
            LinkedList<String> stackRes = new LinkedList<>();

            for (char c : s.toCharArray()) {
                if (c == '[') {
                    // 入栈
                    stackMulti.addLast(multi);
                    stackRes.addLast(res.toString());

                    multi = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    // 出栈
                    StringBuilder tmp = new StringBuilder();
                    int curMulti = stackMulti.removeLast();
                    for (int i = 0; i < curMulti; i++) {
                        tmp.append(res);
                    }
                    res = new StringBuilder(stackRes.removeLast() + tmp);
                } else if (Character.isDigit(c)) {
                    multi = multi * 10 + Integer.parseInt(c + "");
                } else if (Character.isLetter(c)) {
                    res.append(c);
                }
            }

            return res.toString();
        }

    }

}

```





### 复杂度分析

![image-20230717214026493](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230717214026493.png)









# THE END