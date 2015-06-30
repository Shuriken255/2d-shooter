package ua.kiev.shuriken.shooter.objects.interfaces.health;


import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.shooter.objects.hero.Hero;

public class HealthBar4 extends GameObject{
	public static final float MAX_SIZE = 280.7f/2;
	
	Hero hero;
	public HealthBar4(Hero hero){
		super("health");
		this.hero = hero;
		this.setAtlas("health_bar_line");
		currentFrame = 2;
		this.bindsToCamera = true;
		this.posX = 0;
		this.posY = 75/2;
		this.sizeX = 17.6f/2;
		this.sizeY = 33.6f/2;
	}
	
	@Override
	public void logic() {
		this.posX = ((float)hero.getCurrentHP()/(float)hero.getMaxHP())*MAX_SIZE+Game.windowSizeX-184.4f;
	}
}
