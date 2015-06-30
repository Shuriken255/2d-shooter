package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.text.MultilineText;
import ua.kiev.shuriken.game.text.Text;

public class MenuMultilineText {
	MultilineText text;
	float textPosX, textPosY;
	public MenuMultilineText(String layer, String text,
			float posX, float posY, float size, int maxChar){
		textPosX = posX;
		textPosY = posY;
		this.text = new MultilineText(layer, text,
				textPosX+Menu.POSX, textPosY+Menu.posY,
				size, true, Text.LEFT, 19, maxChar);
		this.text.changeAlpha(Menu.alpha);
	}
	
	public void changePosition(){
		text.changePosition(Menu.POSX+textPosX, Menu.posY+textPosY);
	}
	
	public void changeAlpha(){
		text.changeAlpha(Menu.alpha);
	}
}
