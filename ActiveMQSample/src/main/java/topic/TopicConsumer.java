package topic;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class TopicConsumer  {


	public static void main(String[] args) throws URISyntaxException, Exception {
		new TopicConsumer().receiveMessages();
	}

	

	
	public void receiveMessages() throws JMSException, InterruptedException {
		Connection connection = null;
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		try {
			Topic topic = session.createTopic("MyTopic");
			MessageConsumer consumer = session.createConsumer(topic);
			while (true) {
                TextMessage textMsg = (TextMessage) consumer.receive();
                System.out.println(textMsg);
                System.out.println("Received: " + textMsg.getText());
                if (textMsg.getText().equals("END")) {
                    break;
                }
            }  
			connection.start();
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
