
public class ExpensiveObject {

	public ExpensiveObject() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Expensive Object Created");
	}
	public void doSomething() {
		// TODO Auto-generated method stub
		
	}

}
