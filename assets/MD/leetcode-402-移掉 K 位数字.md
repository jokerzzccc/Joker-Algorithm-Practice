# 目录

[toc]

# leetcode-402-移掉 K 位数字

- 时间：2023-07-25
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/remove-k-digits/
- 难度：中等

给你一个以字符串表示的非负整数 `num` 和一个整数 `k` ，移除这个数中的 `k` 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。

 

**示例 1 ：**

```
输入：num = "1432219", k = 3
输出："1219"
解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
```

**示例 2 ：**

```
输入：num = "10200", k = 1
输出："200"
解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
```

**示例 3 ：**

```
输入：num = "10", k = 2
输出："0"
解释：从原数字移除所有的数字，剩余为空就是 0 。
```

 

**提示：**

+ `1 <= k <= num.length <= 10^5`
+ `num` 仅由若干位数字（0 - 9）组成
+ 除了 **0** 本身之外，`num` 不含任何前导零



# 2、题解

## 题目分析



## 解法一：贪心 + 单调栈

### 算法分析

基本的对比规则：

- 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于A=1axx,B=1bxxx,如果a>b则A>B。

可以得出「删除一个数字」的**贪心策略**：

![image-20230725203243350](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230725203243350.png)

![image-20230725203313724](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230725203313724.png)



### 代码

```java


/**
 * <p>
 * 移掉 K 位数字
 * </p>
 *
 * @author admin
 * @date 2023/7/25
 */
public class leetcode402 {

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.removeKdigits(num, k));

    }

    /**
     * 解法一：贪心 + 单调栈
     */
    private static class Solution01 {

        public String removeKdigits(String num, int k) {
            int len = num.length();
            if (len <= k) {
                return "0";
            }

            // 栈中的元素代表截止到当前位置，删除不超过 k 次个数字后，所能得到的最小整数
            // 在使用 k 个删除次数之前，栈中的序列从栈底到栈顶单调不降
            Deque<Character> deque = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                char digit = num.charAt(i);
                // 贪心策略：找到降序的，然后删掉
                while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                    deque.pollLast();
                    k--;
                }
                deque.offerLast(digit);
            }

            // 如果我们删除了 m 个数字且 m<k，这种情况下我们需要从序列尾部删除额外的 k−m 个数字。
            for (int i = 0; i < k; ++i) {
                deque.pollLast();
            }

            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            while (!deque.isEmpty()) {
                char digit = deque.pollFirst();
                // 删除前导0
                if (leadingZero && digit == '0') {
                    continue;
                }
                leadingZero = false;
                ret.append(digit);
            }

            return ret.length() == 0 ? "0" : ret.toString();
        }

    }

}

```





### 复杂度分析

![image-20230725203343381](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230725203343381.png)









# THE END