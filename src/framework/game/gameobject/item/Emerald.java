package framework.game.gameobject.item;

public class Emerald extends Item
{
	public static final float SIZE = 32;
	public static final float R = 0.0f;
	public static final float G = 1.0f;
	public static final float B = 0.0f;
	
	public Emerald(float x, float y)
	{
		init(x,y,R,G,B,SIZE,SIZE,"Emerald Stone");
	}
}
