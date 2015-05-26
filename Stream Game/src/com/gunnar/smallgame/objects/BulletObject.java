package com.gunnar.smallgame.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.gunnar.smallgame.Game;
import com.gunnar.smallgame.utils.GameObject;
import com.gunnar.smallgame.utils.GameObjectManager;

public class BulletObject extends GameObject {
	
	private int x, y, direction;
	private GameObjectManager gom;
	
	public BulletObject(int x, int y, int direction, GameObjectManager gom) {
		super(x, y, ID.Bullet);
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.gom = gom;
	}
	
	public void tick() {
		
		//System.out.println( x+","+y );
		
		x += getVelx();
		y += getVely();
		
		setX(x);
		setY(y);
		
		if (direction == 1) {
			setVelY(-8);
		}
		
		if (direction == 2) {
			setVelX(8);
		}
		
		if (direction == 3) {
			setVelY(8);
		}
		
		if (direction == 4) {
			setVelX(-8);
		}
		
		if (isOutside())
			gom.removeObject(this);
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(x, y, 8, 8);
	}
	
	private boolean isOutside() {
		if (x >= Game.GameWidth) {
			return true;
		} else if (x <= (0-8)) {
			return true;
		} else if (y >= Game.GameHeight) {
			return true;
		} else if (y <= (0-8)) {
			return true;
		} else {
			return false;
		}
			
	}
	
}
