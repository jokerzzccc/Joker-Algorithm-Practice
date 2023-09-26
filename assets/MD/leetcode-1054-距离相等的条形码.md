# 目录

[toc]

# leetcode-1054-距离相等的条形码

- 时间：2023-08-24
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/distant-barcodes/
- 难度：中等

在一个仓库里，有一排条形码，其中第 `i` 个条形码为 `barcodes[i]`。

请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。

 

**示例 1：**

```
输入：barcodes = [1,1,1,2,2,2]
输出：[2,1,2,1,2,1]
```

**示例 2：**

```
输入：barcodes = [1,1,1,1,2,2,3,3]
输出：[1,3,1,3,2,1,2,1]
```

 

**提示：**

+ `1 <= barcodes.length <= 10000`
+ `1 <= barcodes[i] <= 10000`



# 2、题解

## 题目分析

利用好，题目中**肯定有解**的信息



## 解法一：计数 + 排序

### 算法分析

![image-20230824204942363](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230824204942363.png)



### 代码

```java


/**
 * <p>
 * 距离相等的条形码
 * </p>
 *
 * @author admin
 * @date 2023/8/24
 */
public class leetcode1054 {

    public static void main(String[] args) {
        int[] barcodes = {1, 1, 1, 1, 2, 2, 3, 3};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.rearrangeBarcodes(barcodes)));

    }

    /**
     * 解法一：哈希表 + 排序
     */
    private static class Solution01 {

        public int[] rearrangeBarcodes(int[] barcodes) {
            int n = barcodes.length;
            Integer[] tmp = new Integer[n];
            int max = 0;
            for (int i = 0; i < n; i++) {
                tmp[i] = barcodes[i];
                max = Math.max(max, tmp[i]);
            }

            // 统计每个数字出现的次数
            int[] counts = new int[max + 1];
            for (int barcode : barcodes) {
                counts[barcode]++;
            }

            // 将 barcodes 中的数按照它们在 counts 中出现的次数从大到小排序，
            // 如果出现次数相同，那么就按照数的大小从小到大排序（确保相同的数相邻）
            Arrays.sort(tmp, (a, b) -> counts[a] == counts[b] ? a - b : counts[b] - counts[a]);

            int[] ans = new int[n];
            // 将元素依次填入答案数组的 0,2,4,⋯ 等偶数下标位置，
            // 然后将剩余元素依次填入答案数组的 1,3,5,⋯ 等奇数下标位置即可
            for (int k = 0, j = 0; k < 2; k++) {
                for (int i = k; i < n; i += 2) {
                    ans[i] = tmp[j++];
                }
            }

            return ans;
        }

    }

}

```





### 复杂度分析

- 时间复杂度 O(n×log⁡n)，

- 空间复杂度 O(M)。其中 nnn 和 MMM 分别是数组 barcodes 的长度以及数组 barcodes 中的最大值。







## 解法二：大顶堆

### 算法分析

题目要求重新排列这些条形码，使其中任意两个相邻的条形码不能相等，可以返回任何满足该要求的答案，并且此题保证存在答案。我们首先想到的思路就是，找到剩余数量最多的元素，尽可能优先排列它。

我们首先统计 barcodes 每个元素的个数，然后遍历这个频数表，把每个元素的 (剩余数量， 元素值) 二元数组，依次插入最大堆。这样操作后，堆顶的元素就是剩余数量最多的元素。

然后我们每次从堆顶拿出一个剩余最多的元素，放入排列中，再更新剩余数量，重新放入最大堆中。如果这个元素和排列结果中的最后一个元素相同，那么我们就需要再从最大堆中取出第二多的元素，放入排列中，之后再把这两个元素放回最大堆中。

依次重复上面的操作，直到我们把所有元素都重新排列。





### 代码

```java


/**
 * <p>
 * 距离相等的条形码
 * </p>
 *
 * @author admin
 * @date 2023/8/24
 */
public class leetcode1054 {

    public static void main(String[] args) {
        int[] barcodes = {1, 1, 1, 1, 2, 2, 3, 3};

        Solution02 solution02 = new Solution02();
        System.out.println(Arrays.toString(solution02.rearrangeBarcodes(barcodes)));

    }

    /**
     * 解法二：大顶堆
     */
    private static class Solution02 {

        public int[] rearrangeBarcodes(int[] barcodes) {
            // 统计数字出现的次数
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int barcode : barcodes) {
                countMap.put(barcode, countMap.getOrDefault(barcode, 0) + 1);
            }

            // 堆顶的元素就是剩余数量最多的元素
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            // 把每个元素的 (剩余数量， 元素值) 二元数组，依次插入最大堆
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                queue.offer(new int[]{entry.getValue(), entry.getKey()});
            }

            int n = barcodes.length;
            int[] res = new int[n];
            // 每次从堆顶拿出一个剩余最多的元素，放入排列中，再更新剩余数量，重新放入最大堆中。
            // 如果这个元素和排列结果中的最后一个元素相同，
            // 那么我们就需要再从最大堆中取出第二多的元素，放入排列中，之后再把这两个元素放回最大堆中。
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int numCount1 = node[0], num1 = node[1];
                if (i == 0 || res[i - 1] != num1) {
                    res[i] = num1;
                    if (numCount1 > 1) {
                        queue.offer(new int[]{numCount1 - 1, num1});
                    }
                } else {
                    int[] node2 = queue.poll();
                    int numCount2 = node2[0], num2 = node2[1];
                    res[i] = num2;
                    if (numCount2 > 1) {
                        queue.offer(new int[]{numCount2 - 1, num2});
                    }
                    queue.offer(new int[]{numCount1, num1});
                }
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(nlog⁡n)，其中 n 是 barcodes 长度。

- 空间复杂度：O(n)，其中 n 是 barcodes 长度。









# THE END