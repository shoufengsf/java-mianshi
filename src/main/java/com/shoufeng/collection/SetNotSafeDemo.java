package com.shoufeng.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * set不安全问题
 *
 * @author shoufeng
 */
public class SetNotSafeDemo {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        Set<String> synchronizedSet = Collections.synchronizedSet(set);
        CopyOnWriteArraySet<String> stringCopyOnWriteArraySet = new CopyOnWriteArraySet<>();
    }
}
