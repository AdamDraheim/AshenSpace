package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import ashenSpace.anim.Texture;
import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;
import ashenSpace.input.KeyInput;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.SerializationTut;
import ashenSpace.ser.fileInfo;
import ashenSpace.ser.fileInfoTut;
import ashenSpace.tutorial.tutHUD;
import ashenSpace.game.Handler;

public class Opening {

	int red = 0;
	int pause = 10;
	int step = 0;
	Game game;
	
	Texture tex = Game.getInstance();
	Serialization ser = new Serialization();
	SerializationTut sertut = new SerializationTut();

	Handler handler;
	public Opening(Game game, Handler handler){
		this.game = game;
		this.handler = handler;


	}
	
	
	public void tick(){
		
		if(red < 255 && step == 0){
			red++;
		}
		if(red == 255){
			step = 1;
		}
		
		if(step == 1 && red != 0){
			red--;
		}
		
		if(red == 0 && step == 1){
			pause--;
		}
		if(pause == 0){

			fileInfo loadFile = ser.load();
			fileInfoTut loadtut = sertut.load();
			try{
				handler.area1 = loadFile.area1;
				handler.area2 = loadFile.area2;
				handler.area3 = loadFile.area3;
				handler.area4 = loadFile.area4;
				handler.area5 = loadFile.area5;
				handler.area6 = loadFile.area6;
				KeyInput.keySetting = loadFile.keySetting;
				
			}catch(Exception e){
				e.printStackTrace();
				KeyInput.keySetting = 1;
			}
			
			try{
				Settings.sequence = loadFile.sequence;
				Achievements.kills = loadFile.kills;
				Achievements.finalBoss = loadFile.finalboss;
				Achievements.danton = loadFile.danton;
				Achievements.dead = loadFile.dead;
				Charsel.character = loadFile.character;
			}catch(Exception e){
				e.printStackTrace();
				Charsel.character = 0;
			}
			try{
				tutHUD.tutComplete = loadtut.isComplete();
			}catch(Exception e){
				e.printStackTrace();
			}


			game.gameState = STATE.menu;
			
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(red, red, red));
		g.fillRect(200, 100, 400, 400);
		g.setFont(new Font("Arial", 0, 30));
		g.drawString("LOW ORBIT GAMES", 250, 550);
		g.drawImage(tex.misc[10], 200, 100, 400, 400, null);
	}
	
}
