package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.HeavyEnemy;

public class HeavyWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_heavy", "res/interface/wave_signs/heavy.png");
	}
	
	public HeavyWave(int position) {
		super(position, "wave_sign_heavy");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new HeavyEnemy(difficulty));
	}

}
