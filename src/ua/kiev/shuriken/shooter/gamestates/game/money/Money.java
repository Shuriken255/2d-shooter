package ua.kiev.shuriken.shooter.gamestates.game.money;

import ua.kiev.shuriken.game.Collisions;
import ua.kiev.shuriken.game.SolidGameObject;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public abstract class Money extends SolidGameObject {
	protected int cost;
	protected int moveToPlayerCooldown = 240;
	protected float speedX, speedY;
	protected float maxSpeed = 10;
	protected float acceleration = 0;

	public Money(float direction, float posX, float posY) {
		super("money", "money");
		this.posX = posX;
		this.posY = posY;
		rotation = direction;
		this.sizeX = 16;
		this.sizeY = 16;
		this.centerX = 6;
		this.centerY = 6;
		this.hitBoxLeft = 4;
		this.hitBoxTop = 4;
		this.hitBoxRight = 12;
		this.hitBoxBottom = 12;
		speedX = (float)(Math.cos(rotation/180*Math.PI)*25);
		speedY = (float)(Math.sin(rotation/180*Math.PI)*25);
	}

	@Override
	public void logic() {
		if(moveToPlayerCooldown != 0){
			moveToPlayerCooldown--;
			speedX /= 1.1f;
			speedY /= 1.1f;
		}
		if(acceleration < 2){
			acceleration += 0.05f;
		}
		this.rotateTo(GamingProcess.hero);
		this.speedX += Math.cos(rotation/180*Math.PI)*acceleration;
		this.speedY += Math.sin(rotation/180*Math.PI)*acceleration;
		
		
		if(Collisions.checkCollision(this, 0, 0, GamingProcess.hero)){
			this.destroy();
			GamingProcess.money += cost;
		}
		if(Math.sqrt(speedX*speedX+speedY*speedY) > maxSpeed){
			float k = (float)(Math.sqrt(speedX*speedX+speedY*speedY)/maxSpeed);
			speedX /= k;
			speedY /= k;
		}
		this.posX += speedX;
		this.posY += speedY;
	}

	@Override
	public void touch(String objective) {}

}
