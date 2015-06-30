package ua.kiev.shuriken.shooter.objects.hero.artifacts;


import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class GoldenBolt extends Artifact{
	
	public GoldenBolt(){
		super("artifact_golden_bolt", "Golden Bolt");
		this.cost = 1000;
	}

	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(100, 0, 100, 0, 0, 0, 10);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new GoldenBolt();
	}
}
