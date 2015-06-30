package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.FastEnemy;

public class FastWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_fast", "res/interface/wave_signs/fast.png");
	}
	
	public FastWave(int position) {
		super(position, "wave_sign_fast");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new FastEnemy(difficulty));
	}

}
