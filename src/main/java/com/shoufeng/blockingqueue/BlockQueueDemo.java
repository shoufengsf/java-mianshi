package com.shoufeng.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列样例
 *
 * @author shoufeng
 */
public class BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockQueueDemo blockQueueDemo = new BlockQueueDemo();
        //blockQueueDemo.method1();
        //blockQueueDemo.method2();
        //blockQueueDemo.method3();
        blockQueueDemo.method4();
    }

    //ArrayBlockingQueue抛出异常的操作方法
    public void method1() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        blockingQueue.add("1");
        blockingQueue.add("2");
        blockingQueue.add("3");
        System.out.println(blockingQueue.element());
        //抛异常
        //blockingQueue.add("4");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        //抛异常
        //blockingQueue.remove();
    }

    //返回boolean的操作方法
    public void method2() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        for (int i = 0; i < 4; i++) {
            System.out.println(blockingQueue.offer(i + ""));
        }
        for (int i = 0; i < 4; i++) {
            //查看第一个元素
            System.out.println(blockingQueue.peek());
        }
        for (int i = 0; i < 4; i++) {
            //取出第一个元素
            System.out.println(blockingQueue.poll());
        }
    }

    //阻塞的操作方法
    public void method3() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
//        for (int i = 0; i < 4; i++) {
        for (int i = 0; i < 3; i++) {
            blockingQueue.put(i + "");
        }
        for (int i = 0; i < 4; i++) {
            //取出第一个元素
            System.out.println(blockingQueue.take());
        }
    }

    //超时的操作方法
    public void method4() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        for (int i = 0; i < 4; i++) {
            System.out.println(blockingQueue.offer(i + "", 2, TimeUnit.SECONDS));
        }
        for (int i = 0; i < 4; i++) {
            //取出第一个元素
            System.out.println(blockingQueue.poll());
        }
    }
}
