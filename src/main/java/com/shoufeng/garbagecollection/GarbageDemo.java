package com.shoufeng.garbagecollection;

import java.util.ArrayList;

/**
 * 垃圾收集器样例
 *
 * @author shoufeng
 */
public class GarbageDemo {

    /**
     * 查看默认垃圾收集器
     * java -XX:+PrintCommandLineFlags -version
     * -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
     * <p>
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  (DefNew + Tenured)
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC  (ParNew + Tenured)
     * ParNew不推荐和Serial Old一起使用
     * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  (PSYoungGen + ParOldGen)
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  (par new generation + concurrent mark-sweep generation)
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
     * SerialOldGC串行老年代收集器已经别java8抛弃
     * Unrecognized VM option 'UseSerialOldGC'
     * Did you mean '(+/-)UseSerialGC'?
     * Error: Could not create the Java Virtual Machine.
     * Error: A fatal exception has occurred. Program will exit.
     * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
     *
     * @param args
     */
    public static void main(String[] args) {
//        ArrayList<Byte[]> list = new ArrayList<Byte[]>();
//        while (true) {
//            list.add(new Byte[1024]);
//        }
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = new String(i + "").intern();
            list.add(s);
        }
    }
}
