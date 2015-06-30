package ua.kiev.shuriken.shooter.objects.hero.artifacts;

import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class Wheel extends Artifact{
	
	public Wheel(){
		super("artifact_wheel", "Wheel");
		Item[] recipe = {new Battery(), new Battery(), new Bolt(), new Gear()};
		itemsForCraft = recipe;
		this.cost = 650;
	}

	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(0, 0, 0, 10, 0, 0, 0);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new Wheel();
	}
}
