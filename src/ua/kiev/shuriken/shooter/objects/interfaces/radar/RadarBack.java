package ua.kiev.shuriken.shooter.objects.interfaces.radar;

import ua.kiev.shuriken.game.GameObject;

public class RadarBack extends GameObject{
	
	public static float size = 1.6f;
	
	public RadarBack(String layer) {
		super(layer);
		posX = 0;
		posY = 0;
		sizeX = 128*size;
		sizeY = 128*size;
		bindsToCamera = true;
		setTexture("radar_back");
	}

	@Override
	public void logic() {}
	
}
