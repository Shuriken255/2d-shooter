package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.game.Collisions;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class VampireEnemy extends Enemy{
	
	@Override
	public void initSizes(){
		sizeX = 50;
		sizeY = 50;
		this.hitBoxLeft = 20;
		this.hitBoxRight = 30;
		this.hitBoxTop = 20;
		this.hitBoxBottom = 30;
		this.centerX = 25;
		this.centerY = 25;
	}
	
	public VampireEnemy(int difficulty) {
		super(difficulty, "enemy_vampire");
	}
	
	@Override
	protected void collisionLogic(){
		if(Collisions.checkCollision(this, 0, 0, GamingProcess.hero) && damageCooldown == 0){
			GamingProcess.hero.damage(damage, pureDamage);
			damageCooldown = 15;
			this.health += maxHealth/10;
			if(health > maxHealth){
				health = maxHealth;
			}
		}
	}

	@Override
	public void touch(String objective) {
		
	}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){}

	@Override
	protected void initBasicStats() {}
}
