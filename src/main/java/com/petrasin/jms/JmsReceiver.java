package com.petrasin.jms;

import com.petrasin.service.Consumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.UUID;

@MessageDriven(name = "JmsReceiverMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/uuid"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
})
public class JmsReceiver implements MessageListener {

    @EJB
    private Consumer delegate;

    @Override
    public void onMessage(Message message) {
        try {
            UUID body = message.getBody(UUID.class);
            delegate.consume(body);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
