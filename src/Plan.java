import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jing
 * 
 *         This class describe the plan to buy a list of products
 *
 */
public class Plan implements Comparable<Plan> {
	private ArrayList<PlanItem> planItems;
	private Set<Store> stores;
	private Location currentLoc;
	private double totalDis;
	private double totalPri;
	private double totalCos;
	private Path shortestPath;
	private static double GAS_PRICE = 2.0;
	private static double TIMECOST = 10.0;
	private static double SPEED = 40.0;
	private double timeCostFactor = 0.0;

	public Plan(Location currentLoc, double tcf) {
		planItems = new ArrayList<PlanItem>();
		stores = new HashSet<Store>();
		this.currentLoc = currentLoc;
		this.timeCostFactor = tcf;
	}

	public void addPlanItem(PlanItem pi) {
		this.planItems.add(pi);
		this.stores.add(pi.getStore());
	}

	public void addPlanItem(Store store, String product, double price) {
		PlanItem pi = new PlanItem(store, product, price);
		this.planItems.add(pi);
		this.stores.add(pi.getStore());
	}

	public void computeTotalPrice() {
		double totalPrice = 0.0;
		for (PlanItem pi : planItems) {
			totalPrice += pi.getPrice();
		}
		this.totalPri = totalPrice;
	}

	public double getTotalPrice() {
		return this.totalPri;
	}

	public double getTotalCost() {
		return this.totalCos;
	}

	public double getTimeCostFactor() {
		return timeCostFactor;
	}

	public void setTimeCostFactor(double timeCostFactor) {
		this.timeCostFactor = timeCostFactor;
		double time = this.totalDis / SPEED;
		this.totalCos = this.totalPri + time * TIMECOST * timeCostFactor + this.totalDis * GAS_PRICE;
	}

	public void organize() {
		computeShortestPath();
		this.totalDis = this.shortestPath.getDis();
		computeTotalPrice();
		double time = this.totalDis / SPEED;
		this.totalCos = this.totalPri + time * TIMECOST * timeCostFactor + this.totalDis * GAS_PRICE;
		for(PlanItem pi : planItems)
		{
			pi.setOrder(this.shortestPath.getStores().indexOf(pi.getStore()));
		}
		Collections.sort(this.planItems);
	}

	public ArrayList<PlanItem> getPlanItems() {
		return planItems;
	}

	public double getTotalDistance() {
		return this.totalDis;
	}

	public void computeShortestPath() {
		Path shortest = new Path();
		List<Store> listStores = new ArrayList<Store>(this.stores);
		permute(listStores, 0, shortest);
		this.shortestPath = shortest;
	}

	public Path getShortestPath() {
		return this.shortestPath;
	}

	private void permute(List<Store> arr, int k, Path shortest) {
		for (int i = k; i < arr.size(); i++) {
			Collections.swap(arr, i, k);
			permute(arr, k + 1, shortest);
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			double dis = GetTotalDistance(arr);
			if (shortest.getStores() == null)
				shortest.setPath(arr, dis);
			else if (dis < shortest.getDis())
				shortest.setPath(arr, dis);
		}
	}

	private double GetTotalDistance(List<Store> arr) {
		double dis = this.currentLoc.distanceTo(arr.get(0).getLoc());
		for (int i = 1; i < arr.size(); i++) {
			dis += arr.get(i - 1).getLoc().distanceTo(arr.get(i).getLoc());
		}
		dis += arr.get(arr.size() - 1).getLoc().distanceTo(this.currentLoc);
		return dis;
	}

	public int compareTo(Plan comparePlan) {
		double compareCost = comparePlan.getTotalCost();
		return new Double(this.totalCos - compareCost).intValue();
	}

	public String toString() {
		String output = "";
		output += "Total Cost:" + this.totalCos + "\n" + "Total Distance:" + this.totalDis + "\n" + "Total Price:"
				+ this.totalPri + "\n";
		String storeName = "";
		for(PlanItem pi: this.planItems)
		{
			if(!pi.getStore().getName().equals(storeName))
			{
				storeName = pi.getStore().getName();
				output += storeName + ":\n";
			}
				output += pi.getProduct() + "," + pi.getPrice() + "\n";
		}
		return output;
	}
	
	public ArrayList<String> getPlan()
	{
		ArrayList<String> strPlan = new ArrayList<String>();
		String eachStore = "";
		String storeName = "";
		for(PlanItem pi: this.planItems)
		{
			if(!pi.getStore().getName().equals(storeName))
			{
				if(!eachStore.equals(""))	strPlan.add(eachStore);
				storeName = pi.getStore().getName();
				eachStore = storeName;
			}
			eachStore += "," + pi.getProduct() + "," + pi.getPrice();
		}
		if(!eachStore.equals(""))	strPlan.add(eachStore);
		return strPlan;
	}
}
