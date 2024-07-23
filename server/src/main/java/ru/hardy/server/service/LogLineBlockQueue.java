package ru.hardy.server.service;

import lombok.extern.slf4j.Slf4j;
import ru.hardy.server.entity.LogLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


//Singltone
@Slf4j
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
        log.info("-- BlockQueue  putt");
    }

    public LogLine take() throws InterruptedException {
        log.info("-- BlockQueue  take");
        return blockingQueue.take();
    }

}
