package queue;

import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

public class ProducerRedelivery {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		ActiveMQConnection connection =  (ActiveMQConnection)factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("MyQueue");
/*		
		RedeliveryPolicy policy = connection.getRedeliveryPolicy();
		policy.setInitialRedeliveryDelay(500);
		policy.setRedeliveryDelay(10000);
		policy.setBackOffMultiplier(2);
		policy.setUseExponentialBackOff(true);
		policy.setMaximumRedeliveries(5);
		connection.setRedeliveryPolicy(policy);
		*/
		MessageProducer producer = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("Test Msg");
		producer.send(msg);
		System.out.println("Msg sent");
		session.close();
		connection.close();
	}

}
