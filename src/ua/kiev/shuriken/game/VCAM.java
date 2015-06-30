package ua.kiev.shuriken.game;

import org.lwjgl.input.Keyboard;

public class VCAM {
	public static final float DEFAULT_SIZE_X = Game.windowSizeX;
	public static final float DEFAULT_SIZE_Y = Game.windowSizeY;
	public static float posX = 0, posY = 0;
	public static float sizeX = DEFAULT_SIZE_X;
	public static float sizeY = DEFAULT_SIZE_Y;
	private static float flow = 5;
	private static GameObject followingObject;
	public static float borderLeft, borderTop, borderBottom, borderRight;
	public static boolean hasBorders = false;
	
	public static float cofficientX = 1f;
	public static float cofficientY = 1f;
	
	static{
		posX -= sizeX/2;
		posY -= sizeY/2;
	}
	
	public static void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			cofficientX += 0.01f;
			cofficientY += 0.01f;
			float prevSizeX = sizeX;
			sizeX = DEFAULT_SIZE_X*cofficientX;
			float prevSizeY = sizeY;
			sizeY = DEFAULT_SIZE_Y*cofficientY;
			posX += (prevSizeX-sizeX)/2;
			posY += (prevSizeY-sizeY)/2;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			cofficientX -= 0.01f;
			cofficientY -= 0.01f;
			float prevSizeX = sizeX;
			sizeX = DEFAULT_SIZE_X*cofficientX;
			float prevSizeY = sizeY;
			sizeY = DEFAULT_SIZE_Y*cofficientY;
			posX += (prevSizeX-sizeX)/2;
			posY += (prevSizeY-sizeY)/2;
		}
		
		
		if(followingObject != null){
			posX += (followingObject.posX+followingObject.centerX-posX-sizeX/2)*flow/100;
			posY += (followingObject.posY+followingObject.centerY-posY-sizeY/2)*flow/100;
		}
		
		if(hasBorders){
			if(posX < borderLeft){
				posX = borderLeft;
			}
			if(posX+sizeX > borderRight){
				posX = borderRight-sizeX;
			}
			if(posY < borderTop){
				posY = borderTop;
			}
			if(posY+sizeY > borderBottom){
				posY = borderBottom-sizeY;
			}
		}
	}
	
	public static void setFollowingObject(GameObject object){
		followingObject = object;
	}
	
	public static void setBorders(float left, float top, float right, float bottom){
		hasBorders = true;
		borderLeft = left;
		borderTop = top;
		borderRight = right;
		borderBottom = bottom;
	}
	
	public static void deleteBorders(){
		hasBorders = false;
	}
}
