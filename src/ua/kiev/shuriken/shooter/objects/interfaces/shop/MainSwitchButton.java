package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import org.lwjgl.input.Mouse;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.InvicibleButton;
import ua.kiev.shuriken.game.text.Text;

public class MainSwitchButton extends InvicibleButton{

	float iconPosX, iconPosY, textSize;
	Text text;
	String buttonText;
	boolean textIsCreated = false;
	
	public MainSwitchButton(String layer, float posX, float posY, float sizeX,
			float sizeY, float textSize, String text, int horisontalAlign,
			int verticalAlign) {
		super(layer, posX, posY, sizeX, sizeY, textSize, text, horisontalAlign,
				verticalAlign);
		this.alphaPicture = Menu.alpha;
		iconPosX = posX;
		iconPosY = posY;
		buttonText = text;
		this.textSize = textSize;
		this.active = true;
	}
	
	@Override
	public void logic(){
		if(!textIsCreated){
			textIsCreated = true;
			this.text = new Text(layer, buttonText, posX+Menu.POSX+sizeX/2, posY+Menu.posY+sizeY/2,
					0.6f, true, Text.CENTER, Text.CENTER);
		}
		posY = iconPosY+Menu.posY;
		posX = iconPosX+Menu.POSX;
		text.changePosition(iconPosX+Menu.POSX+sizeX/2, iconPosY+Menu.posY+sizeY/2);
		text.changeAlpha(Menu.alpha);
		this.alphaPicture = Menu.alpha;
		if(active){
			if(Mouse.getX() > posX && Mouse.getX() < posX+sizeX &&
					(Game.windowSizeY-Mouse.getY()) > posY &&
					(Game.windowSizeY-Mouse.getY()) < posY+sizeY ){
				if(Mouse.isButtonDown(0) && pressedCooldown == 0){
					action();
					pressedCooldown = 15;
				}
			}
		}
	}

}
