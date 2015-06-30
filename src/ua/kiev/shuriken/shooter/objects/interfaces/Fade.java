package ua.kiev.shuriken.shooter.objects.interfaces;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Game;

public class Fade extends GameObject{
	static{
		GameObject.addTexture("black", "res/interface/black.png");
	}
	
	public Fade(String layer, float alpha){
		super(layer);
		this.alphaPicture = alpha;
		this.bindsToCamera = true;
		this.setTexture("black");
		this.posX = 0;
		this.posY = 0;
		this.sizeX = Game.windowSizeX;
		this.sizeY = Game.windowSizeY;
		this.visible = true;
	}
	
	@Override public void logic(){
		
	}
}
