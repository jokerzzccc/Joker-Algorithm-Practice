## 目录

[toc]

# offer-15-二进制中 1 的个数

- 时间：2023-05-01





# 1、题目

- https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/mian-shi-ti-15-er-jin-zhi-zhong-1de-ge-shu-wei-yun/



# 2、解法

算法：





代码：

法一：

```java
public class Solution {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;

    }

}
```



法二：

```java
public class Solution {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

}
```



# THE END