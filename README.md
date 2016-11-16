in-container-jms-sample: Example Using JMS, EJB Java technologies
==============================================================================================
Author: Miloš Petrašinović

Technologies: JMS, EJB

Summary: Simple producer/consumer processing via JMS

Source: <https://github.com/petrasin/in-container-jms-sample>

What is it?
-----------

It is a simple, deployable Maven 3 project to show implementation of producer consumer architecture on JBoss WildFly.
There are configurable amount of parallel producers sending random generated uuids to the designated queue.
On the other side, parallel consumers (parallelism is provided by container mdb thread policy) consumes messages from the queue.

System requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly 10.1.0.Final.


Configure Maven
---------------

If you have not yet done so, you must Configure Maven before testing the app.


Start JBoss WildFly
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server:

        For Linux:   JBOSS_HOME/bin/standalone.sh -c standalone-full.xml
        For Windows: JBOSS_HOME\bin\standalone.bat -c standalone-full.xml


Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this app.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/in-container-jms-sample-1.0-SNAPSHOT.jar` to the running instance of the server.


Access the application
---------------------

The application will be running in Wildfly container and its results can be seen on either console log or configured application log file.
Application log file is located at ${jboss.home.dir}/standalone/log/in-container-jms-sample.log and its content looks like:  
	
    2016-11-16 15:03:12,936 [Producer-1] INFO  UUIDProducer - Producing UUID: 5e6da6ab-e6bb-460b-bdde-682d889d26d5
    2016-11-16 15:03:12,938 [Producer-3] INFO  UUIDProducer - Producing UUID: 1619d4f4-c04b-4c5e-bb1d-524050b5d5d3
    2016-11-16 15:03:12,938 [Thread-63 (ActiveMQ-client-global-threads-678884679)] INFO  UUIDConsumer - Consuming UUID: 5e6da6ab-e6bb-460b-bdde-682d889d26d5
    2016-11-16 15:03:12,940 [Thread-62 (ActiveMQ-client-global-threads-678884679)] INFO  UUIDConsumer - Consuming UUID: 1619d4f4-c04b-4c5e-bb1d-524050b5d5d3
    2016-11-16 15:03:12,940 [Producer-4] INFO  UUIDProducer - Producing UUID: e2203c0d-4525-4cf9-8cb4-67826060800a
    

Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Investigate the Server Console Output
---------------------
You should see messages similar to the following:

    15:03:43,824 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-5) WFLYSRV0027: Starting deployment of "in-container-jms-sample-1.0-SNAPSHOT.jar" (runtime-name: "in-container-jms-sample-1.0-SNAPSHOT.jar")
    15:03:43,907 INFO  [org.jboss.weld.deployer] (MSC service thread 1-2) WFLYWELD0003: Processing weld deployment in-container-jms-sample-1.0-SNAPSHOT.jar
    15:03:43,913 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-2) WFLYEJB0473: JNDI bindings for session bean named 'JmsSender' in deployment unit 'deployment "in-container-jms-sample-1.0-SNAPSHOT.jar"' are as follows:

            java:global/in-container-jms-sample-1.0-SNAPSHOT/JmsSender!com.petrasin.jms.JmsSender
            java:app/in-container-jms-sample-1.0-SNAPSHOT/JmsSender!com.petrasin.jms.JmsSender
            java:module/JmsSender!com.petrasin.jms.JmsSender
            java:global/in-container-jms-sample-1.0-SNAPSHOT/JmsSender
            java:app/in-container-jms-sample-1.0-SNAPSHOT/JmsSender
            java:module/JmsSender

    15:03:43,913 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-2) WFLYEJB0473: JNDI bindings for session bean named 'ApplicationDriver' in deployment unit 'deployment "in-container-jms-sample-1.0-SNAPSHOT.jar"' are as follows:

            java:global/in-container-jms-sample-1.0-SNAPSHOT/ApplicationDriver!com.petrasin.startup.ApplicationDriver
            java:app/in-container-jms-sample-1.0-SNAPSHOT/ApplicationDriver!com.petrasin.startup.ApplicationDriver
            java:module/ApplicationDriver!com.petrasin.startup.ApplicationDriver
            java:global/in-container-jms-sample-1.0-SNAPSHOT/ApplicationDriver
            java:app/in-container-jms-sample-1.0-SNAPSHOT/ApplicationDriver
            java:module/ApplicationDriver

    15:03:43,913 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-2) WFLYEJB0473: JNDI bindings for session bean named 'UUIDConsumer' in deployment unit 'deployment "in-container-jms-sample-1.0-SNAPSHOT.jar"' are as follows:

        java:global/in-container-jms-sample-1.0-SNAPSHOT/UUIDConsumer!com.petrasin.service.Consumer
        java:app/in-container-jms-sample-1.0-SNAPSHOT/UUIDConsumer!com.petrasin.service.Consumer
        java:module/UUIDConsumer!com.petrasin.service.Consumer
        java:global/in-container-jms-sample-1.0-SNAPSHOT/UUIDConsumer
        java:app/in-container-jms-sample-1.0-SNAPSHOT/UUIDConsumer
        java:module/UUIDConsumer

    15:03:43,940 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 82) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
    15:03:43,941 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 82) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
    15:03:43,948 INFO  [org.apache.activemq.artemis.core.server] (ServerService Thread Pool -- 83) AMQ221003: trying to deploy queue jms.queue.uuid
    15:03:43,949 INFO  [org.wildfly.extension.messaging-activemq] (ServerService Thread Pool -- 83) WFLYMSGAMQ0002: Bound messaging object to jndi name jms/queue/uuid
    15:03:43,951 INFO  [org.wildfly.extension.messaging-activemq] (ServerService Thread Pool -- 83) WFLYMSGAMQ0002: Bound messaging object to jndi name java:jboss/exported/jms/queue/uuid
    15:03:43,953 INFO  [org.jboss.as.ejb3] (MSC service thread 1-2) WFLYEJB0042: Started message driven bean 'JmsReceiverMDB' with 'activemq-ra.rar' resource adapter
    15:03:43,954 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 82) WFLYCLINF0002: Started client-mappings cache from ejb container
    2016-11-16 15:03:44,090 [erverService Thread Pool -- 83] INFO  ApplicationDriver - Starting up
    15:03:44,161 INFO  [org.jboss.as.server] (management-handler-thread - 2) WFLYSRV0010: Deployed "in-container-jms-sample-1.0-SNAPSHOT.jar" (runtime-name : "in-container-jms-sample-1.0-SNAPSHOT.jar")
    2016-11-16 15:03:53,170 [Producer-7                    ] INFO  UUIDProducer - Producing UUID: acedcfa2-1321-4c97-b818-e4f52f69741e
    2016-11-16 15:03:53,175 [ient-global-threads-678884679)] INFO  UUIDConsumer - Consuming UUID: acedcfa2-1321-4c97-b818-e4f52f69741e
    2016-11-16 15:03:53,458 [erverService Thread Pool -- 84] INFO  ApplicationDriver - Shutting down
    15:03:53,464 INFO  [org.wildfly.extension.messaging-activemq] (ServerService Thread Pool -- 86) WFLYMSGAMQ0006: Unbound messaging object to jndi name java:/jms/queue/uuid
    15:03:53,507 INFO  [org.wildfly.extension.messaging-activemq] (ServerService Thread Pool -- 86) WFLYMSGAMQ0006: Unbound messaging object to jndi name java:jboss/exported/jms/queue/uuid
    15:03:53,520 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 86) WFLYCLINF0003: Stopped client-mappings cache from ejb container
    15:03:53,526 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-8) WFLYSRV0028: Stopped deployment in-container-jms-sample-1.0-SNAPSHOT.jar (runtime-name: in-container-jms-sample-1.0-SNAPSHOT.jar) in 69ms
    15:03:53,555 WARN  [org.jboss.as.controller] (management-handler-thread - 3) WFLYCTL0357: Notification of type deployment-undeployed is not described for the resource at the address []
    15:03:53,557 INFO  [org.jboss.as.repository] (management-handler-thread - 3) WFLYDR0002: Content removed from location c:\Users\Milos Petrasinovic\Develop\Java\wildfly-10.1.0.Final\standalone\data\content\a9\01367a5fa6e5ee4dd6ad89f776847747a1872b\content
    15:03:53,558 INFO  [org.jboss.as.server] (management-handler-thread - 3) WFLYSRV0009: Undeployed "in-container-jms-sample-1.0-SNAPSHOT.jar" (runtime-name: "in-container-jms-sample-1.0-SNAPSHOT.jar")
