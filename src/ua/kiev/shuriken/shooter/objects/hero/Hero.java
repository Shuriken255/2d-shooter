package ua.kiev.shuriken.shooter.objects.hero;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.effects.EnemyExplosion;
import ua.kiev.shuriken.shooter.objects.hero.weapons.*;
import ua.kiev.shuriken.shooter.objects.interfaces.radar.RadarBack;
import ua.kiev.shuriken.shooter.objects.interfaces.radar.RadarHero;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.Sound;
import ua.kiev.shuriken.game.VCAM;
import ua.kiev.shuriken.game.SolidGameObject;

public class Hero extends SolidGameObject{
	
	static{
		Sound.loadSound("hero_damage", "res/sounds/damage.wav");
	}
	
	public Item[] inventory = new Item[6];
	Weapon currentGun;
	Weapon noneWeapon = new NoneGun();
	int currentItem;
	boolean isDead = false;
	
	// Basic character's stats
	int charMaxHP = 100;
	int charRegenHP = 1;
	int charDefence = 100;
	int charSpeed = 100;
	int charReloadSpeed = 100;
	int charFireSpeed = 100;
	int charAccuracy = 100;
	
	// Character stats
	int maxHP = 100;
	int currentHP = 100;
	int regenHP = 1;
	int defence = 100;
	int speed = 100;
	int reloadSpeed = 100;
	int fireSpeed = 100;
	int accuracy = 100;
	
	// Some basic variables
	private float speedX = 0;
	private float speedY = 0;
	private float acceleration = 1f;
	int animationSpeed = 1;
	int currentAnimationSpeed = 1;
	int invincible = 15;
	int currentInvincible = 0;
	
	RadarHero radar;
	
	public void addStats(int maxHP,
			int regenHP,
			int defence,
			int speed,
			int reloadSpeed,
			int fireSpeed,
			int accuracy){
		this.maxHP += maxHP;
		this.regenHP += regenHP;
		this.speed += speed;
		this.reloadSpeed += reloadSpeed;
		this.fireSpeed += fireSpeed;
		this.accuracy += accuracy;
	}
	
	private float maxSpeed = 6;
	private int regenCooldown = 60;
	
	static public void addWeapon(String name, String path){
		addAtlas(name, path, 4, 4);
	}
	
	public void setFrame(int frame){
		currentFrame = frame;
	}
	
	protected void changeGun(int number){
		if(currentGun != null){
			currentGun.isReloading = false;
		}
		currentGun = (Weapon) inventory[number];
		this.setAtlas(currentGun.name);
		currentFrame = currentGun.finalFrame;
		currentItem = number;
	}
	
	protected void changeGun(Weapon weapon){
		if(currentGun != null){
			currentGun.isReloading = false;
		}
		currentGun = noneWeapon;
		this.setAtlas(noneWeapon.name);
		currentFrame = noneWeapon.finalFrame;
	}
	
	public int getCurrentHP(){
		return currentHP;
	}
	
	public int getMaxHP(){
		return maxHP;
	}
	
	public void refreshStats(){
		maxHP = charMaxHP;
		regenHP = charRegenHP;
		defence = charDefence;
		speed = charSpeed;
		reloadSpeed = charReloadSpeed;
		fireSpeed = charFireSpeed;
		accuracy = charAccuracy;
		for(int i = 0; i<6; i++){
			if(inventory[i] != null){
				if(inventory[i].getType() == Item.ARTIFACT){
					((Artifact)inventory[i]).changeStats(this);
				}
			}
		}
		maxSpeed = ((speed/100))*(currentGun.mobility/100);
	}
	
	public Item[] getInventory(){
		return inventory;
	}
	
	public float getRotation(){
		return this.rotation;
	}
	
	public float getSpeedX(){
		return speedX;
	}
	
	public float getSpeedY(){
		return speedY;
	}
	
	public int getAccuracy(){
		return this.accuracy;
	}
	
	private void tryToShoot(){
		if(currentGun.availableForShooting && !currentGun.isReloading){
			currentGun.shoot(this);
			this.setSound("gun_shot");
			this.playSound();
		} else {
			if(currentGun.currentAmmo == 0 && currentGun != noneWeapon){
				currentGun.reload(this);	
			}
		}
	}
	
	public void playReloadSound(int number){
		switch(number){
		case 0: 
			this.setSound("gun_reload1");
			this.playSound();
			break;
		case 1:
			this.setSound("gun_reload2");
			this.playSound();
			break;
		}
	}
	
	public int switchItemBack(){
		for(int i = 0; i < 6; i++){
			if(currentItem == 0){
				currentItem = 5;
			} else {
				currentItem--;
			}
			if(inventory[currentItem] != null){
				if(inventory[currentItem].getType() == Item.WEAPON){
					this.changeGun(currentItem);
					return currentItem;
				}
			}
		}
		this.changeGun(noneWeapon);
		return currentItem;
	}
	
