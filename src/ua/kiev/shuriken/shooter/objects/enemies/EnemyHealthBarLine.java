package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.game.GameObject;

public class EnemyHealthBarLine extends GameObject{
	
	public EnemyHealthBarLine(float posX, float posY, float sizeX, float sizeY) {
		super("enemy_health_bars");
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.setTexture("green");
		this.alphaPicture = 70;
	}

	@Override
	public void logic() {}
	
	public void changeColor(String s){
		this.setTexture(s);
	}
}
