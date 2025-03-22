package com.joker.algorithm.huaweiod.sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 手机App防沉迷系统
 * <p>
 * 问题描述
 * 智能手机方便了我们生活的同时, 也侵占了我们不少的时间. 手机App防沉迷系统能够让我们每天合理地规划手机App使用时间, 在正确的时间做正确的事.
 * 它的大概原理是这样的:
 * 在一天24小时内, 可以注册每个App的允许使用时段
 * 一个时间段只能使用一个App
 * App有优先级, 数值越高, 优先级越高. 注册使用时段时, 如果高优先级的App时间和低优先级的时段有冲突, 则系统会自动注销低优先级的时段, 如果App的优先级相同, 则后添加的App不能注册
 * 请编程实现, 根据输入数据注册App, 并根据输入的时间点, 返回时间点使用的App名称, 如果该时间点没有注册任何App, 请返回字符串 NA.
 * 输入格式
 * 第一行表示注册的App数量 N（N ≤ 100）
 * 第二部分包括 N 行, 每行表示一条App注册数据
 * 最后一行输入一个时间点, 程序即返回该时间点使用的App
 * 2
 * App1 1 09:00 10:00
 * App2 2 11:00 11:30
 * 09:30
 * 数据说明如下:
 * N行注册数据以空格分隔, 四项数依次表示: App名称、优先级、起始时间、结束时间
 * 优先级1~5, 数字越大, 优先级越高
 * 时间格式 HH:MM, 小时和分钟都是两位, 不足两位前面补0
 * 起始时间需小于结束时间, 否则注册不上
 * 注册信息中的时间段包含起始时间点, 不包含结束时间点
 * 输出格式
 * 输出一个字符串, 表示App名称, 或NA表示空闲时间
 * 示例输入1
 * 1
 * App1 1 09:00 10:00
 * 09:30
 * 示例输出1
 * App1
 * 示例输入2
 * 2
 * App1 1 09:00 10:00
 * App2 2 09:10 09:30
 * 09:20
 * 示例输出2
 * App2
 * <p>
 * 示例输入3
 * 2
 * App1 1 09:00 10:00
 * App2 2 09:10 09:30
 * 09:50
 * 示例输出3
 * NA
 * <p>
 * -------------
 * 题型分析
 * 【中等】排序
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE29 {

    static class App {

        private String name;
        private int priority;
        private int startTime;
        private int endTime;

        public App(String name, int priority, int startTime, int endTime) {
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<App> apps = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] rows = scanner.nextLine().split(" ");
            String name = rows[0];
            int priority = Integer.parseInt(rows[1]);
            int startTime = convertTimeToInt(rows[2]);
            int endTime = convertTimeToInt(rows[3]);
            App app = new App(name, priority, startTime, endTime);
            apps.add(app);
        }
        String time = scanner.nextLine();
        int targetTime = convertTimeToInt(time);
        scanner.close();

        // 可以注册的 APP
        List<App> registeredApps = new ArrayList<>();
        for (App app : apps) {
            // 如果起始时间不小于结束时间，则跳过
            if (app.startTime >= app.endTime) {
                continue;
            }
            boolean isAdded = true;

            for (int i = registeredApps.size() - 1; i >= 0; i--) {
                App registered = registeredApps.get(i);
                // 如果存在时间冲突，注销低优先级的App, 优先级相同，则后添加的 APP 不能注册
                if (Math.max(app.startTime, registered.startTime) < Math.min(app.endTime, registered.endTime)) {
                    // 此时 app 就是后添加的，registered 就是先添加的
                    if (app.priority > registered.priority) {
                        registeredApps.remove(i);
                    } else {
                        isAdded = false;
                        continue;
                    }
                }
            }

            if (isAdded) {
                // 将当前App添加到已注册App列表中
                registeredApps.add(app);
            }
        }

        String resName = "NA";
        // 输出结果
        for (App registeredApp : registeredApps) {
            if (targetTime >= registeredApp.startTime && targetTime < registeredApp.endTime) {
                resName = registeredApp.name;
                break;
            }
        }

        System.out.println(resName);
    }

    /**
     * 转换为分钟
     *
     * @param str
     * @return
     */
    private static int convertTimeToInt(String str) {
        String[] split = str.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

}
