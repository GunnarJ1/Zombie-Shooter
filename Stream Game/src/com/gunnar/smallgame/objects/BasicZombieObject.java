package com.gunnar.smallgame.objects;

import java.awt.Graphics;
import java.util.Random;

import com.gunnar.smallgame.Game;
import com.gunnar.smallgame.utils.GameObject;
import com.gunnar.smallgame.utils.GameObjectManager;
import com.gunnar.smallgame.utils.SpriteSheet;

public class BasicZombieObject extends GameObject {
	
	/**
	 * Main enemy class
	 * This is the enemy that you will see loaded on the screen
	 * (NOTE:) The AI of enemy is based off the enemy comparing it's position to the players then going the opposite of the players coordinates
	 * (NOTE:) Using the same comparison system, it will decrease the players health
	 */
	
	private int x, y;
	private int direction;
	
	private Random random;
	private PlayerObject player;
	private GameObjectManager om;
	private SpriteSheet ss;
	
	public BasicZombieObject(int x, int y, SpriteSheet ss,PlayerObject player, GameObjectManager om) {
		super(x, y, ID.Zombie);
		this.x = x;
		this.y = y;
		this.ss = ss;
		this.player = player;
		this.om = om;
		
		random = new Random();
		checkSpawn();
		
		direction = 0;
	}
	
	public void tick() {
		x += getVelx();
		y += getVely();
		
		setX(x);
		setY(y);
		findPlayer();
		collide();
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.fillRect(x, y, 32, 32);
//		
		if (direction == 1) {
			g.drawImage(ss.getSprite(1, 2), x, y, null);
		}
	
		if (direction == 3) {
			g.drawImage(ss.getSprite(1, 4), x, y, null);
		}
	
		if (direction == 2) {
			g.drawImage(ss.getSprite(1, 1), x, y, null);
		}
	
		if (direction == 0) {
			g.drawImage(ss.getSprite(1, 3), x, y, null);
		}
		
	}
	
	private void collide() {
		for (int i = 0; i < om.object.size(); i++) {
			GameObject temp = om.object.get(i);
			if (temp.getId() == ID.Bullet) {
				if (this.getRectangle().intersects(temp.getRectangle())) {
					om.removeObject(temp);
					player.setScore(player.getScore() + 10);
					om.removeObject(this);
				}
			}
		}
		
	}
	
	private void findPlayer() {
		//Tracks player position
	
		int speed = 3;
		
		if (player.getHealth() <= 0) {
			setVelX(0);
			setVelY(0);
		}
		
		if (player.getHealth() >= 1) { 
		
			if (x <= player.getX()) {
				setVelX(speed);
				direction = 1;
			}else if (x >= player.getX()) {
				setVelX(-speed);
				direction = 3;
			}
		
			if (y <= player.getY()) {
				setVelY(speed);
				direction = 2;
			}else if (y >= player.getY()) {
				setVelY(-speed);
				direction = 0;
			}
			
			//Collision with player
			if (this.getRectangle().intersects(player.getRectangle())) {
				player.setHealth(player.getHealth() - 1);
			}
		
		}
	}
	
	private void checkSpawn() {
		if (this.getRectangle().intersects(player.getRectangle())) {
			resetCoords();
		}
		
		if (x >= (player.getX() + 128) && x <= (player.getX() - 128)) {
			resetCoords();
		}
		
		if (y >= (player.getY() + 128) && y <= (player.getY() - 128)) {
			resetCoords();
		}
		
	}
	
	private void resetCoords() {
		System.out.println("Just reset the coords");
		setX(random.nextInt(Game.GameWidth));
		setY(random.nextInt(Game.GameHeight));
	}
	
}
