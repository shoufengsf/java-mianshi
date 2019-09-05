package com.shoufeng.volatiledemo;

/**
 * 验证volatile的可见性
 *
 * @author shoufeng
 */
public class VolatileDemo1 {
    //    public int num = 0;
    public volatile int num = 0;

    public static void main(String[] args) {
        VolatileDemo1 volatileDemo = new VolatileDemo1();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileDemo.changeNum();
                System.out.println("修改了num");
            }
        }).start();
        while (volatileDemo.num == 0) {
        }
        System.out.println("main线程感知num被修改");
    }

    public void changeNum() {
        this.num = 100;
    }
}
