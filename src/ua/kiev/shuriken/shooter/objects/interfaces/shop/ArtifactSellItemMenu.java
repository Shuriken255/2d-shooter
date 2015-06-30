package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class ArtifactSellItemMenu extends BasicMenu{
Artifact artifact;
	
	MenuLinearText artifactName;
	
	MenuMultilineText description;
	
	int number;
	TextButton sell;
	MenuLinearText price;
	BasicMenu thisMenu = this;
	
	public ArtifactSellItemMenu(String layer, Artifact artifact, int number) {
		super(layer);
		this.artifact = ((Artifact) GamingProcess.hero.inventory[number]);
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
		this.number = number;
		buildThis();
	}
	
	public void buildThis(){
		sell = new TextButton(layer, 16, 304, 80, 32, 0.6f, "SELL", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				GamingProcess.money += artifact.cost/2;
				GamingProcess.hero.inventory[number] = null;
				sell.active = false;
				Menu.refreshAll(thisMenu);
			}
		};
		Layers.addObject(sell);
		
		price = new MenuLinearText(layer, "$"+Long.toString(artifact.cost/2),
				105, 307, 0.4f, Text.LEFT);
	}
	
	@Override
	public void build() {
		Layers.addObject(new Frame(layer, 6));
		Layers.addObject(new TextButton(layer, 320, 304, 176, 32, 0.4f,
				"BACK", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.deleteLastWindow();
			}
		});
	}
	
	@Override public void refresh(){}

	@Override
	public void logic() {
		artifactName.changePosition();
		artifactName.changeAlpha();
		description.changePosition();
		description.changeAlpha();
		price.changeAlpha();
		price.changePosition();
	}
}
