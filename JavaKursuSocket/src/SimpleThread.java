
public class SimpleThread {

	public static void main2(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.print('a');
		}
		for (int i = 0; i < 5; i++) {
			System.out.print('b');
		}
		for (int i = 0; i < 5; i++) {
			System.out.print('c');
		}
//aaaaabbbbbccccc
	}

	public class CharThread extends Thread {
		private char c;

		public CharThread(char c) {
			this.c = c;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.print(c);
			}
		}
	}

	public void runThreads() {
		for (int i=0;i<100; i++ ) {
			Thread t1 = new CharThread('a');
			Thread t2 = new CharThread('b');
			Thread t3 = new CharThread('c');
			t1.start();
			t2.start();
			t3.start();
		}
	}

	public static void main(String[] args) {
		new SimpleThread().runThreads();
	}

}
