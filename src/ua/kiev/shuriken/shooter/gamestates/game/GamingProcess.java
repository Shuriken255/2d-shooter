package ua.kiev.shuriken.shooter.gamestates.game;

import ua.kiev.shuriken.game.*;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.objects.background.*;
import ua.kiev.shuriken.shooter.objects.enemies.*;
import ua.kiev.shuriken.shooter.objects.hero.*;
import ua.kiev.shuriken.shooter.objects.interfaces.health.*;
import ua.kiev.shuriken.shooter.objects.interfaces.*;
import ua.kiev.shuriken.shooter.objects.interfaces.radar.*;
import ua.kiev.shuriken.shooter.objects.interfaces.shop.Menu;

import org.lwjgl.input.Keyboard;

public class GamingProcess extends GameProcess{
	static{
		// loading basic things
		GameObject.addTexture("default", "res/background/background.png");
		GameObject.addTexture("borders", "res/background/borders.png");
		GameObject.addTexture("black", "res/interface/black.png");
		GameObject.addTexture("red", "res/interface/red.png");
		GameObject.addTexture("green", "res/interface/green.png");
		GameObject.addTexture("wave_frame", "res/interface/wave_frame.png");
		GameObject.addAtlas("health_bar_interface", "res/interface/HealthBar1.png", 1, 2);
		GameObject.addAtlas("health_bar_line", "res/interface/HealthBar2.png", 2, 1);
		GameObject.addTexture("radar_back", "res/interface/radar_back.png");
		GameObject.addTexture("radar_top", "res/interface/radar_top.png");
		
		// loading effects
		GameObject.addAtlas("blood", "res/objects/Effects/Blood.png", 4, 4);
		GameObject.addAtlas("effect_enemyexplosion", "res/objects/Effects/explosion.png", 4, 4);
		
		// loading ammo
		GameObject.addTexture("ammo_bullet", "res/objects/Items/Weapons/ammo/bullet.png");
		
		// loading money
		GameObject.addTexture("coin_1", "res/objects/Money/1.png");
		GameObject.addTexture("coin_25", "res/objects/Money/25.png");
		GameObject.addTexture("coin_5", "res/objects/Money/5.png");
		
		// loading icons
		Artifact.init("battery");
		Artifact.init("bolt");
		Artifact.init("gear");
		Artifact.init("golden_bolt");
		Artifact.init("golden_gear");
		Artifact.init("wheel");
		Weapon.init("gun");
		Weapon.init("double_gun");
		Weapon.init("quad_gun");
		Weapon.init("none");
		
		// loading enemies
		GameObject.addTexture("enemy_dasher", "res/objects/Enemies/dasher.png");
		GameObject.addTexture("enemy_fast", "res/objects/Enemies/fast.png");
		GameObject.addTexture("enemy_healer", "res/objects/Enemies/healer.png");
		GameObject.addTexture("enemy_healer_heals", "res/objects/Enemies/healer_heals.png");
		GameObject.addTexture("enemy_heavy", "res/objects/Enemies/heavy.png");
		GameObject.addTexture("enemy_normal", "res/objects/Enemies/Normal.png");
		GameObject.addTexture("enemy_reflect", "res/objects/Enemies/reflect.png");
		GameObject.addTexture("enemy_reflect_red", "res/objects/Enemies/reflect_red.png");
		GameObject.addTexture("enemy_spawn", "res/objects/Enemies/spawn.png");
		GameObject.addTexture("enemy_vampire", "res/objects/Enemies/vampire.png");
		
		// loading wave signs
		
		
		// loading shop
		GameObject.addTexture("bar", "res/interface/InventoryBar.png");
		GameObject.addTexture("grey_box", "res/interface/grey_box.png");
		GameObject.addTexture("interface_green_light", "res/interface/light_green.png");
		GameObject.addAtlas("item_button_icon", "res/interface/IconButton.png", 1, 4);
		GameObject.addAtlas("item_pause_menu", "res/interface/Pause_menu.png", 3, 2);
		GameObject.addTexture("interface_red_light", "res/interface/light_red.png");
		GameObject.addAtlas("text_button", "res/interface/Button.png", 1, 4);
		
		// loading sounds
		Sound.loadSound("gun_shot", "res/sounds/shot.wav");
		Sound.loadSound("gun_reload1", "res/sounds/reload1.wav");
		Sound.loadSound("gun_reload2", "res/sounds/reload2.wav");
		Sound.loadSound("explosion", "res/sounds/explosion.wav");
		Sound.loadSound("hit", "res/sounds/hit.wav");
	}
	
