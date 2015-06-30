package ua.kiev.shuriken.shooter.objects.background;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class RedBorder extends GameObject{
	
	@Override
	public void logic(){
		
	}
	
	public RedBorder(){
		super("border");
		this.setTexture("borders");
		this.posX = 0;
		this.posY = 0;
		this.alphaPicture = 20;
		this.sizeX = GamingProcess.sizeX;
		this.sizeY = GamingProcess.sizeY;
	}
}
