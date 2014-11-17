package framework.game.gameobject;

import java.util.ArrayList;
import framework.game.Delay;
import framework.game.Game;
import framework.game.Time;
import framework.game.Util;
import framework.main.GameObject;
import framework.main.Sprite;

public class Enemy extends StatObject
{
	private static final float DAMPING = 0.5f;
	private StatObject target;
	private float attackRange;
	private float sightRange;
	private int attackDamage; 
	private Delay attackDelay;
	
	public Enemy(int level)
	{
		stats = new Stats(level,false);
		target = null;
		attackDelay = new Delay(1000);
		attackRange = 48f;
		sightRange = 128f;
		attackDamage = 1;
		attackDelay.terminate();
		setSolid(true);
	}
	
	@Override
	public void update()
	{
		if(target == null)
			look();
		else
		{
			if(Util.lineOfSight(this, target) && Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= attackRange)
			{
				if(attackDelay.isOver())
					attack();
			}
			else
				chase();
		}
		
		if(stats.getCurrentHealth() < 0)
			death();
	}
	
	protected void attack()
	{	
		getTarget().damage(getAttackDamage());
		System.out.println("Player's HP: " + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
		attackDelay.restart();
	}
	
	protected void death()
	{
		setRemove();
	}
	
	protected void look()
	{
		ArrayList<GameObject> objects = Game.checkCollisionCircle(x, y, sightRange);
		
		for(GameObject go : objects)
			if(go.getType() == PLAYER_ID)
				setTarget((StatObject)go);
	}
	
	protected void chase()
	{
		float speedX = getTarget().getX() - x;
		float speedY = getTarget().getY() - y;
		
		float maxSpeed = getStats().getSpeed() * DAMPING;
		
		if(speedX > maxSpeed)
			speedX = maxSpeed;
		if(speedX < -maxSpeed)
			speedX = -maxSpeed;
		
		if(speedY > maxSpeed)
			speedY = maxSpeed;
		if(speedY < -maxSpeed)
			speedY = -maxSpeed;
		
		x = x + speedX * Time.getDelta();
		y = y + speedY * Time.getDelta();
	}
	
	public void setTarget(StatObject go)
	{
		target = go;
	}
	
	public StatObject getTarget()
	{
		return target;
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public int getAttackDamage()
	{
		return attackDamage;
	}
	
	public void setAttackRange(int amt)
	{
		attackRange = amt;
	}
	
	public void setAttackDelay(int time)
	{
		attackDelay = new Delay(time);
		attackDelay.terminate();
	}
	
	public void setAttackDamage(int amt)
	{
		attackDamage = amt;
	}
	
	public void setSightRange(float amt)
	{
		sightRange = amt;
	}
	
	@Override
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
	{
		this.x = x;
		this.y = y;
		this.type = ENEMY_ID;
		this.spr = new Sprite(r,g,b,sx,sy);
	}
}
