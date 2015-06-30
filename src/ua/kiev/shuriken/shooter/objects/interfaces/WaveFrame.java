package ua.kiev.shuriken.shooter.objects.interfaces;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class WaveFrame extends GameObject{
	
	public WaveFrame() {
		super("wave_frame");
		this.bindsToCamera = true;
		this.setTexture("wave_frame");
		this.posX = Game.windowSizeX-160;
		this.posY = Game.windowSizeY/2-192;
		this.sizeX = 160;
		this.sizeY = 384;
	}

	@Override
	public void logic() {}

}
