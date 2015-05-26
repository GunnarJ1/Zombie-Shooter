package com.gunnar.smallgame.utils;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.gunnar.smallgame.objects.ID;

public abstract class GameObject {
	
	private int x, y;
	private int velx, vely;
	private ID id;
	
	private Rectangle rec;
	private int width, height;
	
	public GameObject(int x, int y, ID id) { 
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = 32;
		this.height = 32;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public ID getId() {
		return id;
	}
	
	public int getVelx() {
		return velx;
	}
	
	public int getVely() {
		return vely;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHieght() {
		return height;
	}
	
	public Rectangle getRectangle() {
		rec = new Rectangle(x, y, width, height);
		return rec;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVelX(int velx) {
		this.velx = velx;
	}
	
	public void setVelY(int velx) {
		this.vely = velx;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHieght(int h) {
		this.height = h;
	}
}
