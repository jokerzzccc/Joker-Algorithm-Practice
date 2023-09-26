# 目录

[toc]

# leetcode-39-组合总和

- 时间：2023-05-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/combination-sum/
- 难度：中等



给你一个 **无重复元素** 的整数数组 `candidates` 和一个目标整数 `target` ，找出 `candidates` 中可以使数字和为目标数 `target` 的 所有 **不同组合** ，并以列表形式返回。你可以按 **任意顺序** 返回这些组合。

`candidates` 中的 **同一个** 数字可以 **无限制重复被选取** 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 `target` 的不同组合数少于 `150` 个。



**示例 1：**

```
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
```

**示例 2：**

```
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
```

**示例 3：**

```
输入: candidates = [2], target = 1
输出: []
```



**提示：**

+ `1 <= candidates.length <= 30`
+ `2 <= candidates[i] <= 40`
+ `candidates` 的所有元素 **互不相同**
+ `1 <= target <= 40`

Related Topics

数组

回溯



# 2、题解

## 题目分析

**思路分析**：根据示例 1：输入: `candidates = [2, 3, 6, 7]`，`target = 7`。

+ 候选数组里有 `2`，如果找到了组合总和为 `7 - 2 = 5` 的所有组合，再在之前加上 `2` ，就是 `7` 的所有组合；
+ 同理考虑 `3`，如果找到了组合总和为 `7 - 3 = 4` 的所有组合，再在之前加上 `3` ，就是 `7` 的所有组合，依次这样找下去。



**说明**：

+ 以 `target = 7` 为 **根结点** ，创建一个分支的时 **做减法** ；
+ 每一个箭头表示：从父亲结点的数值减去边上的数值，得到孩子结点的数值。边的值就是题目中给出的 `candidate` 数组的每个元素的值；
+ 减到 0 或者负数的时候停止，即：结点 0 和负数结点成为叶子结点；
+ 所有从根结点到结点 0 的路径（只能从上往下，没有回路）就是题目要找的一个结果。

这棵树有 4 个叶子结点的值 0，对应的路径列表是 `[[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]]`，而示例中给出的输出只有 `[[7], [2, 2, 3]]`。即：题目中要求每一个符合要求的解是 **不计算顺序** 的。下面我们分析为什么会产生重复。



### 针对具体例子分析重复路径产生的原因（难点）

> 友情提示：这一部分我的描述是晦涩难懂的，建议大家先自己观察出现重复的原因，进而思考如何解决。

产生重复的原因是：在每一个结点，做减法，展开分支的时候，由于题目中说 **每一个元素可以重复使用**，我们考虑了 **所有的** 候选数，因此出现了重复的列表。

一种简单的去重方案是借助哈希表的天然去重的功能，但实际操作一下，就会发现并没有那么容易。

可不可以在搜索的时候就去重呢？答案是可以的。遇到这一类相同元素不计算顺序的问题，我们在搜索的时候就需要 **按某种顺序搜索**。具体的做法是：每一次搜索的时候设置 **下一轮搜索的起点** `begin`

## 解法一:回溯+剪枝

### 代码-无注释

```java
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    LinkedList<Integer> track = new LinkedList<Integer>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);

        backtrack(candidates, 0, target);
        return res;
    }

    void backtrack(int[] candidates, int start, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            track.addLast(candidates[i]);

            backtrack(candidates, i, target - candidates[i]);

            track.removeLast();
        }
    }

}
```





### 代码-debug 版

```java
/**
 * <p>
 * 组合总和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/17
 */
public class leetcode39 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res;

        // 法一
//        Solution01 solution01 = new Solution01();
//        res = solution01.combinationSum(candidates, target);

        // 法二
        Solution02 solution02 = new Solution02();
        res = solution02.combinationSum(candidates, target);

        System.out.println(res);
    }

    /**
     * 未剪枝
     */
    private static class Solution02 {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }

            backtrack(candidates, 0, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                track.addLast(candidates[i]);
                System.out.println("递归之前 track: => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 track: => " + track);
            }
        }

    }

    /**
     * 剪枝了
     */
    private static class Solution01 {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }
            // 排序是剪枝的前提
            Arrays.sort(candidates);

            backtrack(candidates, 0, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 剪枝
                if (target - candidates[i] < 0) {
                    break;
                }
                track.addLast(candidates[i]);
                System.out.println("递归之前 track: => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 track: => " + track);
            }
        }

    }

}
```



### **复杂度分析**：

这个问题的复杂度分析是在我的能力之外的，这里给出我的思考。

我的结论是：时间复杂度与 `candidate` 数组的值有关：

+ 如果 `candidate` 数组的值都很大，`target` 的值很小，那么树上的结点就比较少；
+ 如果 `candidate` 数组的值都很小，`target` 的值很大，那么树上的结点就比较多。

所以时间复杂度与空间复杂度不确定。



- **时间复杂度**：*O*(*S*)，其中 *S* 为所有可行解的长度之和。从分析给出的搜索树我们可以看出时间复杂度取决于搜索树所有叶子节点的深度之和，即所有可行解的长度之和。在这题中，我们很难给出一个比较紧的上界，我们知道 *O*(*n*×2*n*) 是一个比较松的上界，即在这份代码中，*n* 个位置每次考虑选或者不选，如果符合条件，就加入答案的时间代价。但是实际运行的时候，因为不可能所有的解都满足条件，递归的时候我们还会用 *target*−*candidates*[*idx*]≥0 进行剪枝，所以实际运行情况是远远小于这个上界的。

- **空间复杂度**：*O*(*target*)。除答案数组外，空间复杂度取决于递归的栈深度，在最差情况下需要递归 *O*(*target*) 层。





# THE END