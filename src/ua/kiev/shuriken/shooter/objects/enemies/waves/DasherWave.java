package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.DasherEnemy;

public class DasherWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_dasher", "res/interface/wave_signs/dasher.png");
	}
	
	public DasherWave(int position) {
		super(position, "wave_sign_dasher");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new DasherEnemy(difficulty));
	}

}
