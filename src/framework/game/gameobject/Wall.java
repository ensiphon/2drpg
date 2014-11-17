package framework.game.gameobject;

import framework.main.GameObject;

public class Wall extends GameObject
{
	public Wall(float x, float y, float sizeX, float sizeY)
	{
		init(x,y,1,1,1,sizeX,sizeY,DEFAULT_ID);
		setSolid(true);
	}
}
