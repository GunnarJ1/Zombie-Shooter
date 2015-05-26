package com.gunnar.smallgame.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gunnar.smallgame.Game;

public class HudObject {
	
	private BufferedImage icon;	
	private PlayerObject player;
	
	public HudObject(BufferedImage icon, PlayerObject player) {
		this.icon = icon;
		this.player = player;
	}
	
	public void render(Graphics g) {
		int hBarX = 25;
		int hBarY = 670-25;
		int sBarX = Game.GameWidth;
		int sBarY = 670-25;
		
		Font hFont = new Font("TimesRoman", Font.PLAIN, 20);
		Font sFont = new Font("TimesRoman", Font.BOLD, 20);
		
		g.drawImage(icon, 0, Game.GameHeight-100, null);
		
		g.setFont(hFont);
		
		g.setColor(Color.black);
		g.fillRect(hBarX, hBarY, 200, 25);
		g.setColor(Color.GREEN);
		g.fillRect(hBarX, hBarY, player.getHealth()*2, 25);
		g.setColor(Color.black);
		g.drawRect(hBarX, hBarY, 200, 25);
		
		g.drawString("HEALTH", hBarX, hBarY-5);
		
		
		g.setFont(sFont);
		g.setColor(Color.CYAN);
		g.drawString("SCORE: " + player.getScore(), sBarX-200, sBarY-2);
	}

}
