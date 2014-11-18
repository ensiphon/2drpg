package framework.main;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import framework.game.gameobject.Inventory;

public class GUIMain
{
	private static final float SX = 220;
	private static final float SY = 178;
	private static final float X = Display.getWidth() - SX - 20;
	private static final float Y = 20;
	private static final float INVGAP = 10;
	
	private static boolean showInventory = false;
	
	public void render(Inventory inventory)
	{
		if(showInventory)
		{
			glColor3f(0.4f,0.4f,0.4f);
			glBegin(GL_QUADS);
			{
				glVertex2f(X, Y);
				glVertex2f(X, Y + SY);
				glVertex2f(X + SX, Y + SY);
				glVertex2f(X + SX, Y);
			}
			glEnd();
		
			for(int i = 0, j = 0, k = 0; i < inventory.getNum(); i++, k++)
			{
				glColor3f(inventory.items[i].getR(),inventory.items[i].getG(),inventory.items[i].getB());
				glBegin(GL_QUADS);
				{
					glVertex2f(INVGAP + X +(k*42), INVGAP + Y + (j*42));
					glVertex2f(INVGAP + X + (k*42), INVGAP + Y + 32 + (j*42));
					glVertex2f(INVGAP + X + (k*42) + 32, INVGAP + Y + 32 + (j*42));
					glVertex2f(INVGAP + X + (k*42) + 32, INVGAP + Y + (j*42));
				}
				glEnd();
				if(k > 3)
				{
					k = -1;
					j++;
				}
			}
		}
	}
	
	public void toggleInventory(boolean choice)
	{
		showInventory = choice;
	}
}
