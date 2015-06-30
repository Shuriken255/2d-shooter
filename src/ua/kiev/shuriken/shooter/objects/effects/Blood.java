package ua.kiev.shuriken.shooter.objects.effects;

import java.util.Random;
import ua.kiev.shuriken.game.GameObject;

public class Blood extends GameObject{
	Random rand = new Random();
	int currentSpeed = 2;
	
	public Blood(float posX, float posY) {
		super("effects");
		this.setSound("hit");
		this.playSound();
		this.posX = posX-8;
		this.posY = posY-8;
		this.sizeX = this.sizeY = 16;
		this.centerX = this.centerY = 8;
		this.rotation = rand.nextInt(360);
		this.setAtlas("blood");
		currentFrame = 0;
	}

	@Override
	public void logic() {
		if(currentFrame == 13){
			this.destroy();
		} else {
			if(currentSpeed == 1){
				currentFrame++;
				currentSpeed = 1;
			} else {
				currentSpeed--;
			}
		}
	}
	
}
