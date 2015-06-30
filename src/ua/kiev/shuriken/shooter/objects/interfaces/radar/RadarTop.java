package ua.kiev.shuriken.shooter.objects.interfaces.radar;

import ua.kiev.shuriken.game.GameObject;

public class RadarTop extends GameObject{
	
	public RadarTop(String layer) {
		super(layer);
		posX = 0;
		posY = 0;
		sizeX = 128*RadarBack.size;
		sizeY = 128*RadarBack.size;
		bindsToCamera = true;
		setTexture("radar_top");
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
	}

}
