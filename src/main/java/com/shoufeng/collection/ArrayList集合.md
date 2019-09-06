# 集合

## 集合不安全问题

### ArrayList

```java
new ArrayList<>()
```

ArrayList**线程不安全**，**默认**创建长度为**10**的**object[]**，扩容每次**扩容原长度的一半**。

**ConcurrentModificationException**并发修改异常

**解决方案** 

1、使用**Vector**（Synchronized加在方法上）

2、使用**Collections.synchronizedList**（Synchronized加在代码块上）

3、**CopyOnWriteArrayList**（add操作会复制一个当前数组长度+1的object[]进行添加操作，用ReentrantLock保证写线程安全，在写同时的读操作发生在旧的object[]上）

**注意**：**Collections.synchronizedList**比**Vector**的**优势**——Synchronized加在代码块上比Synchronized加在方法上**锁的范围更小**（单例模式中的双检索也有这个优势）

