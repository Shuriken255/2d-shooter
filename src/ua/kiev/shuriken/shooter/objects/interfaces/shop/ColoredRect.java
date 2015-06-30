package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.GameObject;

public class ColoredRect extends GameObject{
	float iconPosX, iconPosY;
	public ColoredRect(String layer, float posX, float posY, float sizeX, float sizeY) {
		super(layer);
		iconPosX = posX;
		iconPosY = posY;
		setTexture("grey_box");
		alphaPicture = Menu.alpha;
		this.posX = iconPosX + Menu.POSX;
		this.posY = iconPosY + Menu.posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.bindsToCamera = true;
	}

	@Override
	public void logic() {
		this.posY = iconPosY + Menu.posY;
		alphaPicture = Menu.alpha;
	}
	
}
