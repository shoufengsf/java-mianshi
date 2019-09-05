package com.shoufeng.singleton;


/**
 * 单例模式 懒汉 多线程情况下存在问题
 * @author shoufeng
 */
public class SingletonDemo1 {
    public volatile static SingletonDemo1 singletonDemo1 = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "执行了一次构造方法");
    }

    //加synchronized解决对线程情况下的单例
    public synchronized static SingletonDemo1 getInstance1(){
        if (singletonDemo1 == null){
            singletonDemo1 = new SingletonDemo1();
            return singletonDemo1;
        }
        return singletonDemo1;
    }

    //双检锁
    public static SingletonDemo1 getInstance2(){
        if (singletonDemo1 == null){
            synchronized (SingletonDemo1.class){
                //如果不再做一次空判断将导致多次创建
                //如果singletonDemo1不volatile可能由于指令重排的原因导致singletonDemo1不为null但是未完成初始化
                if (singletonDemo1 == null){
                    singletonDemo1 = new SingletonDemo1();
                    return singletonDemo1;
                }
            }
        }
        return singletonDemo1;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo1.getInstance());
//        System.out.println(SingletonDemo1.getInstance());
//        System.out.println(SingletonDemo1.getInstance());
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
//                SingletonDemo1.getInstance1();
                SingletonDemo1.getInstance2();
            }).start();
        }
    }
}
