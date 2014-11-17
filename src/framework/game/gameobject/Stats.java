package framework.game.gameobject;

public class Stats
{
	public static final int MAX_XP = 999999;
	public static final int MAX_LEVEL = 99;
	public static final double  LEVEL_CONST = (double)MAX_XP/((double)MAX_LEVEL * (double)MAX_LEVEL);
	//public static final double LEVEL_CONST = 25.0 * Math.pow(3,(3.0/2.0)); OLD
	
	private StatScale scale;
	private boolean levelable;
	private int level;
	private float xp;
	private int health;
	
	public Stats(float xp, boolean levelable)
	{
		this.levelable = levelable;
		scale = new StatScale();
		//WARNING: Entering the
		scale.generateStatScale();
		//WARNING: LEaving
		
		if(levelable)
		{
			this.xp = xp;
			this.level = 1;
		}
		else
		{
			this.xp = -1;
			this.level = (int)xp;
		}
		health = getMaxHealth();
	}
	
	public int getLevel()
	{
		if(!levelable)
			return level;
		
		return (int)Math.sqrt((double)xp/LEVEL_CONST) + 1;
		
		/*OL' SHITE
		double x = xp + 105.0;
		double a = Math.sqrt(243.0 * (x * x) + 4050.0 * x + 17500.0);
		double c = (3.0 * x + 25.0)/25.0;
		double d = Math.cbrt(a / LEVEL_CONST + c);
		
		return (int)(d - 1.0/d * 3.0) - 1;	*/
	}
	
	public int getMaxHealth()
	{
		return (int)(getLevel() * scale.getScale(StatScale.VITALITY) * 10);
	}
	
	public int getCurrentHealth()
	{
		if(health > getMaxHealth())
			health = getMaxHealth();
		
		return health;
	}
	
	public float getSpeed()
	{
		return 4f;//(float)(getLevel() * scale.getScale(StatScale.SPEED) * 10);
	}
	
	public float getStrength()
	{
		return (float)(getLevel() * scale.getScale(StatScale.STRENGTH) * 10);
	}
	
	public float physicalDefense()
	{
		return (float)(getLevel() * scale.getScale(StatScale.PHYSICALDEFENSE) * 10);
	}
	
	public float getMagic()
	{
		return (float)(getLevel() * scale.getScale(StatScale.MAGIC) * 10);
	}
	
	public float getMagicDefense()
	{
		return (float)(getLevel() * scale.getScale(StatScale.MAGICDEFENSE) * 10);
	}
	
	public float getXP()
	{
		return xp;
	}
	
	public void addXP(float amt)
	{
		xp += amt;
		if(xp > MAX_XP)
			xp = MAX_XP;
	}
	
	public void damage(int amt)
	{
		health -= amt;
	}
}
