package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.game.GameObject;

public class EnemyHealthBar extends GameObject{
	
	public EnemyHealthBar(float posX, float posY, float sizeX, float sizeY) {
		super("enemy_health_bars");
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.setTexture("black");
		this.alphaPicture = 70;
	}

	@Override
	public void logic() {}
}
