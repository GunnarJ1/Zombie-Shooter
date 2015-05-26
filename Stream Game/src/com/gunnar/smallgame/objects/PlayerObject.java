package com.gunnar.smallgame.objects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.gunnar.smallgame.utils.GameObject;
import com.gunnar.smallgame.utils.GameObjectManager;
import com.gunnar.smallgame.utils.InputHandler;
import com.gunnar.smallgame.utils.SpriteSheet;

public class PlayerObject extends GameObject{
	
	private int x, y;
	
	private InputHandler input;
	@SuppressWarnings("unused")
	private SpriteSheet ss;
	private GameObjectManager om;
	
	private BufferedImage iconUp;
	private BufferedImage iconRight;
	private BufferedImage iconDown;
	private BufferedImage iconLeft;
	
	private int playerState = 1;
	private int health = 100;
	
	private int shootTimer = 0;
	private int shootTimerMax = 7;
	private boolean canShoot = true;
	
	private boolean isImmortal = false;
	
	public PlayerObject(InputHandler inputHandler, int x, int y, SpriteSheet ss, GameObjectManager om) {
		super(x, y, ID.Player);
		this.x = x; 
		this.y = y;
		this.ss = ss;
		this.input = inputHandler;
		this.om = om;
		
		iconUp = ss.getSprite(1, 1);
		iconRight = ss.getSprite(1, 2);
		iconDown = ss.getSprite(1, 3);
		iconLeft = ss.getSprite(1, 4);
		
	}
	
	public void tick() {
		
		x += getVelx();
		y += getVely();
		
		setX(x);
		setY(y);
		
		if (isImmortal) {
			health = 100;
		}
		
		if (health >= 1)
			checkInput();
		
		if (health <= 0) {
			om.removeObject(this);
		}
		
		if (!canShoot) {
			shootTimer++;
			
			if (shootTimer >= shootTimerMax) {
				canShoot = true;
				shootTimer = 0;
			}
		}
		
		
	}
	
	
	public void render(Graphics g) {
		if (health >= 1) {
			if (playerState == 1) {
				g.drawImage(iconUp, x, y, null);
			} else if (playerState == 2) {
				g.drawImage(iconRight, x, y, null);
			} else if (playerState == 3) {
				g.drawImage(iconDown, x, y, null);
			} else if (playerState == 4) {
				g.drawImage(iconLeft, x, y, null);
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	
	private void checkInput() {
		
		int speed = 4;
		
		if (input.isKeyDown(KeyEvent.VK_UP)) {
			setVelY(-speed);
			playerState = 1;
		}
		
		if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			setVelY(speed);
			playerState = 3;
		}
		
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			setVelX(-speed);
			playerState = 4;
		}
		
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			setVelX(speed);
			playerState = 2;
		}
		
		if (!input.isKeyDown(KeyEvent.VK_RIGHT) && !input.isKeyDown(KeyEvent.VK_LEFT)) {
			setVelX(0);
		}
		
		if (!input.isKeyDown(KeyEvent.VK_UP) && !input.isKeyDown(KeyEvent.VK_DOWN)) {
			setVelY(0);
		}
		
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			if (canShoot && health >= 1) {
				om.addObject(new BulletObject(x+16, y+16, playerState));
				canShoot = false;
			}
		
		}
		
	}
	
}
