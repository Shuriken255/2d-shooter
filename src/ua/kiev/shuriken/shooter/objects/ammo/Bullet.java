package ua.kiev.shuriken.shooter.objects.ammo;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.SolidGameObject;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.effects.Blood;
import ua.kiev.shuriken.shooter.objects.enemies.Enemy;

public class Bullet extends SolidGameObject{
	float speedX;
	float speedY;
	float damage;
	public Bullet(float x, float y, float rotation, float damage){
		super("bullets", "bullets");
		this.layer = "bullets";
		this.posX = x-24;
		this.posY = y-16;
		this.sizeX = 32;
		this.sizeY = 32;
		this.centerX = 24;
		this.centerY = 16;
		this.rotation = rotation;
		this.setTexture("ammo_bullet");
		this.speedX = 10*(float)Math.sin((double)((this.rotation+90)/180*Math.PI));
		this.speedY = -10*(float)Math.cos((double)((this.rotation+90)/180*Math.PI));
		this.hitBoxBottom = 17;
		this.hitBoxTop = 15;
		this.hitBoxRight = 25;
		this.hitBoxLeft = 23;
		this.damage = damage;
	}
	
	@Override
	public void logic() {
		SolidGameObject object;
		object = this.checkCollision("enemies", -speedX/2, -speedY/2);
		if(object == null){
			object = this.checkCollision("enemies", speedX, speedY);
		}
		if(object != null){
			Layers.addObject(new Blood(posX+centerX, posY+centerY));
			((Enemy) object).getDamage(damage);
			this.destroy();
		}
		posX += speedX;
		posY += speedY;
		if(this.posX < -32 || this.posX > GamingProcess.sizeX+32 ||
			this.posY < -32 || this.posY > GamingProcess.sizeY+32 ){
			this.destroy();
		}
	}
	
	@Override
	public void touch(String target) {
		
	}
}
