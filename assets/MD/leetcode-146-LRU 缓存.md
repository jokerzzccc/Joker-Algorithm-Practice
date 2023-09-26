# 目录

[toc]

# leetcode-146-LRU 缓存

- 时间：2023-06-29
- 参考链接：
  - [算法就像搭乐高：带你手撸 LRU 算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/suan-fa-ji-8674e/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/lru-cache/?company_slug=alibaba
- 难度：中等



请你设计并实现一个满足  **LRU (最近最少使用 Least Recently Used) 缓存** 约束的数据结构。
实现 LRUCache 类：

- LRUCache(int capacity) 以 **正整数** 作为容量 capacity 初始化 LRU 缓存
- int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
- void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 **逐出** 最久未使用的关键字。



函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。



示例：

```sh
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```




提示：

- 1 <= capacity <= 3000
- 0 <= key <= 10000
- 0 <= value <= 10^5
- 最多调用 2 * 10^5 次 get 和 put







# 2、题解

## 题目分析

- 本质上，就是 LinkedHashMap 的特性，但是要自己写。



## 解法一:*自己构造：哈希表* *+* *双向链表*

### 算法分析

分析上面的操作过程，要让 `put` 和 `get` 方法的时间复杂度为 O(1)，我们可以总结出 `cache` 这个数据结构必要的条件：

1、显然 `cache` 中的元素**必须有时序**，以区分最近使用的和久未使用的数据，当容量满了之后要删除最久未使用的那个元素腾位置。

2、我们要在 `cache` 中快速找某个 `key` 是否已存在并得到对应的 `val`；

3、每次访问 `cache` 中的某个 `key`，需要将这个元素变为最近使用的，也就是说 `cache` 要**支持在任意位置快速插入和删除元素**。

那么，什么数据结构同时符合上述条件呢？哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，但是查找慢。所以结合一下，形成一种新的数据结构：哈希链表 `LinkedHashMap`。

LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体。这个数据结构长这样：

![image-20230630231151172](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230630231151172.png)

借助这个结构，我们来逐一分析上面的 3 个条件：

1、如果我们每次默认从链表尾部添加元素，那么显然越靠尾部的元素就是最近使用的，越靠头部的元素就是最久未使用的。

2、对于某一个 `key`，我们可以通过哈希表快速定位到链表中的节点，从而取得对应 `val`。

3、链表显然是支持在任意位置快速插入和删除的，改改指针就行。只不过传统的链表无法按照索引快速访问某一个位置的元素，而这里借助哈希表，可以通过 `key` 快速映射到任意一个链表节点，然后进行插入和删除。

**也许读者会问，为什么要是双向链表，单链表行不行？另外，既然哈希表中已经存了 `key`，为什么链表中还要存 `key` 和 `val` 呢，只存 `val` 不就行了**？

想的时候都是问题，只有做的时候才有答案。这样设计的原因，必须等我们亲自实现 LRU 算法之后才能理解，所以我们开始看代码吧～

- 「为什么必须要用双向链表」的问题了，因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)。

**注意我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久未使用的**。

有了双向链表的实现，我们只需要在 LRU 算法中把它和哈希表结合起来即可，先搭出代码框架：

```java
class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;
    
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
```

先不慌去实现 LRU 算法的 `get` 和 `put` 方法。由于我们要同时维护一个双链表 `cache` 和一个哈希表 `map`，很容易漏掉一些操作，比如说删除某个 `key` 时，在 `cache` 中删除了对应的 `Node`，但是却忘记在 `map` 中删除 `key`。

**解决这种问题的有效方法是：在这两种数据结构之上提供一层抽象 API**。

说的有点玄幻，实际上很简单，就是尽量让 LRU 的主方法 `get` 和 `put` 避免直接操作 `map` 和 `cache` 的细节。我们可以先实现下面几个函数：

```java
class LRUCache {
    // 为了节约篇幅，省略上文给出的代码部分...

    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {
        Node x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 别忘了在 map 中添加 key 的映射
        map.put(key, x);
    }

    /* 删除某一个 key */
    private void deleteKey(int key) {
        Node x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从 map 中删除
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deletedNode = cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }
}
```

这里就能回答之前的问答题「为什么要在链表中同时存储 key 和 val，而不是只存储 val」，注意 `removeLeastRecently` 函数中，我们需要用 `deletedNode` 得到 `deletedKey`。

