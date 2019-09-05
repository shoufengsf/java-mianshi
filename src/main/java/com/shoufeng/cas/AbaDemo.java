package com.shoufeng.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题
 *
 * @author shoufeng
 */
public class AbaDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(0,0);
        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行" + integerAtomicStampedReference.compareAndSet(0,1,stamp,stamp+1));
            stamp = integerAtomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "执行" + integerAtomicStampedReference.compareAndSet(1,0,stamp,stamp+1));
        }).start();
        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行" + integerAtomicStampedReference.compareAndSet(0,2,stamp,stamp+1));
        }).start();
    }
}
