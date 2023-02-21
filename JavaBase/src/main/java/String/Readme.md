### String源码阅读笔记

##### `public boolean contentEquals(CharSequence cs);`//该方法通过同步代码块确保了String与StringBuffer内容比较时的线程安全

`public String(char value[]) {`<br>
         `this.value = Arrays.copyOf(value, value.length);`<br>
`}`

`String(char[] value, boolean share) {`<br>
        `// assert share : "unshared not supported";`<br>
        `this.value = value;`<br>
`}`

##### 不将该方法修饰为public保证了String的不可变性
##### 目前不支持使用false，只使用true。所以就目前来看加入这个share只是为了区分于String(char[] value)方法，从而进行方法的重载。
##### share=true的含义为这个方法构造出来的String和参数传过来的char[] value共享同一个数组
##### 优点：
##### ①性能更好，一个是直接将value的引用赋给String的value，一个是逐一拷贝
##### ②共享内部数组，节约内存

##### `public String trim();`//去掉字符串首尾的空格' '

### AbstractStringBuilder源码阅读笔记

##### AbstractStringBuilder中只有toString一个抽象方法<br><br>

#### 容量分配算法

`if(指定最小容量为负数)`<br>
`{ensureCapacity不会有任何行为}`<br>
`else if(当前容量比指定最小容量小)`<br>
`{重新分配更大容量的数组}`
<br><br>
#####新容量newCapacity分配原则为：
`首先默认新容量为当前容量的2倍加2`<br>
`if(这个默认新容量仍然小于指定的最小容量)`<br>
`{则新容量赋值为指定的最小容量}`
<br><br>
##### 接下来要考虑新容量溢出的问题:
`if(newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)`<br>
`{进行大容量分配算法hugeCapacity}`
##### （补充知识：java中没有无符号类型，所以数值溢出时会变成负数）
##### `newCapacity <= 0`是因为newCapacity是移位运算得到的,newCapacity溢出了int的数值范围，溢出到最高位置为1，就变成了负数<br><br>

##### hugeCapacity算法分配的容量newCapacity=MAX_ARRAY_SIZE或者MAX_ARRAY_SIZ<newCapacity<Integer.MAX_VALUE,
##### newCapacity>Integer.MAX_VALUE则抛出内存溢出错误<br><br>

`private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;`<br>
//减8是因为某些虚拟机会在数组中保留一些头信息，比Integer.MAX_VALUE - 8更大<br>
<u>**可能**</u>给会导致内存溢出，比Integer.MAX_VALUE更大肯定内存溢出
<br><br>

##### StringBuffer和StringBuilder都大量调用了其抽象父类的方法,它俩差不多，但StringBuffer保证了线程安全
##### 注意：StringBuffer虽说是线程安全的，但也有某些inster、indexOf、lastIndexOf、readObject方法并没有确保线程安全，调用时可以调用线程安全的方法，或者自己手动保证线程安全
##### 为了保证StringBuffer的线程安全，String中对StringBuffer的操作都使用了synchronized来保证线程安全