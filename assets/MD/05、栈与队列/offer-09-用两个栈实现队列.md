# 目录

[toc]

# offer-09-用两个栈实现队列

- 时间：2023-04-27



# 1、题目

- https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
- 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )



# 2、题解

**解题思路：**

1. 栈无法实现队列功能：栈底元素（对应队首元素）无法直接删除，需要将上方元素出栈；
2. 双栈可实现列表倒序：设有含三个元素的栈A=[1,2,3]和空栈B=[]。若循环执行A元素出
   栈并添加入栈B,直到栈A为空，则A=[],B=[3,2,1],即栈B元素实现栈A元素倒序。
3. 利用栈 B 删除队首元素：倒序后，B 执行出栈则相当于删除了 A 的栈底元素，即对应队首元素



**函数设计：**

![image-20230428215025706](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230428215025706.png)



**代码：**

```java
class CQueue {

    LinkedList<Integer> A, B;

    public CQueue() {
        A = new LinkedList<Integer>();
        B = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        A.addLast(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()) return B.removeLast();
        if (A.isEmpty()) return -1;
        while (!A.isEmpty())
            B.addLast(A.removeLast());
        return B.removeLast();
    }

}
```

