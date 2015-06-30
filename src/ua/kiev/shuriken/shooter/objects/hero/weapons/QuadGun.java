package ua.kiev.shuriken.shooter.objects.hero.weapons;

import java.util.Random;


import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.objects.ammo.Bullet;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;
import ua.kiev.shuriken.shooter.objects.hero.artifacts.GoldenBolt;

public class QuadGun extends Weapon{
	Random rand = new Random();
	
	public QuadGun() {
		super(450, 300, 100, 100, 175, 100, "weapon_quad_gun", 6, "Quadruple Gun");
		this.cost = 3000;
		Item[] recipe = {new BasicGun(), new BasicGun(), new BasicGun(), new BasicGun(), new GoldenBolt()};
		itemsForCraft = recipe;
	}

	@Override
	public void shoot(Hero hero) {
		currentAmmo -= 2;
		this.availableForShooting = false;
		currentFrame = 0;
		float rot = rand.nextBoolean() ? (rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+90)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+90)/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot, damage));
		rot = rand.nextBoolean() ? (rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+180)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+180)/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot+90, damage));
		rot = rand.nextBoolean() ? (rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation()+270)/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation()+270)/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot+180, damage));
		rot = rand.nextBoolean() ? (rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25) : -((rand.nextInt(1000)/hero.getAccuracy())/(this.accuracy/25));
		Layers.addObject(new Bullet(
				(hero.posX+hero.centerX+12*(float)Math.sin((double)((hero.getRotation())/180*Math.PI)))+hero.getSpeedX(),
				(hero.posY+hero.centerY-12*(float)Math.cos((double)((hero.getRotation())/180*Math.PI)))+hero.getSpeedY(),
				hero.getRotation()+rot+270, damage));
	}
	
	@Override
	public void graphicLogic(Hero hero){
		if(currentFrame != finalFrame){
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
		return new QuadGun();
	}
}
