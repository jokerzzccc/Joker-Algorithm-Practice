# 目录

[toc]

# leetcode-93-复原 IP 地址

- 时间：2023-05-13
- 参考链接：



# 1、题目

- https://leetcode.cn/problems/restore-ip-addresses/
- 难度：中等



**有效 IP 地址** 正好由四个整数（每个整数位于 `0` 到 `255` 之间组成，且不能含有前导 `0`），整数之间用 `'.'` 分隔。

+ 例如：`"0.1.2.201"` 和` "192.168.1.1"` 是 **有效** IP 地址，但是 `"0.011.255.245"`、`"192.168.1.312"` 和 `"192.168@1.1"` 是 **无效** IP 地址。

给定一个只包含数字的字符串 `s` ，用以表示一个 IP 地址，返回所有可能的**有效 IP 地址**，这些地址可以通过在 `s` 中插入 `'.'` 来形成。你 **不能** 重新排序或删除 `s` 中的任何数字。你可以按 **任何** 顺序返回答案。



**示例 1：**

```
输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
```

**示例 2：**

```
输入：s = "0000"
输出：["0.0.0.0"]
```

**示例 3：**

```
输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
```



**提示：**

+ `1 <= s.length <= 20`
+ `s` 仅由数字组成





# 2、题解

## 题目分析

剪枝条件：

- 一开始，字符串的长度小于 4 或者大于 12 ，一定不能拼凑出合法的 ip 地址（这一点可以一般化到中间结点的判断中，以产生剪枝行为）；
- 每一个结点可以选择截取的方法只有 3 种：截 1 位、截 2 位、截 3 位，因此每一个结点可以生长出的分支最多只有 3 条分支；
- 由于 ip 段最多就 4 个段，因此这棵三叉树最多 4 层，这个条件作为递归终止条件之一；
- 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
- 每一段再区间 [0,255] 之间；
- ip 地址必须有4段；
- 当剩余的字符数量比剩余的段 * 3还大时，就可以剪枝了；
- 





## 解法一

### 算法

- 设题目中给出的字符串为 *s*。我们用递归函数 *dfs*(*segId*,*segStart*) 表示我们正在从 *s*[*segStart*] 的位置开始，搜索 IP 地址中的第 *segId* 段，其中 *segId*∈{0,1,2,3}。由于 IP 地址的每一段必须是 [0,255] 中的整数，因此我们从 *segStart* 开始，从小到大依次枚举当前这一段 IP 地址的结束位置 *segEnd*。如果满足要求，就递归地进行下一段搜索，调用递归函数 *dfs*(*segId*+1,*segEnd*+1)。

- 特别地，由于 IP 地址的每一段不能有前导零，因此如果 *s*[*segStart*] 等于字符 0，那么 IP 地址的第 *segId* 段只能为 0，需要作为特殊情况进行考虑。

- 在搜索的过程中，如果我们已经得到了全部的 4 段 IP 地址（即 *segId*=4），并且遍历完了整个字符串（即 *segStart*=∣*s*∣，其中 ∣*s*∣ 表示字符串 *s* 的长度），那么就复原出了一种满足题目要求的 IP 地址，我们将其加入答案。在其它的时刻，如果**提前**遍历完了整个字符串，那么我们需要结束搜索，回溯到上一步。



### 代码-debug版



```java
/**
 * <p>
 * 复原 IP 地址
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/14
 */
public class leetcode93 {

    public static void main(String[] args) {
        String s = "25525511135";
        Solution solution = new Solution();
        List<String> ipAddresses = solution.restoreIpAddresses(s);
        System.out.println(ipAddresses);
    }

    static class Solution {

        /**
         * 结果：ip 地址
         */
        List<String> res = new ArrayList<>();
        /**
         * IP 地址的段数
         */
        static final int SEG_COUNT = 4;
        /**
         * 存放每一段
         */
        int[] segments = new int[SEG_COUNT];

        public List<String> restoreIpAddresses(String s) {
            if (s.length() < 4 || s.length() > 12) {
                return res;
            }
            int[] nums = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                nums[i] = s.charAt(i) - '0';
            }
            backTrack(nums, 0, 0);
            return res;
        }

        void backTrack(int[] nums, int segId, int segStart) {
            // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
            if (segId == SEG_COUNT) {
                if (segStart == nums.length) {
                    StringBuilder ipAddr = new StringBuilder();
                    for (int i = 0; i < SEG_COUNT; i++) {
                        ipAddr.append(segments[i]);
                        if (i != SEG_COUNT - 1) {
                            ipAddr.append(".");
                        }
                    }
                    res.add(ipAddr.toString());
                }
                return;
            }

            // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
            if (segStart == nums.length) {
                return;
            }
            // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
            if (nums[segStart] == 0) {
                segments[segId] = 0;
                backTrack(nums, segId + 1, segStart + 1);
            }

            int addr = 0;
            for (int segEnd = segStart; segEnd < nums.length; ++segEnd) {
                addr = addr * 10 + nums[segEnd];
                if (addr > 0 && addr <= 255) {
                    segments[segId] = addr;
                    // 当剩余的字符数量比剩余的段 * 3还大时，就可以剪枝了
                    if ((SEG_COUNT - segId - 1) * 3 < (nums.length - segEnd - 1)) {
                        continue;
                    }
                    backTrack(nums, segId + 1, segEnd + 1);
                } else {
                    break;
                }
            }
        }

    }

}
```



### **复杂度分析**

我们用 SEG_COUNT=4 表示 IP 地址的段数。

+ 时间复杂度：*O*(3SEG_COUNT×∣*s*∣)。由于 IP 地址的每一段的位数不会超过 3，因此在递归的每一层，我们最多只会深入到下一层的 3 种情况。由于 SEG_COUNT=4，对应着递归的最大层数，所以递归本身的时间复杂度为 *O*(3SEG_COUNT)。如果我们复原出了一种满足题目要求的 IP 地址，那么需要 *O*(∣*s*∣) 的时间将其加入答案数组中，因此总时间复杂度为 *O*(3SEG_COUNT×∣*s*∣)。
+ 空间复杂度：*O*(SEG_COUNT)，这里只计入除了用来存储答案数组以外的**额外空间复杂度**。递归使用的空间与递归的最大深度 SEG_COUNT 成正比。并且在上面的代码中，我们只额外使用了长度为 SEG_COUNT 的数组 *segments* 存储已经搜索过的 IP 地址，因此空间复杂度为 *O*(SEG_COUNT)。



















# THE END