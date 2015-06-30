package ua.kiev.shuriken.shooter.objects.hero.weapons;

import java.util.Random;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.ammo.Bullet;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;
import ua.kiev.shuriken.shooter.objects.hero.artifacts.Bolt;

public class DoubleGun extends Weapon{
	Random rand = new Random();
	
	public DoubleGun() {
		super(450, 300, 100, 100, 150, 50, "weapon_double_gun", 6, "Double Gun");
		this.cost = 2000;
		Item[] recipe = {new BasicGun(), new BasicGun(), new Bolt(), new Bolt()};
		itemsForCraft = recipe;
	}

	@Override
	public void shoot(Hero hero) {
		currentAmmo -= 2;
		this.availableForShooting = false;
		currentFrame = 0;
		float rot = rand.nextBoolean() ? (rand.nextInt(100)/hero.getAccuracy())/(this.accuracy/1000) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+105)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+105)/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot, damage));
		rot = rand.nextBoolean() ? (rand.nextInt(100)/hero.getAccuracy())/(this.accuracy/1000) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+75)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+75)/180*Math.PI)))+hero.getSpeedY(),
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
		return new DoubleGun();
	}
}

