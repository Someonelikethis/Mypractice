
#### List 有序，可重复，如果List元素允许为null，则可以有多个null

LinkedList 双向链表实现的双端队列，允许null，线程不安全

Vector与ArrayList大致相同，最大的区别在线程安全上

Vector     线程安全（靠synchronized关键字），容量默认2倍自增，可指定容量增长，允许null，这个类现在基本不使用了，有更好的选择

ArrayList  线程不安全，容量默认1.5自增，不可指定容量增长，允许null

`List list = Collections.synchronizedList(new ArrayList(...)); `

##### List选择：

在不考虑线程安全，确定容量，查找操作频繁于插入删除操作，选用ArrayList

在ArrayList的基础上想要保证线程安全，可以选用CopyOnWriteArrayList或者Collections.synchronizedList(new ArrayList(...));

在不考虑线程安全，不确定容量，插入删除操作频繁的情况下，选用LinkedList


NavigableMap和NavigableSet扩展了SortedSet添加了各种导航方法

#### Map 具有映射关系的键值对，key不可重复，注意可变对象作为key，三个集合视图：keys的set、values的collection、key-value mappings（Entry）的set

    key可以有一个null，value可以null

##### HashMap  无序，基于数组、单链表、红黑树，key和value允许null

     根据key的hash值去存取元素，当key的hash值相同时，此时即发生了hash碰撞，equals方法碰撞时才会执行，
          
          存，如果value不同，则以该桶（bucket）作为头节点，先以单链表进行存储，当该单链表上的元素数量超过8时，转用红黑树结构进行存储，原单链表仍然保留并维护
          
          取，在以该桶作为头节点的单链表或者红黑树上查找

     HashMap无法保证顺序，尤其是无法保证顺序会随时间保持不变
     
     HashMap是无序的，也就是说，迭代HashMap所得到的元素顺序并不是它们最初放置到HashMap的顺序。
     
     集合视图的迭代需要与HashMap实例的“容量”(桶的数量)及其大小(键值映射的数量)成比例的时间。如果迭代性能很重要，不要将初始容量设置得太高（或负载因子太低）是非常重要的。
     
     threshold 阈值 = 容量*负载因子
     
     默认的负载因子(.75)在时间和空间成本之间提供了很好的权衡。
     
     负载因子越高，减少了空间开销，却增加了查询成本（影响包括get、put在内的大多数操作）
     
     当元素数量超过阈值，扩容，默认将桶数量2倍扩容，然后重新散列（rehash）
     
     设置合适的初始容量值和负载因子，尽量避免或者减少rehash
     
     HashMap的同步实现：ConcurrentHashMap或者Collections.synchronizedMap(new HashMap(...)); 
     
     HashMap和HashTable几乎等同，最大的区别在于HashTable线程安全（靠synchronized关键字）且HashTable的key不能为null，HashTable现在基本不使用了，因为有更好的选择

##### ConcurrentHashMap  
     使用分段锁，相比HashTable锁粒度更小，有更好的并发性，但同时增加了系统开销，每一个Segment相当于一个小的HashTable

##### TreeMap 基于红黑树，有序
     
     有自然顺序、自定义顺序（自定义比较器）两种，其中默认为自然排序
     
     Collections.synchronizedSortedMap(new TreeMap(...));
     
LinkedHashMap 有序，迭代顺序可以是插入顺序，也可以是访问顺序。

     根据链表中元素的顺序可以将LinkedHashMap分为：保持插入顺序的LinkedHashMap 和 保持访问顺序的LinkedHashMap，其中LinkedHashMap的默认实现是按插入顺序排序的。
     
     LinkedHashMap可以很好的支持LRU算法(least recently used)，用以 LRU 缓存
     
     额外维护了一个双向链表用于保持迭代顺序，一定程度上增加了开销，但没有TreeMap开销大
     
     重新插入key不会影响插入顺序
     
     它可以用来生成与原始Map顺序相同的Map副本，而不管原始Map的实现是什么:
     void foo(Map m) {
               Map copy = new LinkedHashMap(m);
               ...
     }
           
     如果模块接受输入的Map并复制它，然后返回由复制的顺序决定的结果，那么这种技术特别有用。(客户通常很喜欢按照展示物品的顺序退货。)

#### Set 无序，不可重复，最多只能由一个元素为null，注意可变对象作为set元素

##### HashSet  基于HashMap，允许null，线程不安全，不过只使用了HashMap的key，将元素存储在key上
     Collections.synchronizedSet(new HashSet(...));
    
##### TreeSet  基于TreeMap，不过只使用了HashMap的key，将元素存储在key上，
              有序，有自然顺序、自定义顺序（自定义比较器）两种，其中默认为自然排序
    
##### LinkedHashSet  基于LinkedHashMap


#### 补充：

Comparator中    (Comparator & Serializable)创建的对象同时实现这两个接口

Stream API用来丰富集合操作， .stream() 方法将常规数组转化为流

fail-fast与fail-safe

fail-fast机制作为并发操作检查的机制，通过modCount来实现fail-fast机制

Iterator   普通的顺序迭代器<br>
Spliterator 可切割迭代器，为了并发操作集合，将集合分成了好几段，每个线程执行一段，因此是线程安全的

List默认的排序算法为折半插入排序(二分法)

index都是从0开始的

####源码笔记：

LinkedList  双向链表实现的双端队列，addAll 源码阅读

`//index   第一个新节点将插入在index位置`<br>
`public boolean addAll(int index, Collection<? extends E> c) {`
        
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        /**
         * pred 新节点的前指针
         * succ index是第一个新节点插入的位置索引，succ是index上的原节点
         */
        Node<E> pred, succ;
        
        //分为中间插入和末尾插入两种情况
        if (index == size) {  //在末尾插入
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;//这个newNode作为下一个newNode的前节点
        }

        if (succ == null) {  //在末尾插入
            last = pred;  //尾指针指向最后一个newNode
        } else {  //将succ接在最后一个newNode上
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }`