import java.util.ArrayList;
import java.util.List;

public class SimpleLoadThread {

	public class CharThread extends Thread {


		@Override
		public void run() {
			System.out.println("Thread basladi:" + Thread.currentThread().getName());
			int a=0;
			for (int i=0;i<1000;i++) {
				// 100 - 1000  o(n)
				for (int j=0; j<1000; j++) {
					// 10000 - 1000 000 o(n^2)
					for (int k=0; k<1000; k++) {
						// 1 000 000 - 1 000 000 000  O(n^3)
						a=i+j+k;
					}
				}
			}
			
			while (true) {
				a++;
				a--;
				a=+2;
			}
		}
	}
	
	public void runThreads() throws InterruptedException {
		List <Thread> list = new ArrayList<Thread>();
		for (int i=0; i<4000; i++) {
			CharThread t = new CharThread();
			t.start();
			list.add(t);
		}
		for (int i=0; i<list.size(); i++) {
			Thread t = list.get(i);
			t.join();
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Program basladi");
		new SimpleLoadThread().runThreads();

		System.out.println("Program bitti");
	}
}
