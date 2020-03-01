import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Main {

	private ExpensiveObjectPool pool;

	private AtomicInteger count = new AtomicInteger(0);

	public Main() throws Exception {
		GenericObjectPoolConfig<ExpensiveObject> config = new GenericObjectPoolConfig<ExpensiveObject>();
		config.setMinIdle(5);
		// config.setMaxIdle(5);
		config.setMaxTotal(5);
		// config.setMaxWaitMillis(50000);
		/*---------------------------------------------------------------------+
		|TestOnBorrow=true --> To ensure that we get a valid object from pool  |
		|TestOnReturn=true --> To ensure that valid object is returned to pool |
		+---------------------------------------------------------------------*/
		config.setTestOnBorrow(true);
		config.setBlockWhenExhausted(true);
		config.setTestOnReturn(true);
		pool = new ExpensiveObjectPool(new ExpensiveObjectFactory(), config);
		// pool.preparePool();
		System.out.println("Pool created");
	}

	public void test() {
		int limit = 100000;
		ExecutorService es = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(limit));

		System.out.println("Thread pool executed created");

		for (int i = 0; i < limit; i++) {
			Runnable r = new Thread() {
				@Override
				public void run() {
					ExpensiveObject eo = null;
					try {
						eo = pool.borrowObject();
						count.getAndIncrement();
						eo.doSomething();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					} finally {
						if (eo != null) {
							pool.returnObject(eo);
						}
					}
				}
			};
			es.submit(r);
		}
		es.shutdown();
		try {
			es.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException ignored) {

		}
		System.out.println("Pool Stats:\n Created:[" + pool.getCreatedCount() + "], Borrowed:[" + pool.getBorrowedCount() + "]");
		System.out.println("Limit was:" + limit + " count:" + count.get());
		System.out.println("Total thread created:" + count.get() + " borrowed:" + pool.getBorrowedCount());
		System.out.println("Created object count:" + pool.getCreatedCount());
	}

	public static void main(String args[]) throws Exception {
		new Main().test();
	}
}
