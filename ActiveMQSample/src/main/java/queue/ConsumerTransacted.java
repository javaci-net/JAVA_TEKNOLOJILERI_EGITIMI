package queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerTransacted {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.redeliveryPolicy.initialRedeliveryDelay=3000&jms.redeliveryPolicy.redeliveryDelay=5000&jms.redeliveryPolicy.maximumRedeliveries=3");
		connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		try {
			Queue queue = session.createQueue("MyQueue");

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			while (true) {
				TextMessage textMsg = (TextMessage) consumer.receive();
				System.out.println(textMsg);
				System.out.println("Received: " + textMsg.getText());

				session.rollback();
				
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
