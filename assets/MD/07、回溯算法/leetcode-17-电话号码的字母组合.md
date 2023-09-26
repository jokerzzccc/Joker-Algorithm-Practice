# 目录

[toc]

# leetcode-17-电话号码的字母组合

- 时间：2023-07-06
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
- 难度：中等

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



 

示例 1：

```sh
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```



**提示：**

+ `0 <= digits.length <= 4`
+ `digits[i]` 是范围 `['2', '9']` 的一个数字。



# 2、题解

## 题目分析



## 解法一: 回溯

### 算法分析





### 代码

```java


/**
 * <p>
 * 电话号码的字母组合
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode17 {

    public static void main(String[] args) {
        String digits = "23";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.letterCombinations(digits));

    }

    /**
     * 解法一：回溯
     */
    private static class Solution01 {

        Map<Character, String> cachePhone = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        List<String> resList = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return resList;
            }

            StringBuilder sb = new StringBuilder();
            backTracking(digits, 0, sb);

            return resList;
        }

        private void backTracking(String digits, int index, StringBuilder curStr) {
            // base case
            // digits 遍历深度完的时候
            if (index == digits.length()) {
                resList.add(curStr.toString());
                return;
            }

            char digit = digits.charAt(index);
            String letters = cachePhone.get(digit);
            int curCnt = letters.length();

            for (int i = 0; i < curCnt; i++) {
                // 做选择
                curStr.append(letters.charAt(i));

                backTracking(digits, index + 1, curStr);

                // 撤销选择
                curStr.deleteCharAt(index);
            }
        }

    }

}

```





### 复杂度分析

![image-20230706220218408](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230706220218408.png)









# THE END