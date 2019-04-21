package com.huldar.ch23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future的练习
 *
 * @author huldar
 * @date 2019/4/21 21:21
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result result = new Result();
        result.setName(1);
        //executorService.submit()
    }
}
