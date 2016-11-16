package com.petrasin.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import static java.lang.String.format;

public class ProducerThreadFactory implements ThreadFactory {
    private static final String THREAD_NAME_FORMAT = "Producer-%d";
    private AtomicLong lastId = new AtomicLong(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, format(THREAD_NAME_FORMAT, lastId.getAndIncrement()));
    }
}
