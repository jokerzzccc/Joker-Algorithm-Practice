# 目录

[toc]

# leetcode-46-全排列 Permutations

- 时间：2023-05-13
- 参考链接：



# 1、题目

- leetcode: https://leetcode.cn/problems/permutations/
- 难度：中等



给定一个不含重复数字的数组 `nums` ，返回其 *所有可能的全排列* 。你可以 **按任意顺序** 返回答案。



**示例 1：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**示例 2：**

```
输入：nums = [0,1]
输出：[[0,1],[1,0]]
```

**示例 3：**

```
输入：nums = [1]
输出：[[1]]
```



**提示：**

+ `1 <= nums.length <= 6`
+ `-10 <= nums[i] <= 10`
+ `nums` 中的所有整数 **互不相同**



# 2、题解

## 解法一：标记数组法

### 算法

- 我们用 `used` 数组标记已经在路径上的元素避免重复选择，然后收集所有叶子节点上的值，就是所有全排列的结果

属性：

- res:

### 代码

```java
class Solution {

    /**
     * 存放结果
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    /**
     * 记录回溯算法的递归路径
     */
    LinkedList<Integer> track = new LinkedList<>();
    /**
     * track 中的元素会被标记为 true
     */
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0){
            return res;
        }
        used = new boolean[nums.length];
        backtack(nums);
        return res;
    }

    void backtack(int[] nums) {
        if (nums.length == track.size()) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);

            backtack(nums);

            track.removeLast();
            used[i] = false;
        }
    }

}
```





## 解法二：无标记数组

### 算法

- 参考链接；
  - https://leetcode.cn/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/

- 不使用法一的标记数组 `used` ，减少空间复杂度。
- 将题目给定的 n 个数的数组 nums 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在回溯的时候只要动态维护这个数组即可。



### 代码-写法一

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int depth) {
        // 所有数都填完了
        if (depth == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, depth, i);
            // 继续递归填下一个数
            backtrack(n, output, res, depth + 1);
            // 撤销操作
            Collections.swap(output, depth, i);
        }
    }
}
```



### 代码-写法二

```java
class Solution {

    /**
     * 存放结果
     */
    LinkedList<List<Integer>> res = new LinkedList<>();


    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return res;
        }

        List<Integer> track = new ArrayList<>();
        for (int num : nums) {
            track.add(num);
        }
        int depth = 0;
        backtack(track, depth, nums.length);
        return res;
    }

    void backtack(List<Integer> track, int depth, int length) {
        if (length == depth) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = depth; i < length; ++i) {
            Collections.swap(track, depth, i);

            backtack(track, depth + 1, length);

            Collections.swap(track, depth, i);
        }
    }

}
```





# THE END