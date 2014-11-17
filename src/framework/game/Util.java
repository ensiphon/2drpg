package framework.game;

import framework.main.GameObject;

public class Util
{	
	public static boolean lineOfSight(GameObject go1, GameObject go2)
	{
		return true;
	}
	
	public static float dist(float x1, float y1, float x2, float y2)
	{
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		return (float)Math.sqrt((dx * dx) + (dy * dy));
	}
}
