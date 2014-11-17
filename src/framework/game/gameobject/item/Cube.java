package framework.game.gameobject.item;

public class Cube extends Item
{
	public static final float SIZE = 32;
	
	public Cube(float x, float y)
	{
		init(x,y,0.6f,0.6f,0.3f,SIZE,SIZE,"The Cube");
	}
}
