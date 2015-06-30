package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.VampireEnemy;

public class VampireWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_vampire", "res/interface/wave_signs/vampire.png");
	}
	
	public VampireWave(int position) {
		super(position, "wave_sign_vampire");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new VampireEnemy(difficulty));
	}

}