	public static float sizeX = 2048;
	public static float sizeY = 2048;
	
	// Main variables
	public static Hero hero;
	int enemySpawnerCooldown = 120;
	int currentEnemySpawnerCooldown = 120;
	public Fade fade;
	public static RedFade redFade;
	Text reloadingInfo;
	Text ammoInfo;
	Text moneyInfo;
	public static long money;
	public static boolean paused = false;
	
	private int gameOverCooldown = 360;
	public boolean gameOver = false;
	
	// Additional variables
	boolean availableForPressE = true;
	boolean availableForPressQ = true;
	
	public GamingProcess(){
		Text.setFont("default");
		VCAM.setBorders(0, 0, sizeX, sizeY);
		VCAM.cofficientX = 1f;
		VCAM.cofficientY = 1f;
		float prevSizeX = VCAM.sizeX;
		VCAM.sizeX = VCAM.DEFAULT_SIZE_X*VCAM.cofficientX;
		float prevSizeY = VCAM.sizeY;
		VCAM.sizeY = VCAM.DEFAULT_SIZE_Y*VCAM.cofficientY;
		VCAM.posX += (prevSizeX-VCAM.sizeX)/2;
		VCAM.posY += (prevSizeY-VCAM.sizeY)/2;
		
		Collisions.addGroup("hero");
		Collisions.addGroup("enemies");
		Collisions.addGroup("bullets");
		Collisions.addGroup("money");
		
		Layers.addLayer("background");
		Layers.addObject(new Background());
		Layers.addLayer("bullets");
		Layers.addLayer("money");
		Layers.addLayer("hero");
		Layers.addLayer("enemies");
		Layers.addLayer("effects");
		Layers.addLayer("border");
		Layers.addLayer("ammoInfo");
		Layers.addLayer("enemy_health_bars");
		Layers.addObject(new RadarBack("radar_back"));
		Layers.addLayer("radar_objects");
		Layers.addObject(new RadarTop("radar_top"));
		Layers.addLayer("wave_signs");
		Layers.addObject(new WaveFrame());
		
		hero = new Hero();
		Layers.addObject(hero);
		
		ammoInfo = new Text("ammoInfo", hero.getInformationAboutAmmo(), Game.windowSizeX-230, 85, 0.4f, true, Text.LEFT, Text.TOP);
		reloadingInfo = new Text("ammoInfo", hero.getReloadingInfo(), Game.windowSizeX-230, 115, 0.4f, true, Text.LEFT, Text.TOP);
		moneyInfo = new Text("ammoInfo", "$"+Long.toString(money), 0, RadarBack.size*128, 0.5f, true, Text.LEFT, Text.TOP);
		
		Layers.addObject(new HealthBar2());
		Layers.addObject(new HealthBar3(hero));
		Layers.addObject(new HealthBar4(hero));
		Layers.addObject(new HealthBar1());
		Layers.addObject(new HealthBar1());
		Layers.addObject(new RedBorder());
		redFade = new RedFade("red_fade");
		Layers.addObject(redFade);
		fade = new Fade("fade", 30);
		Layers.addObject(fade);
		Layers.addLayer("mainMenu");
	}
	
	@Override
	public void logic(){
		Menu.logic(this);
		
		redFade.decreaseAlpha();
		
		ammoInfo.changeText(hero.getInformationAboutAmmo());
		reloadingInfo.changeText(hero.getReloadingInfo());
		moneyInfo.changeText("$"+money);

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Layers.deleteAllLayers();
			Game.isRunning = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			if(availableForPressQ){
				hero.switchItemBack();
				availableForPressQ = false;
			}
		} else {
			availableForPressQ = true;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			if(availableForPressE){
				hero.switchItemForward();
				availableForPressE = false;
			}
		} else {
			availableForPressE = true;
		}

		if(hero.getCurrentHP() <= 0){
			gameOver = true;
		}
		
		if(!gameOver){
			if(!paused){
				EnemyLogic.logic();	
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
				Menu.react();
			}
		} else {
			gameOverCooldown--;
			if(gameOverCooldown == 240){
				new Text("ammoInfo", "YOU LOST!", Game.windowSizeX/2, Game.windowSizeY/2, 1f, true, Text.CENTER, Text.CENTER);
			}
			if(gameOverCooldown < 50){
				fade.alphaPicture = 100-gameOverCooldown*2;
			}
			if(gameOverCooldown == 0){
				Game.isRunning = false;
			}
		}
	}
}
