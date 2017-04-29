import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CombinedStore {
	private ArrayList<Store> stores;
	private ArrayList<Map<String, Double>> items;
	private Set<String> productList;
	
	public CombinedStore(Set<String> productList) {
		this.productList = productList;
		stores = new ArrayList<Store>();
		items = new ArrayList<Map<String, Double>>();
	}
	
	public void addStoreItem(Store s,Map<String, Double> m)
	{
		stores.add(s);
		items.add(m);
	}
	
	public Plan organize()
	{
		Plan p= new Plan();
		int size = stores.size();
		for(String s: productList)
		{
			double lowest = 0.0;
			int index = -1;
			for(int i=0; i< size; i++)
			{
				Map<String, Double> pMap = items.get(i);
				if(pMap.containsKey(s))
				{
					if(index == -1)
					{
						lowest = pMap.get(s);
						index = 0;
					}
					else if(pMap.get(s) < lowest)
					{
						lowest = pMap.get(s);
						index = i;
					}
				}
			}
			if(index != -1)
			{
				PlanItem pi = new PlanItem(stores.get(index), s, lowest);
				p.addPlanItem(pi);
			}
			else return null;
		}
		return p;
	}
}
