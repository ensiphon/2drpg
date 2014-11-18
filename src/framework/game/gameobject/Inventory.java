package framework.game.gameobject;

import framework.game.gameobject.item.Item;

public class Inventory
{
	public Item[] items;
	private int firstFree;
	public int itemNum = 0;
	
	public Inventory(int size)
	{
		items = new Item[size];
		firstFree = 0;
	}
	
	public boolean add(Item item)
	{
		if(itemNum<20)
		{
			System.out.println("You picked up " + item.getName());
			itemNum += 1;
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
		return false;
	}
	
	public Item get(int index)
	{
		return items[index];
	}
	
	public void remove(int index)
	{
		itemNum -= 1;
		items[index] = null;
		if(index < firstFree)
			firstFree = index;
	}
	
	public void remove(Item item)
	{
		itemNum -= 1;
		for(int i = 0; i < items.length; i++)
			if(items[i] == item)
			{
				remove(i);
				return;
			}
	}
	
	public int getNum()
	{
		return itemNum;
	}
}
