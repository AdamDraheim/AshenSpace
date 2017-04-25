package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;

public class Loading {

	sound sound;
	Handler handler;
	public static int imageChoice = 0;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	BufferedImage[] images = new BufferedImage[3];
	
	public Loading(sound sound, Handler handler){
		this.sound  = sound;
		this.handler = handler;
		
		try{
			images[0] = loader.loadImage("/loading/Bossfight(2).png");
			images[1] = loader.loadImage("/loading/World1-1(2).png");
			images[2] = loader.loadImage("/loading/World6-3(2).png");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
		
		for(int i = 0; i < sound.clips.size(); i++){
			sound.clips.get(i).close();
		}
		Game.gameState = STATE.game;
		handler.switchLevel();
		
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 70));
		g.drawString("Loading...", 100, 100);
		
		g.drawImage(images[imageChoice], 200, 200, 400, 400, null);
		
	}
	
}
