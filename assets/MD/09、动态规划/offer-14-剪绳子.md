# 目录

[toc]

# offer-14-剪绳子

- 时间：2023-05-01



# 1、题目

- https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
- 

# 2、题解

算法思路:

- 核心理论：
  - 尽可能将绳子以长度 33 等分为多段时，乘积最大。





切分规则：

- **最优：** 3。把绳子尽可能切为多个长度为 33 的片段，留下的最后一段绳子的长度可能为 0,1,20,1,2 三种情况。
- **次优：** 2 。若最后一段绳子长度为 2 ；则保留，不再拆为 1+1 。
- **最差：**1。若最后一段绳子长度为1；则应把一份3+1替换为2+2，因为2×2>3×1。



算法流程：

![image-20230501232940741](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230501232940741.png)

代码：

```java
class Solution {

    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

}
```



复杂度分析：

![image-20230501233018674](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230501233018674.png)

# THE END