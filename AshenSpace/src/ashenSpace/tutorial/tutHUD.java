package ashenSpace.tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.input.KeyInput;

public class tutHUD {

	Handler handler;
	
	public static String objective = "";
	public static String objectiveCont = "";
	public static String objectiveContFin = "";
	public static String ai = "??????";
	private String closeDialog = "Press 'n' to close";
	public static String dialog = "";
	public static String dialog2 = "";
	public static String dialog3 = "";
	public static String dialog4 = "";
	public static int health = 100;
	public static int bullet = 10;
	public static int Reloadtimer = 100;
	public static int medkit = 5;
	private Animation shieldIcon, shieldLoading;
	public int shield;
	int green;
	public static int dialogTimer = 500;
	private int dialogDecrease = 1;
	public static int lives = 3;
	public static boolean tutComplete = false;;
	
	public static boolean shieldOnline = false;
	public static boolean healthOnline = true;

	Texture tex = Game.getInstance();
	
	BufferedImage healthBar = null, textScreen = null, objPanel = null, ashenSpace = null;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public tutHUD(Handler handler){
		this.handler = handler;
		shieldIcon = new Animation(10, tex.misc[4]);
		shieldLoading = new Animation(10, tex.misc[5]);
		try{
			healthBar = loader.loadImage("/tex/HealthBar.png");
			textScreen = loader.loadImage("/tex/TextScreen.png");
			objPanel = loader.loadImage("/tex/ObjPanel.png");
			ashenSpace = loader.loadImage("/tex/AshenSpace.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
		
		dialogTimer = 100;
		
		health = (int) Game.clamp(health, 0, 100);
		green = health + 155;
		if(bullet == 0){
			Reloadtimer--;
			if(Reloadtimer == 0){
				bullet = 10;
				Reloadtimer = 100;
			}
			
		}
		
		shield = (int) Game.clamp(KeyInput.shieldTimer, 0, 64);
		shieldIcon.runAnimation();
		shieldLoading.runAnimation();
		
	}
	
	public void render(Graphics g){
		

		if(healthOnline){
			g.setColor(new Color(0, green, 0));
			g.fillRect(10, 10, health, 32);
			g.setColor(Color.white);
			g.drawRect(10, 10, health, 32);
			g.drawImage(healthBar, 8,8, 106, 38,null);
			
			Font fnt = new Font("Arial", 1, 13);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString(bullet + "/10 Bullets", 10, 55);
			
			g.setColor(Color.gray);
			g.fillRect(10, 70, 100, 55);
			g.drawImage(objPanel, 7, 67, null);
			if(dialogTimer >= 0)
				g.drawImage(textScreen, 120, 10, 600, 90, null);
			
			g.setColor(Color.white);
			g.drawString("OBJECTIVE", 25, 85);
			g.setFont(new Font("Arial", 1, 10));
			g.drawString(objective, 17, 100);
			g.drawString(objectiveCont, 17, 111);
			g.drawString(objectiveContFin, 17, 122);
			g.drawString("Lives: " + lives, 10, Game.height / 2 - 75);
			g.drawString("Medkits: " + medkit, 10, Game.height / 2 - 87);
			if(dialogTimer >= 0){
				g.drawImage(ashenSpace, 135, 35, null);
				g.drawString(ai, 133, 27);
				g.drawString(dialog, 190, 33);
				g.drawString(dialog2, 190, 44);
				g.drawString(dialog3, 190, 55);
				g.drawString(dialog4, 190, 66);
			}
		}
			
		if(shieldOnline){
			g.setColor(Color.gray);
			g.fillRect(9, 140, 64, 64);
			g.setColor(Color.green);
			g.fillRect(10, 141, 62, shield);
	
			if(KeyInput.shieldTimer < 62){
				shieldLoading.drawAnimation(g, 8, 141, 64, 64);
			}else{
				shieldIcon.drawAnimation(g, 9, 141, 64, 64);
			}
		}
	}
		
	
}
