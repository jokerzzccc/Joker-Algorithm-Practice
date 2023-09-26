# 目录

[toc]

# leetcode-460-LFU 缓存

- 时间：2023-07-01
- 参考链接：
  - [算法就像搭乐高：带你手撸 LFU 算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/suan-fa-ji-fb527/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/lfu-cache/
- 难度：困难

实现 `LFUCache` 类：

+ `LFUCache(int capacity)` - 用数据结构的容量 `capacity` 初始化对象
+ `int get(int key)` - 如果键 `key` 存在于缓存中，则获取键的值，否则返回 `-1` 。
+ `void put(int key, int value)` - 如果键 `key` 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 `capacity` 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 **最近最久未使用** 的键。

为了确定最不常使用的键，可以为缓存中的每个键维护一个 **使用计数器** 。使用计数最小的键是最久未使用的键。

当一个键首次插入到缓存中时，它的使用计数器被设置为 `1` (由于 put 操作)。对缓存中的键执行 `get` 或 `put` 操作，使用计数器的值将会递增。

函数 `get` 和 `put` 必须以 `O(1)` 的平均时间复杂度运行。



**示例：**

```
输入：
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
输出：
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

解释：
// cnt(x) = 键 x 的使用计数
// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // 返回 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // 返回 -1（未找到）
lfu.get(3);      // 返回 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // 返回 -1（未找到）
lfu.get(3);      // 返回 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // 返回 4
                 // cache=[3,4], cnt(4)=2, cnt(3)=3
```



**提示：**

+ `0 <= capacity <= 10^4`
+ `0 <= key <= 10^5`
+ `0 <= value <= 10^9`
+ 最多调用 `2 * 10^5` 次 `get` 和 `put` 方法



# 2、题解

## 题目分析

LRU 算法的淘汰策略是 Least Recently Used，也就是每次淘汰那些最久没被使用的数据；而 LFU 算法的淘汰策略是 Least Frequently Used，也就是每次淘汰那些使用次数最少的数据。

LRU 算法的核心数据结构是使用哈希链表 `LinkedHashMap`，首先借助链表的有序性使得链表元素维持插入顺序，同时借助哈希映射的快速访问能力使得我们可以在 O(1) 时间访问链表的任意元素。

从实现难度上来说，LFU 算法的难度大于 LRU 算法，因为 LRU 算法相当于把数据按照时间排序，这个需求借助链表很自然就能实现，你一直从链表头部加入元素的话，越靠近头部的元素就是新的数据，越靠近尾部的元素就是旧的数据，我们进行缓存淘汰的时候只要简单地将尾部的元素淘汰掉就行了。

而 **LFU 算法**相当于是把数据按照访问频次进行排序，这个需求恐怕没有那么简单，而且还有一种情况，如果多个数据拥有相同的访问频次，我们就得删除最早插入的那个数据。也就是说 LFU 算法是**淘汰访问频次最低的数据**，如果**访问频次最低的数据有多条，需要淘汰最旧的数据**。

所以说 LFU 算法是要复杂很多的，而且经常出现在面试中，因为 LFU 缓存淘汰算法在工程实践中经常使用，也有可能是因为 LRU 算法太简单了。**不过话说回来，这种著名的算法的套路都是固定的，关键是由于逻辑较复杂，不容易写出漂亮且没有 bug 的代码**。

## 解法一：双哈希表

### 算法分析



我们定义两个哈希表：

- 第一个 `freq_table` 以频率 `freq` 为索引，每个索引存放一个双向链表，这个链表里存放所有使用频率为 `freq` 的缓存，缓存里存放三个信息，分别为键 `key`，值 `value`，以及使用频率 `freq`。
- 第二个 `key_table` 以键值 `key` 为索引，每个索引存放对应缓存在 `freq_table` 中链表里的内存地址，这样我们就能利用两个哈希表来使得两个操作的时间复杂度均为 *O*(1)。

同时需要记录一个当前缓存最少使用的频率 `minFreq`，这是为了删除操作服务的。



**对于 `get(key)` 操作**，我们能通过索引 `key` 在 `key_table` 中找到缓存在 `freq_table` 中的链表的内存地址，如果不存在直接返回 `-1`，否则我们能获取到对应缓存的相关信息，这样我们就能知道缓存的键值还有使用频率，直接返回 `key` 对应的值即可。

但是我们注意到 `get` 操作后这个缓存的使用频率加一了，所以我们需要更新缓存在哈希表 `freq_table` 中的位置。已知这个缓存的键 `key`，值 `value`，以及使用频率 `freq`，那么该缓存应该存放到 `freq_table` 中 `freq + 1` 索引下的链表中。所以我们在当前链表中 *O*(1) 删除该缓存对应的节点，根据情况更新 `minFreq` 值，然后将其*O*(1) 插入到 `freq + 1` 索引下的链表头完成更新。这其中的操作复杂度均为 *O*(1)。

你可能会疑惑更新的时候为什么是插入到链表头，这其实是为了保证缓存在当前链表中从链表头到链表尾的**插入时间是有序的**，为下面的删除操作服务。



**对于 `put(key, value)` 操作**，我们先通过索引 `key`在 `key_table` 中查看是否有对应的缓存，

- 如果有的话，其实操作等价于 `get(key)` 操作，唯一的区别就是我们需要将当前的缓存里的值更新为 `value`。
- 如果没有的话，相当于是新加入的缓存，如果缓存已经到达容量，需要先删除最近最少使用的缓存，再进行插入。

先考虑插入，由于是新插入的，所以缓存的使用频率一定是 `1`，所以我们将缓存的信息插入到 `freq_table` 中 `1` 索引下的列表头即可，同时更新 `key_table[key]` 的信息，以及更新 `minFreq = 1`。

那么剩下的就是**删除操作**了，由于我们实时维护了 `minFreq`，所以我们能够知道 `freq_table` 里目前最少使用频率的索引，同时因为我们保证了链表中从链表头到链表尾的插入时间是有序的，所以 `freq_table[minFreq]` 的**链表中链表尾的节点即为使用频率最小且插入时间最早的节点**，我们删除它同时根据情况更新 `minFreq` ，整个时间复杂度均为 *O*(1)。



### 代码

```java


/**
 * <p>
 * LFU 缓存
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode460 {

    public static void main(String[] args) {
        LFUCache01 lfu = new LFUCache01(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3

    }

    /**
     * 解法一：双哈希表，
     * 时间复杂度最优
     */
    private static class LFUCache01 {

        /**
         * 当前缓存最少使用的频率（服务于删除操作）
         */
        private int minFreq;

        /**
         * 容量
         */
        private int capacity;
        /**
         * 键值 key -> freqTable 的结点
         */
        Map<Integer, Node> keyTable;
        /**
         * 频率 -> 同一频率构成的双向链表
         */
        Map<Integer, DoublyLinkedList> freqTable;

        public LFUCache01(int capacity) {
            this.minFreq = 0;
            this.capacity = capacity;
            this.keyTable = new HashMap<>();
            this.freqTable = new HashMap<>();

        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!keyTable.containsKey(key)) {
                return -1;
            }
            Node node = keyTable.get(key);
            int val = node.val, freq = node.freq;

            // 从原 freq 频率中删除
            freqTable.get(freq).remove(node);
            // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minFreq == freq) {
                    minFreq++;
                }
            }

            // 插入到 freq + 1 中
            DoublyLinkedList freqPlusList = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            freqPlusList.addFirst(new Node(key, val, freq + 1));
            freqTable.put(freq + 1, freqPlusList);
            keyTable.put(key, freqTable.get(freq + 1).getHead());

            return val;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            // 缓存中未包含 key
            if ((!keyTable.containsKey(key))) {
                // 缓存已满，需要进行删除操作
                if (keyTable.size() == capacity) {
                    // 删除最小频率的结点
                    // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                    Node minNode = freqTable.get(minFreq).getTail();
                    keyTable.remove(minNode.key);
                    freqTable.get(minFreq).remove(minNode);
                    if (freqTable.get(minFreq).size == 0) {
                        freqTable.remove(minFreq);
                    }
                }

                DoublyLinkedList newList = freqTable.getOrDefault(1, new DoublyLinkedList());
                newList.addFirst(new Node(key, value, 1));
                freqTable.put(1, newList);
                keyTable.put(key, freqTable.get(1).getHead());
                minFreq = 1;
            } else { // 缓存中已包含 key
                // 与 get 操作基本一致，除了需要更新缓存的值
                Node node = keyTable.get(key);
                int freq = node.freq;
                freqTable.get(freq).remove(node);
                if (freqTable.get(freq).size == 0) {
                    freqTable.remove(freq);
                    if (minFreq == freq) {
                        minFreq += 1;
                    }
                }
                DoublyLinkedList freqPlusList = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
                freqPlusList.addFirst(new Node(key, value, freq + 1));
                freqTable.put(freq + 1, freqPlusList);
                keyTable.put(key, freqTable.get(freq + 1).getHead());

            }

        }

        /**
         * 双向链表节点
         */
        class Node {

            int key, val, freq;
            Node prev, next;

            Node() {
                this(-1, -1, 0);
            }

            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }

        }

        /**
         * 双向链表
         */
        class DoublyLinkedList {

            // 虚拟头尾结点
            Node dummyHead, dummyTail;
            // 链表大小
            int size;

            DoublyLinkedList() {
                dummyHead = new Node();
                dummyTail = new Node();
                dummyHead.next = dummyTail;
                dummyTail.prev = dummyHead;
                size = 0;
            }

            public void addFirst(Node node) {
                Node prevHead = dummyHead.next;
                node.prev = dummyHead;
                dummyHead.next = node;
                node.next = prevHead;
                prevHead.prev = node;
                size++;
            }

            public void remove(Node node) {
                Node prev = node.prev, next = node.next;
                prev.next = next;
                next.prev = prev;
                size--;
            }

            public Node getHead() {
                return dummyHead.next;
            }

            public Node getTail() {
                return dummyTail.prev;
            }

        }

    }

}

```





### 复杂度分析

- 时间复杂度：get时间复杂度O(1),put时间复杂度O(1)。由于两个操作从头至尾都只利用了哈希
  表的插入删除还有链表的插入删除，且它们的时间复杂度均为O(1),所以保证了两个操作的时间复杂
  度均为0(1)。
- 空间复杂度：O(capacity),其中capacity为 `LFU` 的缓存容量。哈希表中不会存放超过缓存容量的键
  值对。









# THE END