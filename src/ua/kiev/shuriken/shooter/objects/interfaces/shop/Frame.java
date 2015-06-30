package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.GameObject;

public class Frame extends GameObject{
	public Frame(String layer, int frame){
		super(layer);
		bindsToCamera = true;
		currentFrame = frame;
		this.setAtlas("item_pause_menu");
		posX = Menu.POSX;
		posY = Menu.posY;
		sizeX = Menu.WIDTH;
		sizeY = Menu.HEIGHT;
		alphaPicture = Menu.alpha;
	}
	
	@Override
	public void logic(){
		alphaPicture = Menu.alpha;
		posY = Menu.posY;
	}
}
