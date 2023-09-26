# 目录

[toc]

# leetcode-165-比较版本号

- 时间：2023-07-03
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/compare-version-numbers/?company_slug=alibaba
- 难度：中等



给你两个版本号 `version1` 和 `version2` ，请你比较它们。

版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 **多位数字** 组成，可能包含 **前导零** 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。

比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 **忽略任何前导零后的整数值** 。也就是说，修订号 1 和修订号 001 **相等** 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。

返回规则如下：

- 如果 version1 > version2 返回 1，
- 如果 version1 < version2 返回 -1，
- 除此之外返回 0。



示例 1：

```sh
输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
```



提示：

- 1 <= version1.length, version2.length <= 500
- version1 和 version2 仅包含数字和 '.'
- version1 和 version2 都是 **有效版本号**
- version1 和 version2 的所有修订号都可以存储在 **32 位整数** 中





# 2、题解

## 题目分析



## 解法一: 字符串分割

### 算法分析





### 代码

```java


/**
 * <p>
 * 比较版本号
 * </p>
 *
 * @author admin
 * @date 2023/7/4
 */
public class leetcode165 {

    public static void main(String[] args) {
        String version1 = "1.0.1";
        String version2 = "1";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.compareVersion(version1, version2));
    }

    /**
     * 解法一：字符串分割
     */
    private static class Solution01 {

        public int compareVersion(String version1, String version2) {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");
            int maxLen = Math.max(v1.length, v2.length);
            int[] v1_int = new int[maxLen];
            int[] v2_int = new int[maxLen];
            for (int i = 0; i < maxLen; i++) {
                if (i < v1.length) {
                    v1_int[i] = Integer.parseInt(v1[i]);
                } else {
                    v1_int[i] = 0;
                }

                if (i < v2.length) {
                    v2_int[i] = Integer.parseInt(v2[i]);
                } else {
                    v2_int[i] = 0;
                }
            }

            int p = 0;
            int res = 0;
            while (p < maxLen) {
                if (v1_int[p] > v2_int[p]) {
                    res = 1;
                    break;
                } else if (v1_int[p] < v2_int[p]) {
                    res = -1;
                    break;
                }
                p++;
            }

            return res;

        }

    }

}

```





### 复杂度分析











# THE END