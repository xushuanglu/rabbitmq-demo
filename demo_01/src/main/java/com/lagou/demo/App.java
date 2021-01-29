package com.lagou.demo;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName App
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/7
 * @Time 14:15
 * @Version v1.0
 **/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<KouZhao> queue = new ArrayBlockingQueue<>(20);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
//        Thread.sleep(20000);
        new Thread(consumer).start();
    }
}
