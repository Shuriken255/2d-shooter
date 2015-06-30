package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.HealerEnemy;

public class HealerWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_healer", "res/interface/wave_signs/healer.png");
	}
	
	public HealerWave(int position) {
		super(position, "wave_sign_healer");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new HealerEnemy(difficulty));
	}

}
