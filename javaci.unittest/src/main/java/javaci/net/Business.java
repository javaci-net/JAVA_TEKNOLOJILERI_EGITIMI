package javaci.net;

public class Business {

	protected PersonDAO dao = new PersonDAOImpl();
	  
	public double calculateAverageNameLength() {
		int total = 0;
		int counter = 0;
		for (Person p : dao.findAll()) {
			total += p.getFirstName().length();
			counter++;
		}
		if (counter==0)
			return 0.0;
		return ((double)total) / counter;
	}
}
