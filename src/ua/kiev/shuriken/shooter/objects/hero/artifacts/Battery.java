package ua.kiev.shuriken.shooter.objects.hero.artifacts;


import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Hero;
import ua.kiev.shuriken.shooter.objects.hero.Item;

public class Battery extends Artifact{
	
	public Battery(){
		super("artifact_battery", "Battery");
		this.cost = 200;
	}
	
	@Override
	public void changeStats(Hero hero) {
		// maxHP regenHP defence speed reloadSpeed fireSpeed accuracy
		hero.addStats(0, 1, 0, 0, 0, 0, 0);
	}
	
	@Override
	public int wayToGet(){
		return Item.BUY;
	}
	
	@Override
	public Item getThis(){
		return new Battery();
	}
}
