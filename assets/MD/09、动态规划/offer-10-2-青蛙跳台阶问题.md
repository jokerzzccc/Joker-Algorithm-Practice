# 目录

[toc]

# offer-10-2-青蛙跳台阶问题

- 时间：2023-04-28



# 1、题目

- https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/solution/
- 



# 2、题解

思路：

![image-20230429011456757](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230429011456757.png)



代码：

```java
class Solution {
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a =b;
            b = sum;
        }
        return a;
    }
}
```

