package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <p>
 * 你可以安排的最多任务数目
 * </p>
 *
 * @author admin
 * @date 2023/8/10
 */
public class leetcode2071 {

    public static void main(String[] args) {
        int[] tasks = {3, 2, 1}, workers = {0, 3, 3};
        int pills = 1, strength = 1;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxTaskAssign(tasks, workers, pills, strength));

    }

    /**
     * 解法一：二分查找 + 双端队列
     */
    private static class Solution01 {

        public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
            Arrays.sort(tasks);
            Arrays.sort(workers);

            // 最少完成 min 个任务
            int min = 0;
            // 最多完成 max + 1 个任务
            int max = Math.min(tasks.length, workers.length) - 1;

            // 二分查找
            while (min <= max) {
                int mid = (min + max) / 2;
                if (check(tasks, workers, pills, strength, mid)) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }

            }

            return min;
        }

        /**
         * 从下标0到下标mid的任务(最小的mid+1个任务),
         * 是否都能被workers.length - 1 - mid到workers.length - 1下标的工人(力量最大的mid+1个工人)完成
         */
        private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
            // 工人队列(双端队列)
            // （在使用药丸的情况下）所有可以完成任务的工人
            Deque<Integer> workerDeque = new ArrayDeque<>();
            int workerIdxMax = workers.length - 1;
            int workerIdxMin = workers.length - 1 - mid;
            // 从大到小（从后向前）遍历任务
            for (int i = mid; i >= 0; i--) {
                // 工人力量：队首 -> 队尾，大 -> 小
                while (workerIdxMax >= workerIdxMin && workers[workerIdxMax] + strength >= tasks[i]) {
                    // 从力量最大的工人开始遍历,如果工人吃药丸后,能够完成当前任务,加入队尾
                    workerDeque.addLast(workers[workerIdxMax]);
                    workerIdxMax--;
                }
                if (workerDeque.size() == 0) {
                    // 情况一：没有工人能够完成当前任务
                    return false;
                }
                if (workerDeque.getFirst() >= tasks[i]) {
                    // 情况二：队头(队列中,力量最大)的工人可以完成当前任务,让他去完成当前任务.
                    workerDeque.removeFirst();
                } else {
                    // 情况三：队头(队列中,力量最大)的工人不能完成当前任务,需要吃药丸
                    if (pills == 0) {
                        // 没有药丸了
                        return false;
                    } else {
                        // 情况四：
                        // 让队里力量最小的工人吃了药丸去完成当前任务.
                        // 因为任务需要的力量是递减的,力量大的工人有可能不吃药丸完成后面的任务
                        pills--;
                        workerDeque.removeLast();
                    }
                }
            }

            return true;
        }

    }

}
