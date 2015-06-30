package ua.kiev.shuriken.shooter.gamestates.game.money;

import java.util.Random;

public class Coin5 extends Money{
	static Random rand = new Random();
	public Coin5(float posX, float posY) {
		super(rand.nextInt(360), posX, posY);
		setTexture("coin_5");
		cost = 5;
	}
}
