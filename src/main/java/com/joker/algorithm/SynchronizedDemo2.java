package com.joker.algorithm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class SynchronizedDemo2 {
//
//    Object object = new Object();
//    public void method1() {
//        synchronized (object) {
//
//        }
//        method2();
//    }
//
//    private synchronized void method2() {
//
//    }
//
//    public void method3() {
//        ReentrantLock fairLock = new ReentrantLock(true);
//
//    }

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();

        // 创建线程 1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁操作
                lock.lock();
                System.out.println("线程 1:获取到锁.");
                // 线程 1 未释放锁
            }
        });
        t1.start();

        // 创建线程 2
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 先休眠 0.5s，让线程 1 先执行
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取锁
                try {
                    System.out.println("线程 2:尝试获取锁.");
                    lock.lockInterruptibly(); // 可中断锁
                    System.out.println("线程 2:获取锁成功.");
                } catch (InterruptedException e) {
                    System.out.println("线程 2:执行已被中断.");
                }
            }
        });
        t2.start();

        // 等待 2s 后,终止线程 2
        Thread.sleep(2000);
        if (t2.isAlive()) { // 线程 2 还在执行
            System.out.println("执行线程的中断.");
            t2.interrupt();
        } else {
            System.out.println("线程 2:执行完成.");
        }
    }

}
