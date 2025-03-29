package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 最长的顺子
 * 题目描述
 * 斗地主起源于湖北十堰房县，据说是一位叫吴修全的年轻人根据当地流行的扑克玩法“跑得快”改编的，如今已风靡整个中国，并流行于互联网上。
 * 牌型：单顺，又称顺子，最少5张牌，最多12张牌(3…A)不能有2，也不能有大小王，不计花色。
 * 例如： 3-4-5-6-7-8，7-8-9-10-J-Q，3-4-5-6-7-8-9-10-J-Q-K-A
 * 可用的牌 3<4<5<6<7<8<9<10<J<Q<K<A<2<B(小王)<C(大王)，每种牌除大小王外有四种花色
 * (共有13×4+2张牌)
 * 输入：
 * 手上有的牌
 * 已经出过的牌(包括对手出的和自己出的牌)
 * 输出：
 * 对手可能构成的最长的顺子(如果有相同长度的顺子，输出牌面最大的那一个)，
 * 如果无法构成顺子，则输出 NO-CHAIN。
 * 输入描述
 * 输入的第一行为当前手中的牌
 * 输入的第二行为已经出过的牌
 * 输出描述
 * 示例1
 * 输入
 * 3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A
 * 4-5-6-7-8-8-8
 * 输出
 * 9-10-J-Q-K-A
 * 说明
 * 无
 * 示例2
 * 输入
 * 3-3-3-3-8-8-8-8
 * K-K-K-K
 * 输出
 * NO-CHAIN
 * 说明
 * 剩余的牌无法构成顺子
 * ————————————————
 * 题型分析
 * 【中等】逻辑分析 + 滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/27
 */
public class OD25A05 {

    // K-牌面值，V-下标
    static Map<String, Integer> poke2NumMap = new LinkedHashMap<String, Integer>() {{
        put("3", 0);
        put("4", 1);
        put("5", 2);
        put("6", 3);
        put("7", 4);
        put("8", 5);
        put("9", 6);
        put("10", 7);
        put("J", 8);
        put("Q", 9);
        put("K", 10);
        put("A", 11);
    }};

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] ownPokes = scanner.nextLine().split("-");
        String[] outPokes = scanner.nextLine().split("-");
        scanner.close();

        // 自己的牌 + 出了的牌
        // 按照 [3...A] 的顺序，下标就对应牌面，数组的值就是牌的个数
        int[] mingPokes = new int[12];
        for (int i = 0; i < ownPokes.length; i++) {
            if (ownPokes[i].equals("2") || Objects.equals(ownPokes[i], "B") || Objects.equals(ownPokes[i], "C")) {
                continue;
            }
            mingPokes[poke2NumMap.get(ownPokes[i])]++;
        }
        for (int n = 0; n < outPokes.length; n++) {
            if (outPokes[n].equals("2") || Objects.equals(outPokes[n], "B") || Objects.equals(outPokes[n], "C")) {
                continue;
            }
            mingPokes[poke2NumMap.get(outPokes[n])]++;
        }
        // 对手的牌
        int[] enemyPokes = new int[12];
        Arrays.fill(enemyPokes, 4);
        for (int i = 0; i < enemyPokes.length; i++) {
            enemyPokes[i] -= mingPokes[i];
        }

        // 顺子起始位置
        int maxChainCnt = 0;
        // 顺子起始索引
        int startIndex = -1;
        int left = 0;
        int right = 0;
        while (left < enemyPokes.length - 5) {
            // 当前顺子长度
            int currChainCnt = 0;
            while (right < enemyPokes.length && enemyPokes[right] > 0) {
                currChainCnt++;
                right++;
            }
            if (currChainCnt >= 5 && currChainCnt >= maxChainCnt) {
                maxChainCnt = currChainCnt;
                startIndex = left;
            }

            left = right + 1;
            right = left;
        }

        List<String> res = new ArrayList<>();
        if (startIndex == -1) {
            System.out.println("NO-CHAIN");
        } else {
            for (int i = startIndex; i < startIndex + maxChainCnt; i++) {
                res.add((String) poke2NumMap.keySet().toArray()[i]);
            }
            System.out.println(String.join("-", res));
        }

    }

}
