# 目录

[toc]

# leetcode-496-下一个更大元素 I

- 时间：2023-07-01
- 参考链接：
  - [单调栈结构解决三道算法题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/dan-diao-z-1bebe/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/next-greater-element-i/
- 难度：简单

nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。

给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。

对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。

返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。



提示：

- 1 <= nums1.length <= nums2.length <= 1000
- 0 <= nums1[i], nums2[i] <= 10^4
- nums1和nums2中所有整数 **互不相同**
- nums1 中的所有整数同样出现在 nums2 中





# 2、题解

## 题目分析

### 单调栈模板

现在给你出这么一道题：输入一个数组 `nums`，请你返回一个等长的结果数组，结果数组中对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1。函数签名如下：

```java
int[] nextGreaterElement(int[] nums);
```

比如说，输入一个数组 `nums = [2,1,2,4,3]`，你返回数组 `[4,2,4,-1,-1]`。因为第一个 2 后面比 2 大的数是 4; 1 后面比 1 大的数是 2；第二个 2 后面比 2 大的数是 4; 4 后面没有比 4 大的数，填 -1；3 后面没有比 3 大的数，填 -1。

这道题的暴力解法很好想到，就是对每个元素后面都进行扫描，找到第一个更大的元素就行了。但是暴力解法的时间复杂度是 `O(n^2)`。

这个问题可以这样**抽象思考**：把数组的元素想象成并列站立的人，元素大小想象成人的身高。这些人面对你站成一列，如何求元素「2」的下一个更大元素呢？很简单，如果能够看到元素「2」，那么他后面可见的第一个人就是「2」的下一个更大元素，因为比「2」小的元素身高不够，都被「2」挡住了，第一个露出来的就是答案。

![image-20230701224503304](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230701224503304.png)

这个情景很好理解吧？带着这个抽象的情景，先来看下代码。

```java
int[] nextGreaterElement(int[] nums) {
    int n = nums.length;
    // 存放答案的数组
    int[] res = new int[n];
    Stack<Integer> s = new Stack<>(); 
    // 倒着往栈里放
    for (int i = n - 1; i >= 0; i--) {
        // 判定个子高矮
        while (!s.isEmpty() && s.peek() <= nums[i]) {
            // 矮个起开，反正也被挡着了。。。
            s.pop();
        }
        // nums[i] 身后的更大元素
        res[i] = s.isEmpty() ? -1 : s.peek();
        s.push(nums[i]);
    }
    return res;
}

```

这就是单调队列解决问题的模板。for 循环要从后往前扫描元素，因为我们借助的是栈的结构，倒着入栈，其实是正着出栈。while 循环是把两个「个子高」元素之间的元素排除，因为他们的存在没有意义，前面挡着个「更高」的元素，所以他们不可能被作为后续进来的元素的下一个更大元素了。

这个算法的时间复杂度不是那么直观，如果你看到 for 循环嵌套 while 循环，可能认为这个算法的复杂度也是 `O(n^2)`，但是实际上这个算法的复杂度只有 `O(n)`。

分析它的时间复杂度，要从整体来看：总共有 `n` 个元素，每个元素都被 `push` 入栈了一次，而最多会被 `pop` 一次，没有任何冗余操作。所以总的计算规模是和元素规模 `n` 成正比的，也**就是 `O(n)` 的复杂度**。

## 解法一：单调栈

### 算法分析

其实和把我们刚才的代码改一改就可以解决这道题了，因为题目说 `nums1` 是 `nums2` 的子集，那么我们先把 `nums2` 中每个元素的下一个更大元素算出来存到一个映射里，然后再让 `nums1` 中的元素去查表即可：



### 代码

```java

/**
 * <p>
 * 下一个更大元素 I
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode496 {

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};

        Solution01 solution01 = new Solution01();
        int[] greaterElement01 = solution01.nextGreaterElement(nums1, nums2);
        Arrays.stream(greaterElement01).forEach(System.out::println);

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 记录 nums2 中每个元素的下一个更大元素
            int[] greater = nextGreaterElement(nums2);
            // 转化成映射：元素 x -> x 的下一个最大元素
            HashMap<Integer, Integer> greaterMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums2.length; i++) {
                greaterMap.put(nums2[i], greater[i]);
            }
            // nums1 是 nums2 的子集，所以根据 greaterMap 可以得到结果
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i] = greaterMap.getOrDefault(nums1[i], -1);
            }
            return res;
        }

        /**
         * 返回 nums 所有的下一个最大元素的数组 res
         */
        public int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                    stack.pop();
                }

                res[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(nums[i]);
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(n)









# THE END