package ua.kiev.shuriken.shooter.objects.hero.artifacts;


import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class Bolt extends Artifact{
	
	public Bolt(){
		super("artifact_bolt", "Bolt");
		this.cost = 100;
	}

	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(10, 0, 10, 0, 0, 0, 0);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new Bolt();
	}
}
