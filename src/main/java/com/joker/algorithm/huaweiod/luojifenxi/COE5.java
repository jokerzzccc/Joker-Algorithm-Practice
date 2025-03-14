package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * boss 的收入
 * 题目描述
 * 一个XX产品行销总公司，只有一个boss，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销。
 * <p>
 * 规定，每个月，下级分销需要将自己的总收入（自己的+下级上交的）每满100元上交15元给自己的上级。
 * <p>
 * 现给出一组分销的关系，和每个分销的收入，请找出boss并计算出这个boss的收入。
 * <p>
 * 比如：
 * <p>
 * 收入100元，上交15元；
 * 收入199元（99元不够100），上交15元；
 * 收入200元，上交30元。
 * 输入：
 * <p>
 * 分销关系和收入：[[分销id 上级分销id 收入], [分销id 上级分销id 收入], [分销id 上级分销id 收入]]
 * <p>
 * 分销ID范围： 0…65535
 * 收入范围：0…65535，单位元
 * 提示： 输入的数据只存在1个boss，不存在环路
 * <p>
 * 输出：
 * <p>
 * [boss的ID, 总收入]
 * <p>
 * 输入描述
 * 第一行输入关系的总数量 N
 * 第二行开始，输入关系信息，格式：
 * <p>
 * 分销ID 上级分销ID 收入
 * <p>
 * 比如：
 * <p>
 * 5
 * 1 0 100
 * 2 0 199
 * 3 0 200
 * 4 0 200
 * 5 0 200
 * 输出描述
 * 输出：
 * <p>
 * boss的ID 总收入
 * <p>
 * 比如：
 * 0 120
 * <p>
 * 备注
 * 给定的输入数据都是合法的，不存在环路，重复的
 * <p>
 * <p>
 * 题型分析：
 * 多叉树 + 递归 + 回溯
 *
 * @author jokerzzccc
 * @date 2025/3/11
 */
public class COE5 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 分销节点总数
        int n = Integer.parseInt(input.nextLine().trim());

        // 记录每个分销商的收入
        HashMap<Integer, Long> incomeMap = new HashMap<>();
        // 记录所有的分销商 ID
        HashSet<Integer> idSet = new HashSet<>();
        // 记录子分销商到父分销商的映射关系
        HashMap<Integer, Integer> childToParent = new HashMap<>();
        // 记录父分销商到其所有子分销商的映射关系
        HashMap<Integer, LinkedList<Integer>> parentToChildren = new HashMap<>();

        // 构建初始数据
        for (int i = 0; i < n; i++) {
            String[] curInfo = input.nextLine().split("\\s+");
            // 解析当前子分销商的 ID
            int childId = Integer.parseInt(curInfo[0]);
            // 解析当前子分销商的父分销商 ID
            int parentId = Integer.parseInt(curInfo[1]);
            // 解析当前子分销商的收入
            long childIncome = Long.parseLong(curInfo[2]);

            incomeMap.put(childId, childIncome);
            idSet.add(childId);
            idSet.add(parentId);

            childToParent.put(childId, parentId);
            parentToChildren.putIfAbsent(parentId, new LinkedList<>());
            parentToChildren.get(parentId).add(childId);

        }

        // 寻找顶级分销商 (即没有父分销商的分销商，即 boss)
        for (Integer id : idSet) {
            // 如果当前分销商 ID 不在 childToParent 映射中，说明它是顶级分销商
            if (!childToParent.containsKey(id)) {
                // 初始化顶级分销商的收入为0，因为它自身没有任何直接收入
                incomeMap.put(id, 0L);
                dfsCalcTotalIncome(id, parentToChildren, incomeMap);
                // 输出顶级分销商的 ID 和其计算出的总收入
                System.out.println(id + " " + incomeMap.get(id));
                // 一旦找到顶级分销商，结束循环
                break;
            }

        }

        input.close();

    }

    /**
     * 使用递归的深度优先搜索算法计算分销商的总收入，包括从下级分销商获取的部分
     *
     * @param rootId
     * @param parentToChildren
     * @param incomeMap
     */
    private static void dfsCalcTotalIncome(Integer rootId, HashMap<Integer, LinkedList<Integer>> parentToChildren, HashMap<Integer, Long> incomeMap) {
        LinkedList<Integer> children = parentToChildren.get(rootId);
        if (children == null || children.isEmpty()) {
            return;
        }
        for (Integer child : children) {
            // 递归计算子分销商的总收入
            dfsCalcTotalIncome(child, parentToChildren, incomeMap);
            // 计算父分销商从该子分销商处获取的提成收入
            long additionalIncome = incomeMap.get(child) / 100 * 15;
            // 将提成收入累加到父分销商的总收入中
            incomeMap.put(rootId, incomeMap.get(rootId) + additionalIncome);
        }
    }

}