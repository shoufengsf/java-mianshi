package com.shoufeng.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * callable
 * @author shoufeng
 */
public class CallableDemo implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call方法被调用");
        TimeUnit.SECONDS.sleep(2);
        return "success";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        //适配器方法
        FutureTask<String> stringFutureTask = new FutureTask<String>(callableDemo);
        new Thread(stringFutureTask).start();
        System.out.println(stringFutureTask.get());
    }

}
