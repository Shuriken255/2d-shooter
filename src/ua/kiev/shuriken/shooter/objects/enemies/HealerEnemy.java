package ua.kiev.shuriken.shooter.objects.enemies;

public class HealerEnemy extends Enemy{
	
	public int healCooldown = 60;
	public int currentHealCooldown = healCooldown;
	
	public HealerEnemy(int difficulty) {
		super(difficulty, "enemy_healer");
	}

	@Override
	public void touch(String objective) {}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){
		if(currentHealCooldown == 0){
			health += maxHealth/120;
			this.setTexture("enemy_healer_heals");
			if(health > maxHealth){
				health = maxHealth;
			}
		} else {
			currentHealCooldown--;
		}
	}
	
	@Override
	public void getDamage(float damage){
		this.health -= damage;
		currentHealCooldown = healCooldown;
		this.setTexture("enemy_healer");
	}

	@Override
	protected void initBasicStats() {}
}
