package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.Game.STATE;
import ashenSpace.input.KeyInput;

public class Checkpoint extends MouseAdapter{

	Game game;
	Handler handler;
	private BufferedImage card1 = null, card2 = null, card3 = null, card4 = null, card5 = null, card6 = null, locked = null, button = null;
	public static int delay = 10;
	public Checkpoint(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			card1 = loader.loadImage("/cards/World1.png");
			card2 = loader.loadImage("/cards/World2.png");
			card3 = loader.loadImage("/cards/World3.png");
			card4 = loader.loadImage("/cards/World4.png");
			card5 = loader.loadImage("/cards/World5.png");
			card6 = loader.loadImage("/cards/World6.png");
			locked = loader.loadImage("/cards/Locked.png");
			button = loader.loadImage("/tex/Buttons.png");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
				
		if(game.gameState == STATE.checkpoint){
			if(delay < 0){
				System.out.println("Banana");

				if(mouseOver(mx, my, 25, 25, 150, 75)){
					game.gameState = STATE.menu;
					
				}else if(mouseOver(mx, my, 65, 175, 200, 150)){
					if(handler.area1 == true){
						game.gameState = STATE.game;
						game.LEVEL = 1;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						game.theme.stop();
						game.music.play();
						game.OpenMusic.stop();
					}
				}else if(mouseOver(mx, my, 315, 175, 200, 150)){
					if(handler.area2 == true){
						game.gameState = STATE.game;
						game.LEVEL = 6;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						HUD.danton = "Danton";
						game.OpenMusic.stop();
						game.theme.stop();
						game.music.play();
					}
				}else if(mouseOver(mx, my, 565, 175, 200, 150)){
					if(handler.area3 == true){
						game.gameState = STATE.game;
						game.LEVEL = 13;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						HUD.danton = "Danton";
						game.theme.stop();
						game.music.play();
						game.OpenMusic.stop();


					}
				}else if(mouseOver(mx, my, 65, 375, 200, 150)){
					if(handler.area4 == true){
						game.gameState = STATE.game;
						game.LEVEL = 20;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						HUD.danton = "Danton";
						game.theme.stop();
						game.music.play();
						game.OpenMusic.stop();


					}
				}else if(mouseOver(mx, my, 315, 375, 200, 150)){
					if(handler.area5 == true){
						game.gameState = STATE.game;
						game.LEVEL = 27;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						HUD.danton = "Danton";
						game.theme.stop();
						game.music.play();
						game.OpenMusic.stop();


					}
				}else if(mouseOver(mx, my, 565, 375, 200, 150)){
					if(handler.area6 == true){
						game.gameState = STATE.game;
						game.LEVEL = 34;
						HUD.health = 100;
						HUD.lives = 3;
						HUD.medkit = 5;
						HUD.mode = 0;
						KeyInput.shieldTimer = 0;
						handler.switchLevel();
						numberCounter.delay = 10;
						HUD.danton = "Danton";
						game.theme.stop();
						game.music.play();
						game.OpenMusic.stop();


					}
				}
			}
		}
	}

	public void tick(){
		delay--;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void render(Graphics g){
		
		
		g.setColor(Color.red);
		g.drawRect(24, 24, 152, 77);
		
		g.setColor(Color.green);
		g.drawImage(button, 25, 25, 150, 75, null);
		
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 60));
		g.drawString("Back", 30, 85);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 90));
		g.drawString("Checkpoint", 280, 100);	
		
		if(handler.area1 == false){
			g.drawImage(locked, 65, 175, null);
		}else if(handler.area1 == true){
			g.drawImage(card1, 65, 175, null);
		}
		
		if(handler.area2 == false){
			g.drawImage(locked, 315, 175, null);
		}else if(handler.area2 == true){
			g.drawImage(card2, 315, 175, null);
		}
		
		if(handler.area3 == false){
			g.drawImage(locked, 565, 175, null);
		}else if(handler.area3 == true){
			g.drawImage(card3, 565, 175, null);
		}
		if(handler.area4 == false){
			g.drawImage(locked, 65, 375, null);
		}else if(handler.area4 == true){
			g.drawImage(card4, 65, 375, null);
		}
		
		if(handler.area5 == false){
			g.drawImage(locked, 315, 375, null);
		}else if(handler.area5 == true){
			g.drawImage(card5, 315, 375, null);
		}
		
		if(handler.area6 == false){
			g.drawImage(locked, 565, 375, null);
		}else if(handler.area6 == true){
			g.drawImage(card6, 565, 375, null);
		}
		
	}
	
}
