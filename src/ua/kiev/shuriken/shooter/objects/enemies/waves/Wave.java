package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.EnemyLogic;
import ua.kiev.shuriken.shooter.objects.interfaces.wavesigns.WaveSign;

public abstract class Wave {
	protected int valueOfEnemies = 10;
	protected int cooldownBetweenSpam = 180;
	protected int currentCooldown;
	protected int position;
	protected WaveSign sign;
	
	public Wave(int position, String texture){
		sign = new WaveSign(position, texture);
		Layers.addObject(sign);
	}
	
	public void changePosition(int position){
		sign.position = position;
	}
	
	public void logic(){
		if(currentCooldown == 0){
			currentCooldown = cooldownBetweenSpam;
			if(valueOfEnemies != 0){
				spawnEnemy(EnemyLogic.difficulty);
				valueOfEnemies--;
			}
		}
		currentCooldown--;
	}
	
	public void destroy(){
		sign.destroy();
	}
	
	abstract public void spawnEnemy(int difficulty);
}
