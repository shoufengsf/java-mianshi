package com.shoufeng.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 引用样例
 *
 * @author shoufeng
 */
public class ReferenceDemo {

    /**
     * 强引用
     */
    public void method1() {
        //这样定义默认就是强引用
        Object o1 = new Object();
        //o2引用赋值
        Object o2 = o1;
        //置空
        o1 = null;
        System.gc();
        System.out.println("o1: " + o1);
        //o2不会被回收
        System.out.println("o2: " + o2);
    }

    /**
     * 软引用
     * 创建大对象，故意造成OOM，导致SoftReference被回收
     * -XX:InitialHeapSize=5M -XX:MaxHeapSize=5M (等价于 -xms5m -xmx5m)
     */
    public void method2() {
        //强引用
        Object o1 = new Object();
        //软引用
        SoftReference objectSoftReference = new SoftReference<>(o1);
        o1 = null;
        System.gc();
        try {
            while (true) {
                byte[] bytes = new byte[10 * 1024 * 1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("o1: " + o1);
            System.out.println("objectSoftReference.get(): " + objectSoftReference.get());
        }
    }

    /**
     * 弱引用
     */
    public void method3() {
        Object o1 = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(o1);
        o1 = null;
        System.gc();
        System.out.println("o1: " + o1);
        System.out.println("objectWeakReference.get(): " + objectWeakReference.get());
    }

    /**
     * weakHashMap
     */
    public void method4() {
        HashMap<String, String> hashMap = new HashMap<>();
        String hashMapKey = new String("1");
        String hashMapValue = "hashMap";
        hashMap.put(hashMapKey, hashMapValue);
        hashMapKey = null;
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        //直接赋值"1"不会被回收 用的是常量池的"1"
//        String weakHashMapKey = "1";
        //new String("1") 会被回收，用的是堆内开辟的string类型的"1"
        String weakHashMapKey = new String("1");
        String weakHashMapValue = "hashMap";
        weakHashMap.put(weakHashMapKey, weakHashMapValue);
        weakHashMapKey = null;
        System.gc();
        System.out.println("hashMap: " + hashMap);
        System.out.println("weakHashMap: " + weakHashMap);
    }

    /**
     * 虚引用
     */
    public void method5() throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, objectReferenceQueue);
        System.out.println("=========GC前==========");
        System.out.println("o1: " + o1);
        System.out.println("phantomReference.get(): " + phantomReference.get());
        System.out.println("objectReferenceQueue.poll(): " + objectReferenceQueue.poll());
        o1 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("=========GC后==========");
        System.out.println("o1: " + o1);
        System.out.println("phantomReference.get(): " + phantomReference.get());
        System.out.println("objectReferenceQueue.poll(): " + objectReferenceQueue.poll());
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceDemo referenceDemo = new ReferenceDemo();
//        referenceDemo.method1();
//        referenceDemo.method2();
//        referenceDemo.method3();
//        referenceDemo.method4();
        referenceDemo.method5();
    }
}
