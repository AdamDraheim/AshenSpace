package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.anim.Texture;
import ashenSpace.creature.Player;
import ashenSpace.game.Game;
import ashenSpace.game.HUD;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;

public class MenuState extends MouseAdapter{
	private Game game;
	private Handler handler;
	Texture tex = Game.getInstance();
	
	private BufferedImage Trophy = null, button = null;
		
	public MenuState(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			Trophy = loader.loadImage("/cards/Trophy.png");
			button = loader.loadImage("/tex/Buttons.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.menu){
			
			if(mouseOver(mx, my, 100, 200, 300, 100)){
				game.gameState = STATE.difficulty;
			}else if(mouseOver(mx, my, 100, 350, 300, 100)){
				
				game.gameState = STATE.settings;
				
			}else if(mouseOver(mx, my, 450, 200, 300, 100)){
				
				Checkpoint.delay = 10;
				game.gameState = STATE.checkpoint;
				
			}else if(mouseOver(mx, my, 350, 550, 150, 50)){
				game.gameState = STATE.credits;
			}else if(mouseOver(mx, my, 715, Game.height - 74, 64, 64)){
				game.gameState = STATE.achieve;
			}else if(mouseOver(mx, my, 10, Game.height - 74, 64, 64)){
				Charsel.choice = Charsel.character;
				game.gameState = STATE.charsel;
			}else if(mouseOver(mx, my, 450, 350, 300, 100)){
				System.exit(1);
			}
			
		}
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
		HUD.finalBossActive = false;
		Player.charge = 0;
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 90));
		
		g.drawString("ASHEN SPACE", 95, 150);
		g.setColor(Color.red);
		g.fillRect(99,199,302,102);
		g.fillRect(449, 349, 302, 102);
		g.fillRect(99, 349, 302, 102);
		g.fillRect(449, 199, 302, 102);
		g.fillRect(349, 549, 152, 52);


		g.setColor(Color.green);
		g.drawImage(button, 100, 200, 300, 100, null);
		g.drawImage(button, 450, 350, 300, 100, null);
		g.drawImage(button, 100, 350, 300, 100, null);
		g.drawImage(button, 450, 200, 300, 100, null);
		g.drawImage(button, 350, 550, 150, 50, null);



		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 60));
		g.drawString("PLAY", 168, 270);
		g.drawString("SETTINGS", 100, 425);
		g.drawString("QUIT", 530, 425);
		g.drawString("LEVELS", 490, 270);
		g.setFont(new Font("Arial", 1, 20));
		g.setColor(Color.white);
		g.drawString("loworbitgames@gmail.com", Game.width - 260,  20);
		g.drawString("Version 1.0.0", 20, 20);
		g.drawImage(tex.player[1], game.width / 2, game.height - 100, null);
		
		g.setFont(new Font("Arial", 1, 40));
		g.setColor(Color.black);
		g.drawString("Credits", 353, 590);
		
		g.drawImage(Trophy, 715, Game.height - 74, 64, 64, null);
		
		g.drawImage(tex.misc[0], 10, Game.height - 74, 64, 64, null);
		
	}

}
