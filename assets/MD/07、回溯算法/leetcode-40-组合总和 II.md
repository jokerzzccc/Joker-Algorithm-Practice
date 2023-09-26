# 目录

[toc]

# leetcode-40-组合总和 II

- 时间：2023-05-18
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/combination-sum-ii/
- 难度：中等



给定一个候选人编号的集合 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用 **一次** 。

**注意：**解集不能包含重复的组合。



**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
```



**提示:**

+ `1 <= candidates.length <= 100`
+ `1 <= candidates[i] <= 50`
+ `1 <= target <= 30`

Related Topics

数组

回溯



# 2、题解

## 题目分析

- 属于，组合（元素可重不可复选类型）

## 解法一

- 参考：https://leetcode.cn/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/

### 代码-无注释

```java
class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    private LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        int start = 0;
        backtrack(candidates, start, target);
        return res;
    }

    void backtrack(int[] candidates, int start, int target) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(track));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0 ) {
                break;
            }
            if (i > start && candidates[i] == candidates[i - 1]){
                continue;
            }
            track.addLast(candidates[i]);

            backtrack(candidates, i + 1, target - candidates[i]);

            track.removeLast();
        }
    }

}
```





### 代码-debug 版



```java
/**
 * <p>
 * 组合总和 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/18
 */
public class leetcode40 {

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> res;

        Solution solution = new Solution();
        res = solution.combinationSum2(candidates, target);

        System.out.println(res);
    }

    private static class Solution {

        /**
         * 结果集
         */
        private List<List<Integer>> res = new ArrayList<>();

        /**
         * 路径
         */
        private LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }

            // 关键步骤:排序
            Arrays.sort(candidates);
            int start = 0;
            backtrack(candidates, start, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
                if (target - candidates[i] < 0) {
                    break;
                }
                // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                track.addLast(candidates[i]);
                System.out.println("递归之前 => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i + 1, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 => " + track + "，剩余 = " + (target - candidates[i]));
            }
        }

    }

}
```

运行结果：

```sh
递归之前 => [1]，剩余 = 7
递归之前 => [1, 1]，剩余 = 6
递归之前 => [1, 1, 2]，剩余 = 4
递归之后 => [1, 1]，剩余 = 4
递归之前 => [1, 1, 5]，剩余 = 1
递归之后 => [1, 1]，剩余 = 1
递归之前 => [1, 1, 6]，剩余 = 0
递归之后 => [1, 1]，剩余 = 0
递归之后 => [1]，剩余 = 6
递归之前 => [1, 2]，剩余 = 5
递归之前 => [1, 2, 5]，剩余 = 0
递归之后 => [1, 2]，剩余 = 0
递归之后 => [1]，剩余 = 5
递归之前 => [1, 5]，剩余 = 2
递归之后 => [1]，剩余 = 2
递归之前 => [1, 6]，剩余 = 1
递归之后 => [1]，剩余 = 1
递归之前 => [1, 7]，剩余 = 0
递归之后 => [1]，剩余 = 0
递归之后 => []，剩余 = 7
递归之前 => [2]，剩余 = 6
递归之前 => [2, 5]，剩余 = 1
递归之后 => [2]，剩余 = 1
递归之前 => [2, 6]，剩余 = 0
递归之后 => [2]，剩余 = 0
递归之后 => []，剩余 = 6
递归之前 => [5]，剩余 = 3
递归之后 => []，剩余 = 3
递归之前 => [6]，剩余 = 2
递归之后 => []，剩余 = 2
递归之前 => [7]，剩余 = 1
递归之后 => []，剩余 = 1
[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
```



解释语句: if cur > begin and candidates[cur-1] == candidates[cur] 是如何避免重复的。

```sh
这个避免重复当思想是在是太重要了。
这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
                  1
                 / \
                2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
               /     \
              5       5
                例2
                  1
                 /
                2      这种情况确是允许的
               /
              2  
                
为何会有这种神奇的效果呢？
首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。 
因为当第二个2出现的时候，他就和前一个2相同了。
                
那么如何保留例2呢？
那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
例2的两个2是处在不同层级上的。
在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
```



# THE END