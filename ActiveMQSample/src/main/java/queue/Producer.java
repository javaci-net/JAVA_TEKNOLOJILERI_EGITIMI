package queue;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();

		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Queue destination = session.createQueue("MyQueue");

		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		Scanner s = new Scanner(System.in);
		String response;
		do {

			response = s.nextLine();
			TextMessage msg = session.createTextMessage(response);
			msg.setJMSPriority(Integer.parseInt(response));
			//msg.setJMSExpiration(60000);
			//msg.setExpiration(System.currentTimeMillis() + 5000);
			//producer.setTimeToLive(5000);
			producer.send(msg);
			if (response.equals("gonderme"))
				session.rollback();
			else
				session.commit();

		} while (!response.equalsIgnoreCase("SHUTDOWN"));

		connection.close();
	}
}
