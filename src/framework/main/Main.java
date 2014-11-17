package framework.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import framework.game.Game;
import framework.game.Time;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public static void main(String[] args)
	{
		//Initialize program
		initDisplay();
		initGL();
		
		initGame();
		gameLoop();
		cleanUp();
	}

	private static void initDisplay()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Keyboard.create();
			Display.setVSyncEnabled(true);
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void initGL()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
		glClearColor(0,0,0,0);
	}
	
	private static void initGame()
	{
		Game.game = new Game();
	}

	private static void gameLoop()
	{
		Time.init();
		
		int frame = 0;
		long lastTime = System.nanoTime();
		long totalTime = 0;
		
		while(!Display.isCloseRequested())
		{
			long now = System.nanoTime();
			long passed = now - lastTime;
			lastTime = now;
			totalTime += passed;
			
			if(totalTime >= 1000000000)
			{
				if(frame < 60)
					System.out.println(frame);
				totalTime = 0;
				frame = 0;
			}
			
			Time.update();
			getInput();
			update();
			render();
			frame++;
		}
	}

	private static void getInput()
	{
		Game.game.getInput();
	
		if(Keyboard.isKeyDown(Keyboard.KEY_BACK) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			cleanUp();
			System.exit(1);
		}
	}
	
	private static void update()
	{
		Game.game.update();
	}
	
	private static void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		Game.game.render();
		
		Display.update();
		Display.sync(60);
	}
	
	
	private static void cleanUp()
	{
		Display.destroy();
		Keyboard.destroy();
	}
}
