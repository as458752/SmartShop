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
		Store store = getStore(storeName,longitude,latitude);
		priceTable.put(store, productName, price);
	}
	
	public void addProduct(String productName, Store store, double price)
	{
		priceTable.put(store, productName, price);
	}
	
	public Store getStore(String storeName, float longitude, float latitude)
	{
		Set<Store> stores = getStoreList();
		for(Store s : stores)
		{
			if(s.getName().equals(storeName))	return s;
		}
		Store store = new Store(storeName, longitude, latitude);
		return store;
	}
	
	public ArrayList<Plan> getMultiplyStorePlan(int i)
	{
		ArrayList<Plan> plans = new ArrayList<Plan>();
		
		Set<Store> sList = getStoreList();
		Set<String> pList = getProductList();
		Store[] sArray = sList.toArray(new Store[sList.size()]);
		
		int numOfStores = numOfStores();
		
		ArrayList<Integer[]> list = getAllCombination(numOfStores, i);
		for(Integer[] indexs : list)
		{
			CombinedStore cStore = new CombinedStore(pList);
			for(Integer index :indexs)
			{
				Map<String, Double> pMap = priceTable.row(sArray[index]);
				cStore.addStoreItem(sArray[index], pMap);
			}
			Plan p = cStore.organize();
			if(p!=null) plans.add(p);
		}
		
		return plans;
	}
	
	public ArrayList<Integer[]> getAllCombination(int max, int num)
	{
		Integer[] first = new Integer[num];
		for(int i=0; i<num; i++)
		{
			first[i] = i;
		}
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		list.add(first);
		addToList(list,max,num);
		return list;
	}
	
	private void addToList(ArrayList<Integer[]> list, int max, int num)
	{
		Integer[] last = list.get(list.size()-1);
		for(int i=1; i<=num; i++)
		{
			if(last[num-i] < max-i)
			{
				Integer[] next = last.clone();
				next[num-i] ++;
				for(int y=num-i+1; y< num; y++)
				{
					next[y] = next[y-1] +1;
				}
				list.add(next);
				addToList(list,max,num);
				return;
			}
		}
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
