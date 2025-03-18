package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 日志采集系统
 * 题目描述
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。
 * 如果上报太频繁，会对服务端造成压力;
 * 如果上报太晚，会降低用户的体验；
 * 如果一次上报的条数太多，会导致超时失败。
 * 为此，项目组设计了如下的上报策略：
 * 每成功上报一条日志，奖励1分
 * 每条日志每延迟上报1秒，扣1分
 * 积累日志达到100条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
 * 输入描述
 * 按时序产生的日志条数 T1,T2…Tn，其中 1<=n<=1000，0<=Ti<=100
 * 输出描述
 * 首次上报最多能获得的积分数
 * 示例1
 * 输入
 * 1 98 1
 * 输出
 * 98
 * 说明
 * T1 时刻上报得 1 分
 * T2 时刻上报得98分，最大
 * T3 时刻上报得 0 分
 * <p>
 * 示例2
 * 输入
 * 50 60 1
 * 输出
 * 50
 * 说明
 * 如果第1个时刻上报，获得积分50。如果第2个时刻上报，最多上报100条，前50条延迟上报1s，每条扣除1分，共获得积分为 100-50=50
 * <p>
 * 示例3
 * 输入
 * 3 7 40 10 60
 * 输出
 * 37
 * 说明
 * T1时刻上报得3分
 * T2时刻上报得7分
 * T3时刻上报得37分，最大
 * T4时刻上报得-3分
 * T5时刻上报，因为已经超了100条限制，所以只能上报100条，得-23分
 * ————————————————
 * 题型分析
 * 【中等】动态规划 + 逻辑分析
 * 数据前后数据有依赖关系，有点像动态规划，因为不同时刻之前扣的积分会变化，所以，又和动态规划又不太一样
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE10 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputRecords = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        int N = inputRecords.length;
        int totalCount = 0; // 总日志条数
        int maxScore = 0;
        for (int i = 0; i < N; i++) {
            int currLogs = inputRecords[i];
            if (currLogs == 0) {
                continue;
            }

            totalCount += currLogs; // 更新总日志条数
            // 当前时刻的积分
            int currScore = 0;
            // 核心：更新当前时刻的积分
            for (int j = 0; j <= i; j++) {
                if (i == j && totalCount >= 100) {
                    // 如果总日志条数超过100，并且当前时间点是最后一个时间点
                    // 只能上报 100 条
                    currScore += currLogs - (totalCount - 100);
                    break;
                }
                currScore += inputRecords[j] - (i - j) * inputRecords[j];
            }

            // 更新最大积分
            maxScore = Math.max(maxScore, currScore);

            // 100 条必须上报
            if (totalCount >= 100) {
                break;
            }

        }

        System.out.println(maxScore);

    }

}
