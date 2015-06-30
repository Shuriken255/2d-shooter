package ua.kiev.shuriken.game.text;

import java.util.Arrays;


public class MultilineText{
	
	Text[] textLines;
	float delay;
	public MultilineText(String layer, String text, float posX, float posY, float size,
			boolean bindsToCamera, int horisontalAlign, float delay, int maxCharInLine){
		this.delay = delay;
		String[] lines = new String[1];
		String[] words = text.split(" ");
		lines[0] = "";
		int currentLine = 0;
		for(int word = 0; word<words.length; word++){
			if(word == 0){
				lines[0] = words[word];
			} else {
				if((lines[currentLine]+words[word]).length()+1 < maxCharInLine){
					lines[currentLine] = (lines[currentLine]+' '+words[word]);
				} else {
					lines = Arrays.copyOf(lines, lines.length+1);
					currentLine++;
					lines[currentLine] = words[word];
				}
			}
		}
		
		textLines = new Text[lines.length];
		for(int i = 0; i < lines.length; i++){
			textLines[i] = new Text(layer, lines[i], posX, posY+delay*i, size, bindsToCamera,
					horisontalAlign, Text.TOP);
		}
	}
	
	public void changeAlpha(float alpha){
		for(Text t:textLines){
			t.changeAlpha(alpha);
		}
	}
	
	public void changePosition(float posX, float posY){
		for(int i = 0; i<textLines.length; i++){
			textLines[i].changePosition(posX, (posY+delay*i));
		}
	}
}
