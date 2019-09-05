package com.shoufeng.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带版本号的原子引用
 *
 * @author shoufeng
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(0,0);
        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();
            integerAtomicStampedReference.compareAndSet(0,1,stamp,stamp+1);
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(integerAtomicStampedReference.compareAndSet(1,2,0,1));
        }).start();
    }
}
