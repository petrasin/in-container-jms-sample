package com.petrasin.jms;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.io.Serializable;

import static java.util.Objects.requireNonNull;

@Stateless
public class JmsSender {

    @Resource(mappedName = "java:jboss/exported/jms/queue/uuid")
    private Queue queue;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    public void send(Serializable message) {
        Connection connection = null;
        try  {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);
            connection.start();
            publisher.send(session.createObjectMessage(requireNonNull(message, "Message body can not be null")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
