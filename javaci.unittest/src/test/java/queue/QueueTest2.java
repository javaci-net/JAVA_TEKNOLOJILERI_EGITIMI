package queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class QueueTest2 {

	private static Queue<String> queueGlobal = new Queue<String>();
	
	@RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    public void tekarliTest() throws InterruptedException {
		queueGlobal.enqueue("elem");
		queueGlobal.dequeue();
	}
	
	@Test
	public void testBasicFunction() throws InterruptedException {
		Queue<String> q = new Queue<String>();
		q.enqueue("a");
		q.enqueue("b");
		String s1 = q.dequeue();
		assertEquals("a", s1);
		q.enqueue("c");
	}
	
	@Test
	public void testQueueFull() throws InterruptedException {
		Thread t = new Thread(()-> {
			Queue<String> q = new Queue<String>();
			try {
				q.enqueue("a");
				q.enqueue("b");
				q.enqueue("c");
				q.enqueue("d");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t.start();
		t.join(500);
		assertTrue(t.isAlive());
		
	}
	
	@Test
	public void testQueueEmpty() throws InterruptedException {
		Thread t = new Thread(()-> {
			Queue<String> q = new Queue<String>();
			try {
				q.dequeue();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t.start();
		t.join(500);
		assertTrue(t.isAlive());
	}
	
	
	@Test
	public void testQueueParallel() throws InterruptedException {
		Queue<String> q = new Queue<String>();
		List<String> enqueElements = Collections.synchronizedList(new ArrayList<String>());
		List<String> dequeElements = Collections.synchronizedList(new ArrayList<String>());
		
		Thread t1 = new Thread(()-> {
			try {
				for (int i=0;i<100;i++) {
					int wait = (int)(Math.random() * 10);
					Thread.sleep(wait);
					String elem = q.dequeue();
					dequeElements.add(elem);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()-> {
			try {
				for (int i=0;i<100;i++) {
					int wait = (int)(Math.random() * 10);
					Thread.sleep(wait);
					String elem = "" + i;
					enqueElements.add(elem);
					q.enqueue(elem);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		assertArrayEquals(dequeElements.toArray(), enqueElements.toArray());
		//assertNotNull(anyElement);
	}
}
