package com.petrasin.service;

import com.petrasin.jms.JmsSender;
import com.petrasin.util.ProducerThreadFactory;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class ProducerExecutor {
    private static final int DEFAULT_PRODUCER_THREAD_SIZE = 10;

    private final ExecutorService pool = newCachedThreadPool(new ProducerThreadFactory());

    private int producers;

    private JmsSender sender;

    public ProducerExecutor(JmsSender sender) {
        this(sender, DEFAULT_PRODUCER_THREAD_SIZE);
    }

    public ProducerExecutor(JmsSender sender, int numOfProducers) {
        this.sender = sender;
        this.producers = numOfProducers;
    }

    public void startProducing() {
        for(int i = 0; i < producers; i++)
            pool.submit(new UUIDProducer(sender));
    }

    public void stopProducing() {
        pool.shutdownNow();
    }

}
