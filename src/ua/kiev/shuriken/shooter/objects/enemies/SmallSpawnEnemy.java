package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class SmallSpawnEnemy extends Enemy{
	protected float direction;
	protected int cooldown = 20;
	
	public SmallSpawnEnemy(int difficulty, float direction, float posX, float posY) {
		super(difficulty, "enemy_spawn");
		System.out.println(difficulty);
		this.direction = direction;
		initSpeed();
		this.posX = posX-12.5f;
		this.posY = posY-12.5f;
	}
	
	@Override
	public void initPosition(){}
	
	@Override
	public void initSizes(){
		sizeX = 25;
		sizeY = 25;
		this.hitBoxLeft = 5;
		this.hitBoxRight = 20;
		this.hitBoxTop = 5;
		this.hitBoxBottom = 20;
		this.centerX = 12.5f;
		this.centerY = 12.5f;
	}
	
	@Override
	public void initSpeed(){
		this.rotateTo(GamingProcess.hero);
		this.speedX = (float)(Math.cos(direction/180*Math.PI)*speed);
		this.speedY = (float)(Math.sin(direction/180*Math.PI)*speed);
	}
	
	@Override
	public void touch(String objective) {}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){
		cooldown--;
		if(this.cooldown == 0){
			this.speed = 4f;
			this.acceleration = 0.4f;
		} else {
			speedX /= 1.1f;
			speedY /= 1.1f;
		}
	}

	@Override
	protected void initBasicStats() {
		this.health = 50f;
	}
}
