package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import org.lwjgl.input.Mouse;
import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.game.Button;

public class IconButton extends Button{
	boolean iconDrawn = false;
	float iconPosX, iconPosY;
	Item item;
	public IconButton(String layer, float posX, float posY,
			float size, Item item) {
		super(layer, posX+Menu.POSX, posY+Menu.posY, size, size);
		if(item != null){
			active = true;
		} else {
			active = false;
		}
		iconPosX = posX;
		iconPosY = posY;
		this.posY = iconPosY+Menu.posY;
		this.posX = iconPosX+Menu.POSX;
		Layers.addObject(this);
		setAtlas("item_button_icon");
		currentFrame = 1;
		this.item = item;
		this.bindsToCamera = true;
	}
	
	public IconButton(String layer, float posX, float posY,
			float size, Item item, boolean yes) {
		super(layer, posX+Menu.POSX, posY+Menu.posY, size, size);
		if(item != null){
			active = true;
		} else {
			active = false;
		}
		Layers.addObject(this);
		setAtlas("item_button_icon");
		currentFrame = 1;
		this.item = item;
		this.bindsToCamera = true;
	}
	
	@Override
	protected void action(){
		
	}
	
	@Override
	public void logic(){
		if(item != null && !iconDrawn){
			item.drawIcon(layer, iconPosX, iconPosY, sizeX, true);
			iconDrawn = true;
		}
		posY = iconPosY+Menu.posY;
		posX = iconPosX+Menu.POSX;
		alphaPicture = Menu.alpha;
		if(active){
			if(Mouse.getX() > posX && Mouse.getX() < posX+sizeX &&
					(Game.windowSizeY-Mouse.getY()) > posY &&
					(Game.windowSizeY-Mouse.getY()) < posY+sizeY ){
				if(Mouse.isButtonDown(0) && pressedCooldown == 0){
					currentFrame = 3;
					action();
					pressedCooldown = 15;
				} else {
					currentFrame = 2;
				}
			} else {
				currentFrame = 1;
			}
		} else {
			currentFrame = 4;
		}
	}
	
}
