package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.GameState.Charsel;
import ashenSpace.GameState.Difficulty;
import ashenSpace.GameState.Settings;
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
import ashenSpace.object.ChargeRune2;
import ashenSpace.object.Explosion;
import ashenSpace.object.Flag;
import ashenSpace.object.Flash;
import ashenSpace.object.LComplexBullet;
import ashenSpace.object.LSniperBullet;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.fileInfo;

public class FinalBoss extends GameObject{
	
	Texture tex = Game.getInstance();
	int dir = -1;
	int velX;
	Handler handler;
	int timer = 20;
	boolean shoot;
	int shootTimer = 100;
	boolean invincible = true;
	int health;
	int phase = 0;
	int sniperTimer = 300;
	int recharge = 100;
	double bulletvelX, bulletvelY;
	boolean teleP2 = false;
	boolean P3 = false;
	boolean P1 = false;
	int selfdestruct = 1000;
	int speechTimer = 0;
	
	public FinalBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.health = 5000;
		HUD.finalBossActive = true;
		
	}

	public void tick() {
		
		HUD.finalBossHealth = health;
		speechTimer--;
				
		timer--;
		if(timer < 0){
			timer = 20;
		}
		if(health > 3750){
			phase = 0;
		}
		else if(health <= 3750 && health > 2500){
			phase = 1;
		}else if(health <= 2500 && health > 1250){
			phase = 2;
		}else if(health <= 1250){
			phase = 3;
		}
		
		if(phase == 0 || phase == 2){
			if(shoot == false){
				x += velX;
				
				if(dir == -1){
					velX = -2;
				}
				if(dir == 1){
					velX = 2;
				}
			}
			
			if(shoot == true){
				invincible = false;
				HUD.dialogTimer = 1;
				HUD.dialog = "His chest is open! Shoot it!";
				HUD.dialog2 = "";
				HUD.dialog3 = "";



				shootTimer--;
				if(shootTimer <= 0){
					
					if(dir == 1){
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 15, -5, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 16, -4, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 17, -3, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 18, -2, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 19, -1, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 20, 0, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 19, 1, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 18, 2, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 17, 3, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 16, 4, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 200, (int)y + 144, 15, 5, ID.enemyBullet, handler));
						sound.play("/sound/Shoot.wav");

					}else if(dir == -1){
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -15, -5, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -16, -4, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -17, -3, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -18, -2, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -19, -1, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -20, 0, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -19, 1, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -18, 2, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -17, 3, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -16, 4, ID.enemyBullet, handler));
						handler.addObject(new LComplexBullet((int)x + 100, (int)y + 144, -15, 5, ID.enemyBullet, handler));
						sound.play("/sound/Shoot.wav");

					}
					
					
					shootTimer = 100;
					shoot = false;
					invincible = true;
				}
			}
		}
		
		if(phase == 1){
			if(P1 == false){
				handler.addObject(new Flash((int)x, (int)y, null, handler));
				x = 18 * 32;
				y = 18 * 32;
				handler.addObject(new Flash((int)x, (int)y, null, handler));
				P1 = true;
			}
			sniperTimer--;
			recharge--;
			track();
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.player){
					if(tempObject.getX() <= this.getX()){
						dir = -1;
					}else if(tempObject.getX() > this.getX()){
						dir = 1;
					}
				}
			}
		}
		
		if(phase == 2){
			if(teleP2 == false){
				teleP2 = true;
				handler.addObject(new Flash((int)x, (int)y, null, handler));
				this.x = 25 * 32;
				this.y = 3 * 32;
				handler.addObject(new Flash((int)x, (int)y, null, handler));
				dir = -1;
			}
		}
		
		if(phase == 3){
			if(P3 == false){
				
				handler.addObject(new Flash((int)x, (int)y, null, handler));
				this.x = 18 * 32;
				this.y = 3 * 32;
				handler.addObject(new Flash((int)x, (int)y, null, handler));

				
				handler.addObject(new ChargeRune2(7 * 32, 23 * 32 + 16, ID.rune, handler));
				handler.addObject(new ChargeRune2(20 * 32, 23 * 32 + 16, ID.rune, handler));
				handler.addObject(new ChargeRune2(34 * 32, 23 * 32 + 16, ID.rune, handler));
				
				P3 = true;
			
			}
			
			selfdestruct--;
			
			if(selfdestruct == -255){
				Player.charge = 0;
				HUD.health = 0;
			}
			
		}
		
		collision();
		die();
		
	}

	public void render(Graphics g) {
		
		if(phase == 0 || phase == 2){
			if(shoot == false){
				if(dir == -1){
					if(timer > 10){
						g.drawImage(tex.finalBoss[6], (int)x + 25, (int)y + 75, 110, 110, null);
						g.drawImage(tex.finalBoss[0], (int)x, (int)y - 10, 256, 256, null);
						g.drawImage(tex.finalBoss[6], (int)x + 35, (int)y + 75, 128, 128, null);
					}else if(timer <= 10){
						g.drawImage(tex.finalBoss[6], (int)x + 25, (int)y + 70, 110, 110, null);
						g.drawImage(tex.finalBoss[1], (int)x, (int)y - 10, 256, 256, null);
						g.drawImage(tex.finalBoss[6], (int)x + 35, (int)y + 70, 128, 128, null);
					}
				}else if(dir == 1){
					if(timer > 10){
						g.drawImage(tex.finalBoss[9], (int)x + 95, (int)y + 75, 110, 110, null);
						g.drawImage(tex.finalBoss[4], (int)x, (int)y - 10, 256, 256, null);
						g.drawImage(tex.finalBoss[9], (int)x + 95, (int)y + 75, 128, 128, null);
					}else if(timer <= 10){
						g.drawImage(tex.finalBoss[9], (int)x + 95, (int)y + 70, 110, 110, null);
						g.drawImage(tex.finalBoss[3], (int)x, (int)y - 10, 256, 256, null);
						g.drawImage(tex.finalBoss[9], (int)x + 95, (int)y + 70, 128, 128, null);
					}
				}
			}else if(shoot == true){
				if(dir == 1){
					g.drawImage(tex.finalBoss[8], (int)x + 105, (int)y + 75, 110, 110, null);
					g.drawImage(tex.finalBoss[5], (int)x, (int)y - 10, 256, 256, null);
					g.drawImage(tex.finalBoss[8], (int)x + 105, (int)y + 75, 128, 128, null);
				}else if(dir == -1){
					g.drawImage(tex.finalBoss[7], (int)x + 25, (int)y + 75, 110, 110, null);
					g.drawImage(tex.finalBoss[2], (int)x, (int)y - 10, 256, 256, null);
					g.drawImage(tex.finalBoss[7], (int)x + 25, (int)y + 75, 128, 128, null);
				}
			}
		}else if(phase == 1){
			if(dir == 1){
				g.drawImage(tex.finalBoss[8], (int)x + 105, (int)y + 75, 110, 110, null);
				g.drawImage(tex.finalBoss[5], (int)x, (int)y - 10, 256, 256, null);
				g.drawImage(tex.finalBoss[8], (int)x + 105, (int)y + 75, 128, 128, null);
				
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getId() == ID.player){
						if(recharge < 0){
							g.setColor(Color.red);
							g.drawLine((int)this.x + 190, (int)this.y + 64, (int)tempObject.getX() + 10, (int)tempObject.getY() + 5);
							g.drawLine((int)this.x + 190, (int)this.y + 65, (int)tempObject.getX() + 10, (int)tempObject.getY() + 6);
							g.drawLine((int)this.x + 190, (int)this.y + 66, (int)tempObject.getX() + 10, (int)tempObject.getY() + 7);
							g.drawLine((int)this.x + 190, (int)this.y + 67, (int)tempObject.getX() + 10, (int)tempObject.getY() + 8);
							g.drawLine((int)this.x + 190, (int)this.y + 68, (int)tempObject.getX() + 10, (int)tempObject.getY() + 9);
						}
					}
				}
				
			}else if(dir == -1){
				g.drawImage(tex.finalBoss[7], (int)x + 25, (int)y + 75, 110, 110, null);
				g.drawImage(tex.finalBoss[2], (int)x, (int)y - 10, 256, 256, null);
				g.drawImage(tex.finalBoss[7], (int)x + 25, (int)y + 75, 128, 128, null);
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getId() == ID.player){
						if(recharge < 0){
							g.setColor(Color.red);
							g.drawLine((int)this.x + 70, (int)this.y + 64, (int)tempObject.getX() + 22, (int)tempObject.getY() + 5);
							g.drawLine((int)this.x + 70, (int)this.y + 65, (int)tempObject.getX() + 22, (int)tempObject.getY() + 6);
							g.drawLine((int)this.x + 70, (int)this.y + 66, (int)tempObject.getX() + 22, (int)tempObject.getY() + 7);
							g.drawLine((int)this.x + 70, (int)this.y + 67, (int)tempObject.getX() + 22, (int)tempObject.getY() + 8);
							g.drawLine((int)this.x + 70, (int)this.y + 68, (int)tempObject.getX() + 22, (int)tempObject.getY() + 9);
						}
					}
				}
			}
		}
		if(phase == 3){
		
			
			g.drawImage(tex.finalBoss[12], (int)x, (int)y - 10, 256, 256, null);
			g.drawImage(tex.finalBoss[8], (int)x + 125, (int)y + 75, 128, 128, null);
			g.drawImage(tex.finalBoss[7], (int)x - 50, (int)y + 75, 128, 128, null);
			
			if(selfdestruct == 0){
				int areaX = (int)(Math.random() * 248);
				int areaY = (int)(Math.random() * 248);
				sound.play("/sound/Explosion.wav");

				for(int i = 0; i < 20; i++){
					handler.addObject(new Explosion(x + areaX, y  + areaY, null, handler, 200, 0, 0, "red", 5));
				}
			}
			
			if(selfdestruct > 0 && selfdestruct % 100 == 0){
				int areaX = (int)(Math.random() * 248);
				int areaY = (int)(Math.random() * 248);
				sound.play("/sound/Explosion.wav");

				for(int i = 0; i < 20; i++){
					handler.addObject(new Explosion(x + areaX, y  + areaY, null, handler, 200, 0, 0, "red", 5));
				}
			}
			
			if(selfdestruct < 0){
				
				handler.addObject(new Explosion(x + (int)(Math.random() * 248), y + (int) (Math.random() * 248), null, handler, 200, 0, 0, "red", 5));
				
				g.setColor(new Color(0, 0, 0, Math.abs(selfdestruct)));
				g.fillRect(0, 0, 2000, 2000);
			}
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 70, (int)y + 8, 110, 220);
	}
	
	public Rectangle farRightBounds(){
		return new Rectangle((int)x + 305, (int)y + 8, 50, 150);
	}
	
	public Rectangle farLeftBounds(){
		return new Rectangle((int)x - 200, (int)y + 8, 50, 150);
	}
	
	public Rectangle RightHitBounds(){
		return new Rectangle((int)x + 165, (int)y + 8, 50, 150);
	}
	public Rectangle LeftHitBounds(){
		return new Rectangle((int)x + 30, (int)y + 8, 50, 150);
	}
	
	public Rectangle leftBounds(){
		return new Rectangle((int)x - 30, (int)y + 2, 2, 60);
	}
	public Rectangle RightBounds(){
		return new Rectangle((int)x + 300, (int)y + 2, 2, 60);
	}
	
	public void track(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				if(sniperTimer < 0){
					
					double theta = 0;
					
					theta = Math.atan(-1 * (((tempObject.getY()) - (this.getY() + 68)) / ((tempObject.getX()) - (this.getX()))));

					
					if(tempObject.getX() > this.getX() && tempObject.getY() < this.getY()){
						
						bulletvelY = -100 * Math.sin(theta);
						bulletvelX = 100 * Math.cos(theta);
					}
					
					if(tempObject.getX() > this.getX() && tempObject.getY() > this.getY()){

						bulletvelY = -100 * Math.sin(theta);
						bulletvelX = 100 * Math.cos(theta);

					}
					
					if(tempObject.getX() < this.getX() && tempObject.getY() < this.getY()){
						bulletvelY = 100 * Math.sin(theta);
						bulletvelX = -100 * Math.cos(theta);

					}
					
					if(tempObject.getX() < this.getX() && tempObject.getY() > this.getY()){
						bulletvelY = 100 * Math.sin(theta);
						bulletvelX = -100 * Math.cos(theta);

					}			
					
					if(dir == 1){
						handler.addObject(new LSniperBullet((int)x + 190, (int)y + 68, ID.sniperBullet, bulletvelX,  bulletvelY - 20, handler));
						handler.addObject(new LSniperBullet((int)x + 190, (int)y + 68, ID.sniperBullet, bulletvelX,  bulletvelY - 10, handler));
						handler.addObject(new LSniperBullet((int)x + 190, (int)y + 68, ID.sniperBullet, bulletvelX,  bulletvelY, handler));
						handler.addObject(new LSniperBullet((int)x + 190, (int)y + 68, ID.sniperBullet, bulletvelX,  bulletvelY + 10, handler));
						handler.addObject(new LSniperBullet((int)x + 190, (int)y + 68, ID.sniperBullet, bulletvelX,  bulletvelY + 20, handler));
						sound.play("/sound/Shoot.wav");

					}else if(dir == -1){
						handler.addObject(new LSniperBullet((int)x + 70, (int)y + 68, ID.sniperBullet,  bulletvelX,  bulletvelY - 20, handler));
						handler.addObject(new LSniperBullet((int)x + 70, (int)y + 68, ID.sniperBullet,  bulletvelX,  bulletvelY - 10, handler));
						handler.addObject(new LSniperBullet((int)x + 70, (int)y + 68, ID.sniperBullet,  bulletvelX,  bulletvelY, handler));
						handler.addObject(new LSniperBullet((int)x + 70, (int)y + 68, ID.sniperBullet,  bulletvelX,  bulletvelY + 10, handler));
						handler.addObject(new LSniperBullet((int)x + 70, (int)y + 68, ID.sniperBullet,  bulletvelX,  bulletvelY + 20, handler));
						sound.play("/sound/Shoot.wav");

					}
					recharge = 100;
					invincible = false;
					sniperTimer = 300;
				}
			}
		}
		
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				if(phase == 0){
					if(tempObject.getBounds().intersects(this.leftBounds()) || tempObject.getBounds().intersects(this.RightBounds())){
						if(dir == -1){
							x += 10;
						}else if(dir == 1){
							x -= 10;
						}
						dir *= -1;
						shoot = true;
					}
				}else if(phase == 2){
					if(tempObject.getBounds().intersects(this.farLeftBounds()) || tempObject.getBounds().intersects(this.farRightBounds())){
						if(dir == -1){
							x += 10;
						}else if(dir == 1){
							x -= 10;
						}
						dir *= -1;
						shoot = true;
					}
				}
			}else if(tempObject.getId() == ID.bullet){
				if(phase == 0 || phase == 1 || phase == 2) {
					if(dir == -1){
						if(invincible == false){
							if(tempObject.getBounds().intersects(this.LeftHitBounds())){
								this.health -= 25;
								handler.removeObject(tempObject);
	
							}
						}
					}else if(dir == 1){
						if(invincible == false){
							if(tempObject.getBounds().intersects(this.RightHitBounds())){
								this.health -= 25;
								handler.removeObject(tempObject);
	
								
							}
						}
					}
				}else if(phase == 3){
					if(tempObject.getBounds().intersects(this.RightHitBounds()) || tempObject.getBounds().intersects(this.LeftHitBounds())){
						if(Player.charge == 3){
							if(selfdestruct > 0){

								this.health -= 1250;
								handler.removeObject(tempObject);
							}
						}
					}
				}
			}
		}
	}
	
	public void die(){
		if(health <= 0){
			Achievements.kills++;
			HUD.finalBossActive = false;
			Player.charge = 0;
			Achievements.finalBoss = true;
			fileInfo file = new fileInfo(handler.area1, handler.area2, handler.area3, handler.area4, handler.area5, 
					handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
					Achievements.danton, Achievements.dead, Charsel.character);	
			Serialization.save(file);
			if(Difficulty.difficulty == 1){
				handler.addObject(new Flag((39 * 32) - 2, 24 * 32, ID.flagD, 0, handler));
				handler.removeObject(this);
				handler.addObject(new DeadFinal((int)x, (int)y, ID.dead));
				
			}else if(Difficulty.difficulty == 0){
				handler.removeObject(this);
				handler.addObject(new DeadFinal((int)x, (int)y, ID.dead));
				Game.gameState = STATE.credits;
				Game.music.stop();
				Game.OpenMusic.play();
			}
		}
	}

}
