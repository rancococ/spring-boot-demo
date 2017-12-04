package com.catvgd.springbootdemo.common.logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggerQueue {

    private volatile static LoggerQueue instance;

    // 队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    // 阻塞队列
    private BlockingQueue<LoggerMessage> blockingQueue = null;

    private LoggerQueue() {
        blockingQueue = new LinkedBlockingQueue<LoggerMessage>(QUEUE_MAX_SIZE);
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
     * @param log
     * @return
     */
    public boolean push(LoggerMessage loggerMessage) {
        return this.blockingQueue.add(loggerMessage);// 队列满了就抛出异常，不阻塞
    }

    /**
     * 消息出队
     * 
     * @return
     */
    public LoggerMessage poll() {
        LoggerMessage result = null;
        result = this.blockingQueue.poll();
        return result;
    }

}
