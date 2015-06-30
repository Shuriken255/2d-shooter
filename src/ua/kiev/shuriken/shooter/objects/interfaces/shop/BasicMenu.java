package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;

public abstract class BasicMenu {
	String layer;
	abstract public void build();
	abstract public void logic();
	abstract public void refresh();
	
	public BasicMenu(String layer){
		this.layer = layer;
		build();
	}
	
	public void setActive(){
		Layers.setActive(layer);
	}
	
	public void setInactive(){
		Layers.setInactive(layer);
	}
}
