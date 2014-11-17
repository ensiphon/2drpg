package framework.game.gameobject.item;

public class SwordOfAkasha extends EquippableItem
{
	public static final float SIZE = 32;
	
	private int damage;
	
	public SwordOfAkasha(float x, float y, int slot)
	{
		init(x,y,0.6f,0.6f,0.3f,SIZE,SIZE,"The Sword of Akasha", WEAPON_SLOT);
		this.damage = 3;
	}

	public int getDamage() {
		return damage;
	}
}
