package ua.kiev.shuriken.shooter.objects.interfaces.wavesigns;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.GameObject;

public class WaveSign extends GameObject{
	public static float displacement;
	public int position;
	
	@Override
	public void logic(){
		this.posY = Game.windowSizeY/2-128+(position-1)*64+displacement;
	}
	
	public WaveSign(int position, String texture){
		super("wave_signs");
		this.position = position;
		this.sizeX = 128;
		this.sizeY = 64;
		this.posX = Game.windowSizeX-144;
		this.posY = Game.windowSizeY/2-128+position*64;
		this.setTexture(texture);
		this.bindsToCamera = true;
	}
}
