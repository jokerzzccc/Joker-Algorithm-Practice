package com.joker.algorithm.huaweiod.greedy;

import java.util.*;

/**
 * 内存资源分配
 * 题目描述
 * 有一个简易内存池，内存按照大小粒度分类，每个粒度有若干个可用内存资源，用户会进行一系列内存申请，需要按需分配内存池中的资源返回申请结果成功失败列表。
 * 分配规则如下:
 * 1.分配的内存要大于等于内存的申请量，存在满足需求的内存就必须分配，优先分配粒度小的，但内存不能拆分使用:
 * 2.需要按申请顺序分配，先申请的先分配，有可用内存分配则申请结果为 true;
 * 3.没有可用则返回 false。
 * 注意:不考虑内存释放
 * 输入描述
 * 输入为两行字符串
 * 第一行为内存池资源列表，包含内存粒度数据信息，粒度数据间用逗号分割 。一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量 。资源列表不大于 1024 。每个粒度的数量不大于 4096. 第二行为申请列表，申请的内存大小间用逗号分割 。申请列表不大于 100000.
 * 如
 * 64:2,128:1,32:4,1:128
 * 50,36,64,128,127
 * 输出描述
 * 输出为内存池分配结果
 * 如 true,true,true,false.false、
 * 说明：
 * 内存池资源包含：64K共2个、128K共1个、32K共4个、1K共128个的内存资源；
 * 针对 50，36，64，128，127的内存申请序列，分配的内存依次是：64,64,128,NULL,NULL
 * 第三次申请内存时已经将128分配出去，因此输出结果是：
 * true,true,true,false,false
 * -----------------
 * 题型分析：
 * 【简单】 字符串 +一维数组 + 贪心算法 + 排序
 *
 * @author jokerzzccc
 * @date 2025/3/20
 */
public class COE46 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] availableResources = scanner.nextLine().trim().split(",");
        int[] applyResources = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        // 数据预处理
        // 存储所有内存块
        List<Integer> availableResourcesList = new LinkedList<>();
        for (String availableResource : availableResources) {
            String[] resourceInfo = availableResource.split(":");
            int resourceSize = Integer.parseInt(resourceInfo[0]);
            int resourceCount = Integer.parseInt(resourceInfo[1]);

            for (int i = 0; i < resourceCount; i++) {
                availableResourcesList.add(resourceSize);
            }
        }
        Collections.sort(availableResourcesList);

        // 遍历申请内存
        List<String> result = new ArrayList<>();
        for (int applyResource : applyResources) {
            int currIndex = 0;
            boolean isFound = false;
            // 寻找匹配的内存
            // 贪心策略；找到第一个比申请内存大的。
            for (; currIndex < availableResourcesList.size(); currIndex++) {
                int currMemory = availableResourcesList.get(currIndex);
                if (currMemory >= applyResource) {
                    result.add("true");
                    isFound = true;
                    availableResourcesList.remove(currIndex);
                    break;
                }
            }

            if (!isFound) {
                result.add("false");
            }
        }

        System.out.println(String.join(",", result));

    }

}
