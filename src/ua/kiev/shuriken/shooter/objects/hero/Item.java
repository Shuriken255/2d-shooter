package ua.kiev.shuriken.shooter.objects.hero;

import ua.kiev.shuriken.game.GameObject;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.interfaces.shop.Menu;
import java.util.Map;
import java.util.HashMap;

public abstract class Item {
	public static Map<String, String> descriptions = new HashMap<String, String>();
	
	public Item[] itemsForCraft = {};
	public long cost = 0;
	
	public String name;
	public String textName;
	protected String description;
	public static final int WEAPON = 0, ARTIFACT = 1;
	public static final int DROP = 0, BUY = 1, CRAFT = 2;
	
	public class Icon extends GameObject{
		boolean bindsToMenu = false;
		float iconPosX, iconPosY;
		
		public Icon(String name, String layer, float posX, float posY, float size){
			super(layer);
			this.bindsToCamera = true;
			this.setTexture(name+"_icon");
			this.posX = posX;
			this.posY = posY;
			this.sizeX = size;
			this.sizeY = size;
			iconPosX = posX;
			iconPosY = posY;
		}
		
		public Icon(String name, String layer, float posX, float posY, float size, boolean holdsToMenu){
			super(layer);
			this.bindsToCamera = true;
			this.setTexture(name+"_icon");
			this.sizeX = size;
			this.sizeY = size;
			iconPosX = posX;
			iconPosY = posY;
			this.posX = iconPosX + Menu.POSX;
			this.posY = iconPosY + Menu.posY;
			bindsToMenu= true;
		}
		@Override public void logic(){
			if(bindsToMenu){
				posX = iconPosX + Menu.POSX;
				posY = iconPosY + Menu.posY;
				alphaPicture = Menu.alpha;
			}
		}
	}
	
	public void drawIcon(String layer, float posX, float posY, float size, boolean holdsToMenu){
		Layers.addObject(new Icon(name, layer, posX, posY, size, holdsToMenu));
	}
	
	public void drawIcon(String layer, float posX, float posY, float size){
		Layers.addObject(new Icon(name, layer, posX, posY, size));
	}
	
	public Item(String name, String textName){
		this.name = name;
		this.textName = textName;
	}
	
	public Item(String name, String textName, String[] recipe){
		this.name = name;
		this.textName = textName;
	}
	
	abstract public int getType();
	abstract public int wayToGet();
	abstract public Item getThis();
}
