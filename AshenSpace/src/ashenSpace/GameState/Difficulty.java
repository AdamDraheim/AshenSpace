package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.anim.Texture;
import ashenSpace.creature.Player;
import ashenSpace.game.Game;
import ashenSpace.game.HUD;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;
import ashenSpace.input.KeyInput;
import ashenSpace.tutorial.tutHUD;

public class Difficulty extends MouseAdapter{
	private Game game;
	private Handler handler;
	Texture tex = Game.getInstance();
	public static int difficulty = 0;
	private BufferedImage button;
	public Difficulty(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			button = loader.loadImage("/tex/Buttons.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.difficulty){
			
			if(mouseOver(mx, my, 250, 240, 300, 80)){
				
				if(Settings.sequence == true){
					handler.clearLevel();
					game.gameState = STATE.sequence;
					difficulty = 0;
					Game.theme.play();
					game.OpenMusic.stop();

				}else if(Settings.sequence == false){
					game.gameState = STATE.game;
					handler.clearLevel();
					difficulty = 0;
					game.LEVEL = 1;
					HUD.health = 100;
					HUD.lives = 3;
					HUD.medkit = 5;
					HUD.mode = 0;
					KeyInput.shieldTimer = 0;
					handler.switchLevel();
					numberCounter.delay = 10;
					Game.theme.stop();
					Game.music.play();
					game.OpenMusic.stop();

				}
				
			}else if(mouseOver(mx, my, 250,  350,  300,  80)){
				if(Settings.sequence == true){
					handler.clearLevel();
					difficulty = 1;
					Game.theme.play();
					game.OpenMusic.stop();

					Game.gameState = STATE.sequence;
				}else if(Settings.sequence == false){
					game.gameState = STATE.game;
					handler.clearLevel();
					difficulty = 1;
					game.LEVEL = 1;
					HUD.health = 100;
					HUD.lives = 1;
					HUD.medkit = 5;
					HUD.mode = 1;
					KeyInput.shieldTimer = 0;
					handler.switchLevel();
					numberCounter.delay = 10;
					Game.theme.stop();
					Game.music.play();
					game.OpenMusic.stop();

					

				}
			}else if(mouseOver(mx, my, 250, 30, 300, 80)){
				game.gameState = STATE.menu;
			}else if(mouseOver(mx, my, 250, 130, 300, 80)){
				game.gameState = STATE.tutorial;
				handler.clearLevel();
				tutHUD.health = 100;
				tutHUD.lives = 5;
				tutHUD.medkit = 5;
				numberCounter.delay = 10;
				Game.theme.stop();
				Game.music.play();
				game.OpenMusic.stop();
				KeyInput.shieldTimer = 0;
				Game.LEVEL = -1;
				handler.tutLevel();

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
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 150));
		
		g.setColor(Color.red);
		g.fillRect(249,239,302,82);
		g.fillRect(249, 129, 302, 82);
		g.fillRect(249, 349, 302, 82);
		g.fillRect(249,  29, 302, 82);
		g.setColor(Color.green);
		g.drawImage(button,250, 240, 300, 80, null);
		g.drawImage(button,250,  350,  300,  80, null);
		g.drawImage(button,250, 30, 300, 80, null);
		g.drawImage(button,250, 130, 300, 80, null);

		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 65));
		g.drawString("Tutorial", 270, 195);
		g.drawString("Normal", 290, 300);
		g.drawString("Hardcore", 265, 415);
		g.drawString("Back", 320, 95);
		g.setFont(new Font("Arial", 1, 20));
		g.setColor(Color.white);
		g.drawString("Low Orbit Games", Game.width - 200, Game.height - 20);
		g.drawImage(tex.player[1], game.width / 2 - 25, game.height - 100, null);
	}

}
