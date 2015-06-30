package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class ArtifactShopItemMenu extends BasicMenu{
	Artifact artifact;
	
	MenuLinearText artifactName;
	
	MenuMultilineText description;
	
	TextButton buy;
	Text price;
	
	public ArtifactShopItemMenu(String layer, Artifact artifact) {
		super(layer);
		this.artifact = artifact;
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
		buildThis();
	}
	
	public void buildThis(){
		buy = new TextButton(layer, 16, 304, 80, 32, 0.6f, "BUY", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				GamingProcess.money -= artifact.cost;
				boolean puted= false;
				for(int i = 0; i<6 && !puted; i++){
					if(GamingProcess.hero.inventory[i] == null){
						GamingProcess.hero.inventory[i] = artifact.getThis();
						puted = true;
					}
				}
				Menu.refreshAll();
			}
		};
		buy.active = false;
		for(int i = 0; i<6; i++){
			if(GamingProcess.hero.inventory[i] == null){
				buy.active = true;
			}
		}
		Layers.addObject(buy);
		
		price = new Text(layer, "$"+artifact.cost,
				Menu.POSX+105, Menu.posY+307, 0.4f, true, Text.LEFT, Text.TOP);
		
		if(GamingProcess.money < artifact.cost){
			buy.active = false;
		}
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
	
	@Override public void refresh(){
		Layers.clearLayer(layer);
		build();
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
		buildThis();
	}

	@Override
	public void logic() {
		price.changePosition(Menu.POSX+105, Menu.posY+307);
		price.changeAlpha(Menu.alpha);
		artifactName.changePosition();
		artifactName.changeAlpha();
		description.changePosition();
		description.changeAlpha();
	}
}
