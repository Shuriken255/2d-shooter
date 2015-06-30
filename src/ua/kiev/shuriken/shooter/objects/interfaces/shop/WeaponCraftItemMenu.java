package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Crafter;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class WeaponCraftItemMenu extends BasicMenu{
Weapon weapon;
	
	MenuLinearText weaponName, damage, accuracy,
	fireSpeed, reloadSpeed, mobility, ammo;
	
	MenuMultilineText description;
	
	ColoredRect damageRect, accuracyRect, fireSpeedRect,
	ammoRect, reloadRect, mobilityRect;
	
	TextButton craft;
	
	public WeaponCraftItemMenu(String layer, Weapon weapon) {
		super(layer);
		this.weapon = weapon;
		weaponName = new MenuLinearText(layer, weapon.textName, 154, 16.20f, 0.6f);
		weapon.drawIcon(layer, 16, 16, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(weapon.name), 154.8f, 47.2f, 0.4f, 27);
		
		damageRect = new ColoredRect(layer, 152f, 158.5f, 344*weapon.damage/500, 16);
		fireSpeedRect = new ColoredRect(layer, 152f, 181f, 344*weapon.fireSpeed/500, 16);
		reloadRect = new ColoredRect(layer, 152f, 203.5f, 344*weapon.reloadSpeed/500, 16);
		ammoRect = new ColoredRect(layer, 152f, 226f, 344*weapon.maxAmmo/500, 16);
		accuracyRect = new ColoredRect(layer, 152f, 248.5f, 344*weapon.accuracy/500, 16);
		mobilityRect = new ColoredRect(layer, 152f, 271f, 344*weapon.mobility/500, 16);
		
		damage = new MenuLinearText(layer, "DAMAGE", 18.8f, 155.65f, 0.4f);
		fireSpeed = new MenuLinearText(layer, "FIRE RATE", 18.8f, 178.15f, 0.4f);
		reloadSpeed = new MenuLinearText(layer, "RELOADING", 18.8f, 200.65f, 0.4f);
		ammo = new MenuLinearText(layer, "AMMO", 18.8f, 223.15f, 0.4f);
		accuracy = new MenuLinearText(layer, "ACCURACY", 18.8f, 245.65f, 0.4f);
		mobility = new MenuLinearText(layer, "MOBILITY", 18.8f, 268.15f, 0.4f);
		
		Layers.addObject(damageRect);
		Layers.addObject(fireSpeedRect);
		Layers.addObject(reloadRect);
		Layers.addObject(ammoRect);
		Layers.addObject(accuracyRect);
		Layers.addObject(mobilityRect);
		
		drawCraftMenu();
	}
	
	public void drawCraftMenu(){
		craft = new TextButton(layer, 16, 304, 80, 32, 0.45f, "CRAFT", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Crafter.craft(weapon.getThis());
				Menu.refreshAll();
			}
		};
		if(!Crafter.canBeCrafted(weapon, GamingProcess.hero)){
			craft.active = false;
		}
		Layers.addObject(craft);
		boolean[] info = Crafter.getInto(weapon, GamingProcess.hero);
		for(int i = 0; i < info.length; i++){
			final Item craftItem = weapon.itemsForCraft[i];
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
						break;
					case Item.CRAFT:
						if(craftItem.getType() == Item.WEAPON){
							Menu.createWindow(new WeaponCraftItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Weapon) craftItem));
						} else {
							Menu.createWindow(new ArtifactCraftItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Artifact) craftItem));
						}
						break;
					case Item.DROP:
						if(craftItem.getType() == Item.WEAPON){
							Menu.createWindow(new WeaponInfoItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Weapon) craftItem));
						} else {
							Menu.createWindow(new ArtifactInfoItemMenu("menu_layer_" + 
							Integer.toString((Menu.currentLayer+1)),(Artifact) craftItem));
						}
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
		weaponName = new MenuLinearText(layer, weapon.textName, 154, 16.20f, 0.6f);
		weapon.drawIcon(layer, 16, 16, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(weapon.name), 154.8f, 47.2f, 0.4f, 27);
		
		damageRect = new ColoredRect(layer, 152f, 158.5f, 344*weapon.damage/500, 16);
		fireSpeedRect = new ColoredRect(layer, 152f, 181f, 344*weapon.fireSpeed/500, 16);
		reloadRect = new ColoredRect(layer, 152f, 203.5f, 344*weapon.reloadSpeed/500, 16);
		ammoRect = new ColoredRect(layer, 152f, 226f, 344*weapon.maxAmmo/500, 16);
		accuracyRect = new ColoredRect(layer, 152f, 248.5f, 344*weapon.accuracy/500, 16);
		mobilityRect = new ColoredRect(layer, 152f, 271f, 344*weapon.mobility/500, 16);
		
		damage = new MenuLinearText(layer, "DAMAGE", 18.8f, 155.65f, 0.4f);
		fireSpeed = new MenuLinearText(layer, "FIRE RATE", 18.8f, 178.15f, 0.4f);
		reloadSpeed = new MenuLinearText(layer, "RELOADING", 18.8f, 200.65f, 0.4f);
		ammo = new MenuLinearText(layer, "AMMO", 18.8f, 223.15f, 0.4f);
		accuracy = new MenuLinearText(layer, "ACCURACY", 18.8f, 245.65f, 0.4f);
		mobility = new MenuLinearText(layer, "MOBILITY", 18.8f, 268.15f, 0.4f);
		
		Layers.addObject(damageRect);
		Layers.addObject(fireSpeedRect);
		Layers.addObject(reloadRect);
		Layers.addObject(ammoRect);
		Layers.addObject(accuracyRect);
		Layers.addObject(mobilityRect);
		
		drawCraftMenu();
	}
	
	@Override
	public void build() {
		Layers.addObject(new Frame(layer, 5));
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
		weaponName.changePosition();
		weaponName.changeAlpha();
		description.changePosition();
		description.changeAlpha();
		damage.changePosition();
		damage.changeAlpha();
		fireSpeed.changePosition();
		fireSpeed.changeAlpha();
		reloadSpeed.changePosition();
		reloadSpeed.changeAlpha();
		ammo.changePosition();
		ammo.changeAlpha();
		accuracy.changePosition();
		accuracy.changeAlpha();
		mobility.changePosition();
		mobility.changeAlpha();
	}
	
}
