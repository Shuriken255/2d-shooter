package ua.kiev.shuriken.shooter.objects.enemies;

public class NormalEnemy extends Enemy{
	
	public NormalEnemy(int difficulty) {
		super(difficulty, "enemy_normal");
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
