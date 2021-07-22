> ThreadPoolExecutor 的五个参数

> Executors 启动的 四种线程池 都是 默认的 什么值？

> shutdown()等方法的那些事 请去 1/src/com/threadPoolFour/threeScheduled.java 查看

```java
class Demo{
	
    //	使用给定的初始参数和默认线程工厂以及被拒绝的执行处理程序创建一个新的ThreadPoolExecutor 。 使用Executors工厂方法之一而不是这个通用构造函数可能更方便。
    //参数：
    //corePoolSize – 要保留在池中的线程数，即使它们处于空闲状态，除非设置了allowCoreThreadTimeOut
    //maximumPoolSize – 池中允许的最大线程数
    //keepAliveTime – 当线程数大于核心数时，这是多余空闲线程在终止前等待新任务的最长时间。
    //unit – keepAliveTime参数的时间单位
    //workQueue – 用于在执行任务之前保存任务的队列。 这个队列将只保存execute方法提交的Runnable任务。
    //抛出：
    //IllegalArgumentException – 如果以下情况之一成立： corePoolSize < 0 keepAliveTime < 0 maximumPoolSize <= 0 maximumPoolSize < corePoolSize
    //NullPointerException – 如果workQueue为空
	public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }
    
    // one
    // ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    // 其调用ThreadPoolExecutor 参数默认值
    // 0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>()
    
    // two
    // ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
    // 传入核心线程数 nThreads
    // nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()
    
    // three
    // ExecutorService cachedThreadPool = Executors.newScheduledThreadPool(6);
    // ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(6);
    // 传入核心线程数 corePoolSize = 6
    // corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue()
    // 其single版本
    // ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    // 其默认核心线程数为 1 也就是 corePoolSize = 1
    // corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue()
    
    // four
    // ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    // 其默认核心线程数和池中允许的最大线程数为 1 也就是 corePoolSize = 1 | maximumPoolSize = 1
    // keepAliveTime = 0L 当线程数大于核心数时，这是多余空闲线程在终止前等待新任务的最长时间。
    // 1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())
}
```
