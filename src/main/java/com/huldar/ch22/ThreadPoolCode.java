package com.huldar.ch22;

import java.util.concurrent.*;

/**
 * 极客时间《java并发编程实战》
 * ch22:Executor与线程池
 *
 * @author huldar
 * @date 2019/4/21 15:24
 */
public class ThreadPoolCode {

    public static void main(String[] args) {
        int corePoolSize = 5;
        int maximumPoolSize = 100;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;

        ThreadFactory factory = new MyThreadFactory("huldar's thread pool");

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(8);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, factory);

        for (int i = 0; i < 40; i++) {
            try {
                Future<?> future = executor.submit(() -> {
                    System.out.println("正在执行 task" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("执行结束 task" + Thread.currentThread().getName());
                    return "12222";
                });
                try {
                    Object o = future.get();
                    System.out.println(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (RuntimeException e) {
                System.out.println("有错误了");
                e.printStackTrace();
            }
        }

        executor.shutdown();

    }

}
