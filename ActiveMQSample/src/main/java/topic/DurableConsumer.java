package topic;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class DurableConsumer {
	public static void main(String[] args) throws URISyntaxException, Exception {
		Connection connection = null;
		Session session = null;
		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			connection.setClientID("DurabilityTest");
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("MyTopic");

			MessageConsumer consumer1 = session.createDurableSubscriber(topic, "consumer1", "", false);
			MessageConsumer consumer2 = session.createDurableSubscriber(topic, "consumer2", "", false);

			connection.start();
			
			TextMessage msg = null;
			msg =  (TextMessage) consumer1.receive();
			System.out.println("Consumer1 receives " +  (msg == null ? "No-mesage" : msg.getText()));
			
			msg =  (TextMessage) consumer2.receive();
			System.out.println("Consumer2 receives " + (msg == null? "No-mesage" : msg.getText()));
			
			
			
		} finally {
			if (session != null)
				session.close();
			if (connection != null) {
				connection.close();
			}
		}
	}
}
