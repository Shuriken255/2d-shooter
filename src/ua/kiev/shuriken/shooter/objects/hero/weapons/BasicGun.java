package ua.kiev.shuriken.shooter.objects.hero.weapons;

import java.util.Random;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.ammo.Bullet;
import ua.kiev.shuriken.shooter.objects.hero.*;

public class BasicGun extends Weapon{
	Random rand = new Random();
	
	public BasicGun() {
		//mobility fireSpeed accuracy damage reloadSpeed ammo String name, finalFrame, String textName
		super(500, 300, 50, 100, 100, 25, "weapon_gun", 8, "Basic Gun");
		this.cost = 500;
	}

	@Override
	public void shoot(Hero hero) {
		currentAmmo--;
		this.availableForShooting = false;
		currentFrame = 0;
		float rot = rand.nextBoolean() ? (rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+90)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+90)/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot, damage));
	}
	
	@Override
	public void graphicLogic(Hero hero){
		if(this.currentFrame != this.finalFrame){
			currentFrame++;
		}
		hero.setFrame(currentFrame);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new BasicGun();
	}
}
