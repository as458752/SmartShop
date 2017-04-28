import java.util.ArrayList;

/**
 * @author Jing
 * 
 * This class describe the plan to buy a list of products
 *
 */
public class Plan {
	private ArrayList<PlanItem> planItems;

	public Plan() {
		planItems = new ArrayList<PlanItem>();
	}
	
	public void addPlanItem(PlanItem pi)
	{
		this.planItems.add(pi);
	}
	
	public void addPlanItem(Store store, String product, double price)
	{
		PlanItem pi = new PlanItem(store,product,price);
		this.planItems.add(pi);
	}
	
	public double getTotalPrice()
	{
		double totalPrice = 0.0;
		for(PlanItem pi : planItems)
		{
			totalPrice += pi.getPrice();
		}
		return totalPrice;
	}

	public ArrayList<PlanItem> getPlanItems() {
		return planItems;
	}
	
}
