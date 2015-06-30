package ua.kiev.shuriken.shooter.objects.hero.artifacts;


import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class Gear extends Artifact{
	
	public Gear(){
		super("artifact_gear", "Gear");
		this.cost = 150;
	}

	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(0, 0, 0, 0, 10, 10, 0);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new Gear();
	}
}
