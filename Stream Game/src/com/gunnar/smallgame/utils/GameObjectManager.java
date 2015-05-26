package com.gunnar.smallgame.utils;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameObjectManager {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++){
			GameObject gameObject = object.get(i);
			
			gameObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++){
			GameObject gameObject = object.get(i);
			
			gameObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
