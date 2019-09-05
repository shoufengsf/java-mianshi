# CAS（自旋）

cas的全称为compare and swap，他是一条**CPU并发原语**。

他的功能是判断内存某个位置的值是否是预期的值，如果是则改为新值，这个**过程是原子的**。

```java
/**
 * Atomically increments by one the current value.
 *
 * @return the previous value
 */
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
```

this：当前对象

valueOffset：内存偏移量

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}
```



缺点：

1、循环时间长，开销大（如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大开销）

2、只能保证一个共享变量的原子操作

3、aba问题