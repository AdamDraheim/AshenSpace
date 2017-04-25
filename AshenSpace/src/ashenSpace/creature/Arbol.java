package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Flag;
import ashenSpace.object.heavyBullet;

public class Arbol extends GameObject{

	Handler handler;
	private int bulletFire = 400;
	private int charge = 50;
	public static boolean invincible = true;
	private float dirSpeed = -0.5f;
	public static int dirChange = 900;
	public static int invincibleTimer = 0;
	public static int bossHealth;
	public static boolean ArbolActive = false;
	private Animation ArbolRunRight, ArbolRunLeft;
	Texture tex = Game.getInstance();
	public static int animDecrease = 1350;
	public static boolean darkness = false;
	public static int spawnTimer = 0;
	float dir = 1;
	
	public Arbol(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = -0.5f;
		health = 500;
		velY = 2;
		ArbolRunRight = new Animation(20, tex.basicEnemy[9], tex.basicEnemy[10], tex.basicEnemy[11]);
		ArbolRunLeft = new Animation(20, tex.basicEnemy[6], tex.basicEnemy[7], tex.basicEnemy[8]);
	}

	public void tick() {
		if(health <= 250){
			darkness = true;
			spawnTimer--;

			if(spawnTimer <= 0){
				handler.addObject(new rocketEnemy(4 * 32, 12* 32, ID.basicEnemy, handler));
				handler.addObject(new rocketEnemy(25 * 32, 12* 32, ID.basicEnemy, handler));
				spawnTimer = 2000;
			}
		}
		
		animDecrease--;
		if(animDecrease <= -1350){
			animDecrease = 1350;
		}
		bossHealth = health;
		
		dirChange--;
		x += velX;
		y += velY;
		
		bulletFire--;
		if(bulletFire <= 0){
			charge--;
			velX = 0;
			if(charge <= 0){
				
				if(dirSpeed > 0){
					handler.addObject(new heavyBullet((int) x + 66, (int) y, ID.heavyBullet, handler, 5));
				}else if(dirSpeed < 0){
					handler.addObject(new heavyBullet((int) x - 32, (int) y, ID.heavyBullet, handler, -5));
				}
				sound.play("/sound/Shoot.wav");

				bulletFire = 400;
				charge = 50;
				velX = dirSpeed;
				
			}
		}
		
		if(invincibleTimer > 0){
			invincibleTimer--;
		}else if(invincibleTimer <= 0){
			invincible = true;
		}
		if(dirChange <= 0){
			
			dirSpeed *= -1;
			dirChange = 1350;
			dir = dirSpeed;
			
		}
		
		ArbolRunRight.runAnimation();
		ArbolRunLeft.runAnimation();
		collision();
		die();
	}

	public void render(Graphics g) {
		if(animDecrease < 0){
			ArbolRunRight.drawAnimation(g, (int)x, (int)y, 64, 64);
		}else if(animDecrease > 0){
			ArbolRunLeft.drawAnimation(g, (int)x, (int)y, 64, 64);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public void collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY = 0;
					y -= 1;
					
				}
				
			}else if(tempObject.getId() == ID.reboundBullet){
				if(getBounds().intersects(tempObject.getBounds())){
					invincible = false;
					handler.removeObject(tempObject);
					invincibleTimer = 200;
					sound.play("/sound/Explosion.wav");

					
				}
			}else if(tempObject.getId() == ID.bullet){
				if(getBounds().intersects(tempObject.getBounds())){
					if(invincible == true){
						handler.removeObject(tempObject);
						sound.play("/sound/Deflect.wav");

					}
				}
			}
			
		}
		
	}
	
	public void die(){
		if (health <= 0){
				if(HUD.mode == 0){
					HUD.lives++;
				}
			handler.addObject(new Flag((27 * 32), 12 * 32, ID.flagD, 0, handler));
			handler.addObject(new DeadBoss3((int)x, (int)y, ID.dead, dir, handler));
			HUD.dialogTimer = 500;
			HUD.objective = "Leave the";
			HUD.objectiveCont = "biolab";
			HUD.dialog = "Haha! That did it! I isolated the signal. I am heading to the control ";
			HUD.dialog2 = "center now. Thanks for all the help!";
			HUD.dialog3 = "";
			handler.removeObject(this);
			Achievements.kills++;
			
		}
	}

}
