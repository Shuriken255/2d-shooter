package ua.kiev.shuriken.shooter.objects.background;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class Background extends GameObject{
	
	@Override
	public void logic() {
		
	}
	
	public Background(){
		super("background");
		this.setTexture("default");
		this.sizeX = GamingProcess.sizeX;
		this.sizeY = GamingProcess.sizeY;
		this.posX = 0;
		this.posY = 0;
	}
}
