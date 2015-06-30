package ua.kiev.shuriken.game;

import org.lwjgl.input.Mouse;

public class InvicibleButton extends Button{
	
	public InvicibleButton(String layer, float posX, float posY,
			float sizeX, float sizeY, float textSize, String text,
			int horisontalAlign, int verticalAlign){
		super(layer, posX, posY, sizeX, sizeY);
		this.visible = false;
	}
	
	@Override
	public void logic(){
		if(active){
			if(Mouse.getX() > posX && Mouse.getX() < posX+sizeX &&
					(Game.windowSizeY-Mouse.getY()) > posY &&
					(Game.windowSizeY-Mouse.getY()) < posY+sizeY ){
				if(Mouse.isButtonDown(0) && pressedCooldown == 0){
					action();
					pressedCooldown = 15;
				}
			}
		}
	}
	
	@Override
	public void action(){
		
	}
}
