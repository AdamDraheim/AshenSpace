package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;

public class Credits extends MouseAdapter{

	Game game;
	Handler handler;
	int x = 150;
	int y = 700;
	Texture tex = game.getInstance();
	public Credits(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void tick(){
		
		y -= 2;
		
		
		if(y < -2000){
			game.gameState = STATE.menu;
		}
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 50));
		g.drawString("LOW ORBIT GAMES", x, y);
		g.drawString("ASHEN SPACE", x, y + 101);
		g.drawString("Created by:", x, y + 201);
		g.drawString("Adam Draheim", x, y + 301);
		
		g.drawString("Advisor", x, y + 501);
		g.drawString("Mr. Jordan Podlucky", x, y + 601);

		g.drawString("Music Assistance", x, y + 801);
		g.drawString("Nicholas Jadidian", x, y + 901);
		
		g.drawString("Special Thanks to", x, y + 1101);
		g.drawString("Brandon Witkin", x, y + 1201);
		g.drawString("Bryce Covelli", x, y + 1301);
		g.drawString("John Kennedy", x, y + 1401);
		g.drawString("Matt Cuneo", x, y + 1501);

		g.setColor(Color.white);
		g.fillRect(x + 60, y + 1751, 400, 400);
		g.setFont(new Font("Arial", 0, 30));
		g.drawString("LOW ORBIT GAMES", x + 110, y + 2181);
		g.drawImage(tex.misc[10], x + 60, y + 1751, 400, 400, null);
		


		
	}
	
}
