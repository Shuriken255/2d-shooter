package ua.kiev.shuriken.shooter.objects.enemies;

public class FastEnemy extends Enemy{
	
	public FastEnemy(int difficulty) {
		super(difficulty, "enemy_fast");
	}
	
	

	@Override
	public void touch(String objective) {
		
	}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){}



	@Override
	protected void initBasicStats() {
		this.damage = 500f;
		this.speed = 6f;
		this.acceleration = 0.5f;
		this.health = 60;
	}
}
