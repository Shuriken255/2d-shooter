package ua.kiev.shuriken.game;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Atlas{
	public Texture Atlas;
	public int sizeX, sizeY, finalFrame;
	public Atlas(String path, int sizeX, int sizeY){
		try {
			Atlas = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't load Atlas " + path);
		}
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
}
