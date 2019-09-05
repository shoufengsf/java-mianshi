package com.shoufeng.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类
 *
 * @author shoufeng
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        Person zhangsan = new Person("张三", 11);
        Person lisi = new Person("里斯",12);
        AtomicReference<Person> personAtomicReference = new AtomicReference<>();
        personAtomicReference.set(zhangsan);
        personAtomicReference.compareAndSet(zhangsan,lisi);
        System.out.println(personAtomicReference.get().name);
    }
}
class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
