package ua.kiev.shuriken.shooter.objects.interfaces.radar;

import ua.kiev.shuriken.game.GameObject;

public class RadarEnemy extends GameObject{
	
	public RadarEnemy() {
		super("radar_objects");
		sizeX = 2*RadarBack.size;
		sizeY = 2*RadarBack.size;
		setTexture("red");
		bindsToCamera = true;
	}
	
	@Override
	public void logic(){}
}
