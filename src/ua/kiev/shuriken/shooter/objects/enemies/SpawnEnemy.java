package ua.kiev.shuriken.shooter.objects.enemies;
import ua.kiev.shuriken.game.Layers;

public class SpawnEnemy extends Enemy{
	
	public SpawnEnemy(int difficulty) {
		super(difficulty, "enemy_spawn");
	}

	@Override
	public void touch(String objective) {
		
	}
	
	@Override protected void actionsAfterDestroy(){
		Layers.addObject(new SmallSpawnEnemy(this.difficulty, rotation-90,
				this.posX+centerY, this.posY+sizeY/2));
		Layers.addObject(new SmallSpawnEnemy(this.difficulty, rotation+90,
				this.posX+centerY, this.posY+sizeY/2));
	}

	@Override
	protected void enemyLogic(){}

	@Override
	protected void initBasicStats() {
		this.money = 0;
	}
}
