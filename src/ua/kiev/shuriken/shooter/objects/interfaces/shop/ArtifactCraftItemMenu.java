package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Crafter;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class ArtifactCraftItemMenu extends BasicMenu{
	Artifact artifact;
	
	MenuLinearText artifactName;
	
	MenuMultilineText description;
	
	TextButton craft;
	public ArtifactCraftItemMenu(String layer, Artifact artifact) {
		super(layer);
		this.artifact = artifact;
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
		drawCraftMenu();
	}
	
	public void drawCraftMenu(){
		craft = new TextButton(layer, 16, 304, 80, 32, 0.45f, "CRAFT", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Crafter.craft(artifact.getThis());
				Menu.refreshAll();
			}
		};
		if(!Crafter.canBeCrafted(artifact, GamingProcess.hero)){
			craft.active = false;
		}
		
		Layers.addObject(craft);
		boolean[] info = Crafter.getInto(artifact, GamingProcess.hero);
		for(int i = 0; i < info.length; i++){
			final Item craftItem = artifact.itemsForCraft[i];
			Layers.addObject(new IconButton(layer, 112+i*48, 304, 32, craftItem){
				@Override
				public void action(){
					switch(craftItem.wayToGet()){
					case Item.BUY:
						if(craftItem.getType() == Item.WEAPON){
							Menu.createWindow(new WeaponShopItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Weapon) craftItem));
						} else {
							Menu.createWindow(new ArtifactShopItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Artifact) craftItem));
						}
						Menu.refreshAll();
						break;
					case Item.CRAFT:
						if(craftItem.getType() == Item.WEAPON){
							Menu.createWindow(new WeaponCraftItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Weapon) craftItem));
						} else {
							Menu.createWindow(new ArtifactCraftItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Artifact) craftItem));
						}
						Menu.refreshAll();
						break;
					case Item.DROP:
						if(craftItem.getType() == Item.WEAPON){
							Menu.createWindow(new WeaponInfoItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Weapon) craftItem));
						} else {
							Menu.createWindow(new ArtifactInfoItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Artifact) craftItem));
						}
						Menu.refreshAll();
						break;
					}
				}
			});
			if(info[i]){
				Layers.addObject(new GreenLight(layer, 112+i*48, 304, 32));
			} else {
				Layers.addObject(new RedLight(layer, 112+i*48, 304, 32));
			}
		}
	}
	
	@Override public void refresh(){
		Layers.clearLayer(layer);
		build();
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
		drawCraftMenu();
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

	@Override
	public void logic() {
		artifactName.changePosition();
		artifactName.changeAlpha();
		description.changePosition();
		description.changeAlpha();
	}

}
