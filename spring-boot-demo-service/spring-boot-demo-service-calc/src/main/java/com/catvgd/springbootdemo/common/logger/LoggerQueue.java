package com.catvgd.springbootdemo.common.logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LoggerQueue {

    private volatile static LoggerQueue instance;

    // 队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    // 阻塞队列
    private BlockingQueue<LoggerMessage> blockingQueue = null;

    private LoggerQueue() {
        blockingQueue = new ArrayBlockingQueue<LoggerMessage>(QUEUE_MAX_SIZE);
    }

    public static LoggerQueue getInstance() {
        if (instance == null) {
            synchronized (LoggerQueue.class) {
                if (instance == null) {
                    instance = new LoggerQueue();
                }
            }
        }
        return instance;
    }

    /**
     * 消息入队
     * 
     * @param loggerMessage
     * @return
     */
    public boolean push(LoggerMessage loggerMessage) {
        boolean result = this.blockingQueue.offer(loggerMessage);
        if (!result) {
            this.blockingQueue.poll();
            result = this.blockingQueue.offer(loggerMessage);
        }
        return result;
    }

    /**
     * 消息出队
     * 
     * @return
     */
    public LoggerMessage poll() {
        LoggerMessage result = null;
        try {
            result = this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
