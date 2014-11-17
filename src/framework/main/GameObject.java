package framework.main;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject
{
	public static final int DEFAULT_ID = 0;
	public static final int ITEM_ID = 1;
	public static final int PLAYER_ID = 2;
	public static final int ENEMY_ID = 3;
	
	protected float x;
	protected float y;
	protected int type;
	protected Sprite spr;
	
	protected boolean[] flags = new boolean[2];
	
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.spr = new Sprite(r,g,b,sx,sy);
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		glPushMatrix();
		{
			glTranslatef(x,y,0);
			spr.render();
		}
		glPopMatrix();
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getSX()
	{
		return spr.getSX();
	}
	
	public float getSY()
	{
		return spr.getSY();
	}
	
	public int getType()
	{
		return type;
	}
	
	public boolean getRemove()
	{
		return flags[0];
	}
	
	public boolean getSolid()
	{
		return flags[1];
	}
	
	public void setSolid(boolean value)
	{
		flags[1] = value;
	}
	
	public void setRemove()
	{
		flags[0] = true;
	}
}
