package com.gunnar.smallgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import com.gunnar.smallgame.objects.BasicZombieObject;
import com.gunnar.smallgame.objects.HudObject;
import com.gunnar.smallgame.objects.PlayerObject;
import com.gunnar.smallgame.utils.GameObjectManager;
import com.gunnar.smallgame.utils.InputHandler;
import com.gunnar.smallgame.utils.Spawner;
import com.gunnar.smallgame.utils.SpriteSheet;


public class Game {
	
	/**Made by Gunnar Jessee 5/17/15/ */
	
	//Fields
		public static final int GameWidth = 1080;
		public static final int GameHeight = 720;
		int fps = 30;
	
		JFrame frame;
		InputHandler inputHandler;
		GameObjectManager handler;
		Random random;
		
		Graphics g;
		Graphics g2;
	
	//Displays graphics on one single plane instead of all at once.
		BufferedImage i;
	
	//Sheets
		SpriteSheet playerSheet;
		SpriteSheet zombieSheet;
		SpriteSheet hudIcon;
		SpriteSheet backgroundIcon;
	//Entities
		PlayerObject player;
		BasicZombieObject zombie;
		Spawner spawn;
	//Hud
	HudObject hud;
	
	public Game() {
		run();
			//Shuts down the game thread at the end of the loop
		System.exit(-1);
	}
	
	public static void main(String[] args) {
		
		/*
		 * Running this class that controls most of the game
		 * and carries the game engine
		*/
		new Game();
	}
	
	void init() {
		//This is the main window
			frame = new JFrame("Gunnar's Small Game");
		
			//Size and location of the window
			frame.setSize(new Dimension(GameWidth, GameHeight));
			frame.setLocationRelativeTo(null);
		
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		
		//Sheets
			playerSheet = new SpriteSheet("assets/playerSpriteSheet", 32, 32);
			zombieSheet = new SpriteSheet("assets/zombieSpriteSheet", 32, 32);
			hudIcon = new SpriteSheet("assets/hud", 1080, 100);
			backgroundIcon = new SpriteSheet("assets/background", 1080, 620);
		
		//Objects
			random = new Random();
			handler = new GameObjectManager();
			inputHandler = new InputHandler(frame);
			player = new PlayerObject(inputHandler, 50, 50, playerSheet, handler);
			
			handler.addObject(player);
			for (int i = 0; i <= 5; i++)
				handler.addObject(new BasicZombieObject(random.nextInt(1080), random.nextInt(600-50), zombieSheet,player, handler));
			
				hud = new HudObject(hudIcon.getSprite(1, 1), player);
			spawn = new Spawner(handler, zombieSheet, player);
		
		//For all our graphics drawing needs :P
			i = new BufferedImage(GameWidth, GameHeight, BufferedImage.TYPE_INT_RGB);
			g = i.getGraphics();
			g2 = frame.getGraphics();
		
	}
	
	void run() {
		//Calls initial variables
			init();
		
		//Main loop for the game
				while (true) {
					long time = System.currentTimeMillis();
			
					tick();
					render();
					time = (1000/fps) - (System.currentTimeMillis() - time);
			
					if (time > 0) {
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			
		}
		
	}
	
	//Updates the logic of the game
	void tick() {
		handler.tick();
		spawn.tick();
		//Exits the game
			if (inputHandler.isKeyDown(KeyEvent.VK_ESCAPE)) {
				System.exit(-1);
			}
	}
	
	void render() {
		
		//Back ground to fix glitches
			g.drawImage(backgroundIcon.getSprite(1, 1), 0,0, null);
		
			handler.render(g);

			hud.render(g);
		
			//Draws the panel music
			g2.drawImage(i, 0,0, null);
	}
	
	
}
