import java.util.ArrayList;

/**
 * @author Jing
 *
 */
public class Tester {
	
	public static void main(String[] args)
	{
		Shopping s = new Shopping();
		s.addProduct("apple", "store1", 1, 1, 5.0);
		s.addProduct("apple", "store2", 1, 1, 4.0);
		s.addProduct("apple", "store3", 1, 1, 6.0);
		s.addProduct("apple", "store4", 1, 1, 7.0);
		
		s.addProduct("water", "store1", 1, 1, 3.0);
		s.addProduct("water", "store2", 1, 1, 3.0);
		s.addProduct("water", "store4", 1, 1, 4.0);
		s.addProduct("water", "store5", 1, 1, 2.0);
		
		s.addProduct("orange", "store2", 1, 1, 7.0);
		s.addProduct("orange", "store3", 1, 1, 4.0);
		s.addProduct("orange", "store4", 1, 1, 8.0);
		
		s.addProduct("rice", "store1", 1, 1, 2.0);
		s.addProduct("rice", "store2", 1, 1, 5.0);
		s.addProduct("rice", "store3", 1, 1, 4.0);
		s.addProduct("rice", "store4", 1, 1, 7.0);
		
		ArrayList<Plan> plans = s.getOneStorePlan();
		for(Plan p : plans)
		{
			System.out.println(p.getTotalPrice());
		}
	}
}
