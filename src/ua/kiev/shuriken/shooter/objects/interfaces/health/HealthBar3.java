package ua.kiev.shuriken.shooter.objects.interfaces.health;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.shooter.objects.hero.Hero;


public class HealthBar3 extends GameObject{
	public static final float MAX_SIZE = 280.7f/2;
	Hero hero;
	
	public HealthBar3(Hero hero){
		super("health");
		this.hero = hero;
		this.setAtlas("health_bar_line");
		currentFrame = 1;
		this.bindsToCamera = true;
		this.posX = Game.windowSizeX-184.4f;
		this.posY = 75/2;
		this.sizeY = 33.6f/2;
		this.centerX = 0;
		this.centerY = 0;
	}
	
	@Override
	public void logic() {
		this.sizeX = ((float)hero.getCurrentHP()/(float)hero.getMaxHP())*MAX_SIZE;
	}
	
	
}
