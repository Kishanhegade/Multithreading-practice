package com.kh.multithreading;

public class JoinThreadExample {
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 : "+ i);
            }
        });
        Thread two = new Thread(()->{
            for (int i = 0; i < 25; i++) {
                System.out.println("Thread 2 : "+ i);
            }
        });
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Done executing the program");
    }
}
