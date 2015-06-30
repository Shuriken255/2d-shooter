package ua.kiev.shuriken.shooter.gamestates.game.money;

import java.util.Random;

public class Coin1 extends Money{
	static Random rand = new Random();
	public Coin1(float posX, float posY) {
		super(rand.nextInt(360), posX, posY);
		setTexture("coin_1");
		cost = 1;
	}
}
