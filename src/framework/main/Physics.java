package framework.main;

public class Physics
{
	public static final int FORWARD = 0;
	public static final int BACKWARD = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static GameObject checkCollisionGO(GameObject go1, GameObject go2)
	{
		return checkCollisionCG(go1.getX(), go1.getY(), go1.getSX(), go1.getSY(), go2);
	}
	
	public static GameObject checkCollisionCG(float x1, float y1, float sx1, float sy1, GameObject go2)
	{
		float x2 = go2.getX();
		float y2 = go2.getY();
		float sx2 = go2.getSX();
		float sy2 = go2.getSY();
		
		if(x2+sx2 <= x1 || y1+sy1 <= y2 || y2+sy2 <= y1 || x1+sx1 <= x2)
			return null;
			
		return go2;
	}
	
	public static float checkCollisionCG(float x1, float y1, float sx1, float sy1, GameObject go2, int xory)
	{
		float x2 = go2.getX();
		float y2 = go2.getY();
		float sx2 = go2.getSX();
		float sy2 = go2.getSY();
		
		float lefta = Math.abs(x2 + sx2 - x1);
		float righta = Math.abs(x2 - x1 - sx1);
		float forwarda = Math.abs(y2 - sy1 - y1);
		float backwarda = Math.abs(y2 + sy2 - y1);
		float small = lefta;
		int smalldir = 2;
	
		if(small > righta)
		{
			small = righta;
			smalldir = 3;
		}
		if(small > forwarda)
		{
			small = forwarda;
			smalldir = 0;
		}
		if(small > backwarda)
		{
			smalldir = 1;
		}	
		
		if(xory == 0)
		{
			if(smalldir == LEFT)
				return x2 + sx2 - x1;
			if(smalldir == RIGHT)
				return x2 - x1 - sx1;
		}
		else if(xory == 1)
		{
			if(smalldir == FORWARD)
				return y2 - y1 - sy1;
			if(smalldir == BACKWARD)
				return y2 + sy2 - y1;
		}
		
		return 0;
	}
}