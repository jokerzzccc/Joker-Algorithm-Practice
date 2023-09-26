# 目录

[toc]

# leetcode-295-数据流的中位数

- 时间：2023-08-20
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/find-median-from-data-stream/description/
- 难度：困难

**中位数**是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。

+ 例如 `arr = [2,3,4]` 的中位数是 `3` 。
+ 例如 `arr = [2,3]` 的中位数是 `(2 + 3) / 2 = 2.5` 。

实现 MedianFinder 类:

+ `MedianFinder() `初始化 `MedianFinder` 对象。
+ `void addNum(int num)` 将数据流中的整数 `num` 添加到数据结构中。
+ `double findMedian()` 返回到目前为止所有元素的中位数。与实际答案相差 `10-5` 以内的答案将被接受。





# 2、题解

## 题目分析

- 主要是对数据结构的一个考察，设计。

## 解法一：*双优先队列*

### 算法分析

参考链接：

- https://leetcode.cn/problems/find-median-from-data-stream/solutions/961319/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/







### 代码

```java


/**
 * <p>
 * 数据流的中位数
 * </p>
 *
 * @author admin
 * @date 2023/8/20
 */
public class leetcode295 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }

    /**
     * 解法一： 双优先队列
     */
    private static class MedianFinder {

        /**
         * 有序整数列表的：左半队列，大顶堆
         */
        private PriorityQueue<Integer> leftQueue;
        /**
         * 有序整数列表的：右半队列，小顶堆
         */
        private PriorityQueue<Integer> rightQueue;

        /**
         * 总数量是否为奇数
         */
        private boolean isOddSize;

        /**
         * 总数量
         */
        private int size;

        public MedianFinder() {
            leftQueue = new PriorityQueue<>((a, b) -> b - a);
            rightQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        }

        public void addNum(int num) {
            if (!isOddSize) {
                if (rightQueue.isEmpty() || num < rightQueue.peek()) {
                    leftQueue.add(num);
                } else {
                    leftQueue.add(rightQueue.poll());
                    rightQueue.add(num);
                }
            } else {
                if (leftQueue.peek() <= num) {
                    rightQueue.add(num);
                } else {
                    rightQueue.add(leftQueue.poll());
                    leftQueue.add(num);
                }
            }

            size++;
            isOddSize = size % 2 != 0;
        }

        /**
         * O(1) 时间复杂度 取中位数
         */
        public double findMedian() {
            if (size > 0) {
                if (isOddSize) {
                    return leftQueue.peek();
                } else {
                    return (leftQueue.peek() + rightQueue.peek()) / 2.0;
                }
            }

            return 0;

        }

    }

}

```





### 复杂度分析

- 时间复杂度：`addNum` 函数的复杂度为 O(log⁡n)；`findMedian` 函数的复杂度为 O(1)
- 空间复杂度：O(n)











# THE END