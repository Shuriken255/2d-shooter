package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Game;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;

import java.util.List;
import java.util.ArrayList;

public class Menu {
	public static final int OPENING = 0, CLOSING = 1, OPENED = 2, CLOSED = 3;
	public static final int SHOP_WEAPON = 0, SHOP_ARTIFACT = 1,
			CRAFT_WEAPON = 2, CRAFT_ARTIFACT = 3;
	public static final float WIDTH = 512, HEIGHT = 352, POSX = Game.windowSizeX/2-256;
	public static int currentState = 3;
	public static float posY = Game.windowSizeY/2-176;
	static float speedY = 0;
	public static float alpha = 0;
	static int currentLayer = 0;
	public static BasicMenu inventory;
	
	static BasicMenu mainMenu;
	
	static List<BasicMenu> menus = new ArrayList<BasicMenu>();
	
	static{
		inventory = new Inventory("inventoryBar");
	}
	
	static public void react(){
		if(currentState != Menu.OPENING && currentState != Menu.CLOSING){
			if(currentState ==  Menu.CLOSED){
				create();
				GamingProcess.paused = true;
			} else {
				destroy();
			}
		}
	}
	
	static public void createWindow(BasicMenu menu){
		if(currentLayer == 0){
			mainMenu.setInactive();
		} else {
			menus.get(currentLayer-1).setInactive();
		}
		currentLayer++;
		menus.add(menu);
	}
	
	static void create(){
		Layers.setInactive("hero");
		Layers.setInactive("enemies");
		Layers.setInactive("bullets");
		Layers.setInactive("money");
		mainMenu = new ShopWeaponMenu("mainMenu", 0);
		currentState = Menu.OPENING;
		alpha = 0;
		speedY = -3;
		posY = Game.windowSizeY/2-135.1f;
	}
	
	static void destroy(){
		if(currentLayer != 0){
			Layers.setInvisible("mainMenu");
			if(currentLayer != 1){
				for(BasicMenu menu:menus){
					Layers.setInvisible(menu.layer);
				}
			}
		}
		currentState = Menu.CLOSING;
	}
	
	static public void refreshAll(){
		inventory.refresh();
		mainMenu.refresh();
		for(int i = 0; i < menus.size(); i++){
			menus.get(i).refresh();
		}
	}
	
	static public void refreshAll(BasicMenu menu){
		inventory.refresh();
		mainMenu.refresh();
		for(int i = 0; i < menus.size(); i++){
			if(menus.get(i) != menu){
				menus.get(i).refresh();
			}
		}
	}
	
	static public void logic(GamingProcess game){
		
		switch(currentState){
		case Menu.OPENING:
			if(speedY < 0){
				speedY += 0.1f;
				alpha = 100 + speedY/3*100;
			} else {
				speedY = 0;
				currentState = OPENED;
			}
			break;
		case Menu.CLOSING:
			if(speedY < 3){
				speedY += 0.1f;
				alpha = 100 - speedY/3*100;
			} else {
				for(int i = 0; i < menus.size(); i++){
					Layers.deleteLayer(menus.get(i).layer);
				}
				Layers.setActive("hero");
				Layers.setActive("enemies");
				Layers.setActive("bullets");
				Layers.setActive("money");
				GamingProcess.paused = false;
				speedY = 0;
				Layers.deleteLayer("mainMenu");
				currentState = CLOSED;
				currentLayer = 0;
				menus.clear();
			}
			break;
		}
		if(currentState != CLOSED){
			mainMenu.logic();
			for(BasicMenu menu:menus){
				menu.logic();
			}
		}
		game.fade.alphaPicture = alpha/1.5f;
		posY += speedY;
	}
	
	public static void changeMainMenu(int menu, int page){
		Layers.clearLayer("mainMenu");
		switch(menu){
		case 0:
			mainMenu = new ShopWeaponMenu("mainMenu", page);
			break;
		case 1:
			mainMenu = new ShopArtifactMenu("mainMenu", page);
			break;
		case 2:
			mainMenu = new CraftWeaponMenu("mainMenu", page);
			break;
		case 3:
			mainMenu = new CraftArtifactMenu("mainMenu", page);
			break;
		}
	}
	
	static void deleteLastWindow(){
		Layers.setRemove(Menu.menus.get(currentLayer-1).layer);
		menus.remove(currentLayer-1);
		currentLayer--;
		if(currentLayer == 0){
			mainMenu.setActive();
		} else {
			Menu.menus.get(currentLayer-1).setActive();
		}
	}
}
