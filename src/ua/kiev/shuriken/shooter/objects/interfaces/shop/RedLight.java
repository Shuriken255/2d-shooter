package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.GameObject;

public class RedLight extends GameObject{
	
	float positionX, positionY;
	
	public RedLight(String layer, float posX, float posY, float size){
		super(layer);
		this.sizeX = size*2;
		this.sizeY = size*2;
		this.posX = posX-size/2;
		this.posY = posY-size/2;
		this.setTexture("interface_red_light");
		positionX = this.posX;
		positionY = this.posY;
		this.posX += Menu.POSX;
		this.posY += Menu.posY;
		this.alphaPicture = Menu.alpha;
		this.bindsToCamera = true;
	}
	
	@Override
	public void logic(){
		this.posX = Menu.POSX+positionX;
		this.posY = Menu.posY+positionY;
		this.alphaPicture = Menu.alpha;
	}
}
