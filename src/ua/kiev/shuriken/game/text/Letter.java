package ua.kiev.shuriken.game.text;

import ua.kiev.shuriken.game.Atlas;
import ua.kiev.shuriken.game.GameObject;

import java.util.HashMap;
import java.util.Map;

public class Letter extends GameObject{
	static Atlas currentFont = null;
	static{
		atlases.put("font_default", new Atlas("res/fonts/white_black.png", 16, 16));
	}
	public static Map<String, Atlas> fonts = new HashMap<String, Atlas>();
	public Letter(byte litter, String layer, float posX, float posY, float sizeX, float sizeY, boolean bindsToCamera) {
		super(layer);
		this.setAtlas("font_default");
		this.currentFrame = litter+1;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.bindsToCamera = bindsToCamera;
	}
	
	public Letter(byte litter, String layer, float posX, float posY, float sizeX, float sizeY, boolean bindsToCamera, float alpha) {
		super(layer, alpha);
		this.setAtlas("font_default");
		this.currentFrame = litter+1;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.bindsToCamera = bindsToCamera;
	}
	
	@Override
	public void logic(){
		
	}
}
