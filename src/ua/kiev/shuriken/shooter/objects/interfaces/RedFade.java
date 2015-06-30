package ua.kiev.shuriken.shooter.objects.interfaces;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class RedFade extends GameObject{
	static{
		GameObject.addTexture("red", "res/interface/red.png");
	}
	
	public RedFade(String layer){
		super(layer);
		this.alphaPicture = 0;
		this.bindsToCamera = true;
		this.setTexture("red");
		this.posX = 0;
		this.posY = 0;
		this.sizeX = Game.windowSizeX;
		this.sizeY = Game.windowSizeY;
		this.visible = true;
	}
	
	public void decreaseAlpha(){
		if(alphaPicture > 0){
			alphaPicture -= 5;
		}
	}
	
	@Override public void logic(){}
}
