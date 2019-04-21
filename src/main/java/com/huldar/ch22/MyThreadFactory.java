package com.huldar.ch22;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程创建策略
 * 功能:线程命名
 *
 * @author huldar
 * @date 2019/4/21 15:28
 */
public class MyThreadFactory implements ThreadFactory {

    private static final AtomicInteger threadNum = new AtomicInteger(1);
    private final ThreadGroup threadGroup;
    private final String namePrefix;

    public MyThreadFactory(String threadPoolName) {
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup() :
                Thread.currentThread().getThreadGroup();

        namePrefix = "pool--" + threadPoolName + "--thread--";
    }

    @Override
    public Thread newThread(Runnable target) {
        Thread t = new Thread(threadGroup, target, namePrefix + threadNum.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
