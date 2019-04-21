package com.huldar.ch23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Future实现烧水泡茶的最优方案
 *
 * @author huldar
 * @date 2019/4/21 21:59
 */
public class BoilWaterMakeTea {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
        Thread t1 = new Thread(ft1);
        Thread t2 = new Thread(ft2);

        t1.start();
        t2.start();
        System.out.println(ft1.get());


    }
}
