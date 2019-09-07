package com.shoufeng.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义自旋锁
 *
 * @author shoufeng
 */
public class MySpinLock {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock(){
        while (!threadAtomicReference.compareAndSet(null,Thread.currentThread())){
        }
    }

    public Boolean unLock(){
        return threadAtomicReference.compareAndSet(Thread.currentThread(),null);
    }

    public static void main(String[] args) {
        MySpinLock mySpinLock = new MySpinLock();
        new Thread(() -> {
            mySpinLock.lock();
            System.out.println(Thread.currentThread().getName() +"获取锁，并开始休眠5秒");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"释放锁：" + mySpinLock.unLock());
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLock.lock();
            System.out.println(Thread.currentThread().getName() +"获取锁");
            System.out.println(Thread.currentThread().getName() +"释放锁：" + mySpinLock.unLock());
        }).start();
    }
}
