package com.gunnar.smallgame.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.gunnar.smallgame.utils.GameObject;

public class BulletObject extends GameObject {
	
	private int x, y, direction;
	
	public BulletObject(int x, int y, int direction) {
		super(x, y, ID.Bullet);
		this.x = x;
		this.y = y;
		this.direction = direction;
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
	}
	
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(x, y, 8, 8);
		
	}
	
	
	
}
