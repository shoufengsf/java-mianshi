package com.shoufeng.deadlock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁样例
 *
 * @author shoufeng
 */
public class DeadLockDemo {
    private String lockA = "lockA";
    private String lockB = "lockB";

    private Lock reentrantLockA = new ReentrantLock();
    private Lock reentrantLockB = new ReentrantLock();

    public void method1() throws InterruptedException {
        synchronized (lockA) {
            System.out.println("method1获取lockA");
            TimeUnit.SECONDS.sleep(2L);
            synchronized (lockB) {
                System.out.println("method1获取lockA后获取lockB");
            }
        }
    }

    public void method2() throws InterruptedException {
        synchronized (lockB) {
            System.out.println("method2获取lockB");
            TimeUnit.SECONDS.sleep(2L);
            synchronized (lockA) {
                System.out.println("method1获取lockB后获取lockA");
            }
        }
    }

    public void method3() {
        try {
            reentrantLockA.lock();
            System.out.println("method3获取reentrantLockA");
            TimeUnit.SECONDS.sleep(2);
            reentrantLockB.lock();
            System.out.println("method3获取reentrantLockA后获取reentrantLockB");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLockA.unlock();
            reentrantLockB.unlock();
        }
    }

    public void method4() {
        try {
            reentrantLockB.lock();
            System.out.println("method4获取reentrantLockB");
            TimeUnit.SECONDS.sleep(2);
            reentrantLockA.lock();
            System.out.println("method4获取reentrantLockB后获取reentrantLockA");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLockA.unlock();
            reentrantLockB.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        DeadLockDemo deadLockDemo = new DeadLockDemo();
//        threadPoolExecutor.execute(() -> {
//            try {
//                deadLockDemo.method1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        threadPoolExecutor.execute(() -> {
//            try {
//                deadLockDemo.method2();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        threadPoolExecutor.execute(deadLockDemo::method3);
        threadPoolExecutor.execute(deadLockDemo::method4);
    }
}
