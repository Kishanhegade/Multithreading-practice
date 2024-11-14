package com.kh.threadSynchronization;

public class SyncronizationDemo
{
    private static int counter1 = 0;
    private static int counter2 = 0;

    private synchronized static void increment1(){
        counter1++;
    }

    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for(int i=0;i<10000;i++){
                increment1();
            }
        });

        Thread two = new Thread(()->{
            for(int i=0;i<10000;i++){
                increment2();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Counter value: " + counter1 + "--" + counter2);
    }

    private synchronized static void increment2(){
        counter2++;
    }
}
