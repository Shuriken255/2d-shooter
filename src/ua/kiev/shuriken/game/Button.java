package ua.kiev.shuriken.game;

import org.lwjgl.input.Mouse;

public abstract class Button extends GameObject{
	public static int pressedCooldown;
	
	protected static void buttonLogic(){
		if(pressedCooldown != 0){
			pressedCooldown--;
		}
	}
	
	public boolean active = true;
	
	public Button(String layer, float posX, float posY, float sizeX, float sizeY){
		super(layer);
		this.bindsToCamera = true;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	@Override
	public void logic(){
		if(active){
			if(Mouse.getX() > posX && Mouse.getX() < posX+sizeX &&
					(Game.windowSizeY-Mouse.getY()) > posY &&
					(Game.windowSizeY-Mouse.getY()) < posY+sizeY ){
				if(Mouse.isButtonDown(0) && pressedCooldown == 0){
					currentFrame = 3;
					action();
					pressedCooldown = 15;
				} else {
					currentFrame = 2;
				}
			} else {
				currentFrame = 1;
			}
		} else {
			currentFrame = 4;
		}
	}
	
	abstract protected void action();
	
	@Override
	public void destroy(){
		this.isDeleting = true;
	}
}
