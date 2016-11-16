package com.petrasin.startup;

import com.petrasin.jms.JmsSender;
import com.petrasin.service.Producer;
import com.petrasin.service.ProducerExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ApplicationDriver {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationDriver.class);

    @EJB
    private JmsSender sender;

    private ProducerExecutor executor;

    @PostConstruct
    public void startUp() {
        logger.info("Starting up");
        executor = new ProducerExecutor(sender);
        executor.startProducing();
    }

    @PreDestroy
    public void shutDown() {
        logger.info("Shutting down");
        executor.stopProducing();
    }
}
