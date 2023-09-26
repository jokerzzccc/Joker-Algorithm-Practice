# 目录

[toc]

# leetcode-380-O(1) 时间插入、删除和获取随机元素

- 时间：2023-06-16

- 参考链接：
  - [常数时间删除-查找数组中的任意元素](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/chang-shu--6b296/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/insert-delete-getrandom-o1/
- 难度：中等



实现RandomizedSet 类：

- RandomizedSet() 初始化 RandomizedSet 对象
- bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
- bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
- int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。



你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。



提示：

- $-2^{31}$ <= val <= $2^{31} - 1$ 
- 最多调用 insert、remove 和 getRandom 函数 $2 * 10^5$ 次
- 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。





# 2、题解

## 题目分析



## 解法一:  *变长数组* *+* *哈希表*

### 算法分析

本题的难点在于两点：

**1、插入，删除，获取随机元素这三个操作的时间复杂度必须都是 O(1)**。

**2、`getRandom` 方法返回的元素必须等概率返回随机元素**，也就是说，如果集合里面有 `n` 个元素，每个元素被返回的概率必须是 `1/n`。



我们先来分析一下：对于插入，删除，查找这几个操作，哪种数据结构的时间复杂度是 O(1)？

对于 `getRandom` 方法，如果想「等概率」且「在 O(1) 的时间」取出元素，一定要满足：**底层用数组实现，且数组必须是紧凑的**。

**但如果用数组存储元素的话，插入，删除的时间复杂度怎么可能是 O(1) 呢**？

可以做到！对数组尾部进行插入和删除操作不会涉及数据搬移，时间复杂度是 O(1)。

**所以，如果我们想在 O(1) 的时间删除数组中的某一个元素 `val`，可以先把这个元素交换到数组的尾部，然后再 `pop` 掉**。

交换两个元素必须通过索引进行交换对吧，那么我们需要一个哈希表 `valToIndex` 来记录每个元素值对应的索引。



注意 `remove(val)` 函数，对 `nums` 进行插入、删除、交换时，都要记得修改哈希表 `valToIndex`，否则会出现错误。

至此，这道题就解决了，每个操作的复杂度都是 O(1)，且随机抽取的元素概率是相等的。



### 代码

```java

/**
 * <p>
 * O(1) 时间插入、删除和获取随机元素
 * </p>
 *
 * @author admin
 * @date 2023/6/16
 */
public class leetcode380 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        ArrayList<Object> res = new ArrayList<>();
        res.add(randomizedSet.insert(1));
        res.add(randomizedSet.remove(2));
        res.add(randomizedSet.insert(2));
        res.add(randomizedSet.getRandom());
        res.add(randomizedSet.remove(1));
        res.add(randomizedSet.insert(2));
        res.add(randomizedSet.getRandom());
        res.stream().forEach(System.out::println);

    }

    /**
     * 解法一：
     */
    private static class RandomizedSet {

        // 存储元素的值
        List<Integer> nums;
        // 记录每个元素对应在 nums 中的索引 K-value, V-index
        Map<Integer, Integer> valToIndex;

        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();

        }

        /**
         * 如果 val 不存在集合中，则插入并返回 true，否则直接返回 false
         */
        public boolean insert(int val) {
            // 若 val 不存在，插入到 nums 尾部，
            // 并记录 val 对应的索引值
            if (!valToIndex.containsKey(val)) {
                nums.add(val);
                valToIndex.put(val, nums.size() - 1);
                return true;
            }
            // 若 val 已存在，不用再插入
            return false;

        }

        /**
         * 如果 val 在集合中，则删除并返回 true，否则直接返回 false
         */
        public boolean remove(int val) {
            // 若 val 不存在，不用再删除
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            // 先拿到 val 的索引
            Integer index = valToIndex.get(val);
            // 将最后一个元素对应的索引修改为 index
            valToIndex.put(nums.get(nums.size() - 1), index);
            // 交换 val 和最后一个元素
            Collections.swap(nums, index, nums.size() - 1);
            // 在数组中删除元素 val
            nums.remove(nums.size() - 1);
            // 删除元素 val 对应的索引
            valToIndex.remove(val);
            return true;

        }

        /**
         * 从集合中等概率地随机获得一个元素
         */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }

    }

}

```

输出：

```sh
true
false
true
1
true
false
2
```





### 复杂度分析

- 时间复杂度：初始化和各项操作的时间复杂度都是O(1)。
- 空间复杂度：O(n),其中是集合中的元素个数。存储元素的数组和哈希表需要O(n)的空间。





# THE END