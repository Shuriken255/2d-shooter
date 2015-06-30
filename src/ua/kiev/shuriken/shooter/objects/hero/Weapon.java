package ua.kiev.shuriken.shooter.objects.hero;

import ua.kiev.shuriken.game.FileWorker;
import ua.kiev.shuriken.game.GameObject;

public abstract class Weapon extends Item{
	public String name;
	protected String pathToAtlas;
	protected int finalFrame;
	protected int currentFrame;
	
	protected boolean isSecondReloadSoundPlayed = false;
	
	public boolean isReloading;
	protected int reloadingCooldown;
	
	protected boolean availableForShooting;
	protected float fireCooldown;
	
	public float mobility;
	public float fireSpeed;
	public float accuracy;
	public float damage;
	public float reloadSpeed;
	public int maxAmmo;
	protected int currentAmmo;
	
	public static void init(String name){
		GameObject.addTexture("weapon_"+name+"_icon", "res/objects/Items/Weapons/"+name+"/"+name+"_icon.png");
		GameObject.addAtlas("weapon_"+name+"", "res/objects/Items/Weapons/"+name+"/"+name+".png", 4, 4);
		Item.descriptions.put("weapon_"+name, FileWorker.loadStringFromFile("res/objects/Items/Weapons/"+name+"/"+name+".ini"));
	}
	
	public Weapon(float mobility,
			float fireSpeed,
			float accuracy,
			float damage,
			float reloadSpeed,
			int ammo,
			String name,
			int finalFrame,
			String textName){
		super(name, textName);
		this.fireSpeed = fireSpeed;
		this.accuracy = accuracy;
		this.damage = damage;
		this.mobility = mobility;
		this.reloadSpeed = reloadSpeed;
		this.maxAmmo = ammo;
		this.currentAmmo = maxAmmo;
		fireCooldown = 1;
		this.finalFrame = finalFrame;
		currentFrame = finalFrame;
		this.name = name;
	}
	
	public void countCooldown(Hero hero){
		if(!availableForShooting && currentAmmo != 0){
			fireCooldown -= 1/(60/((hero.fireSpeed/100)*this.fireSpeed/50));
			if(fireCooldown <= 0){
				fireCooldown++;
				availableForShooting = true;
			}
		}
		if(currentAmmo == 0){
			availableForShooting = false;
		}
	}
	
	public String getInformation(){
		StringBuilder info = new StringBuilder("Ammo: ");
		int k;
		for(k = 1; k<=maxAmmo/10; k *= 10);
		if(currentAmmo == 0){
			for(k = k-k; k > 1; k /= 10){
				info.append("0");
			}
			info.append(Integer.toString(currentAmmo));
			info.append("/");
			info.append(Integer.toString(maxAmmo));
			return info.toString();
		} else {
			for(k = k-k; k > 1; k /= 10){
				if(currentAmmo < k){
					info.append("0");
				} else {
					info.append(Integer.toString(currentAmmo));
					info.append("/");
					info.append(Integer.toString(maxAmmo));
					return info.toString();
				}
			}
		}
		info.append(Integer.toString(currentAmmo));
		info.append("/");
		info.append(Integer.toString(maxAmmo));
		return info.toString();
	}
	
	public abstract void shoot(Hero hero);
	
	public abstract void graphicLogic(Hero hero);
	
	public void reload(Hero hero){
		if(!isReloading){
			isSecondReloadSoundPlayed = false;
			hero.playReloadSound(0);
			isReloading = true;
			availableForShooting = false;
			reloadingCooldown = (int)(120/(this.reloadSpeed/100*(hero.reloadSpeed/100)));
			
		} else {
			if(reloadingCooldown != 0){
				reloadingCooldown--;
				if(!isSecondReloadSoundPlayed && reloadingCooldown < ((int)(120/(this.reloadSpeed/100*(hero.reloadSpeed/100))))/2){
					isSecondReloadSoundPlayed = true;
					hero.playReloadSound(1);
				}
			} else {
				isReloading = false;
				availableForShooting = true;
				currentAmmo = maxAmmo;
			}
		}
	}
	
	@Override
	public int getType(){
		return (Item.WEAPON);
	}
}
