# 目录

[toc]

# leetcode-215-数组中的第K个最大元素

- 时间：2023-06-22
- 参考链接：
  - [快速排序的正确理解方式及运用](https://mp.weixin.qq.com/s/8ZTMhvHJK_He48PpSt_AmQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/kth-largest-element-in-an-array/
- 难度：中等

给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。



**提示：**

+ `1 <= k <= nums.length <= 10^5`
+ `-10^4 <= nums[i] <= 10^4`





# 2、题解

## 题目分析



## 解法一: 优先队列(小顶堆)

### 算法分析



### 代码

```java
/**
 * <p>
 * 数组中的第K个最大元素
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode215 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findKthLargest(nums, k));

    }

    /**
     * 解法一： 二叉堆（优先队列）
     */
    private static class Solution01 {

        public int findKthLargest(int[] nums, int k) {

            // 小顶堆，堆顶是最小元素
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int e : nums) {
                // 每个元素都要过一遍二叉堆
                pq.offer(e);
                // 堆中元素多于 k 个时，删除堆顶元素
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            // pq 中剩下的是 nums 中 k 个最大元素，
            // 堆顶是最小的那个，即第 k 个最大元素
            return pq.peek();
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(nlogk)$
- 空间复杂度：$O(k)$



## 解法二: 快速选择算法(快排变体)

### 算法分析

**快速选择算法是快速排序的变体，效率更高**，面试中如果能够写出快速选择算法，肯定是加分项。

首先，题目问「第 `k` 个最大的元素」，相当于数组升序排序后「排名第 `n - k` 的元素」，为了方便表述，后文另 `k' = n - k`。

如何知道「排名第 `k'` 的元素」呢？其实在快速排序算法 `partition` 函数执行的过程中就可以略见一二。

我们刚说了，`partition` 函数会将 `nums[p]` 排到正确的位置，使得 `nums[lo..p-1] < nums[p] < nums[p+1..hi]`：

这时候，虽然还没有把整个数组排好序，但我们已经让 `nums[p]` 左边的元素都比 `nums[p]` 小了，也就知道 `nums[p]` 的排名了。

**那么我们可以把 `p` 和 `k'` 进行比较，如果 `p < k'` 说明第 `k'` 大的元素在 `nums[p+1..hi]` 中，如果 `p > k'` 说明第 `k'` 大的元素在 `nums[lo..p-1]` 中**。

进一步，去 `nums[p+1..hi]` 或者 `nums[lo..p-1]` 这两个子数组中执行 `partition` 函数，就可以进一步缩小排在第 `k'` 的元素的范围，最终找到目标元素。



### 代码

```java


/**
 * <p>
 * 数组中的第K个最大元素
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode215 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.findKthLargest(nums, k));

    }



    /**
     * 解法二：快速选择算法(快速排序的变体),更优
     */
    private static class Solution02 {

        public int findKthLargest(int[] nums, int k) {
            // 首先随机打乱数组
            shuffle(nums);
            int low = 0, high = nums.length - 1;
            // 转化成「排名第 k 的元素」
            // 现在 k 表示 第 k 大的元素的下标
            k = nums.length - k;
            while (low <= high) {
                int pivot = partition(nums, low, high);
                if (pivot < k) {
                    // 第 k 大的元素在 nums[p+1..hi] 中
                    low = pivot + 1;
                } else if (pivot > k) {
                    // 第 k 大的元素在 nums[lo..p-1] 中
                    high = pivot - 1;
                } else {
                    // 找到第 k 大的元素
                    return nums[pivot];
                }
            }

            return -1;
        }

        /**
         * 对 nums[low..high] 进行切分, 使得 nums[low..pivot-1] <= nums[pivot] < nums[pivot+1..high]
         *
         * @return pivot 每次排序确定元素位置的下标
         */
        private int partition(int[] nums, int low, int high) {
            int pivot = nums[low];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [low, i) <= pivot；(j, high] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = low + 1, j = high;
            // 当 i > j 时结束循环，以保证区间 [low, high] 都被覆盖
            while (i <= j) {
                while (i < high && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > low && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [low, i) <= pivot && (j, high] > pivot

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, low, j);
            return j;
        }

        /**
         * 洗牌算法，将输入的数组随机打乱
         * 防止极端情况（原数组基本有序，导致最后成链表装），导致，时间复杂度过高
         */
        private void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }

        /**
         * 原地交换两个元素
         */
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：$O(N)$
- 空间复杂度：$O(logn)$



# THE END