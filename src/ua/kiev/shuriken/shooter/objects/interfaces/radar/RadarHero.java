package ua.kiev.shuriken.shooter.objects.interfaces.radar;

import ua.kiev.shuriken.game.GameObject;

public class RadarHero extends GameObject{
	
	public RadarHero() {
		super("radar_objects");
		sizeX = 2*RadarBack.size;
		sizeY = 2*RadarBack.size;
		setTexture("green");
		bindsToCamera = true;
	}
	
	@Override
	public void logic(){}
}
