package queue;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	public static void main(String[] args) throws URISyntaxException, Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		//connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//TemporaryQueue tq = session.createTemporaryQueue();
		try {
			Queue queue = session.createQueue("MyQueue");

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			while (true) {
				TextMessage textMsg = (TextMessage) consumer.receive();
				System.out.println(textMsg);
				System.out.println("Received: " + textMsg.getText());
				if (textMsg.getText().equals("END")) {
					break;
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
