package topic;

import java.net.URISyntaxException;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer {

	public static void main(String[] args) throws URISyntaxException, Exception {
		Connection connection = null;
		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			//onnection.setClientID("DurabilityTest");
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("MyTopic");

			// Publish
			String payload = "Task" +  Calendar.getInstance().getTime();
			TextMessage msg = session.createTextMessage(payload);
			MessageProducer publisher = session.createProducer(topic);
			System.out.println("Sending text '" + payload + "'");
			publisher.send(msg, javax.jms.DeliveryMode.PERSISTENT, javax.jms.Message.DEFAULT_PRIORITY,
					Message.DEFAULT_TIME_TO_LIVE);
			session.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
