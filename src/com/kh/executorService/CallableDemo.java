package com.kh.executorService;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        try(ExecutorService service = Executors.newFixedThreadPool(2)) {
            Future<Integer> result = service.submit(new ReturnValueTask());

            result.cancel(true);

            boolean cancelled = result.isCancelled();

            boolean done = result.isDone();

            System.out.println(result.get(6,TimeUnit.SECONDS));

        } catch (TimeoutException e) {
            System.out.println("Future didn't get the value in the given time");
        }
        System.out.println("Main thread execution completed");
    }
}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 100;
    }
}
