package queue;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerAsync implements MessageListener {

	private static CountDownLatch latch = new CountDownLatch(1);

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			//if (true) 
			//	throw new RuntimeException();
			System.out.println("received " + textMessage.getText());
			if ("END".equals(textMessage.getText())) {
				latch.countDown();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws URISyntaxException, Exception {
		new ConsumerAsync().receiveMessages();
	}

	public void receiveMessages() throws JMSException, InterruptedException {
		Connection connection = null;
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		try {
			Queue queue = session.createQueue("MyQueue");
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(this);
			connection.start();
			latch.await();
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void latchCountDown() {
		latch.countDown();
	}
}
