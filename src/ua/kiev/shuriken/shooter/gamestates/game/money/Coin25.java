package ua.kiev.shuriken.shooter.gamestates.game.money;

import java.util.Random;

public class Coin25 extends Money{
	static Random rand = new Random();
	public Coin25(float posX, float posY) {
		super(rand.nextInt(360), posX, posY);
		setTexture("coin_25");
		cost = 25;
	}
}
