package com.shoufeng.volatiledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证volatile的有序性（禁止指令重排）
 *
 * @author shoufeng
 */
public class VolatileDemo3 {

    public volatile int value = 0;
    public volatile boolean flag = false;

    public void methodA(){
        value = 1;
        flag = true;
    }

    public void methodB(){
        while (!flag){
        }
        value *= 5;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        VolatileDemo3 volatileDemo3 = new VolatileDemo3();
        executorService.execute(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileDemo3.methodA();
        });
        executorService.execute(() -> {
            volatileDemo3.methodB();
            System.out.println(volatileDemo3.value);
        });
        executorService.shutdown();
    }
}
