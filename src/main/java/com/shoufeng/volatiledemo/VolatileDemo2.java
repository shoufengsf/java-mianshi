package com.shoufeng.volatiledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证volatile的不保证原子性
 *
 * @author shoufeng
 */
public class VolatileDemo2 {
    public volatile int num = 0;
    public AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        VolatileDemo2 volatileDemo2 = new VolatileDemo2();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    volatileDemo2.num++;
                    volatileDemo2.atomicInteger.getAndIncrement();
                }
            });
        }
        System.out.println(volatileDemo2.num);
        System.out.println(volatileDemo2.atomicInteger.get());
        executorService.shutdown();
    }
}
