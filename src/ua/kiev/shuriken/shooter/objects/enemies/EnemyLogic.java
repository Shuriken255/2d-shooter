package ua.kiev.shuriken.shooter.objects.enemies;

import java.util.Random;

import ua.kiev.shuriken.shooter.objects.enemies.waves.*;
import ua.kiev.shuriken.shooter.objects.interfaces.wavesigns.WaveSign;

public abstract class EnemyLogic {
	public static int difficulty = 100;
	protected static int difficultyCooldown = 120;
	protected static int currentDifficultyCooldown = difficultyCooldown;
	protected static Wave currentWave;
	protected static int waveCooldown = 2400;
	protected static int currentWaveCooldown = waveCooldown;
	protected static Wave[] waves = new Wave[5];
	
	static{
		for(int i = 0; i < 2; i++){
			waves[i] = new NormalWave(i);
		}
		waves[2] = nextWave(2);
		waves[3] = nextWave(3);
		waves[4] = nextWave(4);
		
		currentWave = waves[0];
	}
	
	public static void changeWave(){
		waves[0].destroy();
		for(int i = 0; i < 4; i++){
			waves[i] = waves[i+1];
			waves[i].changePosition(i);
		}
		waves[4] = nextWave(4);
		currentWave = waves[0];
	}
	
	public static void logic(){
		currentWave.logic();
		if(currentDifficultyCooldown == 0){
			difficulty += difficulty/100;
			currentDifficultyCooldown = difficultyCooldown;
		} else {
			currentDifficultyCooldown--;
		}
		if(currentWaveCooldown == 0){
			currentWaveCooldown = waveCooldown;
			changeWave();
		}
		currentWaveCooldown--;
		WaveSign.displacement = ((float)currentWaveCooldown/(float)waveCooldown)*64;
	}
	
	public static Wave nextWave(int i){
		int rand = new Random().nextInt(8);
		switch(rand){
		case 0: return new NormalWave(i);
		case 1: return new FastWave(i);
		case 2: return new SpawnWave(i);
		case 3: return new HeavyWave(i);
		case 4: return new ReflectWave(i);
		case 5: return new HealerWave(i);
		case 6: return new VampireWave(i);
		case 7: return new DasherWave(i);
		default: return new NormalWave(i);
		}
	}
}
