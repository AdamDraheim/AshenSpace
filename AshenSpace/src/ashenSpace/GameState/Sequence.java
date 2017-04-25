package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Texture;
import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.input.KeyInput;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.HUD;
import ashenSpace.object.Explosion;
import ashenSpace.object.Pod;

public class Sequence {

	Game game;
	Handler handler;
	int filter = 255;
	int part = 0;
	double shipPosX = 100;
	double shipPosY = 100;
	int timer = 100;
	int filter2 = 0, filer3 = 0, filter4 = 0, filter5 = 0;
	
	Texture tex = Game.getInstance();

	private BufferedImage Space = null;
	private int filter3;
	
	public Sequence(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		BufferedImageLoader loader = new BufferedImageLoader();

		Space = loader.loadImage("/background/Space.png");
	}
	
	public void render(Graphics g){
		if(part == 0 || part == 1){
			g.setColor(Color.white);
			g.fillRect(200, 100, 400, 400);
			g.setFont(new Font("Arial", 0, 30));
			g.drawString("LOW ORBIT GAMES", 250, 550);
			g.drawImage(tex.misc[10], 200, 100, 400, 400, null);

		}
		
		if(part == 2 || part == 3 || part == 4  || part == 7 || part == 8 || part == 9){
			g.drawImage(Space, 0, 0, game.width, game.height, null);
			g.drawImage(tex.misc[11], (int)shipPosX, (int)shipPosY, null);
		}
		
		if(part == 5 || part == 6){
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 60));
			g.drawString("PRESENTS", 250, 300);
		}
		
		if(part == 8 || part == 9){
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 90));
			g.drawString("ASHEN SPACE", 75, 200);
		}
		
		
		g.setColor(new Color(0, 0, 0, filter));
		g.fillRect(0, 0, Game.width,  game.height);
		
		if(part == 9){
			g.setFont(new Font("KodchiangUPC", 0, 70));
			g.setColor(new Color(filter2, filter2, filter2));
			g.drawString("The Vault will be opened", 110, 150);
			g.setColor(new Color(filter3, filter3, filter3));
			g.drawString("The Rust will settle", 140, 250);
			g.setColor(new Color(filter4, filter4, filter4 ));
			g.drawString("The Moon will fall dark",170, 350);
			g.setColor(new Color(filter5, filter5, filter5));
			g.drawString("-Ashen Space", 200, 450);
		}
	}
	
	public void tick(){
				
		System.out.println(part);
		if(part == 0){
			filter--;
			shipPosX = 100;
			shipPosY = 100;
			if(filter == 0){
				part = 1;
			}
		}if(part == 1){
			filter++;
			if(filter == 255){
				part = 2;
			}
		}
		
		if(part == 2){
			filter--;
			shipPosX += 0.8f;
			shipPosY += 0.5f;
			if(filter == 0){
				part = 3;
			}
		}if(part == 3 && timer != 0){
			timer--;
			shipPosX += 0.8f;
			shipPosY += 0.5f;
		}
		if(part == 3 && timer == 0){
			
			for(int i = 0; i < 5; i++){
				handler.addObject(new Explosion((int)shipPosX, (int)shipPosY, null, handler, 200, 0, 0, "red", 5));
			}
			part = 4;

		}
		
		if(part == 4){
			filter++;
			
			if(filter % 40 == 0 && filter > 50){
				for(int i = 0; i < 5; i++){
					handler.addObject(new Explosion((int)shipPosX + (int)(Math.random() * 64), (int)shipPosY + (int)(Math.random() * 32), null, handler, 200, 0, 0, "red", 5));
				}
			}
			if(filter == 255){
				part = 5;
			}
		}
		
		if(part == 5){
			filter--;
			if(filter == 0){
				part = 6;
			}
		}
		if(part == 6){
			filter++;
			if(filter == 255){
				part = 7;
			}
		}
		if(part == 7){
			
			if(filter == 255){
				handler.addObject(new Pod((int)shipPosX + 48, (int)shipPosY + 40, ID.block, handler, timer, 1f, 0.6f));
			}
			
			filter -= 5;
			if(filter == 0){
				part = 8;
			}
		}
		
		if(part == 8){
			filter ++;
			if(filter == 255){
				part = 9;
				timer = 0;
				
			}
		}
		
		if(part == 9){
			timer += 3;
			
			if(timer >= 0 && timer < 255){
				filter2 = timer;
			}else if(timer >= 255 && timer < 510){
				filter3 = timer - 255;
			}else if(timer >= 510 && timer < 765){
				filter4 = timer - 510;
			}else if(timer >= 765 && timer < 1020){
				filter5 = timer - 765;
			}
			
			if(timer > 1020){
				part = 10;
			}
			
		}
		
		if(part == 10){
			if(Difficulty.difficulty == 0){
				game.gameState = STATE.game;
				game.LEVEL = 1;
				HUD.health = 100;
				HUD.lives = 3;
				HUD.medkit = 5;
				HUD.mode = 0;
				KeyInput.shieldTimer = 0;
				handler.switchLevel();
				numberCounter.delay = 10;
				part = 0;	
				game.theme.stop();
				game.music.play();

			}else if(Difficulty.difficulty == 1){
				game.gameState = STATE.game;
				game.LEVEL = 1;
				HUD.health = 100;
				HUD.lives = 1;
				HUD.medkit = 5;
				HUD.mode = 1;
				KeyInput.shieldTimer = 0;
				handler.switchLevel();
				numberCounter.delay = 10;
				part = 0;
				game.theme.stop();
				game.music.play();
			}
		}
		
	
	}
	
}
