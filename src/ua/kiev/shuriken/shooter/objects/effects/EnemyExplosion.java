package ua.kiev.shuriken.shooter.objects.effects;

import java.util.Random;

import ua.kiev.shuriken.game.GameObject;

public class EnemyExplosion extends GameObject{
	
	boolean changedInPreviousFrame = true;
	
	public EnemyExplosion(float posX, float posY, float size) {
		super("effects");
		this.setSound("explosion");
		this.playSound();
		this.posX = posX-size/2;
		this.posY = posY-size/2;
		this.sizeX = size;
		this.sizeY = size;
		this.rotation = new Random().nextFloat();
		this.currentFrame = 1;
		this.setAtlas("effect_enemyexplosion");
	}

	@Override
	public void logic() {
		if(this.currentFrame < 16){
			if(changedInPreviousFrame){
				changedInPreviousFrame = false;
			} else {
				changedInPreviousFrame = true;
				currentFrame++;
			}
		} else {
			this.destroy();
		}
	}

}