	public int switchItemForward(){
		for(int i = 0; i < 6; i++){
			if(currentItem == 5){
				currentItem = 0;
			} else {
				currentItem++;
			}
			if(inventory[currentItem] != null){
				if(inventory[currentItem].getType() == Item.WEAPON){
					this.changeGun(currentItem);
					return currentItem;
				}
			}
		}
		this.changeGun(noneWeapon);
		return currentItem;
	}
	
	public Hero(){
		super("hero");
		this.posX = GamingProcess.sizeX/2-this.centerX;
		this.posY = GamingProcess.sizeY/2-this.centerY;
		VCAM.setFollowingObject(this);
		this.sizeX = 100;
		this.sizeY = 100;
		this.hitBoxLeft = 30;
		this.hitBoxRight = 70;
		this.hitBoxTop = 30;
		this.hitBoxBottom = 70;
		this.centerX = 50;
		this.centerY = 50;
		VCAM.sizeX /= 2;
		VCAM.sizeY /= 2;
		VCAM.cofficientX /= 2;
		VCAM.cofficientY /= 2;
		VCAM.posX = -VCAM.sizeX/2+(this.posX+this.sizeX/2);
		VCAM.posY = -VCAM.sizeY/2+(this.posY+this.sizeY/2);
		inventory[0] = new BasicGun();
		
		this.changeGun(0);
		this.refreshStats();
		
		radar = new RadarHero();
		Layers.addObject(radar);
	}
	
	@Override
	public void touch(String target){
		if(target == "damage"){
			if(currentInvincible < 0){
				currentHP -= 10;
				currentInvincible = invincible;
			}
		}
	}
	
	public String getInformationAboutAmmo(){
		return currentGun.getInformation();
	}
	
	public String getReloadingInfo(){
		if(currentGun.isReloading){
			return "*RELOADING*";
		} else {
			return "";
		}
	}
	
	@Override
	public void logic() {
		if(currentHP <= 0){
			if(!isDead){
				this.visible = false;
				Layers.addObject(new EnemyExplosion(this.posX+this.sizeX/2, this.posY+this.sizeY/2, 128));
				isDead = true;
			}
		} else {
			if(regenCooldown != 0){
				regenCooldown--;
			} else {
				regenCooldown = 60;
				if(currentHP+regenHP < maxHP){
					currentHP += regenHP;
				} else {
					currentHP = maxHP;
				}
			}
			
			currentInvincible--;
			
			
			
			if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
				this.rotation += 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_X)){
				this.rotation -= 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				if(speedY > -maxSpeed)
					speedY -= acceleration;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				if(speedY < maxSpeed)
				speedY += acceleration;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				if(speedX > -maxSpeed)
					speedX -= acceleration;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				if(speedX < maxSpeed)
					speedX += acceleration;
			}
			
			if(speedX != 0){
				if(Math.abs(speedX) < acceleration/2){
					speedX = 0;
				} else {
					if(speedX > 0){
						speedX -= acceleration/2;
					} else {
						speedX += acceleration/2;
					}
				}
			}
			
			if(speedY != 0){
				if(Math.abs(speedY) < acceleration/2){
					speedY = 0;
				} else {
					if(speedY > 0){
						speedY -= acceleration/2;
					} else {
						speedY += acceleration/2;
					}
				}
			}
			float square = (float)Math.sqrt(speedX*speedX+speedY*speedY);
			if(square > maxSpeed){
				speedX = speedX*(maxSpeed/square);
				speedY = speedY*(maxSpeed/square);
			}
			this.rotateToMouse();
			posX += speedX;
			posY += speedY;
			radar.posX = (8+((this.posX+this.centerX)/GamingProcess.sizeX)*112-1)*RadarBack.size;
			radar.posY = (8+((this.posY+this.centerY)/GamingProcess.sizeY)*112-1)*RadarBack.size;
			
			if(posX < 0){
				posX = 0;
			}
			if(posY < 0){
				posY = 0;
			}
			if(posX+sizeX > GamingProcess.sizeX){
				posX = GamingProcess.sizeX-sizeX;
			}
			if(posY+sizeY > GamingProcess.sizeY){
				posY = GamingProcess.sizeY-sizeY;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_R)){
				if(currentGun != noneWeapon){
					currentGun.reload(this);
				}
			}
			
			currentGun.graphicLogic(this);
			currentGun.countCooldown(this);
			if(currentGun.isReloading){
				currentGun.reload(this);
			}
			
			if(Mouse.isButtonDown(0)){
				tryToShoot();
			}
		}
	}
	
	public void damage(float damage, float pureDamage){
		this.currentHP -= damage/this.defence;
		this.currentHP -= pureDamage;
		GamingProcess.redFade.alphaPicture = 30;
		this.setSound("hero_damage");
		this.playSound();
	}
}
