package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模拟消息队列
 *
 * @author jokerzzccc
 * @date 2025/3/28
 */
public class OD25A10 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] messageInput = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] consumeInput = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        int n = messageInput.length / 2;
        int m = consumeInput.length / 2;

        // 消息队列的优先级队列: 按照发送时刻排序
        // 二维数组：0-发送时刻，1-消息内容
        List<int[]> messageQueue = new LinkedList<>();
        for (int i = 0; i < 2 * n; i += 2) {
            int[] currMessage = new int[]{messageInput[i], messageInput[i + 1]};
            messageQueue.add(currMessage);
        }
        messageQueue.sort(Comparator.comparingInt(o -> o[0]));

        // 消费者的队列：插入顺序，就是消费优先级(越后面，优先级越高)
        // 二维数组：0-start, 1-end，[start, end)
        LinkedList<int[]> consumeQueue = new LinkedList<>();
        for (int i = 0; i < 2 * m; i += 2) {
            int[] currConsume = new int[]{consumeInput[i], consumeInput[i + 1]};
            consumeQueue.add(currConsume);
        }

        // 定义消费者收到消息内容的列表
        LinkedList<List<Integer>> consumerRes = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            consumerRes.add(new ArrayList<>());
        }

        // 从消息角度：消息被哪个消费者消费了
        for (int i = 0; i < messageQueue.size(); i++) {
            int[] currMessage = messageQueue.get(i);
            // 按照消费者优先级
            for (int j = consumeQueue.size() - 1; j >= 0; j--) {
                int[] currConsume = consumeQueue.get(j);
                int startTime = currConsume[0];
                int endTime = currConsume[1];
                if (startTime <= currMessage[0] && currMessage[0] < endTime) {
                    consumerRes.get(j).add(currMessage[1]);
                    break;
                }
            }
        }

        // 输出结果
        for (List<Integer> consume : consumerRes) {
            if (consume.isEmpty()) {
                System.out.println("-1");
            } else {
                String res = consume.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));
                System.out.println(res);
            }
        }

    }

}
