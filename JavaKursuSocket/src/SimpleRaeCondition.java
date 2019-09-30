import java.util.ArrayList;
import java.util.List;

public class SimpleRaeCondition {

	private static int value;
	
	private static Object SYNCH_OBJECT = new Object();
	
	public static void incrementValue() {
		/*synchronized (SYNCH_OBJECT) {
			int newVal = value + 1;
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			value = newVal;
				
		}*/
		for (int i = 0; i < 1000; i++) {
			synchronized (SYNCH_OBJECT) {
				value++;
			}
		}
	}
	
	public class IncrementThread extends Thread {
		
		@Override
		public void run() {
			incrementValue();
		}
	}
	
	public void runThreads() throws InterruptedException {
		List <Thread> list = new ArrayList<Thread>();
		System.out.println("Thread ler baslatiliyor");
		for (int i=0;i<100; i++) {
			Thread t = new IncrementThread();
			t.start();
			list.add(t);
			
		}
		for (int i=0; i<list.size(); i++) {
			Thread t = list.get(i);
			t.join();
		}

		System.out.println("Thread ler bitti. value:" + value);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new SimpleRaeCondition().runThreads();
	}

}
