# 目录

[toc]

# offer-38-字符串的排列

- 时间：2023-08-22
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
- 难度：中等

输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

**示例:**

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

 

**限制：**

```
1 <= s 的长度 <= 8
```





**函数签名：**

```java
class Solution {
    public String[] permutation(String s) {

    }
}
```



# 2、题解

## 题目分析



## 解法一：回溯

### 算法分析

- 参考链接：
  - https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/solutions/178988/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/



递归解析：

1. 终止条件： 当 x = len(c) - 1 时，代表所有位已固定（最后一位只有 111 种情况），则将当前组合 c 转化为字符串并加入 res ，并返回；
2. 递推参数： 当前固定位 x ；
3. 递推工作： 初始化一个 Set ，用于排除重复的字符；将第 x 位字符与 i ∈\in∈ [x, len(c)] 字符分别交换，并进入下层递归；
   1. 剪枝： 若 c[i] 在 Set 中，代表其是重复字符，因此 “剪枝” ；
   2. 将 c[i] 加入 Set ，以便之后遇到重复字符时剪枝；
   3. 固定字符： 将字符 c[i] 和 c[x] 交换，即固定 c[i] 为当前位字符；
   4. 开启下层递归： 调用 dfs(x + 1) ，即开始固定第 x + 1 个字符；
   5. 还原交换： 将字符 c[i] 和 c[x] 交换（还原之前的交换）；



### 代码

```java


/**
 * <p>
 * 字符串的排列
 * </p>
 *
 * @author admin
 * @date 2023/8/22
 */
public class offer38 {

    public static void main(String[] args) {
        String s = "abc";

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.permutation(s)));

    }

    /**
     * 解法一：回溯 + 剪枝
     */
    private static class Solution01 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        /**
         * 入参字符串
         */
        char[] chars;

        public String[] permutation(String s) {
            chars = s.toCharArray();
            backtrack(0);
            return res.toArray(new String[0]);
        }

        private void backtrack(int index) {
            // base case ：终止条件，字符串递归完的时候
            if (index == chars.length - 1) {
                res.add(String.valueOf(chars));
                return;
            }

            // 用于排除重复的字符
            Set<Character> set = new HashSet<>();
            // 往后递归
            for (int j = index; j < chars.length; j++) {
                // 剪枝
                // 固定相同的元素在排列中的相对位置
                if (set.contains(chars[j])) {
                    continue;
                }
                set.add(chars[j]);

                // 做选择, 即固定 chars[j] 为当前位字符
                swap(j, index);
                // 回溯, 即开始固定第 index + 1 个字符
                backtrack(index + 1);
                // 撤销选择
                swap(j, index);
            }
        }

        private void swap(int a, int b) {
            char tmp = chars[a];
            chars[a] = chars[b];
            chars[b] = tmp;
        }

    }

}

```





### 复杂度分析

![image-20230822232019608](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230822232019608.png)









# THE END