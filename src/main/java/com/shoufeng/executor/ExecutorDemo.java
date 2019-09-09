package com.shoufeng.executor;

import java.util.concurrent.*;

/**
 * 线程池样例
 *
 * @author shoufeng
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorDemo executorDemo = new ExecutorDemo();
//        executorDemo.method1();
        executorDemo.method2();
    }

    //通过executors构造线程池
    public void method1() {
        //        System.out.println(Runtime.getRuntime().availableProcessors());
        //单个线程线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //固定个数线程线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //不定个数线程线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(() -> {
//            fixedThreadPool.execute(() -> {
//            cachedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "启动线程");
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        singleThreadExecutor.shutdown();
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
    }

    //自定义线程池
    public void method2() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ": i");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
