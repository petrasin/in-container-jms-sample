package com.petrasin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
@Local(Consumer.class)
public class UUIDConsumer implements Consumer<UUID> {

    private static final Logger logger = LoggerFactory.getLogger(UUIDConsumer.class);

    @Override
    public void consume(UUID msg) {
        logger.info("Consuming UUID: {}", msg);
    }

}
