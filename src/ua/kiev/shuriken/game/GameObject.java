package ua.kiev.shuriken.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.input.Mouse;

public abstract class GameObject {
	public int audioSource = -1;
	protected int currentSound;
	
	protected void setSound(String sound){
		currentSound = Sound.getSound(sound);
		if(audioSource == -1){
			audioSource = AL10.alGenSources();
			AL10.alSourcei(audioSource, AL10.AL_BUFFER, currentSound);
		} else {
			if(audioSource == AL10.AL_PLAYING){
				AL10.alSourceStop(audioSource);
			}
			AL10.alDeleteSources(audioSource);
			audioSource = AL10.alGenSources();
			AL10.alSourcei(audioSource, AL10.AL_BUFFER, currentSound);
		}
	}
	
	protected void playSound(){
		AL10.alSourcePlay(audioSource);
	}
	
	protected int currentFrame;
	protected boolean visible = true;
	protected boolean textureIsAtlas;
	protected Atlas currentAtlas;
	public float alphaPicture = 100f;
	public boolean isDeleting = false;
	
	public GameObject(String layer){
		this.layer = layer;
	}
	
	public GameObject(String layer, float alpha){
		this.layer = layer;
		alphaPicture = alpha;
	}
	
	public boolean bindsToCamera = false;
	public String layer;
	public int layerPosition;
	static Map<String, Texture> textures = new HashMap<String, Texture>();
	protected float rotation = 0f;
	protected Texture currentTexture;
	public float sizeX = 0;
	public float sizeY = 0;
	public float posX = 0;
	public float posY = 0;
	public float centerX = posX+sizeX/2;
	public float centerY = posY+sizeY/2;
	static protected Map<String, Atlas> atlases = new HashMap<String, Atlas>();
	
	public float getPosX(){
		return posX;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public float getSizeX(){
		return sizeX;
	}
	
	public float getSizeY(){
		return sizeY;
	}
	
	public float getCenterX(){
		return posX+sizeX/2;
	}
	
	public float getCenterY(){
		return posY+sizeY/2;
	}
	
	abstract public void logic();
	
	public void draw(){
		if(visible){
			GL11.glColor4f(1f, 1f, 1f, alphaPicture/100);
			if(this.textureIsAtlas){
				currentAtlas.Atlas.bind();
			} else {
				currentTexture.bind();
			}
			GL11.glPushMatrix();
			if(this.bindsToCamera){
				
				GL11.glTranslatef(posX+centerX, posY+centerY, 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef(-posX-centerX, -posY-centerY, 0);
			} else {
				GL11.glTranslatef((posX+centerX-VCAM.posX)/VCAM.cofficientX,
						(posY+centerY-VCAM.posY)/VCAM.cofficientY, 0);
				GL11.glRotatef(rotation, 0f, 0f, 1f);
				GL11.glTranslatef((-posX-centerX+VCAM.posX)/VCAM.cofficientX,
						-(posY+centerY-VCAM.posY)/VCAM.cofficientY, 0);
			}
			GL11.glBegin(GL11.GL_QUADS);
			if(!textureIsAtlas){
				if(bindsToCamera){
					GL11.glTexCoord2f(0, 0);
					GL11.glVertex2d(posX, posY);
					GL11.glTexCoord2f(currentTexture.getWidth(), 0);
					GL11.glVertex2d(posX+sizeX, posY);
					GL11.glTexCoord2f(currentTexture.getWidth(), currentTexture.getHeight());
					GL11.glVertex2d(posX+sizeX, posY+sizeY);
					GL11.glTexCoord2f(0, currentTexture.getHeight());
					GL11.glVertex2d(posX, posY+sizeY);
				} else {
					GL11.glTexCoord2f(0, 0);
					GL11.glVertex2d((posX-VCAM.posX)/VCAM.cofficientX,
						(posY-VCAM.posY)/VCAM.cofficientY); 
					GL11.glTexCoord2f(currentTexture.getWidth(), 0);
					GL11.glVertex2d((posX+sizeX-VCAM.posX)/VCAM.cofficientX,
							(posY-VCAM.posY)/VCAM.cofficientY);
					GL11.glTexCoord2f(currentTexture.getWidth(), currentTexture.getHeight());
					GL11.glVertex2d((posX+sizeX-VCAM.posX)/VCAM.cofficientX,
							(posY+sizeY-VCAM.posY)/VCAM.cofficientY);
					GL11.glTexCoord2f(0, currentTexture.getHeight());
					GL11.glVertex2d((posX-VCAM.posX)/VCAM.cofficientX,
							(posY+sizeY-VCAM.posY)/VCAM.cofficientY);
				}
			} else {
			int x = (int)((currentFrame-1)%currentAtlas.sizeX);
			int y = (int)((currentFrame-1)/currentAtlas.sizeX);
			int x1 = (int)((currentFrame-1)%currentAtlas.sizeX+1);
			int y1 = (int)((currentFrame-1)/currentAtlas.sizeX+1);
				
				if(bindsToCamera){ // 
					GL11.glTexCoord2f((x/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d(posX, posY);
					GL11.glTexCoord2f((x1/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d(posX+sizeX, posY);
					GL11.glTexCoord2f((x1/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y1/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d(posX+sizeX, posY+sizeY);
					GL11.glTexCoord2f((x/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y1/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d(posX, posY+sizeY);
				} else {
					GL11.glTexCoord2f((x/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d((posX-VCAM.posX)/VCAM.cofficientX,
							(posY-VCAM.posY)/VCAM.cofficientY);
					GL11.glTexCoord2f((x1/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d((posX+sizeX-VCAM.posX)/VCAM.cofficientX,
							(posY-VCAM.posY)/VCAM.cofficientY);
					GL11.glTexCoord2f((x1/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y1/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d((posX+sizeX-VCAM.posX)/VCAM.cofficientX,
							(posY+sizeY-VCAM.posY)/VCAM.cofficientY);
					GL11.glTexCoord2f((x/(float)currentAtlas.sizeX)*currentAtlas.Atlas.getWidth(),
							(y1/(float)currentAtlas.sizeY)*currentAtlas.Atlas.getHeight());
					GL11.glVertex2d((posX-VCAM.posX)/VCAM.cofficientX,
							(posY+sizeY-VCAM.posY)/VCAM.cofficientY);
				}
			}
		GL11.glEnd();
		GL11.glPopMatrix();
		}
	}
	
	public void destroy(){
		this.isDeleting = true;
		if(audioSource != -1){
			AL10.alDeleteSources(audioSource);
		}
	}
	
	static public void addTexture(String name, String path){
		try {
			textures.put(name,
					TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream(path)));;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void setTexture(String name){
		this.textureIsAtlas = false;
		currentTexture = textures.get(name);
	}
	
	static public void addAtlas(String name, String path,  int sizeX, int sizeY){
		atlases.put(name, new Atlas(path, sizeX, sizeY));
	}
	
	protected void setAtlas(String name){
		this.textureIsAtlas = true;
		currentAtlas = atlases.get(name);
	}
	
	protected void rotateTo(GameObject object){
		rotation = 180+(float)(Math.atan2(this.getCenterY()-object.getCenterY(), this.getCenterX()-object.getCenterX())*180/Math.PI);
	}
	
	protected void rotateToMouse(){
		rotation = 180+(float)(Math.atan2((posY+centerY-VCAM.posY)/VCAM.cofficientY-(VCAM.DEFAULT_SIZE_Y-Mouse.getY()),(posX+centerX-VCAM.posX)/VCAM.cofficientX-Mouse.getX())*180/Math.PI);
	}
}
