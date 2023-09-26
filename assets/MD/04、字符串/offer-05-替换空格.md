# 目录

[toc]

# offer-05-替换空格

- 时间：2023-04-20



# 1、题目

- 请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。





# 2、题解

思路：

- 首先禁用 replaceAll 方法。
- 



算法：



代码：

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for(Character c : s.toCharArray())
        {
            if(c == ' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }
}

```

