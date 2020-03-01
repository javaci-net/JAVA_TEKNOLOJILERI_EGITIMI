import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpensivePoolTest {
	
	private ExpensiveObjectPool pool;
	
    private AtomicInteger count = new AtomicInteger(0);
    
    @BeforeEach
    public void setUp() throws Exception {
        GenericObjectPoolConfig<ExpensiveObject> config = new GenericObjectPoolConfig<ExpensiveObject>();
        config.setMaxIdle(1);
        config.setMaxTotal(1);
        /*---------------------------------------------------------------------+
        |TestOnBorrow=true --> To ensure that we get a valid object from pool  |
        |TestOnReturn=true --> To ensure that valid object is returned to pool |
        +---------------------------------------------------------------------*/
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        pool = new ExpensiveObjectPool(new ExpensiveObjectFactory(), config);
    }
    
    @Test
    public void test() {
        try {
            int limit = 10;
            ExecutorService es = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(limit));
            for (int i=0; i<limit; i++) {
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
            } catch (InterruptedException ignored) {}
            System.out.println("Pool Stats:\n Created:[" + pool.getCreatedCount() + "], Borrowed:[" + pool.getBorrowedCount() + "]");
            
            assertTrue(limit == count.get());
            assertTrue(count.get() == pool.getBorrowedCount());
            assertTrue(1 == pool.getCreatedCount());
        } catch (Exception ex) {
            fail("Exception:" + ex);
        }
    }
}
