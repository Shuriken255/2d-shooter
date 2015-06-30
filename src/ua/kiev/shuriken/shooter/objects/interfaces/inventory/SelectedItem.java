package ua.kiev.shuriken.shooter.objects.interfaces.inventory;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class SelectedItem extends GameObject{
	public SelectedItem(){
		super("selectedItem");
		this.bindsToCamera = true;
		addTexture("bar", "res/interface/InventoryBar_selected_spot.png");
		this.setTexture("bar");
		sizeX = 64;
		sizeY = 64;
		posX = Game.windowSizeX/2-212;
		posY = Game.windowSizeY-72;
	}
	
	public void changePosition(int pos){
		switch(pos){
		case 0: posX = Game.windowSizeX/2-212; break;
		case 1: posX = Game.windowSizeX/2-140; break;
		case 2: posX = Game.windowSizeX/2-68; break;
		case 3: posX = Game.windowSizeX/2+4; break;
		case 4: posX = Game.windowSizeX/2+76; break;
		case 5: posX = Game.windowSizeX/2+148; break;
		}
	}
	@Override public void logic(){}
}
