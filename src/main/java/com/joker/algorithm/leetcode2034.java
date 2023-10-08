package com.joker.algorithm;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * <p>
 * 股票价格波动
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/8
 **/
public class leetcode2034 {

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        System.out.println(stockPrice.current()); // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
        System.out.println(stockPrice.maximum());    // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
        stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        System.out.println(stockPrice.maximum());     // 返回 5 ，更正后最高价格为 5 。
        stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        System.out.println(stockPrice.minimum());    // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
    }

    /**
     * 解法一：哈希表 + 有序集合（TreeMap）
     */
    static class StockPrice {

        /**
         * 为了获取 currentPrice
         */
        private HashMap<Integer, Integer> timePriceMap;
        /**
         * 有序集合：维护股票价格. K - 价格；V - 价格出现的次数
         * 为了更快的获取 maxPrice, minPrice
         */
        private TreeMap<Integer, Integer> prices;

        private int maxTimestamp;

        public StockPrice() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<>();
            prices = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(timestamp, maxTimestamp);

            int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
            timePriceMap.put(timestamp, price);

            // 时间戳重复：则将原价格从有序集合中删除
            if (prevPrice > 0) {
                // 统计 prevPrice 的数量（因为，timestamp 不同时，price 也可能会重）
                prices.merge(prevPrice, -1, Integer::sum);
                if (prices.get(prevPrice) == 0) {
                    prices.remove(prevPrice);
                }
            }

            prices.merge(price, 1, Integer::sum);
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            return prices.lastKey();
        }

        public int minimum() {
            return prices.firstKey();
        }
    }


}
