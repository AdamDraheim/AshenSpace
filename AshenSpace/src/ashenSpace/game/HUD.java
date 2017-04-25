package ashenSpace.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.creature.Arbol;
import ashenSpace.creature.Boss;
import ashenSpace.creature.Danton;
import ashenSpace.creature.missileBoss;
import ashenSpace.input.KeyInput;


public class HUD {

	public static String objective = "";
	public static String objectiveCont = "";
	public static String objectiveContFin = "";
	public static String danton = "??????";
	private String closeDialog = "Press 'n' to close";
	public static String dialog = "";
	public static String dialog2 = "";
	public static String dialog3 = "";
	public static int health = 100;
	public static int bullet = 10;
	public static int Reloadtimer = 100;
	public static int medkit = 5;
	public static boolean newString = false;
	public static int mode;
	private Animation shieldIcon, shieldLoading;
	public int shield;
	int green;
	public static int dialogTimer = 500;
	private int dialogDecrease = 1;
	public static int lives = 3;
	public static boolean followBossActive = false;
	public static int followBossHealth = 1000;
	public static boolean finalBossActive = false;
	public static int finalBossHealth = 5000;
	public static boolean firstBoss = false;
	Texture tex = Game.getInstance();
	
	BufferedImage healthBar = null, textScreen = null, objPanel = null;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public HUD(){
		shieldIcon = new Animation(10, tex.misc[4]);
		shieldLoading = new Animation(10, tex.misc[5]);
		try{
			healthBar = loader.loadImage("/tex/HealthBar.png");
			textScreen = loader.loadImage("/tex/TextScreen.png");
			objPanel = loader.loadImage("/tex/ObjPanel.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
				
		dialogTimer -= dialogDecrease;
		if(dialogTimer >= 0){
			dialogDecrease = 1;
		}else {
			dialogDecrease = 0;
		}
		
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
		
		if(firstBoss){
			
			g.setColor(Color.gray);
			g.fillRect(Game.width / 2 - 151, Game.height - 30, 302, 22);
			g.setColor(Color.white);
			g.drawRect(Game.width / 2 - 150, Game.height - 30, Boss.bossHealth, 20);
			g.setColor(Color.red);
			g.fillRect(Game.width / 2 - 150, Game.height - 30, Boss.bossHealth, 20);
			g.setFont(new Font("Arial", 1, 15));
			g.setColor(Color.BLACK);
			g.drawString("Vertex-1", Game.width / 2 - 30, Game.height - 15);
			g.drawImage(healthBar, 250, Game.height - 32, 310, 26, null);
			
		}
		
		if(Arbol.bossHealth > 0){
			
			g.setColor(Color.gray);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, 502, 22);
			g.setColor(Color.white);
			g.drawRect(Game.width / 2 - 251, Game.height - 30, Arbol.bossHealth, 20);
			g.setColor(Color.red);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, Arbol.bossHealth, 20);
			g.setFont(new Font("Arial", 1, 15));
			g.setColor(Color.BLACK);
			g.drawString("ArBo-1", Game.width / 2 - 30, Game.height - 15);
			g.drawImage(healthBar, 150, Game.height - 32, 515, 26, null);

			
		}
		
		if(missileBoss.bossHealth > 0){
			g.setColor(Color.gray);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, 502, 22);
			g.setColor(Color.white);
			g.drawRect(Game.width / 2 - 251, Game.height - 30, missileBoss.bossHealth, 20);
			g.setColor(Color.red);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, missileBoss.bossHealth, 20);
			g.setFont(new Font("Arial", 1, 15));
			g.setColor(Color.BLACK);
			g.drawString("Rogue", Game.width / 2 - 30, Game.height - 15);
			g.drawImage(healthBar, 150, Game.height - 32, 515, 26, null);

		}
		
		if(Danton.active){
			g.setColor(Color.gray);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, 502, 22);
			g.setColor(Color.white);
			g.drawRect(Game.width / 2 - 251, Game.height - 30, (int)(Danton.bossHealth * 0.25), 20);
			g.setColor(Color.red);
			g.fillRect(Game.width / 2 - 251, Game.height - 30, (int)(Danton.bossHealth *0.25), 20);
			g.setFont(new Font("Arial", 1, 15));
			g.setColor(Color.BLACK);
			g.drawString("Danton", Game.width / 2 - 30, Game.height - 15);
			g.drawImage(healthBar, 150, Game.height - 32, 515, 26, null);

		}
		if(followBossActive == true){
			
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 251, Game.height - 30, 502, 22);
				g.setColor(Color.white);
				g.drawRect(Game.width / 2 - 251, Game.height - 30, (int)(followBossHealth * 0.5), 20);
				g.setColor(Color.red);
				g.fillRect(Game.width / 2 - 251, Game.height - 30, (int)(followBossHealth *0.5), 20);
				g.setFont(new Font("Arial", 1, 15));
				g.setColor(Color.BLACK);
				g.drawString("Wasps", Game.width / 2 - 30, Game.height - 15);
				g.drawImage(healthBar, 150, Game.height - 32, 515, 26, null);

		}
		if(finalBossActive == true){
			if(finalBossHealth > 0){
				g.setColor(Color.gray);
				g.fillRect(Game.width / 2 - 251, Game.height - 30, 502, 22);
				g.setColor(Color.white);
				g.drawRect(Game.width / 2 - 251, Game.height - 30, (int)(finalBossHealth * 0.1), 20);
				g.setColor(Color.red);
				g.fillRect(Game.width / 2 - 251, Game.height - 30, (int)(finalBossHealth *0.1), 20);
				g.setFont(new Font("Arial", 1, 15));
				g.setColor(Color.BLACK);
				g.drawString("Primum Formam", Game.width / 2 - 30, Game.height - 15);
				g.drawImage(healthBar, 150, Game.height - 32, 515, 26, null);

			}
		}
		
		
		g.setColor(Color.gray);
		g.fillRect(9, 140, 64, 64);
		g.setColor(new Color(0, green, 0));
		g.fillRect(10, 10, health, 32);
		g.fillRect(10, 141, 62, shield);
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
			g.drawImage(textScreen, 120, 10, 600, 80, null);
		
		g.setColor(Color.white);
		g.drawString("OBJECTIVE", 25, 85);
		g.setFont(new Font("Arial", 1, 10));
		g.drawString(objective, 17, 100);
		g.drawString(objectiveCont, 17, 111);
		g.drawString(objectiveContFin, 17, 122);
		g.drawString("Lives: " + lives, 10, Game.height / 2 - 75);
		g.drawString("Medkits: " + medkit, 10, Game.height / 2 - 87);
		if(dialogTimer >= 0){
			g.drawImage(tex.misc[0], 135, 35, null);
			g.drawString(danton, 133, 27);
			g.drawString(dialog, 190, 33);
			g.drawString(dialog2, 190, 44);
			g.drawString(dialog3, 190, 55);
			g.drawString(closeDialog, 130, 80);
		}
			
		if(KeyInput.shieldTimer < 62){
			shieldLoading.drawAnimation(g, 8, 141, 64, 64);
		}else{
			shieldIcon.drawAnimation(g, 9, 141, 64, 64);
		}
		
	}
	
}
