介绍Spring Boot中的异步调用，线程池，WebAsyncTask处理异步任务以及单机定时任务的几种实现

#### 优雅地关闭线程池
由于在应用关闭的时候异步任务还在执行，导致类似 数据库连接池 这样的对象一并被 销毁了，当 异步任务 中对 数据库 进行操作就会出错。

解决方案如下，重新设置线程池配置对象，新增线程池 setWaitForTasksToCompleteOnShutdown() 和 setAwaitTerminationSeconds() 配置：

- setWaitForTasksToCompleteOnShutdown(true): 该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。

- setAwaitTerminationSeconds(60): 该方法用来设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。

