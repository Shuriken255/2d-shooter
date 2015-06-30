package ua.kiev.shuriken.shooter.objects.enemies;

public class HeavyEnemy extends Enemy{
	
	public HeavyEnemy(int difficulty) {
		super(difficulty, "enemy_heavy");
	}

	@Override
	public void touch(String objective) {
		
	}
	
	@Override
	public void initSizes(){
		sizeX = 50;
		sizeY = 50;
		this.hitBoxLeft = 5;
		this.hitBoxRight = 45;
		this.hitBoxTop = 5;
		this.hitBoxBottom = 45;
		this.centerX = 25;
		this.centerY = 25;
	}
	
	@Override protected void actionsAfterDestroy(){}

	@Override
	protected void enemyLogic(){}

	@Override
	protected void initBasicStats() {
		this.speed = 3f;
		this.health = 200f;
	}
}
