package framework.game;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import framework.game.gameobject.Player;
import framework.game.gameobject.Wall;
import framework.game.gameobject.item.Cube;
import framework.main.GameObject;
import framework.main.Physics;

public class Game
{	
	public static Game game;
	
	private ArrayList<GameObject> objects; //16102014
	private ArrayList<GameObject> remove;
	private Player player;
	
	public void generateTestLevel()
	{
		objects.add(new Wall(200,200+10,10,300));
		objects.add(new Wall(200+10,200,300,10));
	}
	
	public Game()
	{		
		objects = new ArrayList<GameObject>();
		remove = new ArrayList<GameObject>();
		
		player = new Player(Display.getWidth()/2 - Player.SIZE/2, Display.getHeight()/2 - Player.SIZE/2);
		
		objects.add(player);
		generateTestLevel();
		objects.add(new Cube(250,250));
		/*objects.add(new CookieMonster(300,500,1));
		objects.add(new CookieMonster(50,500,1));
		objects.add(new Wall(200,200,1,300));*/
	}
	
	public void getInput()
	{
		player.getInput();
	}
	
	public void update()
	{
		for(GameObject go : objects)
		{
			if(!go.getRemove())
				go.update();
			else
			{
				remove.add(go);
			}
		}
		
		for(GameObject go : remove)
		{
			objects.remove(go);
		}
	}
	
	public void render()
	{
		for(GameObject go : objects)
			go.render();
	}
	
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}
	
	public static ArrayList<GameObject> checkCollisionCircle(float x, float y, float radius)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		for(GameObject go : game.getObjects())
		{
			if(Util.dist(go.getX(), go.getY(), x, y) <= radius)
				res.add(go);
		}
		
		return res;
	}
	
	public static ArrayList<GameObject> checkCollisionRectangle(float x1, float y1, float x2, float y2)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		float sx = x2 - x1;
		float sy = y2 - y1;
		
		for(GameObject go : game.getObjects())
		{
			if(Physics.checkCollisionCG(x1,y1,sx,sy,go) != null)
				res.add(go);
		}
	
		return res;
	}
	
	public static float checkCollisionRectangle(float x1, float y1, float x2, float y2, GameObject go2, int xory)
	{
		float sx = x2 - x1;
		float sy = y2 - y1;
		return Physics.checkCollisionCG(x1,y1,sx,sy,go2,xory);
	}
}
