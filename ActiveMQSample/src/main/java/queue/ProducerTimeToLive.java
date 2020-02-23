package queue;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTimeToLive {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue("MyQueue");

		MessageProducer producer = session.createProducer(destination);
		//producer.setTimeToLive(30000);
		Scanner s = new Scanner(System.in);
		String response;
		do {
			response = s.nextLine();
			TextMessage msg = session.createTextMessage(response);
			//msg.setJMSExpiration(10000);
			//int deliveryMode, int priority, long timeToLive
			producer.send(msg, DeliveryMode.PERSISTENT, 4,10000);

		} while (!response.equalsIgnoreCase("SHUTDOWN"));

		connection.close();
	}
	
}
