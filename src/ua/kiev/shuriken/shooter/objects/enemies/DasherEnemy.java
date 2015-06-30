package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class DasherEnemy extends Enemy{
	protected int dashCooldown = 47;
	protected int currentDashCooldown = 0;
	protected float currentSpeed;
	
	public DasherEnemy(int difficulty) {
		super(difficulty, "enemy_dasher");
	}

	@Override
	public void touch(String objective) {
		
	}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){}
	
	@Override
	public void moveToPlayer(){
		if(currentDashCooldown == 0){
			this.rotateTo(GamingProcess.hero);
			currentDashCooldown = dashCooldown;
			currentSpeed = speed;
			this.speedX = (float)Math.cos(rotation/180*Math.PI)*speed;
			this.speedY = (float)Math.sin(rotation/180*Math.PI)*speed;
		}
		currentDashCooldown--;
		speedX /= 1.015f;
		speedY /= 1.015f;
		posX += speedX;
		posY += speedY;
		if(posX > GamingProcess.sizeX){
			posX = GamingProcess.sizeX;
		}
		if(posX < -this.sizeX){
			posX = -this.sizeX;
		}
		if(posY > GamingProcess.sizeY){
			posY = GamingProcess.sizeY;
		}
		if(posY < -this.sizeY){
			posY = -this.sizeY;
		}
		currentSpeed += 0.5f;
	}
	
	@Override
	protected void initBasicStats() {
		this.speed = 11f;
		this.rotateTo(GamingProcess.hero);
		currentSpeed = speed;
	}
}
