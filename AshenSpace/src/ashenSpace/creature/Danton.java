package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.GameState.Charsel;
import ashenSpace.GameState.Settings;
import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.game.Game.STATE;
import ashenSpace.input.KeyInput;
import ashenSpace.object.ChargeRune;
import ashenSpace.object.Enemybullet;
import ashenSpace.object.Explosion;
import ashenSpace.object.FourWayBullet;
import ashenSpace.object.Missile;
import ashenSpace.object.Trail;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.fileInfo;

public class Danton extends GameObject{

	Handler handler;
	double velY = 0;
	float gravity = 0.2f;
	boolean rise = false; boolean fall = false;
	boolean air = false, ground = true;
	int groundTimer = 0;
	int heightSwitch = 0;
	int timer = 0;
	public static int bossHealth;
	int facing = 1;
	public static boolean invincible = false;
	public static int phase = 1;
	public static boolean runeRound = false;
	public static boolean active = false;
	Texture tex = Game.getInstance();
	private Animation danRunRight, danRunLeft, roboRunRight, roboRunLeft, def;
	private boolean explosion = false;
	
	public Danton(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		this.y = y + 15;
		health = 2000;
		
		danRunRight = new Animation(10, tex.danton[1], tex.danton[3], tex.danton[1], tex.danton[5]);
		danRunLeft = new Animation(10, tex.danton[0], tex.danton[2], tex.danton[2], tex.danton[4]);
		roboRunRight = new Animation(10, tex.danton[9], tex.danton[11], tex.danton[9], tex.danton[13]);
		roboRunLeft = new Animation(10, tex.danton[8], tex.danton[10], tex.danton[8], tex.danton[12]);
		def = new Animation(50, tex.danton[16], tex.danton[17]);

	}

