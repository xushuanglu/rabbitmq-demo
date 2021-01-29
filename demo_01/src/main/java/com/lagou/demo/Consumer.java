package com.lagou.demo;/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: Consumer
 * DATE: 2020/12/7
 * TIME: 14:14
 * JDK 11
 *
 * @ClassName Consumer
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/7
 * @Time 14:14
 * @Version v1.0
 **/

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<KouZhao> blockingQueue;

    public Consumer(BlockingQueue<KouZhao> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                long startTime = System.currentTimeMillis();
                // 获取开始时间
                KouZhao kouZhao = blockingQueue.take();
                long endTime = System.currentTimeMillis();
                // 获取结束时间
                System.out.println("我消费了口罩：" + kouZhao + ", 等到货时我阻 塞了" + (endTime - startTime) + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
