package ua.kiev.shuriken.shooter.objects.hero.weapons;

import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class NoneGun extends Weapon{
	
	public NoneGun() {
		super(0, 0, 0, 0, 0, 0, "weapon_none", 1, "None");
		this.currentFrame = 1;
	}

	@Override
	public void shoot(Hero hero){}

	@Override
	public void graphicLogic(Hero hero){}

	@Override
	public int wayToGet() {
		return 0;
	}

	@Override
	public Item getThis() {return null;}

}