	public void tick() {
					
		bossHealth = health;
		
		if(health > 1000){
			phase = 1;
		}else if( health <= 1000 && health > 25){
			phase = 2;
			
			if(explosion == false){
				Player.chargeTimer = 0;
				explosion = true;
				sound.play("/sound/Explosion.wav");

				for(int i = 0; i < 20; i++){
					handler.addObject(new Explosion((int)x, (int)y, null, handler, 200, 0, 0, "red", 5));
					handler.addObject(new Explosion(288 + 16, 160 + 64, null, handler, 200, 0, 0, "red", 5));
					handler.addObject(new Explosion(608 + 16, 160 + 64, null, handler, 200, 0, 0, "red", 5));
					handler.addObject(new Explosion(448 + 16, 256 + 64, null, handler, 200, 0, 0, "red", 5));
					handler.addObject(new Explosion(160 + 16, 384 + 64, null, handler, 200, 0, 0, "red", 5));
					handler.addObject(new Explosion(768 + 16, 384 + 64, null, handler, 200, 0, 0, "red", 5));

				}
			}
			
			
		}else if(health <= 25){
			phase = 3;
			HUD.dialogTimer = 500;

			HUD.dialog = "This is unexpected. I am second only to Ashen Space, how did you beat me? Perhaps I misunderstood";
			HUD.dialog2 = "what the prophecy meant. I sought it for power. Find the meaning. But, beware. There will be more.";
			HUD.dialog3 = "The Vault door opened. The Rust will settle. The moon will fall dark. Prophecy brings zealots.";

		}
		
		x += velX;
		y += velY;
		if(phase == 1 || phase == 2){
			if(heightSwitch == 2){
				velX = 0;
				if(ground == true){
					rise = true;
					ground = false;
				}else if(air == true){
					gravity = 0.2f;
					fall = true;
					air = false;
				}
			}
			
			if(air == true || rise == true){
				if(velX > 0 || rise == true){
					handler.addObject(new Trail((int)x, (int) y + 16, null, handler));
					handler.addObject(new Trail((int)x, (int) y + 16, null, handler));
				}else if(velX < 0){
					handler.addObject(new Trail((int)x + 32, (int) y + 16, null, handler));
					handler.addObject(new Trail((int)x + 32, (int) y + 16, null, handler));
				}
	
			}
			
			if(health >= 1000){
				if(air == true && ((x > 300 && x < 310) || (x > 610 && x < 620))){
					
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, -4, 6, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, -2, 8, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 0, 10, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 2, 8, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 4, 6, ID.enemyBullet, handler));
					sound.play("/sound/Shoot.wav");

				}
				
				if(air == true && (x > 440 && x < 450)){
					
					for(int i = 0; i < handler.object.size(); i++){
						GameObject tempObject = handler.object.get(i);
						if(tempObject.getId() == ID.player){
							
							float bulletVelX = tempObject.getX() - this.getX();
							float bulletVelY = tempObject.getY() - this.getY();
							
							handler.addObject(new Missile((int)x + 16, (int)y + 16, ID.missile, handler, bulletVelX, bulletVelY));
							
						}
					}
				}
				
				if(ground == true && (x % 50 > 45 && x % 50 < 50)){
					if(velX > 0){
						handler.addObject(new FourWayBullet((int) x + 32, (int)y + 16, 10, 0, ID.enemyBullet, handler));
						sound.play("/sound/Shoot.wav");

					}else if(velX < 0){
						handler.addObject(new FourWayBullet((int) x, (int)y + 16, -10, 0, ID.enemyBullet, handler));
						sound.play("/sound/Shoot.wav");

					}
				}
			}else if(health < 1000 && health > 25){
				
				if(air == true && x % 32 == 0){
					
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, -4, 6, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, -2, 8, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 0, 10, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 2, 8, ID.enemyBullet, handler));
					handler.addObject(new  FourWayBullet((int)x + 16, (int)y + 32, 4, 6, ID.enemyBullet, handler));
					sound.play("/sound/Shoot.wav");

				}
				
				if(air == true && ((x > 300 && x < 310) || (x > 610 && x < 620))){
					
					for(int i = 0; i < handler.object.size(); i++){
						GameObject tempObject = handler.object.get(i);
						if(tempObject.getId() == ID.player){
							
							float bulletVelX = tempObject.getX() - this.getX();
							float bulletVelY = tempObject.getY() - this.getY();
							
							handler.addObject(new Missile((int)x + 16, (int)y + 16, ID.missile, handler, bulletVelX, bulletVelY));
							
						}
					}
				
				}
				
				if(ground == true && (x % 50 > 45 && x % 50 < 50)){
					
					sound.play("/sound/Shoot.wav");

					if(velX > 0){
						handler.addObject(new FourWayBullet((int) x + 32, (int)y + 16, 10, 0, ID.enemyBullet, handler));
					}else if(velX < 0){
						handler.addObject(new FourWayBullet((int) x, (int)y + 16, -10, 0, ID.enemyBullet, handler));
					}
					
				}
				
			}
			
			if(rise == true){
				velY = -5;
				velX = 0;
			}
			
			if(fall == true){
				velY += gravity;
				velX = 0;
	
			}
		}else if(phase == 3){
			this.x = 464;
			this.y = 276;
		}
		
		if(phase == 2 && Player.chargeTimer <= 0 && runeRound == false){
			runeRound = true;
			Player.charge = 0;
			handler.addObject(new ChargeRune(288 + 16, 160 - 16, ID.rune, handler));
			handler.addObject(new ChargeRune(608 + 16, 160 - 16, ID.rune, handler));
			handler.addObject(new ChargeRune(448 + 16, 256 - 16, ID.rune, handler));
			handler.addObject(new ChargeRune(160 + 16, 384 - 16, ID.rune, handler));
			handler.addObject(new ChargeRune(768 + 16, 384 - 16, ID.rune, handler));
			invincible = true;

		}
		if(Player.charge == 5){
			Danton.invincible = false;
		}
		if(Player.charge > 0 && Player.chargeTimer <= 0){
			runeRound = false;
		}

		
		collision();
		danRunRight.runAnimation();
		danRunLeft.runAnimation();
		roboRunRight.runAnimation();
		roboRunLeft.runAnimation();
		def.runAnimation();
		ending();
	
	}

	public void render(Graphics g) {

		if(phase == 1){
			if(ground == true && velX > 0){
				danRunRight.drawAnimation(g, (int)x, (int)y, 32, 32);
			}else if(ground == true && velX < 0){
				danRunLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
			}else if((air == true && velX >= 0) || rise == true || fall == true){
				g.drawImage(tex.danton[7], (int)x, (int)y, null);
			}else if((air == true && velX < 0) || rise == true || fall == true){
				g.drawImage(tex.danton[6], (int)x, (int)y, null);
			}
		}else if(phase == 2){
			if(ground == true && velX > 0){
				roboRunRight.drawAnimation(g, (int)x, (int)y, 32, 32);
			}else if(ground == true && velX < 0){
				roboRunLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
			}else if((air == true && velX >= 0) || rise == true || fall == true){
				g.drawImage(tex.danton[15], (int)x, (int)y, null);
			}else if((air == true && velX < 0) || rise == true || fall == true){
				g.drawImage(tex.danton[14], (int)x, (int)y, null);
			}
		}else if(phase == 3){
			def.drawAnimation(g, (int)x, (int)y, 32, 32);
		}
		
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getFarUpper(){
		return new Rectangle((int) x + 4, (int) y - 32, 24, 2);
	}
	
	public Rectangle farLeftBound(){
		return new Rectangle((int) x - 64, (int) y + 4, 2, 10);
	}
	public Rectangle farRightBound(){
		return new Rectangle((int) x + 94, (int) y + 4, 2, 10);
	}
	
	public Rectangle bottomBound(){
		return new Rectangle((int) x + 4, (int) y + 30, 24, 1);
	}
	
	public void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){

				if(this.farLeftBound().intersects(tempObject.getBounds())){
					
					velX *= -1;
					x += 5;
					facing *= -1;
					heightSwitch += 1;

				}
				
				if(this.farRightBound().intersects(tempObject.getBounds())){

					velX *= -1;
					x -= 20;
					heightSwitch += 1;
					facing *= -1;

				}
				if(this.getFarUpper().intersects(tempObject.getBounds())){
					
					velY = 0;
					y += 1;
					rise = false;
					velX = 5;
					air = true;
					heightSwitch = 0;

					
				}
				if(this.bottomBound().intersects(tempObject.getBounds())){
					
					gravity = 0;
					velY = 0;
					y -= 1;
					velX = 5;
					fall = false;
					ground = true;
					heightSwitch = 0;
					
				}
			}
		}
	}
	
	public void ending(){
		if(health <= 0){
			handler.area6 = true;
			active = false;
			Achievements.danton = true;
			fileInfo file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
					handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);		
			Serialization.save(file);
			Game.music.stop();
			Game.OpenMusic.play();
			Game.gameState = STATE.credits;
			Achievements.kills++;

		}
	}
}
