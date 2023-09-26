# 目录

[toc]

# leetcode-1419-数青蛙

- 时间：2023-07-09
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
- 难度：中等

给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。

请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。

要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。

![image-20230709153545700](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230709153545700.png)

**提示：**

+ `1 <= croakOfFrogs.length <= 10^5`
+ 字符串中的字符只有 `'c'`, `'r'`, `'o'`, `'a'` 或者 `'k'`



# 2、题解

## 题目分析



## 解法一: 计数

### 算法分析

![image-20230709155033478](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230709155033478.png)



### 代码

```java

/**
 * <p>
 * 数青蛙
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode1419 {

    public static void main(String[] args) {
        String croakOfFrogs = "croakcroak";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minNumberOfFrogs(croakOfFrogs));
    }

    /**
     * 解法一：计数
     */
    private static class Solution01 {

        private final Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};

        public int minNumberOfFrogs(String croakOfFrogs) {
            int strLen = croakOfFrogs.length();
            if (strLen % 5 != 0) {
                return -1;
            }

            // 最小需要的青蛙数量
            int minFrog = 0;
            // 表示现在正在发出蛙鸣声的青蛙数目
            int frogCroakingNum = 0;
            // cnt[i] 表示已经发出一次有效蛙鸣字符 i 的青蛙个数
            int[] cnt = new int[4];

            for (int i = 0; i < strLen; i++) {
                char c = croakOfFrogs.charAt(i);
                int t = map.get(c);

                if (t == 0) {
                    cnt[t]++;
                    frogCroakingNum++;
                    // 更新答案
                    if (frogCroakingNum > minFrog) {
                        minFrog = frogCroakingNum;
                    }
                } else {
                    if (cnt[t - 1] == 0) {
                        // 因为必须有序发出蛙鸣，如果前一个都没有，说明不符合条件
                        return -1;
                    }
                    cnt[t - 1]--;

                    if (t == 4) {
                        // 已经发出完整蛙鸣了
                        frogCroakingNum--;
                    } else {
                        cnt[t]++;
                    }
                }

            }

            // 还有未蛙鸣完整的，表示，字符串不符合条件
            if (frogCroakingNum > 0) {
                return -1;
            }

            return minFrog;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n),其中 n 为字符串 croakOfFrogs 的长度。
- 空间复杂度：O(1),仅使用常量空间。









# THE END