package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.text.Text;

public class MenuLinearText {
	Text text;
	float textPosX, textPosY;
	public MenuLinearText(String layer, String text,
			float posX, float posY, float size){
		textPosX = posX;
		textPosY = posY;
		this.text = new Text(layer, text,
				textPosX+Menu.POSX, textPosY+Menu.posY,
				size, true, Text.LEFT, Text.TOP);
		this.text.changeAlpha(Menu.alpha);
	}
	
	public MenuLinearText(String layer, String text,
			float posX, float posY, float size, int align){
		textPosX = posX;
		textPosY = posY;
		this.text = new Text(layer, text,
				textPosX+Menu.POSX, textPosY+Menu.posY,
				size, true, align, Text.TOP);
		this.text.changeAlpha(Menu.alpha);
	}
	
	public void changePosition(){
		text.changePosition(Menu.POSX+textPosX, Menu.posY+textPosY);
	}
	
	public void changeAlpha(){
		text.changeAlpha(Menu.alpha);
	}
}
