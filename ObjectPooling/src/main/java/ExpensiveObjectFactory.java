import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ExpensiveObjectFactory extends BasePooledObjectFactory<ExpensiveObject>  {

	@Override
	public ExpensiveObject create() throws Exception {
		return new ExpensiveObject();
	}

	@Override
	public PooledObject<ExpensiveObject> wrap(ExpensiveObject obj) {
		return new DefaultPooledObject<ExpensiveObject>(obj);
	}
	
}
