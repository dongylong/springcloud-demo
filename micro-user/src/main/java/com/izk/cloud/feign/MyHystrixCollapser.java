package com.izk.cloud.feign;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {
    private final String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> requests) {
        return new batchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, String>> requests) {
        int count = 0;
        for (CollapsedRequest<String, String> request : requests
        ) {
            request.setResponse(batchResponse.get(count++));

        }
    }

    private static final class batchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, String>> requests;

        private batchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey("EGGROUP")
            )
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            System.out.println("执行请求");
            List<String> resp = new ArrayList<>();
            for (CollapsedRequest<String,String> request : requests
                 ) {
                resp.add("返回结果： " +request.getArgument());

            }
            return resp;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("test-1").queue();
        Future<String> f2 =new MyHystrixCollapser("test-2").queue();
        System.out.println(f1.get() + " = " +f2.get());
        context.shutdown();
    }
}
