package com.izk.cloud.feign;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/11/29 11:21
 * @changeRecord
 */
public class MyHystrixCommand extends HystrixCommand<String> {
    private final String name;

    public MyHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }
//    public MyHystrixCommand(String name) {
//        super(HystrixCommand.Setter
//        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                        .withExecutionIsolationStrategy(
//                                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
//                        )
//                ));
//        this.name = name;
//    }

//    public MyHystrixCommand(String name) {
//        super(HystrixCommand.Setter
//        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                        .withExecutionIsolationStrategy(
//                                HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
//                        )
//                ).andThreadPoolPropertiesDefaults(
//                        HystrixThreadPoolProperties.Setter()
//                        .withCoreSize(10)
//                        .withMaximumSize(100)
//                        .withMaximumSize(100)
//                        )
//
//        );
//        this.name = name;
//    }

    @Override
    protected String run() throws Exception {
//        try {
//            Thread.sleep(1000*10);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return this.name + " : " + Thread.currentThread().getName();
    }
    @Override
    protected String getCacheKey(){
        return String.valueOf(this.name);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        sync();
        async();
    }

    @Override
    protected String getFallback(){
        return "error.fallback";
    }
    public static void sync(){
        //test  : hystrix-MyGroup-1
        //构造函数设置组名变成线程名字
        String result = new MyHystrixCommand("test-sync ").execute();
        System.out.println(result);
    }
    public static void async() throws ExecutionException, InterruptedException {
        //test-async  : hystrix-MyGroup-2
        Future<String> result = new MyHystrixCommand("test-async ").queue();
        System.out.println(result.get());
    }
}
