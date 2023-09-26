# 目录

[toc]

# leetcode-316-去除重复字母

- 时间：2023-06-16

- 参考链接：
  - [啊这，一道数组去重的算法题把东哥整不会了…](https://mp.weixin.qq.com/s/Yq49ZBEW3DJx6nXk1fMusw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/remove-duplicate-letters/
- 难度：中等



给你一个字符串 `s` ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 **返回结果的字典序最小**（要求不能打乱其他字符的相对位置）。



**提示：**

+ `1 <= s.length <= 10^4`
+ `s` 由小写英文字母组成





# 2、题解

## 题目分析



## 解法一:  

### 算法分析

**字典序**，换成数字更好理解一点 a:1,b:2,c:3 那么：acdb => 1342 dcab => 4312
4312 > 1342 所以 dcab > acdb 也可以想象在英语词典里，acdb会比dcab出现得更早，所以acdb的字典序更小



题目的要求总结出来有三点：

要求一、**要去重**。

要求二、去重字符串中的字符顺序**不能打乱`s`中字符出现的相对顺序**。

要求三、在所有符合上一条要求的去重字符串中，**字典序最小**的作为最终结果。



### 代码

```java

/**
 * <p>
 * 去除重复字母
 * </p>
 *
 * @author admin
 * @date 2023/6/17
 */
public class leetcode316 {

    public static void main(String[] args) {
        String s = "bcabc";

        Solution01 solution01 = new Solution01();
        String s1 = solution01.removeDuplicateLetters(s);
        System.out.println(s1);

    }

    /**
     * 解法一：贪心 + 单调栈
     */
    private static class Solution01 {

        public String removeDuplicateLetters(String s) {

            boolean[] vis = new boolean[256];
            // 维护一个计数器记录字符串中字符的数量
            // 因为输入为 ASCII 字符，大小 256 够用了
            int[] count = new int[256];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i)]++;
            }

            // 存放去重的结果
            Stack<Character> stk = new Stack<>();

            for (char c : s.toCharArray()) {
                // 每遍历过一个字符，都将对应的计数减一
                count[c]--;
                // 如果字符 c 存在栈中，直接跳过
                if (vis[c]) {
                    continue;
                }

                // 插入之前，和之前的元素比较一下大小
                // 如果字典序比前面的小，pop 前面的元素
                while (!stk.isEmpty() && stk.peek() > c) {
                    // 若之后不存在栈顶元素了，则停止 pop
                    if (count[stk.peek()] == 0) {
                        break;
                    }
                    // 若之后还有，则可以 pop
                    vis[stk.pop()] = false;
                }
                // 若不存在，则插入栈顶并标记为存在
                stk.push(c);
                vis[c] = true;
            }

            StringBuilder stringBuilder = new StringBuilder();
            while (!stk.isEmpty()) {
                stringBuilder.append(stk.pop());
            }
            // 栈中元素插入顺序是反的，需要 reverse 一下
            return stringBuilder.reverse().toString();
        }

    }

}

```

输出：

```sh
abc

```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：



# THE END