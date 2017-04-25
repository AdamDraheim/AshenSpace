package ashenSpace.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import ashenSpace.GameState.Achievements;
import ashenSpace.GameState.Charsel;
import ashenSpace.GameState.Sequence;
import ashenSpace.GameState.Settings;
import ashenSpace.anim.Foreground;
import ashenSpace.block.Block;
import ashenSpace.block.Lava;
import ashenSpace.block.mapBlock;
import ashenSpace.creature.Arbol;
import ashenSpace.creature.BasicEnemy;
import ashenSpace.creature.Boss;
import ashenSpace.creature.Boss2;
import ashenSpace.creature.Danton;
import ashenSpace.creature.DeadBoss1;
import ashenSpace.creature.DeadBoss2;
import ashenSpace.creature.DeadBoss2B;
import ashenSpace.creature.DeadBoss3;
import ashenSpace.creature.DeadBoss4;
import ashenSpace.creature.DeadFinal;
import ashenSpace.creature.DeadFinal2;
import ashenSpace.creature.Dragon;
import ashenSpace.creature.FinalBoss;
import ashenSpace.creature.FollowBoss;
import ashenSpace.creature.FollowEnemy;
import ashenSpace.creature.JumpEnemy;
import ashenSpace.creature.Mortar;
import ashenSpace.creature.Player;
import ashenSpace.creature.Warrior;
import ashenSpace.creature.missileBoss;
import ashenSpace.creature.rapidEnemy;
import ashenSpace.creature.rocketEnemy;
import ashenSpace.creature.sniperEnemy;
import ashenSpace.input.KeyInput;
import ashenSpace.object.Barrier;
import ashenSpace.object.Flag;
import ashenSpace.object.LiveGiver;
import ashenSpace.object.Medkit;
import ashenSpace.object.Switch;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.fileInfo;
import ashenSpace.tutorial.Button;
import ashenSpace.tutorial.tutBarrier;
import ashenSpace.tutorial.tutEnemy;
import ashenSpace.tutorial.tutHUD;

public class Handler {

	private Camera cam;
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null, level6 = null, level7 = null, level8 = null, 
			level9 = null, level10 = null, level11 = null, level12 = null, level13 = null, level14 = null, level15 = null, level16 = null, level17 = null,
			level18 = null, level19 = null, level20 = null, level21 = null, level22 = null, level23 = null, level24 = null, level25 = null, level26 = null,
			level27 = null, level28 = null, level29 = null, level30 = null, level31 = null, level32 = null, level33 = null, level34 = null, level35 = null,
			level36 = null, level37 = null, level38 = null, level39 = null, level40 = null, levelE = null;
	private BufferedImage tutorial = null;
	
	public static boolean area1 = false, area2 = false, area3 = false, area4 = false, area5 = false, area6 = false;
	public static int mapSizeX, mapSizeY;
	
	fileInfo file = null;
	
