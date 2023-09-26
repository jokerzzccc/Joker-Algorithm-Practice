# 目录

[toc]

# leetcode-823-带因子的二叉树

- 时间：2023-08-30
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/binary-trees-with-factors/description/
- 难度：中等

给出一个含有不重复整数元素的数组 `arr` ，每个整数 `arr[i]` 均大于 1。

用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。

满足条件的二叉树一共有多少个？答案可能很大，返回 **对** `109 + 7` **取余** 的结果。

 

**示例 1:**

```
输入: arr = [2, 4]
输出: 3
解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
```

**示例 2:**

```
输入: arr = [2, 4, 5, 10]
输出: 7
解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
```

 

**提示：**

+ `1 <= arr.length <= 1000`
+ `2 <= arr[i] <= 10^9`
+ `arr` 中的所有值 **互不相同**



# 2、题解

## 题目分析



## 解法一：动态规划 + 记忆化搜索

### 算法分析

参考链接：

- https://leetcode.cn/problems/binary-trees-with-factors/solutions/2416115/cong-ji-yi-hua-sou-suo-dao-di-tui-jiao-n-nbk6/



![image-20230830204727580](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230830204727580.png)

![image-20230830204742247](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230830204742247.png)

![image-20230830204753644](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230830204753644.png)

![image-20230830204833982](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230830204833982.png)



### 代码

```java

/**
 * <p>
 * 带因子的二叉树
 * </p>
 *
 * @author admin
 * @date 2023/8/30
 */
public class leetcode823 {

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 10};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numFactoredBinaryTrees(arr));

    }

    /**
     * 解法一：动态规划 + 记忆化搜索
     */
    private static class Solution01 {

        private final long MOD = (long) (Math.pow(10, 9) + 7);

        /**
         * 入参
         */
        private int[] arrIn;

        /**
         * 存储值对应的下标
         */
        Map<Integer, Integer> valueToIdx;

        /**
         * 备忘录
         */
        private long[] memo;

        public int numFactoredBinaryTrees(int[] arr) {
            this.arrIn = arr;
            Arrays.sort(arrIn);
            int n = arrIn.length;

            valueToIdx = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                valueToIdx.put(arrIn[i], i);
            }

            // 备忘录初始化，表示没有计算到的
            memo = new long[n];
            Arrays.fill(memo, -1);

            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += dfs(i);
            }

            return (int) (ans % MOD);
        }

        /**
         * dfs(index) 表示根节点值为 arr[index] 的二叉树的个数
         */
        private long dfs(int index) {
            if (memo[index] != -1) {
                return memo[index];
            }
            int val = arrIn[index];
            long res = 1;
            // val 的因子一定比 val 小，因为之前 arr 排过序
            for (int j = 0; j < index; j++) {
                int x = arrIn[j];
                // x 为 val 的因子，且另一个因子 val/x 必须在 arr 中
                if (val % x == 0 && valueToIdx.containsKey(val / x)) {
                    res += dfs(j) * dfs(valueToIdx.get(val / x));
                }
            }

            // 记忆化
            memo[index] = res;

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n^2)，其中 n 为 arr 的长度。由于每个状态只会计算一次，动态规划的时间复杂度 = **状态个数 × 单个状态的计算时间**。本题中状态个数等于 O(n)，单个状态的计算时间为 O(n) 的 `for` 循环，所以动态规划的时间复杂度为 O(n^2)。
- 空间复杂度：O(n)。











# THE END