# 目录

[toc]

# leetcode-347-前 K 个高频元素

- 时间：2023-07-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/top-k-frequent-elements/
- 难度：中等

给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 **任意顺序** 返回答案。

 

示例 1:

```sh
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```



提示：

- 1 <= nums.length <= 10^5
- k 的取值范围是 [1, 数组中不相同的元素的个数]
- 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的





# 2、题解

## 题目分析



## 解法一: 优先队列（堆排序）

### 算法分析





### 代码

```java

/**
 * <p>
 * 前 K 个高频元素
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode347 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        Solution01 solution01 = new Solution01();
        int[] topKFrequent01 = solution01.topKFrequent(nums, k);
        System.out.println(Arrays.toString(topKFrequent01));
    }

    /**
     * 解法一：优先队列（堆排序）
     */
    private static class Solution01 {

        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                frequencies.put(nums[i], frequencies.getOrDefault(nums[i], 0) + 1);
            }

            // 创建一个按出现频率降序排序的优先队列
            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));

            for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();

                if (queue.size() == k) {
                    // 如果，当前出现频率 > 堆顶，则用当前元素替换掉堆顶
                    if (queue.peek()[1] < count) {
                        queue.poll();
                        queue.offer(new int[]{num, count});
                    }
                } else {
                    queue.offer(new int[]{num, count});
                }
            }

            // 得到结果
            int[] ret = new int[k];
            for (int i = 0; i < k; ++i) {
                ret[i] = queue.poll()[0];
            }

            return ret;
        }

    }

}

```





### 复杂度分析

![image-20230708151705368](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230708151705368.png)









# THE END