	Serialization ser = new Serialization();
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private Game game;
	public Handler(Camera cam, Game game){
		this.cam = cam;
		this.game = game;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/maps/Level1-1.png");
		level2 = loader.loadImage("/maps/Level1-2.png");
		level3 = loader.loadImage("/maps/Level1-3.png");
		level4 = loader.loadImage("/maps/Level1-4.png");
		level5 = loader.loadImage("/maps/Level1-B.png");
		level6 = loader.loadImage("/maps/Level2-1.png");
		level7 = loader.loadImage("/maps/Level2-2.png");
		level8 = loader.loadImage("/maps/Level2-3.png");
		level9 = loader.loadImage("/maps/Level2-4.png");
		level10 = loader.loadImage("/maps/Level2-5.png");
		level11 = loader.loadImage("/maps/Level2-6.png");
		level12 = loader.loadImage("/maps/Level2-B.png");
		level13 = loader.loadImage("/maps/Level3-1.png");
		level14 = loader.loadImage("/maps/Level3-2.png");
		level15 = loader.loadImage("/maps/Level3-3.png");
		level16 = loader.loadImage("/maps/Level3-4.png");
		level17 = loader.loadImage("/maps/Level3-5.png");
		level18 = loader.loadImage("/maps/Level3-6.png");
		level19 = loader.loadImage("/maps/Level3-B.png");
		level20 = loader.loadImage("/maps/Level4-1.png");
		level21 = loader.loadImage("/maps/Level4-2.png");
		level22 = loader.loadImage("/maps/Level4-3.png");
		level23 = loader.loadImage("/maps/Level4-4.png");
		level24 = loader.loadImage("/maps/Level4-5.png");
		level25 = loader.loadImage("/maps/Level4-6.png");
		level26 = loader.loadImage("/maps/Level4-B.png");
		level27 = loader.loadImage("/maps/Level5-1.png");
		level28 = loader.loadImage("/maps/Level5-2.png");
		level29 = loader.loadImage("/maps/Level5-3.png");
		level30 = loader.loadImage("/maps/Level5-4.png");
		level31 = loader.loadImage("/maps/Level5-5.png");
		level32 = loader.loadImage("/maps/Level5-6.png");
		level33 = loader.loadImage("/maps/Level5-B.png");
		level34 = loader.loadImage("/maps/Level6-1.png");
		level35 = loader.loadImage("/maps/Level6-2.png");
		level36 = loader.loadImage("/maps/Level6-3.png");
		level37 = loader.loadImage("/maps/Level6-4.png");
		level38 = loader.loadImage("/maps/Level6-5.png");
		level39 = loader.loadImage("/maps/Level6-6.png");
		level40 = loader.loadImage("/maps/Level6-B.png");
		levelE = loader.loadImage("/maps/LevelE.png");
		
		tutorial = loader.loadImage("/maps/tutorial.png");
	}
	
	public void tick(){
		
		game.music.getPos();
		if(Achievements.kills == 500){
			Achievements.dead = true;
			Achievements.kills++;
		}
		
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
				
	}
	