也就是说，当缓存容量已满，我们不仅仅要删除最后一个 `Node` 节点，还要把 `map` 中映射到该节点的 `key` 同时删除，而这个 `key` 只能由 `Node` 得到。如果 `Node` 结构中只存储 `val`，那么我们就无法得知 `key` 是什么，就无法删除 `map` 中的键，造成错误。

上述方法就是简单的操作封装，调用这些函数可以避免直接操作 `cache` 链表和 `map` 哈希表，下面我先来实现 LRU 算法的 `get` 方法：

```java
class LRUCache {
    // 为了节约篇幅，省略上文给出的代码部分...

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }
}
```

`put` 方法稍微复杂一些，我们先来画个图搞清楚它的逻辑：

![image-20230630233805883](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230630233805883.png)







### 代码

```java

/**
 * <p>
 * LRU 缓存
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode146 {

    public static void main(String[] args) {
        LRUCache01 lruCache01 = new LRUCache01(2);
        lruCache01.put(1, 1); // 缓存是 {1=1}
        lruCache01.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache01.get(1);    // 返回 1
        lruCache01.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache01.get(2);    // 返回 -1 (未找到)
        lruCache01.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache01.get(1);    // 返回 -1 (未找到)
        lruCache01.get(3);    // 返回 3
        lruCache01.get(4);    // 返回 4


        System.out.println("================================");

    }

    /**
     * 解法一：自己构造：哈希表 + 双向链表
     */
    private static class LRUCache01 {

        /**
         * 双向链表结点
         */
        class Node {

            private int key;
            private int value;
            private Node prev;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

        }

        /**
         * 双向链表
         */
        class DoubleLinkedList {

            // 链表的头尾结点
            private Node head;
            private Node tail;
            // 链表的大小
            private int size;

            public DoubleLinkedList() {
                // 初始化双向链表的数据
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            // 在链表尾部添加节点 x，时间 O(1)
            public void addLast(Node node) {
                node.prev = tail.prev;
                node.next = tail;
                tail.prev.next = node;
                tail.prev = node;
                size++;
            }

            // 删除链表中的 x 节点（x 一定存在）
            // 由于是双链表且给的是目标 Node 节点，时间 O(1)
            public void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            // 删除链表中第一个节点，并返回该节点，时间 O(1)
            public Node removeFirst() {
                if (head.next == null) {
                    return null;
                }
                Node first = head.next;
                remove(first);
                return first;
            }

            // 返回链表长度，时间 O(1)
            public int getSize() {
                return size;
            }

        }

        // key -> Node(key, val)
        private HashMap<Integer, Node> map;
        // Node(k1, v1) <-> Node(k2, v2)...
        private DoubleLinkedList cache;
        // 最大容量
        private int capacity;

        public LRUCache01(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            cache = new DoubleLinkedList();

        }

        /* 将某个 key 提升为最近使用的 */
        private void makeRecently(int key) {
            Node node = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(node);
            // 重新插到队尾
            cache.addLast(node);
        }

        /* 添加最近使用的元素 */
        public void addRecently(int key, int val) {
            Node node = new Node(key, val);

            // 链表尾部就是最近使用的元素
            cache.addLast(node);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, node);
        }

        /* 删除某一个 key */
        private void deleteKey(int key) {
            Node node = map.get(key);
            // 从链表中删除
            cache.remove(node);
            // 从 map 中删除
            map.remove(key);
        }

        /* 删除最近最久未使用的元素 */
        public void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node node = cache.removeFirst();
            // 同时别忘了从 map 中删除它的 key
            map.remove(node.key);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, value);
                return;
            }

            if (capacity == cache.size) {
                // 删除最久未使用的数据
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, value);
        }

    }


}

```





### 复杂度分析





## 解法二：*使用本身的* *LinkedHashMap*

### 算法分析





### 代码

```java
package com.joker.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * LRU 缓存
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode146 {

    public static void main(String[] args) {

        System.out.println("================================");

        LRUCache02 lruCache02 = new LRUCache02(2);
        lruCache02.put(1, 1); // 缓存是 {1=1}
        lruCache02.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache02.get(1);    // 返回 1
        lruCache02.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache02.get(2);    // 返回 -1 (未找到)
        lruCache02.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache02.get(1);    // 返回 -1 (未找到)
        lruCache02.get(3);    // 返回 3
        lruCache02.get(4);    // 返回 4
    }


    /**
     * 解法二：使用本身的 LinkedHashMap
     */
    private static class LRUCache02 extends LinkedHashMap<Integer, Integer> {

        int capacity;

        public LRUCache02(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int val) {
            super.put(key, val);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

    }

}

```





### 复杂度分析





# THE END