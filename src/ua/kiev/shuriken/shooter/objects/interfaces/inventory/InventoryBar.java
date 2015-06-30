package ua.kiev.shuriken.shooter.objects.interfaces.inventory;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class InventoryBar extends GameObject{
	
	public InventoryBar(String layer){
		super(layer);
		this.bindsToCamera = true;
		this.setTexture("bar");
		this.posX = Game.windowSizeX/2-220;
		this.posY = Game.windowSizeY-80;
		this.sizeX = 440;
		this.sizeY = 80;
	}
	@Override public void logic(){}
}
