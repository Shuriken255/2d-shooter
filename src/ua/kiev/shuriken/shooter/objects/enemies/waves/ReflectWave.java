package ua.kiev.shuriken.shooter.objects.enemies.waves;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.enemies.ReflectEnemy;

public class ReflectWave extends Wave{
	static{
		GameObject.addTexture("wave_sign_reflect", "res/interface/wave_signs/reflect.png");
	}
	
	public ReflectWave(int position) {
		super(position, "wave_sign_reflect");
	}

	@Override
	public void spawnEnemy(int difficulty) {
		Layers.addObject(new ReflectEnemy(difficulty));
	}

}
