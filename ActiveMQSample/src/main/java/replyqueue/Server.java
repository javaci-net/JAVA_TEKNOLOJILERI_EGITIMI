package replyqueue;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class Server implements MessageListener {
    private static String messageBrokerUrl = "tcp://localhost:61616";

    private Session session;
    private MessageProducer replyProducer;
    private MessageProtocol messageProtocol;


    public Server() {
        try {
            //This message broker is embedded
            BrokerService broker = new BrokerService();
            broker.setPersistent(false);
            //broker.setUseJmx(false);
            broker.addConnector(messageBrokerUrl);
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.messageProtocol = new MessageProtocol();
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(messageBrokerUrl);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination adminQueue = this.session.createQueue("TestQueue");

            this.replyProducer = this.session.createProducer(null);

            MessageConsumer consumer = this.session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message message) {
        try {
            TextMessage response = this.session.createTextMessage();
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                response.setText(this.messageProtocol.handleProtocolMessage(messageText));
                System.out.println("Message received by server:" + messageText + " corrId:" + message.getJMSCorrelationID());
            }
            response.setJMSCorrelationID(message.getJMSCorrelationID());

            this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}