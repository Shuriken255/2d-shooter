package ua.kiev.shuriken.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ua.kiev.shuriken.shooter.gamestates.game.*;

public class Game{
	private static GameProcess currentGameProcess = null;
	static DisplayMode displayMode = Display.getDesktopDisplayMode();
	public static int windowSizeX;
	public static int windowSizeY;
	public static final int FPS = 60;
	public static boolean isRunning = true;
	
	//======================================
	// FPS
	public static int fps;
	public static long lastFPS = getTime();
	public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
	public static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
        }
        fps++;
    }
	
	//======================================
	private static void initDisplay(){
		try {
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setTitle("2d shooter");
			Display.setFullscreen(false);
			windowSizeX = displayMode.getWidth();
			windowSizeY = displayMode.getHeight();
			Display.create();
			AL.create();
		} catch (LWJGLException e) {
			AL.destroy();
			Display.destroy();
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static void deleteDisplay(){
		AL.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private static void initOpenGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0f, windowSizeX, windowSizeY, 0.0f, 1.0f, -1.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private static void loop(){
		while(!Display.isCloseRequested() && isRunning){
			Collisions.refreshObjects();
			currentGameProcess.logic();
			Button.buttonLogic();
			logic();
			draw();
			Display.update();
			Display.sync(FPS);
			updateFPS();
		}
	}
	
	public static void main(String args[]){
		initDisplay();
		initOpenGL();
		changeCurrentGameProcess(new GamingProcess());
		loop();
		deleteDisplay();
	}
	
	//==================================================================
	
	private static void draw(){
		Layers.draw();
	}
	
	private static void logic(){
		VCAM.move();
		Layers.logic();
	}
	
	public static void changeCurrentGameProcess(GameProcess gameProcess){
		currentGameProcess = gameProcess;
	}
}