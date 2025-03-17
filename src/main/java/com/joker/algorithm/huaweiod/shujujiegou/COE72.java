package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 热点网站统计
 * 题目描述
 * 企业路由器的统计页面，有一个功能需要动态统计公司访问最多的网页URL top N。请设计一个算法，可以高效动态统计Top N的页面。
 * 输入描述
 * 每一行都是一个URL或一个数字，如果是URL，代表一段时间内的网页访问； 如果是一个数字N，代表本次需要输出的Top N个URL。
 * 输入约束：
 * 1、总访问网页数量小于5000个，单网页访问次数小于65535次；
 * 2、网页URL仅由字母，数字和点分隔符组成，且长度小于等于127字节； 3、数字是正整数，小于等于10且小于当前总访问网页数；
 * 输出描述
 * 行输入要对应一行输出，输出按访问次数排序的前N个URL，用逗号分隔。
 * 输出要求：
 * 1、每次输出要统计之前所有输入，不仅是本次输入；
 * 2、如果有访问次数相等的URL，按URL的字符串字典序升序排列，输出排序靠前的URL；
 * 示例1
 * 输入
 * news.qq.com
 * news.sina.com.cn
 * news.qq.com
 * news.qq.com
 * game.163.com
 * game.163.com
 * www.huawei.com
 * www.cctv.com
 * 3
 * www.huawei.com
 * www.cctv.com
 * www.huawei.com
 * www.cctv.com
 * www.huawei.com
 * www.cctv.com
 * www.huawei.com
 * www.cctv.com
 * www.huawei.com
 * 3
 * <p>
 * 输出
 * news.qq.com,game.163.com,news.sina.com.cn
 * www.huawei.com,www.cctv.com,news.qq.com
 * 示例2
 * 输入
 * news.qq.com
 * www.cctv.com
 * 1
 * www.huawei.com
 * www.huawei.com
 * 2
 * 3
 * 输出
 * news.qq.com
 * www.huawei.com,news.qq.com
 * www.huawei.com,news.qq.com,www.cctv.com
 * ————————————————
 * 题型分析
 * 【简单】字符串 + 排序
 * 1. 思考（map 的 entrySet 转 List ，来构建 Comparator）；
 * 2.
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE72 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>(); // 创建一个字符串列表，用于存储输入的每一行数据
        Map<String, Integer> cache = new HashMap<>(); // 创建一个哈希表，用于存储每个 URL 出现的次数
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine().trim();
            if (currLine.isEmpty()) {
                break;
            }
            lines.add(currLine);
            if (currLine.matches("^\\d+$")) {
                // 如果该行数据只包含数字，说明已经读取到了数字，需要输出统计了
                System.out.println(sortURL(lines, cache)); // 调用 sortURL 方法进行处理，并输出结果
                lines.clear();
            }
        }
        scanner.close();
    }

    private static String sortURL(List<String> lines, Map<String, Integer> cache) {
        // TOP n 个 URL（最后一个是数字，需要移除）
        int n = Integer.parseInt(lines.remove(lines.size() - 1));
        // 更新 URL 次数缓存
        for (String line : lines) {
            cache.put(line, cache.getOrDefault(line, 0) + 1);
        }

        // 将哈希表中的每一项转换成一个键值对，并存入一个列表中(方便排序，编写 Comparator)
        List<Map.Entry<String, Integer>> cacheList = new ArrayList<>(cache.entrySet());
        cacheList.sort((o1, o2) -> {
            // 对列表进行排序，按照计数从大到小排序，如果计数相同则按照字典序从小到大排序
            int res = o2.getValue() - o1.getValue();
            return res != 0 ? res : o1.getKey().compareTo(o2.getKey());
        });

        // 取出前 n 个 URL，并将它们拼接成一个字符串
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n && i < cacheList.size(); i++) {
            res.append(cacheList.get(i).getKey())
                    .append(",");
        }

        // 返回结果
        if (res.length() > 0) {
            // 如果字符串不为空，则删除最后一个逗号
            res.deleteCharAt(res.length() - 1);
        }

        return res.toString();
    }

}
