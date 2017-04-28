import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author Jing
 *
 */
public class Shopping {
	private Table<Store, String, Double> priceTable;

	public Shopping() {
		this.priceTable = HashBasedTable.create();
	}
	
	public void addProduct(String productName, String storeName, float longitude, float latitude, double price)
	{
		Store store = new Store(storeName,longitude,latitude);
		priceTable.put(store, productName, price);
	}
	
	public void addProduct(String productName, Store store, double price)
	{
		priceTable.put(store, productName, price);
	}
	
	public ArrayList<Plan> getOneStorePlan()
	{
		ArrayList<Plan> plans = new ArrayList<Plan>();
		int numOfProducts = numOfProducts();
		Set<Store> sList = getStoreList();
		for(Store s: sList)
		{
			Map<String, Double> pMap = priceTable.row(s);
			if (numOfProducts == pMap.size())
			{
				Plan p = new Plan();
				for(Map.Entry<String, Double> entry : pMap.entrySet())
				{
					p.addPlanItem(s, entry.getKey(), entry.getValue());
				}
				plans.add(p);
			}
		}
		return plans;
	}
	
	public Set<String> getProductList()
	{
		return priceTable.columnKeySet();
	}
	
	public int numOfProducts()
	{
		return getProductList().size();
	}
	
	public Set<Store> getStoreList()
	{
		return priceTable.rowKeySet();
	}
	
	public int numOfStores()
	{
		return getStoreList().size();
	}
}
