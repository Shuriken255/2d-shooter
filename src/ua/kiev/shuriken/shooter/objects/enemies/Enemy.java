package ua.kiev.shuriken.shooter.objects.enemies;

import java.util.Random;
import ua.kiev.shuriken.game.Collisions;
import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.SolidGameObject;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.gamestates.game.money.Coin1;
import ua.kiev.shuriken.shooter.gamestates.game.money.Coin25;
import ua.kiev.shuriken.shooter.gamestates.game.money.Coin5;
import ua.kiev.shuriken.shooter.objects.effects.EnemyExplosion;
import ua.kiev.shuriken.shooter.objects.interfaces.radar.RadarBack;
import ua.kiev.shuriken.shooter.objects.interfaces.radar.RadarEnemy;

public abstract class Enemy extends SolidGameObject{
	
	public static Random rand = new Random();
	protected int difficulty;
	protected float damage = 1000f;
	protected float pureDamage = 0;
	protected float speed = 4f;
	protected float acceleration = 0.4f;
	protected int money = 5;
	public float health = 100;
	protected float maxHealth;
	
	protected EnemyHealthBar healthBar;
	protected EnemyHealthBarLine healthBarLine;
	
	protected float damageMultiplier = 1.5f;
	protected float pureDamageMultiplier = 1f;
	protected float speedMultiplier = 1f;
	protected float accelerationMultiplier = 1f;
	protected float healthMultiplier = 10f;
	
	RadarEnemy radar;
	
	protected int damageCooldown = 0;
	
	abstract protected void initBasicStats();
	
	public float speedX, speedY;
	public Enemy(int difficulty, String texture) {
		super("enemies", "enemies");
		this.setSound("hit");
		this.setTexture(texture);
		this.difficulty = difficulty;
		initBasicStats();
		initStats(difficulty);
		initSizes();
		initPosition();
		initSpeed();
		initRadar();
		initHealthBar();
	}
	
	protected void initHealthBar(){
		healthBar = new EnemyHealthBar(this.posX+this.centerX-15, this.posY+this.centerY-20, 30, 2);
		healthBarLine = new EnemyHealthBarLine(this.posX+this.centerX-15, this.posY+this.centerY-20, 30, 2);
		Layers.addObject(healthBar);
		Layers.addObject(healthBarLine);
	}
	
	protected void healthBarLogic(){
		healthBar.posX = this.posX+this.centerX-15;
		healthBar.posY = this.posY+this.centerY-20;
		healthBarLine.posX = this.posX+this.centerX-15;
		healthBarLine.posY = this.posY+this.centerY-20;
		healthBarLine.sizeX = (health/maxHealth)*30;
		if(health < maxHealth/4){
			healthBarLine.changeColor("red");
		} else {
			healthBarLine.changeColor("green");
		}
	}
	
	protected void deleteRadars(){
		healthBar.destroy();
		healthBarLine.destroy();
	}
	
	public void initRadar(){
		radar = new RadarEnemy();
		Layers.addObject(radar);
	}
	
	public void getDamage(float damage){
		this.health -= damage;
	}
	
	public void initSizes(){
		sizeX = 50;
		sizeY = 50;
		this.hitBoxLeft = 10;
		this.hitBoxRight = 40;
		this.hitBoxTop = 10;
		this.hitBoxBottom = 40;
		this.centerX = 25;
		this.centerY = 25;
	}
	
	public void initStats(int difficulty){
		damage *= 1+((damageMultiplier-1)*(difficulty/100));
		pureDamage *= 1+((pureDamageMultiplier-1)*(difficulty/100));
		acceleration *= 1+((accelerationMultiplier-1)*(difficulty/100));
		speed *= 1+((speedMultiplier-1)*(difficulty/100));
		health *= 1+((healthMultiplier-1)*(difficulty/100));
		money *= 1+(difficulty/10);
		maxHealth = health;
	}
	
	public void initPosition(){
		if(rand.nextBoolean()){
			if(rand.nextBoolean()){
				posX = -64;
				posY = rand.nextInt((int)GamingProcess.sizeY);
			} else {
				posX = GamingProcess.sizeX+64;
				posY = rand.nextInt((int)GamingProcess.sizeY);
			}
		} else {
			if(rand.nextBoolean()){
				posY = -64;
				posX = rand.nextInt((int)GamingProcess.sizeX);
			} else {
				posY = GamingProcess.sizeY+64;
				posX = rand.nextInt((int)GamingProcess.sizeX);
			}
		}
	}
	
	public void initSpeed(){
		this.rotateTo(GamingProcess.hero);
		this.speedX = (float)(Math.cos(rotation/180*Math.PI)*speed);
		this.speedY = (float)(Math.sin(rotation/180*Math.PI)*speed);
	}
	
	abstract protected void enemyLogic();
	
	protected void moveToPlayer(){
		this.rotateTo(GamingProcess.hero);
		this.speedX += Math.cos(rotation/180*Math.PI)*acceleration;
		this.speedY += Math.sin(rotation/180*Math.PI)*acceleration;
		if(Math.sqrt(speedX*speedX+speedY*speedY) > speed){
			float k = (float)(Math.sqrt(speedX*speedX+speedY*speedY)/speed);
			speedX /= k;
			speedY /= k;
		}
		posX += speedX;
		posY += speedY;
	}
	
	protected void damageLogic(){
		if(damageCooldown != 0){
			damageCooldown--;
		}
	}
	
	protected void healthLogic(){
		if(health <= 0){
			radar.destroy();
			throwCoins();
			actionsAfterDestroy();
			this.destroy();
		}
	}
	
	protected void collisionLogic(){
		if(Collisions.checkCollision(this, 0, 0, GamingProcess.hero) && damageCooldown == 0){
			GamingProcess.hero.damage(damage, pureDamage);
			damageCooldown = 15;
		}
	}
	
	public void destroy(){
		this.isDeleting = true;
		Layers.addObject(new EnemyExplosion(this.posX+this.sizeX/2, this.posY+this.sizeY/2, 64));
		healthBar.destroy();
		healthBarLine.destroy();
	}
	
	protected void radarLogic(){
		radar.posX = (8+((this.posX+this.centerX)/GamingProcess.sizeX)*112-1)*RadarBack.size;
		radar.posY = (8+((this.posY+this.centerY)/GamingProcess.sizeY)*112-1)*RadarBack.size;
	}
	
	protected void mainLogic(){
		moveToPlayer();
		damageLogic();
		healthLogic();
		collisionLogic();
		radarLogic();
		healthBarLogic();
		if(GamingProcess.hero.getCurrentHP() <= 0){
			this.destroy();
		}
	}
	
	@Override
	public void logic(){
		enemyLogic();
		mainLogic();
	}
	
	protected void throwCoins(){
		while(money >= 25){
			Layers.addObject(new Coin25(posX+centerX, posY+centerY));
			money -= 25;
		}
		while(money >= 5){
			Layers.addObject(new Coin5(posX+centerX, posY+centerY));
			money -= 5;
		}
		while(money > 0){
			Layers.addObject(new Coin1(posX+centerX, posY+centerY));
			money--;
		}
	}
	
	protected abstract void actionsAfterDestroy();
}
