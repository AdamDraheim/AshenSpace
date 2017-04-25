package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Texture;
import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;

public class Achievements extends MouseAdapter{

	private BufferedImage dantonA = null, finalA = null, deadA = null, unknown = null, button = null;
	
	public static boolean danton, finalBoss, dead;
	public static int kills;
	Game game;
	
	public Achievements(Game game){
		this.game = game;
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			
			dantonA = loader.loadImage("/cards/Danton.png");
			finalA = loader.loadImage("/cards/Final.png");
			deadA = loader.loadImage("/cards/Dead.png");
			unknown = loader.loadImage("/cards/Unknown.png");
			button = loader.loadImage("/tex/Buttons.png");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.achieve){
			if(mouseOver(mx, my, 24, 24, 152, 77)){
				game.gameState = STATE.menu;
			}
		}
	}
	
	public void tick(){
		
		
		if(kills == 500){
			dead = true;
		}
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 70));
		g.drawString("Achievements", 240, 100);
		
		g.setFont(new Font("Arial", 0, 20));
		if(finalBoss == true){
			g.drawImage(finalA, 150, 275, 96, 96, null);
			g.drawString("World-Saver", 140, 260);


		}else{
			g.drawImage(unknown, 150, 275, 96, 96, null);
			g.drawString("???????", 150, 260);

		}
		if(danton == true){
			g.drawImage(dantonA, 350, 275, 96, 96, null);
			g.drawString("Vault-Hunter", 340, 260);

		}else{
			g.drawImage(unknown, 350, 275, 96, 96, null);
			g.drawString("???????", 350, 260);


		}
		if(dead == true){
			g.drawImage(deadA, 550, 275, 96, 96, null);
			g.drawString("Droid-Killer", 540, 260);
		}else{
			g.drawImage(unknown, 550, 275, 96, 96, null);
			g.drawString("???????", 550, 260);

		}

		g.setColor(Color.red);
		g.drawRect(24, 24, 152, 77);
		
		g.setColor(Color.green);
		g.drawImage(button, 25, 25, 150, 75, null);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 60));
		g.drawString("Back", 30, 85);

	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	
}
