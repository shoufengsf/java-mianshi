package com.shoufeng.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap 安全问题
 *
 * @author shoufeng
 */
public class HashMapSafeDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        Map<String, String> synchronizedMap = Collections.synchronizedMap(map);
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