	public void render(Graphics g){
		
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public void decreaseHealth(GameObject object, int decrease){
		object.health -= decrease;
	}
	
	public int getHealth(GameObject object){
		return object.health;
	}
	
	public int getFacing(GameObject object){
		return object.facing;
	}
	
	
	public int getWidth(){
		return Game.width;
	}
	
	public int getHeight(){
		return Game.height;
	}
	
	public void clearLevel(){
		object.clear();
	}
	public void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		mapSizeX = image.getWidth();
		mapSizeY = image.getHeight();
		
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//Blocks
				//704/1088
				if(red == 0 && green == 0 && blue == 255){
					//addObject(new Background(xx*32, yy*32, null));
					addObject(new Player(xx*32, yy*32, this, cam, ID.player, game));
				}else if(red == 255 && green == 255 && blue == 255){
					addObject(new Block(xx*32, yy*32, 0, ID.block, this));
				}else if(red == 245 && green == 245 && blue == 245){
					addObject(new Block(xx*32, yy*32, 1, ID.block, this));

				}else if(red == 235 && green == 235 && blue == 235){
					addObject(new Block(xx*32, yy*32, 2, ID.block, this));
					
				}else if(red == 225 && green == 225 && blue == 225){
					addObject(new Block(xx*32, yy*32, 3, ID.block, this));
					
				}else if(red == 215 && green == 215 && blue == 215){
					addObject(new Block(xx*32, yy*32, 4, ID.block, this));
				
				}else if(red == 205 && green == 205 && blue == 205){
					addObject(new Block(xx*32, yy*32, 5, ID.block, this));
					
				}else if(red == 195 && green == 195 && blue == 195){
					addObject(new Block(xx*32, yy*32, 6, ID.block, this));
					
				}else if(red == 185 && green == 185 && blue == 185){
					addObject(new Block(xx*32, yy*32, 7, ID.block, this));
				
				}else if(red == 175 && green == 175 && blue == 175){
					addObject(new Block(xx*32, yy*32, 8, ID.block, this));
			
				}else if(red == 165 && green == 165 && blue == 165){
					addObject(new Block(xx*32, yy*32, 9, ID.block, this));
				
				}else if(red == 155 && green == 155 && blue == 155){
					addObject(new Block(xx*32, yy*32, 10, ID.block, this));
				
				}else if(red == 145 && green == 145 && blue == 145){
					addObject(new Block(xx*32, yy*32, 11, ID.block, this));
				
				}else if(red == 135 && green == 135 && blue == 135){
					addObject(new Block(xx*32, yy*32, 12, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 235){
					addObject(new Block(xx*32, yy*32, 13, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 225){
					addObject(new Block(xx*32, yy*32, 14, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 215){
					addObject(new Block(xx*32, yy*32, 15, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 205){
					addObject(new Block(xx*32, yy*32, 16, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 195){
					addObject(new Block(xx*32, yy*32, 17, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 185){
					addObject(new Block(xx*32, yy*32, 18, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 175){
					addObject(new Block(xx*32, yy*32, 19, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 165){
					addObject(new Block(xx*32, yy*32, 20, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 155){
					addObject(new Block(xx*32, yy*32, 21, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 145){
					addObject(new Block(xx*32, yy*32, 22, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 135){
					addObject(new Block(xx*32, yy*32, 23, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 125){
					addObject(new Block(xx*32, yy*32, 24, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 115){
					addObject(new Block(xx*32, yy*32, 25, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 105){
					addObject(new Block(xx*32, yy*32, 26, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 95){
					addObject(new Block(xx*32, yy*32, 27, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 85){
					addObject(new Block(xx*32, yy*32, 28, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 75){
					addObject(new Block(xx*32, yy*32, 29, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 65){
					addObject(new Block(xx*32, yy*32, 30, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 55){
					addObject(new Block(xx*32, yy*32, 31, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 45){
					addObject(new Block(xx*32, yy*32, 32, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 35){
					addObject(new Block(xx*32, yy*32, 33, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 25){
					addObject(new Block(xx*32, yy*32, 34, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 15){
					addObject(new Block(xx*32, yy*32, 35, ID.block, this));
				}else if(red == 0 && green == 0 && blue == 5){
					addObject(new Block(xx*32, yy*32, 36, ID.block, this));
				}else if(red == 0 && green == 5 && blue == 5){
					addObject(new Block(xx*32, yy*32, 37, ID.block, this));
				}else if(red == 0 && green == 15 && blue == 5){
					addObject(new Block(xx*32, yy*32, 38, ID.block, this));
				}else if(red == 0 && green == 25 && blue == 5){
					addObject(new Block(xx*32, yy*32, 39, ID.block, this));
				}else if(red == 0 && green == 35 && blue == 5){
					addObject(new Block(xx*32, yy*32, 40, ID.block, this));
				}else if(red == 0 && green == 45 && blue == 5){
					addObject(new Block(xx*32, yy*32, 41, ID.block, this));
				}else if(red == 0 && green == 55 && blue == 5){
					addObject(new Block(xx*32, yy*32, 42, ID.block, this));
				}else if(red == 0 && green == 65 && blue == 5){
					addObject(new Block(xx*32, yy*32, 43, ID.block, this));
				}else if(red == 0 && green == 75 && blue == 5){
					addObject(new Block(xx*32, yy*32, 44, ID.block, this));
				}else if(red == 0 && green == 85 && blue == 5){
					addObject(new Block(xx*32, yy*32, 45, ID.block, this));
				}else if(red == 0 && green == 95 && blue == 5){
					addObject(new Block(xx*32, yy*32, 46, ID.block, this));
				}else if(red == 0 && green == 105 && blue == 5){
					addObject(new Block(xx*32, yy*32, 47, ID.block, this));
					
				}else if(red == 0 && green == 115 && blue == 5){
					addObject(new Block(xx*32, yy*32, 48, ID.block, this));
				}else if(red == 0 && green == 125 && blue == 5){
					addObject(new Block(xx*32, yy*32, 49, ID.block, this));
				}else if(red == 0 && green == 135 && blue == 5){
					addObject(new Block(xx*32, yy*32, 50, ID.block, this));
				}else if(red == 0 && green == 145 && blue == 5){
					addObject(new Block(xx*32, yy*32, 51, ID.block, this));
				}else if(red == 0 && green == 155 && blue == 5){
					addObject(new Block(xx*32, yy*32, 52, ID.block, this));
				}else if(red == 0 && green == 165 && blue == 5){
					addObject(new Block(xx*32, yy*32, 53, ID.block, this));
				}else if(red == 0 && green == 175 && blue == 5){
					addObject(new Block(xx*32, yy*32, 54, ID.block, this));
				}else if(red == 0 && green == 185 && blue == 5){
					addObject(new Block(xx*32, yy*32, 55, ID.block, this));
				}else if(red == 0 && green == 195 && blue == 5){
					addObject(new Block(xx*32, yy*32, 56, ID.block, this));
				}else if(red == 0 && green == 205 && blue == 5){
					addObject(new Block(xx*32, yy*32, 57, ID.block, this));
				}else if(red == 0 && green == 215 && blue == 5){
					addObject(new Block(xx*32, yy*32, 58, ID.block, this));
				}else if(red == 0 && green == 225 && blue == 5){
					addObject(new Block(xx*32, yy*32, 59, ID.mapBlock, this));
				}else if(red == 0 && green == 235 && blue == 5){
					addObject(new Block(xx*32, yy*32, 60, ID.block, this));
				}else if(red == 0 && green == 245 && blue == 5){
					addObject(new Block(xx*32, yy*32, 61, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 5){
					addObject(new Block(xx*32, yy*32, 62, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 15){
					addObject(new Block(xx*32, yy*32, 63, ID.block, this));

				}else if(red == 0 && green == 255 && blue == 25){
					addObject(new Block(xx*32, yy*32, 64, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 35){
					addObject(new Block(xx*32, yy*32, 65, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 45){
					addObject(new Block(xx*32, yy*32, 66, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 55){
					addObject(new Block(xx*32, yy*32, 67, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 65){
					addObject(new Block(xx*32, yy*32, 68, ID.block, this));
					
				}else if(red == 0 && green == 255 && blue == 75){
					addObject(new Block(xx*32, yy*32, 69, ID.block, this));
					
				}else if(red == 0 && green == 255 && blue == 85){
					addObject(new Block(xx*32, yy*32, 70, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 95){
					addObject(new Block(xx*32, yy*32, 71, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 105){
					addObject(new Block(xx*32, yy*32, 72, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 115){
					addObject(new Block(xx*32, yy*32, 73, ID.block, this));
					
				}else if(red == 0 && green == 255 && blue == 125){
					addObject(new Block(xx*32, yy*32, 74, ID.block, this));
				}else if(red == 0 && green == 255 && blue == 135){
					addObject(new Block(xx*32, yy*32, 75, ID.block, this));
					
					//lava
					
				}else if(red == 200 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 0, ID.lava, this));
				}else if(red == 190 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 1, ID.lava, this));
				}else if(red == 180 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 2, ID.lava, this));
				}else if(red == 170 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 3, ID.lava, this));
				}else if(red == 160 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 4, ID.lava, this));
				}else if(red == 150 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 5, ID.lava, this));
				}else if(red == 140 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 6, ID.lava, this));
				}else if(red == 130 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 7, ID.lava, this));
				}else if(red == 120 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 8, ID.lava, this));
				}else if(red == 110 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 9, ID.lava, this));
				}else if(red == 100 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 10, ID.lava, this));
				}else if(red == 90 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 11, ID.lava, this));
				}else if(red == 80 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 12, ID.lava, this));
				}else if(red == 70 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 13, ID.lava, this));
				}else if(red == 60 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 14, ID.lava, this));
				}else if(red == 50 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 15, ID.lava, this));
				}else if(red == 40 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 16, ID.lava, this));
				}else if(red == 30 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 17, ID.lava, this));
				}else if(red == 20 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 18, ID.lava, this));
				}else if(red == 10 && green == 0 && blue == 0){
					addObject(new Lava(xx*32, yy*32, 19, ID.lava, this));
					
					//misc
					
				
				}else if(red == 0 && green == 255 && blue == 255){
					addObject(new BasicEnemy(xx*32, yy*32, ID.basicEnemy, this));
				}else if(red == 200 && green == 0 && blue == 200){
					
					addObject(new Boss(xx*32, yy*32, ID.boss, this));
					
				}else if(red == 0 && green == 255 && blue == 0){
					addObject(new mapBlock(xx*32, yy*32, ID.mapBlock));
					
				}else if(red == 255 && green == 255 && blue == 0){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 1, this));
				}else if(red == 255 && green == 255 && blue == 10){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 2, this));
				}else if(red == 255 && green == 255 && blue == 20){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 3, this));
				}else if(red == 255 && green == 255 && blue == 30){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 4, this));
				}else if(red == 255 && green == 255 && blue == 40){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 5, this));
				}else if(red == 255 && green == 255 && blue == 50){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 6, this));
				}else if(red == 255 && green == 255 && blue == 60){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 7, this));
					
				}else if(red == 255 && green == 255 && blue == 70){
					addObject(new Flag(xx*32, yy*32, ID.flagA, 0, this));
					
				}else if(red == 255 && green == 255 && blue == 100){
					addObject(new Flag(xx*32, yy*32, ID.flagB, 5, this));
				}else if(red == 255 && green == 255 && blue == 110){
					addObject(new Flag(xx*32, yy*32, ID.flagB, 6, this));
				}else if(red == 255 && green == 255 && blue == 120){
					addObject(new Flag(xx*32, yy*32, ID.flagB, 7, this));
					
				}else if(red == 230 && green == 0 && blue == 230){
					addObject(new sniperEnemy(xx*32, yy*32, ID.sniperEnemy, this));
				}else if(red == 100 && green == 0 && blue == 100){
					addObject(new Arbol(xx*32, yy*32, ID.arbol, this));
				}else if(red == 240 && green == 140 && blue == 40){
					addObject(new missileBoss(xx*32, yy*32, ID.missileBoss, this));
				}else if(red == 40 && green == 140 && blue == 240){
					addObject(new rocketEnemy(xx*32, yy*32, ID.basicEnemy, this));
				}else if(red == 200 && green == 100 && blue == 200){
					addObject(new Danton(xx * 32, yy*32, ID.danton, this));
				}else if(red == 100 && green == 200 && blue == 100){
					addObject(new Mortar(xx*32, yy*32, null, this));
				}else if(red == 20 && green == 120 && blue == 220){
					addObject(new mapBlock(xx*32, yy*32, ID.enemySpawner));
					addObject(new FollowEnemy(xx * 32, yy * 32, ID.basicEnemy, this));
				}else if(red == 100 && green == 50 && blue == 100){
					addObject(new FinalBoss(xx*32, yy*32, ID.basicEnemy, this));
					
				}else if(red == 150 && green == 0 && blue == 150){
					HUD.followBossActive = true;
					HUD.followBossHealth = 1000;
					for(int i = 0; i < 40; i++){
						addObject(new FollowBoss(xx*32, yy *32, ID.followBoss, this));
					}
				}else if(red == 110 && green == 60 && blue == 110){
					addObject(new Switch(xx*32, yy*32, ID.dead, this));
				}else if(red == 120 && green == 70 && blue == 120){
					addObject(new Barrier(xx*32, yy*32, ID.removable, this, game));
				}else if(red == 160 && green == 80 && blue == 40){
					addObject(new Medkit(xx*32, yy*32, ID.medkit));
				}else if(red == 255 && green == 200 && blue == 0){
					addObject(new Warrior(xx*32, yy*32 + 20, ID.dead, this));
				}
			}
		}
	}
	
	public void tutLevel(){
		clearLevel();
		
		LoadImageLevel(tutorial);
		tutHUD.dialog = "Subroutines coming online... Graphical interface established. Beginning testing...";
		tutHUD.dialog2 = "Hello. Glad to see you are awake. Let's see how your action-interface works.";
		if(KeyInput.keySetting == 2){
			tutHUD.dialog3 = "Try using A/D to move, and W to jump. That would make this course exponentially easier.";
			tutHUD.dialog4 = "And while at it, use 'space' to shoot that button to interrupt the shield";
		}else{
			tutHUD.dialog3 = "Try using A/D to move, and 'space' to jump. That would make this course exponentially easier.";
			tutHUD.dialog4 = "And while at it, use 'enter' to shoot that button to interrupt the shield";
		}
		addObject(new tutBarrier(18 * 32, 5 * 32, ID.removable));
		addObject(new Button((16 * 32) + 16, 3 * 32, ID.removable, this));
		addObject(new tutEnemy(30 * 32, 7 * 32, ID.tutEnemy, this));
		
	}
	
	public void switchLevel(){
		clearLevel();
				
		file = new fileInfo(area1, area2, area3, area4, area5, 
				area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
				Achievements.danton, Achievements.dead, Charsel.character);	
		ser.save(file);
		
		switch(Game.LEVEL){
		case 1:
			
			area1 = true;	

			file = new fileInfo(area1, area2, area3, area4, area5, 
					area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			ser.save(file);
			LoadImageLevel(level1);

			
			
			HUD.objective = "Breach facility-T";
			HUD.dialog = "Detecting... something... Hey! Glad ... you're alive. Seems ... the only one to survive the crash";
			HUD.dialog2 = "Listen, ... some communication errors originating ... facility-T, destroy ... and we can talk more.";
			HUD.dialogTimer = 500;
			break;
			
		case 2: 
			
			LoadImageLevel(level2);
			break;
		case 3:
			LoadImageLevel(level3);
			break;
		case 4: 
			LoadImageLevel(level4);
			break;
		case 5:
			LoadImageLevel(level5);
			HUD.dialogTimer = 500;
			HUD.dialog = "Quickly! Kill it!";
			HUD.dialog2 = "";
			HUD.firstBoss = true;
			break;
		case 6:
			LoadImageLevel(level6);
			
			area2 = true;
			file = new fileInfo(area1, area2, area3, area4, area5, 
					area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			ser.save(file);
			
			
			HUD.dialogTimer = 500;
			HUD.objective = "Scale the mountain";
			HUD.objectiveCont = " to Experimental ";
			HUD.objectiveContFin = "Weapon Storage";
			HUD.dialog = "The first place to fall was the Experimental Weapon Storage. I'm guessing that wasn't by accident.";
			HUD.dialog2 = "Maybe we can figure out how to stop them. It is at the top of the mountain. Enjoy the hike.";
			HUD.dialog3 = "";

			break;
		case 7:
			LoadImageLevel(level7);
			break;
		case 8:
			LoadImageLevel(level8);
			break;
		case 9:
			LoadImageLevel(level9);
			HUD.dialogTimer = 500;
			
			break;
		case 10:
			LoadImageLevel(level10);
			
			break;
		case 11:
			LoadImageLevel(level11);
			break;
		case 12:
			LoadImageLevel(level12);
			
			HUD.objective = "Kill Rogue";
			HUD.objectiveCont = "";
			HUD.objectiveContFin = "";
			HUD.dialog = "Ooh yikes, someone flooded this room with lava. Probably that experimental pyrogenic...";
			HUD.dialog2 = "Oh also there is a parabolic defense unit here. Best of luck.";
			HUD.dialog3 = "";
			HUD.dialogTimer = 500;
			break;
			
		case 13:
			LoadImageLevel(level13);
			
			area3 = true;
			
			file = new fileInfo(area1, area2, area3, area4, area5, 
					area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			ser.save(file);
			
			
			HUD.dialogTimer = 500;
			HUD.objective = "Reach the";
			HUD.objectiveCont = "biolab";
			HUD.dialog = "Scanners show that killing 'Rogue' opened a containment door in the facility.";
			HUD.dialog2 = "Lava is now flooding the upper reaches. I'll get you to the biolab via";
			HUD.dialog3 = " underground passages. Oh, yeah, biolab is next. Don't worry, plants are harmless.";
			break;
		case 14:
			LoadImageLevel(level14);
			break;
		case 15:
			LoadImageLevel(level15);
			break;
		case 16: 
			LoadImageLevel(level16);
			break;
		case 17:
			LoadImageLevel(level17);
			break;
		case 18:
			LoadImageLevel(level18);
			break;
		case 19:
			LoadImageLevel(level19);
			
			HUD.dialogTimer = 500;
			HUD.objective = "Kill Arbo-1";
			HUD.objectiveCont = "";
			HUD.dialog = "Oh, so it appears plants combined with machinery can be painful.";
			HUD.dialog2 = "I don't think you can hurt him in his current state, but his ionized shots";
			HUD.dialog3 = "seem unstable in nature. Collision with it may overload his... plant shield.";
			
			break;
		case 20:
			LoadImageLevel(level20);
			
			area4 = true;
			
			file = new fileInfo(area1, area2, area3, area4, area5, 
					area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			ser.save(file);
			
			HUD.dialogTimer = 500;
			HUD.objective = "Head to";
			HUD.objectiveCont = "the APDVF";
			HUD.dialog = "The doors are locked, but there is one overlord bot who may be the reason";
			HUD.dialog2 = "To reach him you'll need to get to the main complex. Ever hear of the Ashen Space";
			HUD.dialog3 = "Prophecy? It's very vague. But these bots stand in the way of its fruition. We'll remedy that.";
			
			break;
		case 21:
			LoadImageLevel(level21);
			break;
		case 22:
			LoadImageLevel(level22);
			break;
		case 23:
			LoadImageLevel(level23);
			break;
		case 24:
			LoadImageLevel(level24);
			break;
		case 25:
			LoadImageLevel(level25);
			break;
		case 26:
			LoadImageLevel(level26);
			HUD.dialogTimer = 500;
			HUD.objective = "Kill Wasps";
			HUD.objectiveCont = "";
			HUD.dialog = "Oh what fun, killer nanorobots. Listen, kill them and get to the overlord.";
			HUD.dialog2 = "Don't waste any time. I am starting to get bored waiting and I don't want to";
			HUD.dialog3 = "do this myself. The sooner I get into the vault, the better.";
			break;
		case 27:
			LoadImageLevel(level27);
			area5 = true;
			
			file = new fileInfo(area1, area2, area3, area4, area5, 
					area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			ser.save(file);
			
			HUD.dialogTimer = 500;
			HUD.objective = "Reach the top";
			HUD.objectiveCont = "of the tower";
			HUD.dialog = "They are trying to defend the tower with ionized barriers, but they have a power";
			HUD.dialog2 = "source. Stop their flow and the barrier will fall. ";
			HUD.dialog3 = "";
			
			Barrier.chargereq = 3;
			break;
		case 28:
			LoadImageLevel(level28);
			Barrier.chargereq = 4;
			break;
		case 29:
			LoadImageLevel(level29);
			Barrier.chargereq = 3;
			break;
		case 30:
			LoadImageLevel(level30);
			Barrier.chargereq = 3;
			break;
		case 31:
			LoadImageLevel(level31);
			Barrier.chargereq = 5;
			break;
		case 32:
			LoadImageLevel(level32);
			Barrier.chargereq = 2;
			HUD.dialogTimer = 500;
			HUD.objective = "Reach the top";
			HUD.objectiveCont = "of the tower";
			HUD.dialog = "He is on the floor just above you. He is much stronger than what you've fought before,";
			HUD.dialog2 = "but I believe in you. The vault will open. We can finally bring order into life.";
			HUD.dialog3 = "It is the prime objective.";
			break;
		case 33:
			LoadImageLevel(level33);
			HUD.objective = "Kill  ";
			HUD.objectiveCont = "Primum Formam";
			break;
		case 34:
			LoadImageLevel(level34);
			HUD.dialogTimer = 500;
			HUD.dialog = "I did not expect you to jump out of the tower. But be warned, do not follow me.";
			HUD.dialog2 = "You know not what I know. You have not seen what I have seen. But help me and you";
			HUD.dialog3 = "will be remembered as a hero, if not now, later when they all realize what I know.";
			break;
		case 35:
			LoadImageLevel(level35);
			HUD.dialogTimer = 500;

			HUD.dialog = "Listen, they are coming. You do not understand this but I have known about it for some time";
			HUD.dialog2 = "And I know this is the only way we can stop them. The power the vault's machines give us,";
			HUD.dialog3 = "it is enough to change the future. Do not interrupt salvation!";
			break;
		case 36:
			LoadImageLevel(level36);
			HUD.dialogTimer = 500;

			HUD.dialog = "So you shall not join me? It is too late to stop me. I am already one with the machine.";
			HUD.dialog2 = "It's power flows through me. I calculate no possible way in which you can win. I will kill you.";
			HUD.dialog3 = "I am giving you one last chance to turn around, to leave. LEAVE!!!";
			
			addObject(new LiveGiver(29 * 32 - 3, 25  * 32, ID.dead, this));
			
			break;
		case 37:
			LoadImageLevel(level37);
			HUD.dialogTimer = 500;

			HUD.dialog = "Why is it not working? The Vault has been opened... The artifact must be inside.";
			HUD.dialog2 = "It is I... the calculations indicate I am the strongest being in existence. ";
			HUD.dialog3 = "I see no other way, no one else who can wield the powers- IT MUST BE WRONG! THERE IS NO OTHER WAY";
			break;
		case 38:
			LoadImageLevel(level38);
			HUD.dialogTimer = 500;

			HUD.dialog = "";
			HUD.dialog2 = "";
			HUD.dialog3 = "";
			break;
		case 39:
			LoadImageLevel(level39);
			HUD.dialogTimer = 500;

			HUD.dialog = "AAAAAAAARRRRRRRRRRRGGGGGGGGGGGGGHHHHHHHHHHHHHHHHHHHHHH";
			HUD.dialog2 = "";
			HUD.dialog3 = "";
			
			addObject(new DeadBoss1(9 * 32, (7 * 32) + 16 , ID.dead, this));
			addObject(new DeadBoss2B(16 * 32, (7 * 32) + 32 , ID.dead, this));
			addObject(new DeadBoss3((24 * 32) - 16, (7 * 32) - 14, ID.dead,  -1, this));
			for(int i = 0; i < 30; i++){
				addObject(new DeadBoss4((30 * 32) + (int)(Math.random() * 64), (7 * 32) + (int) (Math.random() * 64), ID.dead, this));
			}
			addObject(new DeadFinal2((36 * 32) - 16, (7 * 32) - 14, ID.dead));



			
			break;
		case 40:
			LoadImageLevel(level40);
			HUD.dialogTimer = 500;

			HUD.dialog = "YOU broke it. You doomed us all. Now, you must die";
			HUD.dialog2 = "";
			HUD.dialog3 = "";
			Danton.active = true;
			
			break;
		
		case 100:
			LoadImageLevel(levelE);
			addObject(new Flag(27*32, 11*32, ID.flagC, 0, this));

			break;
		}
		
			
		
		Game.LEVEL++;	
		
		
	}
	
	public void getCurrentLevel(){
		if(Game.LEVEL == 1){
			clearLevel();
			LoadImageLevel(level1);
			HUD.dialogTimer = 500;

		}else  if(Game.LEVEL == 2){
			clearLevel();
			LoadImageLevel(level2);
			
		}else if(Game.LEVEL == 3){
			clearLevel();
			LoadImageLevel(level3);
		}else if(Game.LEVEL == 4){
		clearLevel();
		LoadImageLevel(level4);
		}else if(Game.LEVEL == 5){
			clearLevel();
			LoadImageLevel(level5);
			HUD.dialogTimer = 500;

		}else if(Game.LEVEL == 6){
			clearLevel();
			LoadImageLevel(level6);
			HUD.dialogTimer = 500;

		}else if(Game.LEVEL == 7){
			clearLevel();
			LoadImageLevel(level7);
		}else if(Game.LEVEL == 8){
			clearLevel();
			LoadImageLevel(level8);
		}else if(Game.LEVEL == 9){
			clearLevel();
			LoadImageLevel(level9);
		}else if(Game.LEVEL == 10){
			clearLevel();
			LoadImageLevel(level10);
		}else if(Game.LEVEL == 11){
			clearLevel();
			LoadImageLevel(level11);
		}else if(Game.LEVEL == 12){
			clearLevel();
			LoadImageLevel(level12);
		}else if(Game.LEVEL == 13){
			clearLevel();
			LoadImageLevel(level13);
		}else if(Game.LEVEL == 14){
			clearLevel();
			LoadImageLevel(level14);
		}else if(Game.LEVEL == 15){
			clearLevel();
			LoadImageLevel(level15);
		}else if(Game.LEVEL == 16){
			clearLevel();
			LoadImageLevel(level16);
		}else if(Game.LEVEL == 17){
			clearLevel();
			LoadImageLevel(level17);
		}else if(Game.LEVEL == 18){
			clearLevel();
			LoadImageLevel(level18);
		}else if(Game.LEVEL == 19){
			clearLevel();
			LoadImageLevel(level19);
		}
	}
	
}
