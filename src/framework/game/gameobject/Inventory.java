package framework.game.gameobject;

import framework.game.gameobject.item.Item;

public class Inventory
{
	private Item[] items;
	private int firstFree;
	
	public Inventory(int size)
	{
		items = new Item[size];
		firstFree = 0;
	}
	
	public boolean add(Item item)
	{
		if(firstFree == items.length)
			return false;
		items[firstFree] = item;

		for(int i = firstFree + 1; i < items.length; i++)
			if(items[i] == null)
			{
				firstFree = i;
				return true;
			}
		
		firstFree = items.length;
		return true;

		/*for(int i = 0; i < items.length; i++)
			if(items[i] == null)
			{
				items[i] = item;
				return true;
			}
		return false;*/
	}
	
	public Item get(int index)
	{
		return items[index];
	}
	
	public void remove(int index)
	{
		items[index] = null;
		if(index < firstFree)
			firstFree = index;
	}
	
	public void remove(Item item)
	{
		for(int i = 0; i < items.length; i++)
			if(items[i] == item)
			{
				remove(i);
				return;
			}
	}
}