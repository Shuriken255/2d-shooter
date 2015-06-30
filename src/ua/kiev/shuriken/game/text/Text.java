package ua.kiev.shuriken.game.text;

import java.io.UnsupportedEncodingException;

import ua.kiev.shuriken.game.Layers;

public class Text{
	public static final int LEFT = 0, CENTER = 1, RIGHT = 2, TOP = 0, BOTTOM = 2;
	
	// Text variables
	protected String layer;
	protected Letter[] litters;
	protected float positionX;
	protected float positionY;
	protected int horisontalAlign;
	protected int verticalAlign;
	protected float size;
	protected boolean bindsToCamera;
	
	
	static float charPosX = 8f;
	static float charPosY = 4f;
	static float charSizeX = 32f;
	static float charSizeY = 58f;
	
	public static void setFont(String font){
		Letter.currentFont = Letter.fonts.get(font);
	}
	
	public Text(String layer, String text, float posX, float posY, float size,
			boolean bindsToCamera, int horisontalAlign, int verticalAlign){
		this.layer = layer;
		positionX = posX;
		positionY = posY;
		this.horisontalAlign = horisontalAlign;
		this.verticalAlign = verticalAlign;
		this.size = size;
		this.bindsToCamera = bindsToCamera;
		
		byte[] sentence = null;
		try {
			sentence = text.getBytes("ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		litters = new Letter[sentence.length];
		
		switch(horisontalAlign){
		case Text.LEFT: break;
		case Text.CENTER: posX -= ((text.length()+0.55f)*Text.charSizeX*size)/2; break;
		case Text.RIGHT: posX -= (text.length()+0.55f)*Text.charSizeX*size; break;
		}
		switch(verticalAlign){
		case Text.TOP: break;
		case Text.CENTER: posY -= Text.charSizeY*size/2; break;
		case Text.BOTTOM: posY -= Text.charSizeY*size; break;
		}
		
		for(int i = 0; i<sentence.length; i++){
			litters[i] = new Letter(sentence[i],
					layer,
					posX+(i*Text.charSizeX-Text.charPosX)*size,
					posY-Text.charPosX*size,
					64*size,
					64*size,
					bindsToCamera);
			Layers.addObject(litters[i]);
		}
	}
	
	public void changeText(String text){
		for(int i = 0; i < litters.length; i++){
			litters[i].destroy();
		}
		
		byte[] sentence = null;
		try {
			sentence = text.getBytes("ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("unused")
		float posX = positionX, posY = positionY;
		
		switch(horisontalAlign){
		case Text.LEFT: break;
		case Text.CENTER: posX -= ((text.length()+0.55f)*Text.charSizeX*size)/2; break;
		case Text.RIGHT: posX -= (text.length()+0.55f)*Text.charSizeX*size; break;
		}
		switch(verticalAlign){
		case Text.TOP: break;
		case Text.CENTER: posY -= Text.charSizeY*size/2; break;
		case Text.BOTTOM: posY -= Text.charSizeY*size; break;
		}
		
		litters = new Letter[sentence.length];
		for(int i = 0; i<sentence.length; i++){
			litters[i] = new Letter(sentence[i],
					layer,
					positionX+(i*Text.charSizeX-Text.charPosX)*size,
					positionY-Text.charPosX*size,
					64*size,
					64*size,
					bindsToCamera);
			Layers.addObject(litters[i]);
		}
	}
	
	public void changeAlpha(float alpha){
		for(Letter l:litters){
			l.alphaPicture = alpha;
		}
	}
	
	public void changePosition(float posX, float posY){
		switch(horisontalAlign){
		case Text.LEFT: break;
		case Text.CENTER: posX -= ((litters.length+0.55f)*Text.charSizeX*size)/2; break;
		case Text.RIGHT: posX -= (litters.length+0.55f)*Text.charSizeX*size; break;
		}
		switch(verticalAlign){
		case Text.TOP: break;
		case Text.CENTER: posY -= Text.charSizeY*size/2; break;
		case Text.BOTTOM: posY -= Text.charSizeY*size; break;
		}
		
		positionX = posX;
		positionY = posY;
		for(int i = 0; i<litters.length; i++){
			positionX = posX;
			positionY = posY;
			litters[i].posX = posX+(i*Text.charSizeX-Text.charPosX)*size;
			litters[i].posY = posY-Text.charPosY*size;
		}
	}
}
