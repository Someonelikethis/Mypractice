### 几个重要对象
    Executor              执行任务的执行器的抽象
    ExecutorService       执行器服务，作为Executor的子接口提供一些标准方法
    ThreadPoolExecutor：  ExecutorService的默认实现，线程池执行器，这个对象作为线程池来使用。(可以中这个类自定义线程池)
### ThreadPoolExecutor几个重要的参数
    corePoolSize：核心池的大小，线程池长期维持的线程数，即使线程处于Idle(空闲)状态，也不会回收。
    maximumPoolSize：线程池最大线程数
    poolSize：线程池中当前线程的数量。
    keepAliveTime：corePoolSize外的空闲线程存活的时间，如果超过了corePoolSize，在keepAliveTime的时间之后，销毁线程
    unit：keepAliveTime的时间单位
    workQueue：一个阻塞队列，用来存储等待执行的任务，任务的排队队列
    threadFactory： 新线程的产生方式
    handler： 拒绝策略
### Java提供的四种线程池(通过Executors创建)
#### newCachedThreadPool
    可缓存线程池，线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
#### newFixedThreadPool
    定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。
#### newScheduledThreadPool
    定长线程池，支持定时及周期性任务执行。
#### newSingleThreadExecutor
    单线程的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
