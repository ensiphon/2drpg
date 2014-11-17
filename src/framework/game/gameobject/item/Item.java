package framework.game.gameobject.item;

import framework.main.GameObject;
import framework.main.Sprite;

public class Item extends GameObject
{	
	protected String name;
	
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
	{
		this.x = x;
		this.y = y;
		this.type = ITEM_ID;
		this.spr = new Sprite(r,g,b,sx,sy);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
