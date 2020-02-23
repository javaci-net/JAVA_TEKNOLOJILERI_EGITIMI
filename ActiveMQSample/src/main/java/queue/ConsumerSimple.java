package queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerSimple {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("MyQueue");

		connection.start(); // -> CONSUMER İÇİN GEREKLİDİR
		MessageConsumer consumer = session.createConsumer(queue);
		TextMessage textMsg = (TextMessage) consumer.receive();
		System.out.println(textMsg);
	}

}
