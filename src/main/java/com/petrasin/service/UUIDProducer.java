package com.petrasin.service;

import com.petrasin.jms.JmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.util.UUID.randomUUID;

public class UUIDProducer implements Producer<UUID>, Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UUIDProducer.class);

    private JmsSender sender;

    public UUIDProducer(JmsSender sender) {
        this.sender = sender;
    }

    @Override
    public UUID produce() {
        UUID uuid = randomUUID();
        logger.info("Producing UUID: {}", uuid);
        return uuid;
    }

    @Override
    public void run() {
        while(!currentThread().interrupted()) {
            try {
                sender.send(produce());
                // simulate processing
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }
}
