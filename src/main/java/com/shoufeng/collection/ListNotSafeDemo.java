package com.shoufeng.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合不安全问题
 *
 * @author shoufeng
 */
public class ListNotSafeDemo {
    public static void main(String[] args) {
        //默认创建长度为10的object[],扩容每次扩容原长度的一半
        ArrayList<String> list = new ArrayList<>();
        //ArrayList线程不安全
        //解决方案 1、使用vector 2、使用Collections.synchronizedList 3、CopyOnWriteArrayList
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                //list.add(UUID.randomUUID().toString());
                //synchronizedList.add(UUID.randomUUID().toString());
                copyOnWriteArrayList.add(UUID.randomUUID().toString());
                //ConcurrentModificationException并发修改异常 System.out.println(list)导致
                //System.out.println(list);
                System.out.println(copyOnWriteArrayList);
            }).start();
        }

    }
}
