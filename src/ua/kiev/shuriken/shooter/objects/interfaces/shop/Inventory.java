package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;
import ua.kiev.shuriken.shooter.objects.interfaces.inventory.InventoryBar;

public class Inventory extends BasicMenu{
	InventoryBar inventory;

	public Inventory(String layer) {
		super(layer);
		buildThis();
	}
	
	public void buildThis(){
		inventory = new InventoryBar(layer);
		Layers.addObject(inventory);
		for(int i = 0; i < 6;i++){
			final int ii = i;
			Layers.addObject(new InventoryBarButton(layer, Game.windowSizeX/2-212+i*72,
					Game.windowSizeY-72, 64, GamingProcess.hero.inventory[i], false){
				@Override
				public void action(){
					if(this.item.getType() == Item.ARTIFACT){
						Menu.createWindow(new ArtifactSellItemMenu("menu_layer_" + 
								Integer.toString((Menu.currentLayer+1)),
								(Artifact) GamingProcess.hero.inventory[ii], ii));
					} else {
						Menu.createWindow(new WeaponSellItemMenu("menu_layer_" + 
								Integer.toString((Menu.currentLayer+1)),
								(Weapon) GamingProcess.hero.inventory[ii], ii));
					}
				}
			});
		}
	}

	@Override
	public void build() {
		
	}

	@Override
	public void logic() {
		if(Menu.currentState == Menu.CLOSED){
			
		}
	}

	@Override
	public void refresh() {
		Layers.clearLayer(layer);
		build();
		buildThis();
		GamingProcess.hero.switchItemForward();
		GamingProcess.hero.switchItemBack();
		GamingProcess.hero.refreshStats();
	}

}
