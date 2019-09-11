package com.shoufeng.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.VM;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常用堆内存溢出，样例
 *
 * @author shoufeng
 */
public class OomDemo {

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * -XX:+PrintGCDetails
     */
    public void method1() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] bytes = new byte[1024 * 1024 * 10];
            list.add(bytes);
        }
    }

    /**
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * -XX:+PrintGCDetails -Xms10M -Xmx10M
     */
    public void method2() {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * java.lang.OutOfMemoryError: Direct buffer memory
     * -XX:+PrintGCDetails -Xms10M -Xmx10M -XX:MaxDirectMemorySize=5M
     */
    public void method3() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 6);
    }

    /**
     * java.lang.OutOfMemoryError: unable to create new native thread
     */
    public void method4() {
        AtomicInteger i = new AtomicInteger(0);
        try {
            while (true) {
                new Thread(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "" + i.incrementAndGet()).start();
            }
        } finally {
            System.out.println("总共创建了：" + i.get() + "个线程");
        }

    }

    static class DemoInner{

    }

    /**
     * -XX:+PrintGCDetails -XX:MaxMetaspaceSize=20M -XX:MetaspaceSize=20M
     */
    public void method5(){
        AtomicInteger i = new AtomicInteger(0);
        try {
            while (true){
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(DemoInner.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o,objects);
                    }
                });
                enhancer.create();
                i.incrementAndGet();
            }
        }finally {
            System.out.println("加载了: " + i.get() + "次");
        }

    }

    public static void main(String[] args) {
        //查看直接内存大小
//        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024);
        OomDemo oomDemo = new OomDemo();
//        oomDemo.method1();
//        oomDemo.method2();
//        oomDemo.method3();
//        oomDemo.method4();
        oomDemo.method5();
    }
}
