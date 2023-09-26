# 目录

[toc]

# leetcode-752-打开转盘锁

- 时间：2023-06-25
- 参考链接：
  - [BFS 算法解题套路框架](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/bfs-suan-f-463fd/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/open-the-lock/
- 难度：中等

你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。



示例 1:

```sh
输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
输出：6
解释：
可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
因为当拨动到 "0102" 时这个锁就会被锁定。
```



提示：

- 1 <= deadends.length <= 500
- deadends[i].length == 4
- target.length == 4
- target **不在** deadends 之中
- target 和 deadends[i] 仅由若干位数字组成





# 2、题解

## 题目分析

但现在的难点就在于，不能出现 `deadends`，应该如何计算出最少的转动次数呢？

**第一步，我们不管所有的限制条件，不管 `deadends` 和 `target` 的限制，就思考一个问题：如果让你设计一个算法，穷举所有可能的密码组合，你怎么做**？

穷举呗，再简单一点，如果你只转一下锁，有几种可能？总共有 4 个位置，每个位置可以向上转，也可以向下转，也就是有 8 种可能对吧。

比如说从 `"0000"` 开始，转一次，可以穷举出 `"1000", "9000", "0100", "0900"...` 共 8 种密码。然后，再以这 8 种密码作为基础，对每个密码再转一下，穷举出所有可能...

**仔细想想，这就可以抽象成一幅图，每个节点有 8 个相邻的节点**，又让你求最短距离，这不就是典型的 BFS 嘛，框架就可以派上用场了，先写出一个「简陋」的 BFS 框架代码再说别的：

```java
// 将 s[j] 向上拨动一次
String plusOne(String s, int j) {
    char[] ch = s.toCharArray();
    if (ch[j] == '9')
        ch[j] = '0';
    else
        ch[j] += 1;
    return new String(ch);
}
// 将 s[i] 向下拨动一次
String minusOne(String s, int j) {
    char[] ch = s.toCharArray();
    if (ch[j] == '0')
        ch[j] = '9';
    else
        ch[j] -= 1;
    return new String(ch);
}

// BFS 框架，打印出所有可能的密码
void BFS(String target) {
    Queue<String> q = new LinkedList<>();
    q.offer("0000");
    
    while (!q.isEmpty()) {
        int sz = q.size();
        /* 将当前队列中的所有节点向周围扩散 */
        for (int i = 0; i < sz; i++) {
            String cur = q.poll();
            /* 判断是否到达终点 */
            System.out.println(cur);

            /* 将一个节点的相邻节点加入队列 */
            for (int j = 0; j < 4; j++) {
                String up = plusOne(cur, j);
                String down = minusOne(cur, j);
                q.offer(up);
                q.offer(down);
            }
        }
        /* 在这里增加步数 */
    }
    return;
}
```

> PS：这段代码当然有很多问题，但是我们做算法题肯定不是一蹴而就的，而是从简陋到完美的。不要完美主义，咱要慢慢来，好不。

**这段 BFS 代码已经能够穷举所有可能的密码组合了，但是显然不能完成题目，有如下问题需要解决**：

1、会走回头路。比如说我们从 `"0000"` 拨到 `"1000"`，但是等从队列拿出 `"1000"` 时，还会拨出一个 `"0000"`，这样的话会产生死循环。

2、没有终止条件，按照题目要求，我们找到 `target` 就应该结束并返回拨动的次数。

3、没有对 `deadends` 的处理，按道理这些「死亡密码」是不能出现的，也就是说你遇到这些密码的时候需要跳过。

如果你能够看懂上面那段代码，真得给你鼓掌，只要按照 BFS 框架在对应的位置稍作修改即可修复这些问题：

## 解法一: BFS

### 算法分析





### 代码

```java


/**
 * <p>
 * 打开转盘锁
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode752 {

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        Solution01 solution01 = new Solution01();
        int openLock01 = solution01.openLock(deadends, target);
        System.out.println(openLock01);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        public int openLock(String[] deadends, String target) {
            // 记录需要跳过的死亡密码
            Set<String> deads = new HashSet<>();
            for (String s : deadends) deads.add(s);
            // 记录已经穷举过的密码，防止走回头路
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            // 从起点开始启动广度优先搜索
            int step = 0;
            queue.offer("0000");
            visited.add("0000");

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();

                    // 属于 dead, 直接跳过
                    if (deads.contains(cur)) {
                        continue;
                    }
                    /* 判断是否到达终点 */
                    if (cur.equals(target)) {
                        return step;
                    }

                    /* 将一个节点的未遍历相邻节点加入队列 */
                    for (int j = 0; j < 4; j++) {
                        String up = plusOne(cur, j);
                        if (!visited.contains(up)) {
                            queue.offer(up);
                            visited.add(up);
                        }
                        String down = minusOne(cur, j);
                        if (!visited.contains(down)) {
                            queue.offer(down);
                            visited.add(down);
                        }
                    }
                }
                /* 在这里增加步数 */
                step++;
            }

            return -1;
        }

        // 将 s[j] 向上拨动一次
        String plusOne(String s, int j) {
            char[] ch = s.toCharArray();
            if (ch[j] == '9')
                ch[j] = '0';
            else
                ch[j] += 1;
            return new String(ch);
        }

        // 将 s[i] 向下拨动一次
        String minusOne(String s, int j) {
            char[] ch = s.toCharArray();
            if (ch[j] == '0')
                ch[j] = '9';
            else
                ch[j] -= 1;
            return new String(ch);
        }

    }

}

```





### 复杂度分析

![image-20230625213632295](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230625213632295.png)









# THE END