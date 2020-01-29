package javaci.net;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOMock implements PersonDAO {

	protected List<Person>findAllResult;
	
	@Override
	public List<Person> findAll() {
		if (findAllResult == null) { 
			findAllResult= new ArrayList<Person>();
			findAllResult.add(new Person("Koray", "Gecici"));
		}
		return findAllResult;
	}

}
