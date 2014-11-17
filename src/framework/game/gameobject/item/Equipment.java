package framework.game.gameobject.item;

import framework.game.gameobject.Inventory;

public class Equipment
{
	private EquippableItem[] items;
	private Inventory inv;
	
	public Equipment(Inventory inv)
	{
		items = new EquippableItem[EquippableItem.NUM_SLOT];
		this.inv = inv;
	}
	
	public boolean equip(EquippableItem item)
	{
		int index = item.getSlot();
		
		if(items[index] != null)
			if(!deEquip(index))
				return false;
		
		inv.remove(item);
		items[index] = item;
		return true;
	}
	
	public boolean deEquip(int slot)
	{
		//Take current item to inventory.
		if(inv.add(items[slot]))
		{
			items[slot] = null;
			return true;
		}
		else
			return false;
	}
}
