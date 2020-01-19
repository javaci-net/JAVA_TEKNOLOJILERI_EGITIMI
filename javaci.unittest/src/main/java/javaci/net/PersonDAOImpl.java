package javaci.net;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

	
	public List<Person> findAll(){
		List<Person> l = new ArrayList<Person>();
		l.add(new Person("Koray", "Gecici"));
		l.add(new Person("Ozkan", "Sari"));
		l.add(new Person("Volkan", "Istek"));
		return l;
	}
}
