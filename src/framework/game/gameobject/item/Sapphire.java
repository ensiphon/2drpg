package framework.game.gameobject.item;

public class Sapphire extends Item
{
	public static final float SIZE = 32;
	public static final float R = 0.0f;
	public static final float G = 0.0f;
	public static final float B = 1.0f;
	
	public Sapphire(float x, float y)
	{
		init(x,y,R,G,B,SIZE,SIZE,"Sapphire Stone");
	}
}
