package ru.hardy.server.service;

import ru.hardy.server.entity.LogLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


//Singltone
public class LogLineBlockQueue {
    private static LogLineBlockQueue instance;
    private BlockingQueue<LogLine> blockingQueue = new LinkedBlockingDeque<>();

    private LogLineBlockQueue() {
    }

    public static LogLineBlockQueue getInstance() {
        if (instance == null) {
            instance = new LogLineBlockQueue();
        }
        return instance;
    }

    public void putt(LogLine logLine) throws InterruptedException {
        blockingQueue.put(logLine);
    }

    public LogLine take() throws InterruptedException {
        return blockingQueue.take();
    }

}
