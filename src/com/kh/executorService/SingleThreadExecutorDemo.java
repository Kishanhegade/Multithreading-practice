package com.kh.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo
{
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 5; i++) {
                executorService.execute(new Task(i));
            }
        }
    }
}

class Task implements Runnable{

    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task with ID " + taskId + " being executed by Thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
