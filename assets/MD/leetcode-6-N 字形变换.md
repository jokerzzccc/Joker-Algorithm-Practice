# 目录

[toc]

# leetcode-6-N 字形变换

- 时间：2023-06-28
- 参考链接：
  - https://leetcode.cn/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/




# 1、题目

- 题目：https://leetcode.cn/problems/zigzag-conversion/?company_slug=alibaba
- 难度：中等



将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

```sh
P   A   H   N
A P L S I I G
Y   I   R
```

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

```sh
string convert(string s, int numRows);
```




示例 1：

```sh
输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
```



**提示：**

+ `1 <= s.length <= 1000`
+ `s` 由英文字母（小写和大写）、`','` 和 `'.'` 组成
+ `1 <= numRows <= 1000`



# 2、题解

## 题目分析



## 解法一

### 算法分析

![image-20230628231838127](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230628231838127.png)



### 代码

```java

/**
 * <p>
 * N 字形变换
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode6 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;

        Solution01 solution01 = new Solution01();
        String convert01 = solution01.convert(s, numRows);
        System.out.println(convert01);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public String convert(String s, int numRows) {
            int n = s.length();
            if (numRows == 1 || numRows >= n) {
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            // flag 用于改变遍历的方向，下上下...
            int flag = -1;
            // 当前添加字符的行数
            int row = 0;
            for (int i = 0; i < n; i++) {
                rows.get(row).append(s.charAt(i));
                if (row == 0 || row == numRows - 1) {
                    flag = -flag;
                }
                row += flag;
            }
            StringBuilder res = new StringBuilder();
            for (StringBuilder sb : rows) {
                res.append(sb);
            }

            return res.toString();
        }

    }

}

```

输出：

```sh
PAHNAPLSIIGYIR
```





### 复杂度分析











# THE END