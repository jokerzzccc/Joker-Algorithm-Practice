# 目录

[toc]

# leetcode-341-扁平化嵌套列表迭代器

- 时间：2023-06-22
- 参考链接：
  - [题目不让我干什么，我偏要干什么](https://mp.weixin.qq.com/s/uEmD5YVGG5LHQEmJQ2GSfw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/flatten-nested-list-iterator/
- 难度：中等

+ 

给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。

实现扁平迭代器类 NestedIterator ：

- NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
- int next() 返回嵌套列表的下一个整数。
- boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。



你的代码将会用下述伪代码检测：

```sh
initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
```

如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。



**提示：**

+ `1 <= nestedList.length <= 500`
+ 嵌套列表中的整数值在范围 `[-10^6, 10^6]` 内





# 2、题解

## 题目分析



## 解法一: *深度优先搜索*

### 算法分析

一般的迭代器求值应该是「惰性的」，也就是说，如果你要一个结果，我就算一个（或是一小部分）结果出来，而不是一次把所有结果都算出来。

如果想做到这一点，使用递归函数进行 DFS 遍历肯定是不行的，而且我们其实只关心「叶子节点」，所以传统的 BFS 算法也不行。实际的思路很简单：

**调用`hasNext`时，如果`nestedList`的第一个元素是列表类型，则不断展开这个元素，直到第一个元素是整数类型**。

由于调用`next`方法之前一定会调用`hasNext`方法，这就可以保证每次调用`next`方法的时候第一个元素是整数型，直接返回并删除第一个元素即可。



### 代码

```java

/**
 * <p>
 * 扁平化嵌套列表迭代器
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode341 {

    public static void main(String[] args) {

    }

    /**
     * 解法一：深度优先搜索
     * 惰性求值
     */
    public class NestedIterator implements Iterator<Integer> {

        private LinkedList<NestedInteger> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
            // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
            list = new LinkedList<>(nestedList);

        }

        @Override
        public Integer next() {
            // hasNext 方法保证了第一个元素一定是整数类型
            return list.remove(0).getInteger();
        }

        @Override
        public boolean hasNext() {
            // 循环拆分列表元素，直到列表第一个元素是整数类型
            while (!list.isEmpty() && !list.getFirst().isInteger()) {
                // 当列表开头第一个元素是列表类型时，进入循环
                List<NestedInteger> first = list.remove(0).getList();
                // 将第一个列表打平并按顺序添加到开头
                for (int i = first.size() - 1; i >= 0; i--) {
                    list.addFirst(first.get(i));
                }
            }
            return !list.isEmpty();

        }

    }

    /**
     * 题目的数据结构体
     */
    private static class NestedInteger {

        private Integer val;
        private List<NestedInteger> list;

        public NestedInteger(Integer val) {
            this.val = val;
            this.list = null;
        }

        public NestedInteger(List<NestedInteger> list) {
            this.list = list;
            this.val = null;
        }

        /**
         * 如果其中存的是一个整数，则返回 true，否则返回 false
         *
         * @return true if this NestedInteger holds a single integer, rather than a nested list.
         */
        public boolean isInteger() {
            return val != null;
        }

        /**
         * 如果其中存的是一个整数，则返回这个整数，否则返回 null
         *
         * @return the single integer that this NestedInteger holds, if it holds a single integer
         * Return null if this NestedInteger holds a nested list
         */

        public Integer getInteger() {
            return this.val;
        }

        /**
         * 如果其中存的是一个列表，则返回这个列表，否则返回 null
         *
         * @return the nested list that this NestedInteger holds, if it holds a nested list
         * Return empty list if this NestedInteger holds a single integer
         */
        //
        public List<NestedInteger> getList() {
            return this.list;
        }

    }

}


```





### 复杂度分析

![image-20230622175900895](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622175900895.png)



# THE END