package framework.game.gameobject.item;

public class Ruby extends Item
{
	public static final float SIZE = 32;
	public static final float R = 1.0f;
	public static final float G = 0.0f;
	public static final float B = 0.0f;
	
	public Ruby(float x, float y)
	{
		init(x,y,R,G,B,SIZE,SIZE,"Ruby Stone");
	}
}
