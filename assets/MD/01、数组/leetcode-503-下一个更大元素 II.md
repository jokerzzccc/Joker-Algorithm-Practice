# 目录

[toc]

# leetcode-503-下一个更大元素 II

- 时间：2023-07-01
- 参考链接：
  - [单调栈结构解决三道算法题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/dan-diao-z-1bebe/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/next-greater-element-ii/
- 难度：中等



给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 **下一个更大元素** 。

数字 x 的 **下一个更大的元素** 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。



**提示:**

+ `1 <= nums.length <= 10^4`
+ `-10^9 <= nums[i] <= 10^9`





# 2、题解

## 题目分析

- 类比 leetcode-496



我们一般是通过 % 运算符求模（余数），来模拟环形特效：

```java
int[] arr = {1,2,3,4,5};
int n = arr.length, index = 0;
while (true) {
    // 在环形数组中转圈
    print(arr[index % n]);
    index++;
}
```

这个问题肯定还是要用单调栈的解题模板，但难点在于，比如输入是 `[2,1,2,4,3]`，对于最后一个元素 3，如何找到元素 4 作为下一个更大元素。

**对于这种需求，常用套路就是将数组长度翻倍**：

![image-20230701232828523](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230701232828523.png)

这样，元素 3 就可以找到元素 4 作为下一个更大元素了，而且其他的元素都可以被正确地计算。

有了思路，最简单的实现方式当然可以把这个双倍长度的数组构造出来，然后套用算法模板。但是，**我们可以不用构造新数组，而是利用循环数组的技巧来模拟数组长度翻倍的效果**。



## 解法一：单调栈

### 算法分析





### 代码

```java


/**
 * <p>
 * 下一个更大元素 II
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode503 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3};

        Solution01 solution01 = new Solution01();
        int[] greaterElements01 = solution01.nextGreaterElements(nums);
        System.out.println(Arrays.toString(greaterElements01));

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();
            // 数组长度加倍模拟环形数组
            for (int i = 2 * n - 1; i >= 0; i--) {
                // 索引 i 要求模，其他的和模板一样
                while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                    stack.pop();
                }
                res[i % n] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(nums[i % n]);
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