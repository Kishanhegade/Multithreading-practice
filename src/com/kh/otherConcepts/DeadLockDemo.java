package com.kh.otherConcepts;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();

    public void workerOne() {
        lockA.lock();
        System.out.println("Worker One acquired lockA");
        try {
            Thread.sleep(200);
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
        lockB.lock();
        System.out.println("Worker One acquired lockB");
        lockA.unlock();
        lockB.unlock();

    }

    public void workerTwo() {
        lockB.lock();
        System.out.println("Worker Two acquired lockB");
        try {
            Thread.sleep(200);
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
        lockA.lock();
        System.out.println("Worker One acquired lockA");
        lockB.unlock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        new Thread(demo::workerOne, "Worker One").start();
        new Thread(demo::workerTwo, "Worker Two").start();

        new Thread(() -> {
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while(true) {
                long[] deadlockedThreadsIds = mxBean.findDeadlockedThreads();
                if(deadlockedThreadsIds!=null) {
                    System.out.println("DeadLock detected!!!");
                    ThreadInfo[] threadInfo = mxBean.getThreadInfo(deadlockedThreadsIds);
                    for (long deadlockedThreadId : deadlockedThreadsIds) {
                        System.out.println("Thread with ID " + deadlockedThreadId + " is in DeadLock");
                    }
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
