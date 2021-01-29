package com.lagou.demo;/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: Producer
 * DATE: 2020/12/7
 * TIME: 14:03
 * JDK 11
 */

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @author xushu
 * @ClassName Producer
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/7
 * @Time 14:03
 * @Version v1.0
 **/
public class Producer implements Runnable {

    private final BlockingQueue<KouZhao> blockingQueue;

    public Producer(BlockingQueue<KouZhao> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                if (blockingQueue.remainingCapacity() > 0) {
                    KouZhao kouZhao = new KouZhao(UUID.randomUUID().toString(), "N95");
                    blockingQueue.add(kouZhao);
                    System.out.println("我在生产口罩，当前库存是：" + blockingQueue.size());
                } else {
                    System.out.println("我的仓库已经堆满了" + blockingQueue.size() + "个口罩，快来买口罩啊！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
