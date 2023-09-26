# 目录

[toc]

# offer-10-斐波那契数列

- 时间：2023-04-28



# 1、题目

- https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/solution/mian-shi-ti-10-i-fei-bo-na-qi-shu-lie-dong-tai-gui/
- 

# 2、题解

算法思路:

- 用循环代替递归；



## 法一：

- 递归转循环

代码：

```java
class Solution {

    public int fib(int n) {
        int[] ret = {0, 1};
        if (n < 2) {
            return ret[n];
        }
        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;

        for (int i = 2; i <= n;++i) {
            fibN = fibNMinusOne + fibNMinusTwo;

            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }

        return fibN;
    }

}
```





## 法二

- 保证不会再 int 类型溢出
- [为什么很多程序竞赛题目都要求答案对 1e9+7 取模？](https://www.zhihu.com/question/49374703)
- 

```java
class Solution {

    public int fib(int n) {
        int a=0, b=1,sum=0;
        // 当n>1时才会进入循环，所以for循环算的是n从2到n+1的值
        for(int i=2; i<=n+1; i++){
            sum=(a+b) % 1000000007;
            a=b;
            b=sum;
        }
        // 由于多算一次，所以返回的是a，不是b
        return a;
    }

}
```





## 法三

- 矩阵快速幂

```java
class Solution {
    public int fib(int n) {
        //矩阵快速幂
        if (n < 2) {
            return n;
        }
        //定义乘积底数
        int[][] base = {{1, 1}, {1, 0}};
        //定义幂次
        int power = n - 1;
        int[][] ans = calc(base, power);
        //按照公式，返回的是两行一列矩阵的第一个数
        return ans[0][0];
    }

    //定义函数,求底数为 base 幂次为 power 的结果
    public int[][] calc(int[][] base, int power) {
        //定义变量，存储计算结果，此次定义为单位阵
        int[][] res = {{1, 0}, {0, 1}};

        //可以一直对幂次进行整除
        while (power > 0) {
            //1.若为奇数，需多乘一次 base
            //2.若power除到1，乘积后得到res
            //此处使用位运算在于效率高
            if ((power & 1) == 1) {
                res = mul(res, base);
            }
            //不管幂次是奇还是偶，整除的结果是一样的如 5/2 和 4/2
            //此处使用位运算在于效率高
            power = power >> 1;
            base = mul(base, base);
        }
        return res;
    }

    //定义函数,求二维矩阵：两矩阵 a, b 的乘积
    public int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                //矩阵乘积对应关系，自己举例演算一遍便可找到规律
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}

```









# THE END