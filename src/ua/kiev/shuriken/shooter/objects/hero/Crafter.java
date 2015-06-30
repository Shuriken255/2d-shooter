package ua.kiev.shuriken.shooter.objects.hero;

import java.util.List;
import java.util.ArrayList;

import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.artifacts.*;
import ua.kiev.shuriken.shooter.objects.hero.weapons.*;

public class Crafter {
	public static List<Artifact> artifactCraftItems = new ArrayList<Artifact>();
	public static List<Weapon> weaponCraftItems = new ArrayList<Weapon>();
	public static List<Artifact> artifactShopItems = new ArrayList<Artifact>();
	public static List<Weapon> weaponShopItems = new ArrayList<Weapon>();
	
	static{
		artifactCraftItems.add(new Wheel());
		weaponShopItems.add(new BasicGun());
		weaponCraftItems.add(new DoubleGun());
		weaponCraftItems.add(new QuadGun());
		artifactShopItems.add(new Gear());
		artifactShopItems.add(new Bolt());
		artifactShopItems.add(new Battery());
		artifactShopItems.add(new GoldenGear());
		artifactShopItems.add(new GoldenBolt());
	}
	
	public static boolean canBeCrafted(Item item, Hero hero){
		Item[] recipe = item.itemsForCraft;
		int[] numberOfItems = new int[recipe.length];
		for(int i = 0; i < numberOfItems.length; i++){
			numberOfItems[i] = -1;
		}
		for(int i = 0; i < 6; i++){
			boolean found = false;
			for(int j = 0; j<numberOfItems.length && !found; j++){
				if(hero.inventory[i] != null){
					if(hero.inventory[i].name == recipe[j].name && numberOfItems[j] == -1){
						numberOfItems[j] = i;
						found = true;
					}
				}
			}
		}
		for(int i:numberOfItems){
			if(i == -1){
				return false;
			}
		}
		return true;
	}
	
	public static boolean[] getInto(Item item, Hero hero){
		Item[] recipe = item.itemsForCraft;
		boolean[] array = new boolean[recipe.length];
		int[] numberOfItems = new int[recipe.length];
		for(int i = 0; i < numberOfItems.length; i++){
			numberOfItems[i] = -1;
		}
		for(int i = 0; i < 6; i++){
			boolean found = false;
			for(int j = 0; j<numberOfItems.length && !found; j++){
				if(hero.inventory[i] != null){
					if(hero.inventory[i].name == recipe[j].name && numberOfItems[j] == -1){
						numberOfItems[j] = i;
						found = true;
					}
				}
			}
		}
		for(int i = 0; i < numberOfItems.length; i++){
			if(numberOfItems[i] != -1){
				array[i] = true;
			}
		}
		return array;
	}
	
	public static void craft(Item item){
		Item[] recipe = item.itemsForCraft;
		for(Item e:recipe){
			boolean deleted = false;
			for(int i = 0; i < 6 && !deleted; i++){
				if(GamingProcess.hero.inventory[i] != null){
					if(GamingProcess.hero.inventory[i].name == e.name){
						deleted = true;
						GamingProcess.hero.inventory[i] = null;
					}
				}
			}
		}
		for(int i = 0; i < 6; i++){
			if(GamingProcess.hero.inventory[i] == null){
				GamingProcess.hero.inventory[i] = item;
				return;
			}
		}
	}
}
