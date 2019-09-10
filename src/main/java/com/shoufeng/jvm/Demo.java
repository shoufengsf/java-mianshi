package com.shoufeng.jvm;


public class Demo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
    }
}
