package framework.game.gameobject;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

import framework.game.Delay;
import framework.game.Game;
import framework.game.Time;
import framework.game.Util;
//import framework.game.gameobject.item.Equipment;
import framework.game.gameobject.item.Item;
import framework.main.GUIMain;
import framework.main.GameObject;

public class Player extends StatObject
{
	public static final int SIZE = 32;
	
	public static final int FORWARD = 0;
	public static final int BACKWARD = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public Inventory inventory;
	//private Equipment equipment;
	public GUIMain guimain;

	private float attackRange;
	private int facingDirection;
	private Delay attackDelay;
	private int attackDamage;
	
	private int moveAmountX;
	private int moveAmountY;
	
	private float newX;
	private float newY;
	
	public Player(float x, float y)
	{
		init(x,y,0.1f,1f,0.5f,SIZE,SIZE,PLAYER_ID);
		stats = new Stats(0,true);
		inventory = new Inventory(20);
		//equipment = new Equipment(inventory);
		attackRange = 49f;
		attackDamage = 1;
		attackDelay = new Delay(500);
		facingDirection = 0;
		moveAmountX = 0;
		moveAmountY = 0;
		attackDelay.terminate();
		guimain = new GUIMain();
	}
	
	@Override
	public void update()
	{
		newX = x + moveAmountX;
		newY = y + moveAmountY;
		
		ArrayList<GameObject> objects = Game.checkCollisionRectangle(newX, newY, newX + SIZE, newY + SIZE);
		
		boolean move = true;
		
		for(GameObject go : objects)
		{
			if(go.getType() == GameObject.ITEM_ID)
			{
				if(inventory.itemNum < 20)
				{
					go.setRemove();
					addItem((Item)go);
				}
			}
			if(go.getSolid())
			{
				if(Game.checkCollisionRectangle(x, y, x + SIZE, y + SIZE, go, 0) == 0 && Game.checkCollisionRectangle(x, y, x + SIZE, y + SIZE, go, 1) == 0)
					move = false;
				else
				{
					newX += Game.checkCollisionRectangle(x, y, x + SIZE, y + SIZE, go, 0) - moveAmountX;
					newY += Game.checkCollisionRectangle(x, y, x + SIZE, y + SIZE, go, 1) - moveAmountY;
				}
			}
		}	
		
		moveAmountX = 0;
		moveAmountY = 0;
		
		if(!move)
			return;
			
		x = newX;
		y = newY;
	}
	
	public void getInput()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP))
			move(0,1);
		if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			move(-1,0);
		if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			move(0,-1);
		if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			move(1,0);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.isOver())
			attack();
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			guimain.toggleInventory(true);		
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			guimain.toggleInventory(false);
	}
	
	@Override
	public void render()
	{
		spr.render();
	}
	
	public void attack()
	{
		//Find objects in attack range.
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		if(facingDirection == FORWARD)
			objects = Game.checkCollisionRectangle(x, y, x + SIZE, y + attackRange);
		else if(facingDirection == BACKWARD)
			objects = Game.checkCollisionRectangle(x, y - attackRange + SIZE, x + SIZE, y);
		else if(facingDirection == LEFT)
			objects = Game.checkCollisionRectangle(x - attackRange + SIZE, y, x, y + SIZE);
		else if(facingDirection == RIGHT)
			objects = Game.checkCollisionRectangle(x, y, x + attackRange, y + SIZE);

		//Find enemy object.
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		for(GameObject go : objects)
		{
			if(go.getType() == ENEMY_ID)
				enemies.add((Enemy)go);
		}
		
		//Find closest enemy.
		if(enemies.size() > 0)
		{
			Enemy target = enemies.get(0);
			
			if(enemies.size() > 1)
			{
				for(Enemy e : enemies)
				{
					if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
						target = e;	
				}
			}
			
			//Attack enemy
			target.damage(attackDamage);
			System.out.println("Enemy's HP: " + target.getCurrentHealth() + "/" + target.getMaxHealth());

			attackDelay.restart();
			return;
		}
		System.out.println("no target!");
		
		attackDelay.restart();
	}
	
	private void move(float magX, float magY)
	{
		if(magX == 0 && magY == 1)
			facingDirection = FORWARD;
		else if(magX == 0 && magY == -1)
			facingDirection = BACKWARD;
		else if(magX == -1 && magY == 0)
			facingDirection = LEFT;
		else if(magX == 1 && magY == 0)
			facingDirection = RIGHT;
		
		moveAmountX += getSpeed() * magX * Time.getDelta();
		moveAmountY += getSpeed() * magY * Time.getDelta();
	}
	
	public void addItem(Item item)
	{
		inventory.add(item);
	}
	
	public void addXP(float amt)
	{
		stats.addXP(amt);
	}
}