package framework.game.gameobject;

public class CookieMonster extends Enemy
{
	private static int SIZE = 32;
	
	public CookieMonster(float x, float y, int level)
	{
		super(level);
		this.init(x, y, 0.2f, 0.3f, 1.0f, SIZE, SIZE, 3);
	}
}
