import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ExpensiveObjectPool extends GenericObjectPool<ExpensiveObject> {

	public ExpensiveObjectPool(PooledObjectFactory<ExpensiveObject> factory) {
		super(factory);
	}

	public ExpensiveObjectPool(final PooledObjectFactory<ExpensiveObject> factory,
			final GenericObjectPoolConfig<ExpensiveObject> config) {
		super(factory, config);
	}

}
