

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



## HashSet

HashSet底层是HashMap，线程不安全**ConcurrentModificationException**并发修改异常

```java
/**
 * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
 * default initial capacity (16) and load factor (0.75).
 */
public HashSet() {
    map = new HashMap<>();
}
```

```java
// Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

/**
 * Adds the specified element to this set if it is not already present.
 * More formally, adds the specified element <tt>e</tt> to this set if
 * this set contains no element <tt>e2</tt> such that
 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
 * If this set already contains the element, the call leaves the set
 * unchanged and returns <tt>false</tt>.
 *
 * @param e element to be added to this set
 * @return <tt>true</tt> if this set did not already contain the specified
 * element
 */
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```

HashSet添加的时候添加的值是HashMap的Key，value是object常量

## HashMap

线程不安全**ConcurrentModificationException**并发修改异常

解决：

1、使用ConcurrentHashMap

2、使用Collections.synchronizedMap