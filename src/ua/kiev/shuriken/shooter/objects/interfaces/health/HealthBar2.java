package ua.kiev.shuriken.shooter.objects.interfaces.health;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class HealthBar2 extends GameObject{
	
	public HealthBar2(){
		super("health");
		this.setAtlas("health_bar_interface");
		currentFrame = 2;
		this.bindsToCamera = true;
		this.posX = Game.windowSizeX-256;
		this.posY = 0;
		this.sizeX = 256;
		this.sizeY = 128;
	}
	
	@Override
	public void logic() {
		
	}
}
