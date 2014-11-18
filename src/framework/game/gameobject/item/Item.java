package framework.game.gameobject.item;

import framework.main.GameObject;
import framework.main.Sprite;

public class Item extends GameObject
{	
	protected String name;
	public float r;
	public float g;
	public float b;
	
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		this.type = ITEM_ID;
		this.spr = new Sprite(r,g,b,sx,sy);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public float getR()
	{
		return r;
	}
	
	public float getG()
	{
		return g;
	}
	
	public float getB()
	{
		return b;
	}
}
