package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.NormalEnemy;

public class NormalWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_normal", "res/interface/wave_signs/normal.png");
	}
	
	public NormalWave(int position) {
		super(position, "wave_sign_normal");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new NormalEnemy(difficulty));
	}

}
