package com.gunnar.smallgame.utils;

import java.util.Random;

import com.gunnar.smallgame.Game;
import com.gunnar.smallgame.objects.BasicZombieObject;
import com.gunnar.smallgame.objects.PlayerObject;

public class Spawner {
	
	private GameObjectManager gom;
	Random random;
	

	private PlayerObject player;
	
	private int spawnTimer = 0;
	private int spawnTimerMax = 30;
	 
	private int startSpawnerTime = 0;
	private int startSpawnerTimerMax = 200;
	private SpriteSheet zombieSpriteSheet;
	
	public Spawner(GameObjectManager gom, SpriteSheet zombieSpriteSheet,PlayerObject player) {
		this.gom = gom;
		this.player = player;
		this.zombieSpriteSheet = zombieSpriteSheet;
		random = new Random();
	}
	
	public void tick() {

		if (spawnTimer >= spawnTimerMax) {
			spawnObject(new BasicZombieObject(random.nextInt(Game.GameWidth), random.nextInt(Game.GameHeight), zombieSpriteSheet, player, gom));
			spawnTimer = 0;
		}
		
		if (startSpawnerTime >= startSpawnerTimerMax) 
			spawnTimer++;
		
		if (startSpawnerTime <= startSpawnerTimerMax) 
			startSpawnerTime++;
	}
	
	
	public void spawnObject(GameObject object) {
		gom.addObject(object);
	}
	
}
