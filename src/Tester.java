import java.util.ArrayList;

/**
 * @author Jing
 *
 */
public class Tester {
	
	public static void main(String[] args)
	{
		Shopping s = new Shopping(0.00,0.00,0.0);
		s.addProduct("apple", "store1", 0.01, 0.00, 5.0);
		s.addProduct("apple", "store2", 0.02, 0.00, 4.0);
		s.addProduct("apple", "store3", 0.03, 0.00, 6.0);
		s.addProduct("apple", "store4", 0.04, 0.00, 7.0);      
		
		s.addProduct("water", "store1", 0.01, 0.00, 3.0);
		s.addProduct("water", "store2", 0.02, 0.00, 3.0);
		s.addProduct("water", "store4", 0.04, 0.00, 4.0);
		s.addProduct("water", "store5", 0.05, 0.00, 2.0);
		
		s.addProduct("orange", "store2", 0.02, 0.00, 7.0);
		s.addProduct("orange", "store3", 0.03, 0.00, 4.0);
		s.addProduct("orange", "store4", 0.04, 0.00, 8.0);
		
		s.addProduct("rice", "store1", 0.01, 0.00, 2.0);
		s.addProduct("rice", "store2", 0.02, 0.00, 5.0);
		s.addProduct("rice", "store3", 0.03, 0.00, 4.0);
		s.addProduct("rice", "store5", 0.05, 0.00, 1.0);
		
		ArrayList<Plan> planss = s.getSortedPlans(3);
		for(Plan p : planss)
		{
			for(String str: p.getPlan())
			{
				System.out.println(str);
			}
			System.out.println(p.toString());
			System.out.println("***********************");
		}
		
		
		System.out.println("----------------------------------------");
		
		Shopping fromSession = s;
		
		ArrayList<Plan> plansss = fromSession.changeTimeCostFactor(0.5);
		for(Plan p : plansss)
		{
			for(String str: p.getPlan())
			{
				System.out.println(str);
			}
			System.out.println(p.toString());
			System.out.println("***********************");
		}
		
	}
}
