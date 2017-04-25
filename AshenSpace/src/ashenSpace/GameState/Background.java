package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Texture;
import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.Handler;
import ashenSpace.game.Game.STATE;

public class Background {

	Game game;
	Handler handler;
	private BufferedImage bg1 = null, bg2 = null, bg3 = null, tut = null;
	
	public Background(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			bg1 = loader.loadImage("/background/Horizon.png");
			bg2 = loader.loadImage("/background/Panel.png");
			bg3 = loader.loadImage("/background/Cavernback.png");
			tut = loader.loadImage("/background/Tutorial.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
	}
	
	public void render(Graphics g){

		if(Game.gameState == STATE.game){
			if(game.LEVEL < 5 || (game.LEVEL >= 7 && game.LEVEL <= 11) || (game.LEVEL > 20 && game.LEVEL <= 26 || Game.LEVEL == 28 || Game.LEVEL == 35)){
				g.drawImage(bg1, 32, 0, (handler.mapSizeX * 32) - 64, (handler.mapSizeY * 32) - 64, null);
			}else if(game.LEVEL == 5 || game.LEVEL >= 14 && game.LEVEL <= 19){
				g.drawImage(bg3, 32, 32, (handler.mapSizeX * 32) - 64, (handler.mapSizeY * 32) - 64, null);
			}else{
				g.drawImage(bg2, 32, 32, (handler.mapSizeX * 32) - 64, (handler.mapSizeY * 32) - 64, null);
			}
		}else if(Game.gameState == STATE.tutorial){
			g.drawImage(tut, 32, 0, (handler.mapSizeX * 32) - 64, (handler.mapSizeY * 32) - 64, null);
		}
		
	}
	
}
