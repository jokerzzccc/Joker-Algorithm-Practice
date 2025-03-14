package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 流浪地球
 * 问题描述 流
 * 浪地球计划在赤道上均匀部署了N个转向发动机，按位置顺序编号为0~N-1。
 * 发动机的启动方式分为“手动启动”和“关联启动”两种方式。如果一个发动机被手动启动，下一个时刻与之相邻的两个发动机会被“关联启动”。
 * 如果准备启动某个发动机时，它已经被启动了，则什么都不用做。发动机0与发动机N-1是相邻。
 * 地球联合政府准备挑选某些发动机在某些时刻进行“手动启动”，最终所有的发动机都会被启动。
 * 需要找出哪些发动机最晚被启动。
 * 输入格式:
 * 第一行两个数字N和E，中间有空格。N代表部署发动机的总个数，E代表计划手动启动的发动机总个数。1<N<=1000，1<=E<=1000,E<=N。
 * 接下来共E行，每行都是两个数字T和P，中间有空格。T代表发动机的手动启动时刻，P代表此发动机的位置编号。0<=T<=N,0<=P<=N。
 * 输出格式:
 * 第一行一个数字N，以回车结束。N代表最后被启动的发动机个数。
 * 第二行N个数字，中间有空格，以回车结束。每个数字代表发动机的位置编号，从小到大排序。
 * 示例输入:
 * 8 2
 * 0 0
 * 1 7
 * 示例输出:
 * 1
 * 4
 *
 * 题型分析：
 * 数组 + BFS
 *
 * @author jokerzzccc
 * @date 2025/3/10
 */

public class COE1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int e = input.nextInt();

        // 记录每个发动机的启动时刻
        int[] engineStart = new int[n];
        // 初始化最大值
        Arrays.fill(engineStart, Integer.MAX_VALUE);

        // 用于 BFS 的队列
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < e; i++) {
            int t = input.nextInt();
            int p = input.nextInt();
            engineStart[p] = t;
            // 将手动启动的发动机加入队列
            queue.offer(new int[]{t, p});
        }

        input.close();

        // 通过 BFS 算法计算所有发动机的最早启动时间， 求出最后被启动的发动机的位置
        List<Integer> lastEnginesPosList = bfs(engineStart, queue);

        // 打印结果
        System.out.println(lastEnginesPosList.size());
        for (int pos : lastEnginesPosList) {
            System.out.print(pos + " ");
        }

    }

    private static List<Integer> bfs(int[] engineStart, Queue<int[]> queue) {
        // 最晚启动的发动机集合
        ArrayList<Integer> lastEngines = new ArrayList<>();
        int n = engineStart.length;

        // 进行BFS，计算所有发动机的最早启动时间
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int time = curr[0];
            int pos = curr[1];
            // 相邻的两个发动机被“关联启动”
            // 考虑环状结构
            int[] next = new int[]{(pos - 1 + n) % n, (pos + 1) % n};
            for (int item : next) {
                // 更新更早的启动时刻
                if (engineStart[item] > time + 1) {
                    engineStart[item] = time + 1;
                    queue.offer(new int[]{time + 1, item});
                }
            }
        }
        // 最晚启动的时刻
        int lastTime = Arrays.stream(engineStart)
                .max().getAsInt();

        // 找到最晚启动的时刻，及该时刻的发动机集合
        for (int i = 0; i < n; i++) {
            if (engineStart[i] == lastTime) {
                lastEngines.add(i);
            }
        }

        return lastEngines;
    }

}

