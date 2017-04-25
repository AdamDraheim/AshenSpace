package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.anim.Texture;
import ashenSpace.creature.Player;
import ashenSpace.game.Game;
import ashenSpace.game.HUD;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;
import ashenSpace.input.KeyInput;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.fileInfo;

public class Settings extends MouseAdapter{
	private Game game;
	private Handler handler;
	public static boolean sequence;
	Texture tex = Game.getInstance();
	Serialization ser = new Serialization();
	fileInfo file = null;
	
	private BufferedImage button = null;
	
	public Settings(Game game, Handler handler){
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
		
		if(game.gameState == STATE.settings){
			if(mouseOver(mx, my, 120, 400, 180, 30)){
				KeyInput.keySetting = 1;
				file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
						handler.area6, KeyInput.keySetting, sequence, Achievements.kills, Achievements.finalBoss,
						Achievements.danton, Achievements.dead, Charsel.character);
				ser.save(file);
			}else if(mouseOver(mx, my, 460, 400, 180, 32)){
				KeyInput.keySetting = 2;
				file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
						handler.area6, KeyInput.keySetting, sequence, Achievements.kills, Achievements.finalBoss,
						Achievements.danton, Achievements.dead, Charsel.character);
				ser.save(file);

			}else if(mouseOver(mx, my, 50, game.height - 100, 32, 32)){
				if(sequence == true){
					sequence = false;
					file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
							handler.area6, KeyInput.keySetting, sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, Charsel.character);
					ser.save(file);
				}else if(sequence == false){
					sequence = true;
					file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
							handler.area6, KeyInput.keySetting, sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, Charsel.character);
					ser.save(file);
				}

			}else if(mouseOver(mx, my, 25, 25, 150, 75)){
				game.gameState = STATE.menu;
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
		g.setFont(new Font("Arial", 0, 90));
		g.drawString("SETTINGS", 240, 100);
		
		if(KeyInput.keySetting == 1){
			g.setColor(Color.white);
			g.drawRect(110, 210, 200, 230);
		}else if(KeyInput.keySetting == 2){
			g.setColor(Color.white);
			g.drawRect(450, 210, 200, 230);

		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 40));
		g.drawString("Standard", 120, 200);
		g.drawString("Original", 470, 200);
		g.setFont(new Font("Arial", 1, 20));
		
		g.drawString("A/D- Move", 150, 230);
		g.drawString("Space- jump", 150, 260);
		g.drawString("Enter- shoot", 150, 290);
		g.drawString("S- shield", 150, 320);
		g.drawString("H- heal", 150, 350);
		g.drawString("R- reload", 150, 380);
	
		g.drawString("A/D- Move", 500, 230);
		g.drawString("W- jump", 500, 260);
		g.drawString("Space- shoot", 500, 290);
		g.drawString("S- shield", 500, 320);
		g.drawString("H- heal", 500, 350);
		g.drawString("R- reload", 500, 380);
		
		
		g.setColor(Color.red);
		g.drawRect(119, 399, 182, 32);
		g.drawRect(459, 399, 182, 32);
		
		g.setColor(Color.green);
		g.drawImage(button, 120, 400, 180, 30, null);
		g.drawImage(button, 460, 400, 180, 32, null);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 30));
		g.drawString("Select", 160, 425);
		g.drawString("Select", 510, 425);

		
		g.setColor(Color.red);
		g.drawRect(24, 24, 152, 77);
		
		g.setColor(Color.green);
		g.drawImage(button, 25, 25, 150, 75, null);
		
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 60));
		g.drawString("Back", 30, 85);
		
		g.setColor(Color.white);
		if(sequence == true){
			g.fillRect(50, game.height - 100, 32, 32);
		}else if(sequence == false){
			g.drawRect(50, game.height - 100, 32, 32);
		}
		g.setFont(new Font("Arial", 0, 32));
		g.drawString("Enable/Disable Opening Cinematic", 100, game.height - 73);
		
		
	}
}
