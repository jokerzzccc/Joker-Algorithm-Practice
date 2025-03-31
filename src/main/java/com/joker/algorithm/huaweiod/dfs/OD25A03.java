package com.joker.algorithm.huaweiod.dfs;

import java.util.*;

/**
 * 文件目录大小
 *
 * @author jokerzzccc
 * @date 2025/3/30
 */
public class OD25A03 {

    public static Integer resSum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] init_params = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int m = init_params[0];
        int n = init_params[1];
        // PARENT - CHILD List
        Map<Integer, List<Integer>> parent2childListMap = new HashMap<>();
        // NODE ID - SIZE
        Map<Integer, Integer> node2sizeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] currCategory = scanner.nextLine().split(" ");
            int parentId = Integer.parseInt(currCategory[0]);
            int size = Integer.parseInt(currCategory[1]);
            node2sizeMap.put(parentId, size);
            String childName = currCategory[2]
                    .replace("(", "")
                    .replace(")", "");
            if ("".equals(childName)) {
                continue;
            }
            int[] childList = Arrays.stream(childName.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            parent2childListMap.putIfAbsent(parentId, new ArrayList<>());
            for (int child : childList) {
                parent2childListMap.get(parentId).add(child);
            }
        }

        scanner.close();

        curr_dfs(parent2childListMap, node2sizeMap, n);
        System.out.println(resSum);
    }

    private static void curr_dfs(Map<Integer, List<Integer>> parent2childListMap, Map<Integer, Integer> node2sizeMap, int rootId) {
        // 如果无效目录，跳过
        if (!parent2childListMap.containsKey(rootId)) {
            return;
        }
        // 计算当前目录的值
        resSum += node2sizeMap.get(rootId);

        List<Integer> currList = parent2childListMap.get(rootId);
        if (currList == null || currList.isEmpty()) {
            return;
        }
        for (Integer child : currList) {
            curr_dfs(parent2childListMap, node2sizeMap, child);
        }
    }

}
