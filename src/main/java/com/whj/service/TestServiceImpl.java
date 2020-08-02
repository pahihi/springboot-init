package com.whj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void run() throws ExecutionException, InterruptedException {

        Thread.sleep(3000);
        logger.info("开始执行runnable");
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Future<String> future = threadPoolExecutor.submit(() -> {
                BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
                logger.info("线程池缓冲队列大小："+queue.size());
                Thread.sleep(100);
                logger.info("开始执行callable");
                return "callable的结果："+ finalI;
            });
            futures.add(future);
        }
        threadPoolExecutor.execute(()->{
            for (Future<String> future : futures) {
                try {
                    logger.info(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
