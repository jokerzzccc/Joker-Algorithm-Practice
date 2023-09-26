# 目录

[toc]

# leetcode-47-全排列 II

- 时间：2023-05-17
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/permutations-ii/
- 难度：中等



给定一个可包含重复数字的序列 `nums` ，***按任意顺序*** 返回所有不重复的全排列。



**示例 1：**

```
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
```

**示例 2：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```



**提示：**

+ `1 <= nums.length <= 8`
+ `-10 <= nums[i] <= 10`

Related Topics

数组

回溯

# 2、题解

## 题目分析



## 解法一

### 代码-无注释

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> track = new LinkedList<>();
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0){
            return res;
        }
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);

        used = new boolean[nums.length];

        backtrack(nums);
        return res;
    }

    void backtrack(int[] nums){
        if (track.size() == nums.length){
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if (used[i] == true){
                continue;
            }
            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }

            track.addLast(nums[i]);
            used[i] = true;

            backtrack(nums);

            track.removeLast();
            used[i] = false;
        }
    }
}
```



### 代码 debug 版

```java
/**
 * <p>
 * 全排列 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/17
 */
public class leetcode47 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res;

        Solution solution = new Solution();
        res = solution.permuteUnique(nums);

        System.out.println(res);
    }

    private static class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> track = new LinkedList<>();
        private boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums.length == 0) {
                return res;
            }
            // 先排序，让相同的元素靠在一起
            Arrays.sort(nums);

            used = new boolean[nums.length];

            backtrack(nums);
            return res;
        }

        void backtrack(int[] nums) {
            if (track.size() == nums.length) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i] == true) {
                    continue;
                }
                // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                track.addLast(nums[i]);
                used[i] = true;
                System.out.println("递归之前 track: => " + track + " ; 下标：i = " + i);

                backtrack(nums);

                track.removeLast();
                used[i] = false;
                System.out.println("递归之后 track: => " + track + "后--------------");
            }
        }
    }

}
```

结果：

```sh
递归之前 track: => [1] ; 下标：i = 0
递归之前 track: => [1, 2] ; 下标：i = 1
递归之前 track: => [1, 2, 2] ; 下标：i = 2
递归之后 track: => [1, 2]后--------------
递归之后 track: => [1]后--------------
递归之后 track: => []后--------------
递归之前 track: => [2] ; 下标：i = 1
递归之前 track: => [2, 1] ; 下标：i = 0
递归之前 track: => [2, 1, 2] ; 下标：i = 2
递归之后 track: => [2, 1]后--------------
递归之后 track: => [2]后--------------
递归之前 track: => [2, 2] ; 下标：i = 2
递归之前 track: => [2, 2, 1] ; 下标：i = 0
递归之后 track: => [2, 2]后--------------
递归之后 track: => [2]后--------------
递归之后 track: => []后--------------
[[1, 2, 2], [2, 1, 2], [2, 2, 1]]

Process finished with exit code 0
```





# THE END