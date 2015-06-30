package ua.kiev.shuriken.shooter.objects.enemies;

import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

public class ReflectEnemy extends Enemy{
	
	protected int reflectionCooldown = 221;
	protected int currentReflectionCooldown = reflectionCooldown;
	protected int turningOffCooldown = 30;
	protected int currentTurningOffCooldown = turningOffCooldown;
	protected boolean isReflecting = false;
	
	public ReflectEnemy(int difficulty) {
		super(difficulty, "enemy_reflect");
	}

	@Override
	public void touch(String objective) {
		
	}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){
		if(currentReflectionCooldown == 0){
			currentTurningOffCooldown--;
			if(currentTurningOffCooldown == turningOffCooldown-1){
				this.setTexture("enemy_reflect_red");
				isReflecting = true;
			} else {
				if(currentTurningOffCooldown == 0){
					currentTurningOffCooldown = turningOffCooldown;
					currentReflectionCooldown = reflectionCooldown;
					this.setTexture("enemy_reflect");
					isReflecting = false;
				}
			}
		} else {
			currentReflectionCooldown--;
		}
	}
	
	@Override
	public void getDamage(float damage){
		if(!isReflecting){
			this.health -= damage;
		} else {
			GamingProcess.hero.damage(damage*10, 0);
		}
	}

	@Override
	protected void initBasicStats() {}
}
