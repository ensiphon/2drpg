package framework.game;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import framework.game.gameobject.Player;
import framework.game.gameobject.Wall;
import framework.game.gameobject.item.Ruby;
import framework.game.gameobject.item.Emerald;
import framework.game.gameobject.item.Sapphire;
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
		for(int i = 1; i <= 7; i++)
		{
			objects.add(new Sapphire(184+(35*i),220 + 200));
		}
		for(int i = 1; i <= 7; i++)
		{
			objects.add(new Ruby(184+(35*i),220 + 35 + 200));
		}
		for(int i = 1; i <= 7; i++)
		{
			objects.add(new Emerald(184+(35*i),220 + 35 + 35 + 200));
		}
		//objects.add(new CookieMonster(300,500,1));
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
		glTranslatef(Display.getWidth()/2 - Player.SIZE/2, Display.getHeight()/2 - Player.SIZE/2,0);
		player.render();
		glTranslatef(-1 * player.getX(), -1 * player.getY(), 0);
		for(GameObject go : objects)
			if(go.getType() != GameObject.PLAYER_ID)
				go.render();		
		glLoadIdentity();
		player.guimain.render(player.inventory);
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
