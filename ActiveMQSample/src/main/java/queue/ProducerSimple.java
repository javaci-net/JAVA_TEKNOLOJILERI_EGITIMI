package queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerSimple {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("MyQueue");

		MessageProducer producer = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("Test Msg");
		producer.send(msg);
		System.out.println("Msg sent");
	}
}
