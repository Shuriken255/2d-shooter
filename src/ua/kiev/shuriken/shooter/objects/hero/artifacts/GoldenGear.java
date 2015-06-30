package ua.kiev.shuriken.shooter.objects.hero.artifacts;

import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class GoldenGear extends Artifact{
	
	public GoldenGear(){
		super("artifact_golden_gear", "Golden Gear");
		this.cost = 1200;
	}
	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(0, 0, 0, 0, 50, 50, 0);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new GoldenGear();
	}
}
