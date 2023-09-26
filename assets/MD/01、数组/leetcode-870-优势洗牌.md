# 目录

[toc]

# leetcode-870-优势洗牌

- 时间：2023-06-16

- 参考链接：
  - [田忌赛马背后的算法决策](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/tian-ji-sa-7baa4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/advantage-shuffle/
- 难度：中等



给定两个长度相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。

返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。



提示：

1 <= nums1.length <= 105
nums2.length == nums1.length
0 <= nums1[i], nums2[i] <= 109





# 2、题解

## 题目分析



## 解法一:  贪心算法（田忌赛马）

### 算法分析

**这就像田忌赛马的情景，`nums1` 就是田忌的马，`nums2` 就是齐王的马，数组中的元素就是马的战斗力，你就是孙膑，展示你真正的技术吧**。

**将齐王和田忌的马按照战斗力排序，然后按照排名一一对比。如果田忌的马能赢，那就比赛，如果赢不了，那就换个垫底的来送人头，保存实力**。

### 代码

```java
/**
 * <p>
 * 优势洗牌
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode870 {

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {1, 10, 4, 11};

        Solution01 solution01 = new Solution01();
        int[] advantageCount01 = solution01.advantageCount(nums1, nums2);
        Arrays.stream(advantageCount01).forEach(System.out::println);
    }

    /**
     * 解法一：双指针法，优先队列，（原理，就是田忌赛马）
     */
    private static class Solution01 {

        public int[] advantageCount(int[] nums1, int[] nums2) {
            int n = nums1.length;
            // 给 nums2 降序排序 的优先级队列
            PriorityQueue<int[]> maxpq = new PriorityQueue<>((int[] pair1, int[] pair2) -> pair2[1] - pair1[1]);
            for (int i = 0; i < n; i++) {
                maxpq.offer(new int[]{i, nums2[i]});
            }

            // nums1 升序排序
            Arrays.sort(nums1);

            // nums1[left] 是最小值，nums1[right] 是最大值
            int left = 0, right = n - 1;
            // nums1 排列的结果数组
            int[] res = new int[n];

            while (!maxpq.isEmpty()) {
                int[] pair = maxpq.poll();
                // maxval 是 nums2 中的最大值，i 是对应索引
                int i = pair[0], maxval = pair[1];
                if (maxval < nums1[right]) {
                    // 如果 nums1[right] 能胜过 maxval，那就自己上
                    res[i] = nums1[right];
                    right--;
                } else {
                    // 否则用最小值混一下，养精蓄锐
                    res[i] = nums1[left];
                    left++;
                }
            }

            return res;
        }

    }

}
```

输出：

```sh
2
11
7
15
```





### 复杂度分析

- 时间复杂度：O(n log n),其中 n 是数组 nums1 和 nums2 的长度，即为排序需要的时间。
- 空间复杂度：O(n),即为排序时存储下标需要的空间。





# THE END