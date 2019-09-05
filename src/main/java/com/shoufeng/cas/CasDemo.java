package com.shoufeng.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas样例
 *
 * @author shoufeng
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(5, 2);
        atomicInteger.compareAndSet(10, 1);
        System.out.println(atomicInteger.get());
    }
}
