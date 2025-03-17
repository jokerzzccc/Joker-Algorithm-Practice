package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 斗地主之顺子
 * 题目描述
 * 在斗地主扑克牌游戏中， 扑克牌由小到大的顺序为：3,4,5,6,7,8,9,10,J,Q,K,A,2，玩家可以出的扑克牌阵型有：单张、对子、顺子、飞机、炸弹等。
 * 其中顺子的出牌规则为：由至少5张由小到大连续递增的扑克牌组成，且不能包含2。
 * 例如：{3,4,5,6,7}、{3,4,5,6,7,8,9,10,J,Q,K,A}都是有效的顺子；而{J,Q,K,A,2}、 {2,3,4,5,6}、{3,4,5,6}、{3,4,5,6,8}等都不是顺子。
 * 给定一个包含13张牌的数组，如果有满足出牌规则的顺子，请输出顺子。
 * 如果存在多个顺子，请每行输出一个顺子，且需要按顺子的第一张牌的大小（必须从小到大）依次输出。
 * 如果没有满足出牌规则的顺子，请输出No。
 * 输入描述
 * 13张任意顺序的扑克牌，每张扑克牌数字用空格隔开，每张扑克牌的数字都是合法的，并且不包括大小王：
 * 2 9 J 2 3 4 K A 7 9 A 5 6
 * 不需要考虑输入为异常字符的情况
 * 输出描述
 * 组成的顺子，每张扑克牌数字用空格隔开：
 * 3 4 5 6 7
 * 示例1
 * 输入
 * 2 9 J 2 3 4 K A 7 9 A 5 6
 * 输出
 * 3 4 5 6 7
 * 说明
 * 13张牌中，可以组成的顺子只有1组：3 4 5 6 7。
 * 示例2
 * 输入：
 * 2 9 J 10 3 4 K A 7 Q A 5 6
 * 输出：
 * 3 4 5 6 7
 * 9 10 J Q K A
 * 说明
 * 13张牌中，可以组成2组顺子，从小到大分别为：3 4 5 6 7 和 9 10 J Q K A
 * 示例3
 * 输入：
 * 2 9 9 9 3 4 K A 10 Q A 5 6
 * 输出：
 * No
 * 说明
 * 13张牌中，无法组成顺子。
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】双端队列 + 排序
 * 题目描述存在不明确之处，未具体说明是要求解最多数量的顺子，还是单个最长的顺子。实际机考按照最长的去找，通过率高于数量最多
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE2 {

    // 静态初始化一个用于映射扑克牌面到数字的HashMap，以方便后续比较大小
    public static final Map<String, Integer> CARD_TO_NUMBER = new HashMap();

    static {
        // 将每张扑克牌对应的面值映射到一个整数，其中2被认为是最大的牌
        CARD_TO_NUMBER.put("3", 3);
        CARD_TO_NUMBER.put("4", 4);
        CARD_TO_NUMBER.put("5", 5);
        CARD_TO_NUMBER.put("6", 6);
        CARD_TO_NUMBER.put("7", 7);
        CARD_TO_NUMBER.put("8", 8);
        CARD_TO_NUMBER.put("9", 9);
        CARD_TO_NUMBER.put("10", 10);
        CARD_TO_NUMBER.put("J", 11);
        CARD_TO_NUMBER.put("Q", 12);
        CARD_TO_NUMBER.put("K", 13);
        CARD_TO_NUMBER.put("A", 14);
        // 这里因为2不能是顺子，所以14变到了16，就相差不是1，技巧！！！
        CARD_TO_NUMBER.put("2", 16);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().trim().split(" ");
        scanner.close();
        // 对输入的扑克牌按照定义的牌面大小进行排序
        Arrays.sort(inputArr, Comparator.comparingInt(CARD_TO_NUMBER::get));

        // 存储所有可能的连续子序列
        List<LinkedList<String>> allSubSequence = new ArrayList<>();
        // 初始化当前正在检查的顺子序列
        LinkedList<String> currentStraight = new LinkedList<>();
        currentStraight.add(inputArr[0]);
        allSubSequence.add(currentStraight);
        // 从第二张牌开始遍历所有牌
        for (int i = 1; i < inputArr.length; i++) {
            String currentCard = inputArr[i];
            boolean isAdded = false;  // 标记当前牌是否已被添加到某个顺子中

            // 遍历当前已存在的所有顺子序列，尝试将当前牌加入
            for (LinkedList<String> subSequence : allSubSequence) {
                if (CARD_TO_NUMBER.get(currentCard) - CARD_TO_NUMBER.get(subSequence.getLast()) == 1) {
                    subSequence.add(currentCard);
                    isAdded = true;
                    break;
                }
            }

            // 如果当前牌没有加入到任何顺子中，创建一个新的顺子序列
            if (!isAdded) {
                LinkedList<String> newStraight = new LinkedList<>();
                newStraight.add(currentCard);
                allSubSequence.add(newStraight);
            }
        }

        // 筛选出长度至少为5的有效顺子序列
        List<LinkedList<String>> validSequence = allSubSequence.stream()
                .filter(subSequence -> subSequence.size() >= 5)
                .collect(Collectors.toList());

        if (validSequence.isEmpty()) {
            System.out.println("No");
        } else {
            validSequence.stream()
                    .sorted(Comparator.comparingInt(o -> CARD_TO_NUMBER.get(o.getLast())))
                    .forEach(subSequence -> {
                        System.out.println(String.join(" ", subSequence));
                    });
        }
    }

}